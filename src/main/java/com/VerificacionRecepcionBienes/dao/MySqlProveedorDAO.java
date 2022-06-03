package com.VerificacionRecepcionBienes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Proveedor;
import com.VerificacionRecepcionBienes.interfaces.ProveedorDAO;
import com.VerificacionRecepcionBienes.utils.MySqlConexion;


public class MySqlProveedorDAO implements ProveedorDAO{

	@Override
	public int save(Proveedor bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConectar();
			String sql="insert into tb_proveedor values(null,?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getRuc_provee());
			pstm.setString(2, bean.getNom_provee());
			pstm.setString(3, bean.getPais_provee());
			pstm.setString(4, bean.getDistritro_provee());
			pstm.setString(5, bean.getRepresen_provee());
			pstm.setString(6, bean.getFono_provee());
			pstm.setString(7, bean.getCorreo_provee());
			pstm.setString(8, bean.getRazonsoci_provee());
			
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
	public int update(Proveedor bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConectar();
			String sql="update tb_proveedor set ruc_provee=?,nom_provee=?,pais_provee=?,distrito_provee=?,represen_provee=?,fono_provee=?,correo_provee=?,razonsoci_provee=? where cod_provee=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getRuc_provee());
			pstm.setString(2, bean.getNom_provee());
			pstm.setString(3, bean.getPais_provee());
			pstm.setString(4, bean.getDistritro_provee());
			pstm.setString(5, bean.getRepresen_provee());
			pstm.setString(6, bean.getFono_provee());
			pstm.setString(7, bean.getCorreo_provee());
			pstm.setString(8, bean.getRazonsoci_provee());
			pstm.setInt(9, bean.getCod_provee());
			
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
			String sql="delete from tb_proveedor where cod_provee=?";
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
	public Proveedor findById(int cod) {
		Proveedor bean=null;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//1
			cn=MySqlConexion.getConectar();
			//2
			String sql="select *from tb_proveedor where cod_provee=?";
			//3
			pstm=cn.prepareStatement(sql);
			//4 parámetros
			pstm.setInt(1, cod);
			//5
			rs=pstm.executeQuery();
			//6 validar si existe fila
			if(rs.next()) {
				//7 crear bean
				bean=new Proveedor();
				//8
				bean.setCod_provee(rs.getInt(1));
				bean.setRuc_provee(rs.getString(2));
				bean.setNom_provee(rs.getString(3));
				bean.setPais_provee(rs.getString(4));
				bean.setDistritro_provee(rs.getString(5));
				bean.setRepresen_provee(rs.getString(6));
				bean.setFono_provee(rs.getString(7));
				bean.setCorreo_provee(rs.getString(8));
				bean.setRazonsoci_provee(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return bean;
	}

	@Override
	public List<Proveedor> listAll() {
		List<Proveedor> data=new ArrayList<Proveedor>();
		Proveedor bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//paso 1 la conexion con la base de datos
			cn=MySqlConexion.getConectar();
			//paso 2 
			String sql="Select * from tb_proveedor";
			//paso 3 pstm tiene que almacenar la sentencia sql
			pstm=cn.prepareStatement(sql);
			//paso 4
			
			// paso 5 ejecutar rs
			rs=pstm.executeQuery();
			//paso 6 bucle da vuelta sobre rs
			while(rs.next()) {
				//paso 7 crear bean
				bean=new Proveedor();
				//paso 8
				bean.setCod_provee(rs.getInt(1));
				bean.setRuc_provee(rs.getString(2));
				bean.setNom_provee(rs.getString(3));
				bean.setPais_provee(rs.getString(4));
				bean.setDistritro_provee(rs.getString(5));
				bean.setRepresen_provee(rs.getString(6));
				bean.setFono_provee(rs.getString(7));
				bean.setCorreo_provee(rs.getString(8));
				bean.setRazonsoci_provee(rs.getString(9));
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
