package com.VerificacionRecepcionBienes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.VerificacionRecepcionBienes.entidad.OrdenCompra;
import com.VerificacionRecepcionBienes.entidad.Proveedor;
import com.VerificacionRecepcionBienes.interfaces.OrdenCompraDAO;
import com.VerificacionRecepcionBienes.utils.MySqlConexion;

public class MySqlOrdenCompraDAO implements OrdenCompraDAO {

	@Override
	public int save(OrdenCompra bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConectar();
			String sql="insert into tb_ordencompra values(null,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getDescripcion_orden());
			pstm.setInt(2, bean.getCantidad_orden());
			pstm.setDouble(3, bean.getPrecio_orden());
			pstm.setDate(4, bean.getFecha_orden());
			pstm.setString(5, bean.getDireccion_orden());
			pstm.setString(6, bean.getRepresentante_orden());
			
			salida=pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null)pstm.close();
				if(cn!=null)cn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public int update(OrdenCompra bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConectar();
			String sql="update tb_ordencompra set descripcion_orden=?,cantidad_orden=?,precioUnidad_orden=?,fecha_orden=?,direccion_orden=?,representante_orden=? where cod_orden=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getDescripcion_orden());
			pstm.setInt(2, bean.getCantidad_orden());
			pstm.setDouble(3, bean.getPrecio_orden());
			pstm.setDate(4, bean.getFecha_orden());
			pstm.setString(5, bean.getDireccion_orden());
			pstm.setString(6, bean.getRepresentante_orden());
			pstm.setInt(7, bean.getCodigo_orden());
			
			salida=pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		
       return salida;
	}

	@Override
	public int deleteById(int cod) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConectar();
			String sql="delete from tb_ordencompra where cod_orden=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			salida=pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(pstm!=null)pstm.close();
				if(cn!=null)cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();

			}
		}
		
		return salida;
	}

	@Override
	public OrdenCompra findById(int cod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdenCompra> listAll() {
		List<OrdenCompra> data=new ArrayList<OrdenCompra>();
		OrdenCompra bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//paso 1 la conexion con la base de datos
			cn=MySqlConexion.getConectar();
			//paso 2 
			String sql="Select * from tb_ordencompra";
			//paso 3 pstm tiene que almacenar la sentencia sql
			pstm=cn.prepareStatement(sql);
			//paso 4
			
			// paso 5 ejecutar rs
			rs=pstm.executeQuery();
			//paso 6 bucle da vuelta sobre rs
			while(rs.next()) {
				//paso 7 crear bean
				bean=new OrdenCompra();
				//paso 8
				bean.setCodigo_orden(rs.getInt(1));
				bean.setDescripcion_orden(rs.getString(2));
				bean.setCantidad_orden(rs.getInt(3));
				bean.setPrecio_orden(rs.getDouble(4));
				bean.setFecha_orden(rs.getDate(5));
				bean.setDireccion_orden(rs.getString(6));
				bean.setRepresentante_orden(rs.getString(7));
				//paso 9
				data.add(bean);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null)rs.close();
				if(pstm!=null)pstm.close();
				if(cn!=null)cn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}		
		return data;
	}
	}


