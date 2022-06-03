package com.VerificacionRecepcionBienes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Bien;
import com.VerificacionRecepcionBienes.entidad.OrdenCompra;
import com.VerificacionRecepcionBienes.interfaces.BienDAO;
import com.VerificacionRecepcionBienes.utils.MySqlConexion;

public class MySqlBienDAO implements BienDAO {

	@Override
	public int save(Bien bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConectar();
			String sql="insert into tb_bienes values(null,?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, bean.getCod_provee());
			pstm.setString(2, bean.getDescripcion_bien());
			pstm.setInt(3, bean.getCanti_bien());
			pstm.setDouble(4, bean.getPrecio_bien());
			pstm.setDate(5, bean.getFechapedi_bien());
			pstm.setDate(6, bean.getFechaentre_bien());
			pstm.setString(7, bean.getEstado_bien());
			pstm.setInt(8, bean.getCod_orden());
			
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
	public int update(Bien bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConectar();
			String sql="update tb_bienes set cod_provee=?,des_bien=?,cant_bien=?,pre_bien=?,fechpedi_bien=?,fechentre_bien=?,estado_bien=?,cod_orden=? where cod_bien=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, bean.getCod_provee());
			pstm.setString(2, bean.getDescripcion_bien());
			pstm.setInt(3, bean.getCanti_bien());
			pstm.setDouble(4, bean.getPrecio_bien());
			pstm.setDate(5, bean.getFechapedi_bien());
			pstm.setDate(6, bean.getFechaentre_bien());
			pstm.setString(7, bean.getEstado_bien());
			pstm.setInt(8, bean.getCod_orden());
			pstm.setInt(9, bean.getCod_bien());
			
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
			String sql="delete from tb_bienes where cod_bien=?";
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
	public Bien findById(int cod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bien> listAll() {
		List<Bien> data=new ArrayList<Bien>();
		Bien bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//paso 1 la conexion con la base de datos
			cn=MySqlConexion.getConectar();
			//paso 2 
			String sql="Select * from tb_bienes";
			//paso 3 pstm tiene que almacenar la sentencia sql
			pstm=cn.prepareStatement(sql);
			//paso 4
			
			// paso 5 ejecutar rs
			rs=pstm.executeQuery();
			//paso 6 bucle da vuelta sobre rs
			while(rs.next()) {
				//paso 7 crear bean
				bean=new Bien();
				//paso 8
				bean.setCod_bien(rs.getInt(1));
				bean.setCod_provee(rs.getInt(2));
				bean.setDescripcion_bien(rs.getString(3));
				bean.setCanti_bien(rs.getInt(4));
				bean.setPrecio_bien(rs.getDouble(5));
				bean.setFechapedi_bien(rs.getDate(6));
				bean.setFechaentre_bien(rs.getDate(7));
				bean.setEstado_bien(rs.getString(8));
				bean.setCod_orden(rs.getInt(9));
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
