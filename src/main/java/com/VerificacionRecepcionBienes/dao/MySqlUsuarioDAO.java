package com.VerificacionRecepcionBienes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Menu;
import com.VerificacionRecepcionBienes.entidad.Proveedor;
import com.VerificacionRecepcionBienes.entidad.Usuario;
import com.VerificacionRecepcionBienes.interfaces.UsuarioDAO;
import com.VerificacionRecepcionBienes.utils.MySqlConexion;

public class MySqlUsuarioDAO implements UsuarioDAO {

	@Override
	public Usuario iniciarSesion(String login, String clave) {
		Usuario bean=null;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//1
			cn=MySqlConexion.getConectar();
			//2
			String sql="select cod_usu,nom_usu,ape_usu from tb_usuario where log_usu=? and pas_usu=?";
			//3
			pstm=cn.prepareStatement(sql);
			//4 parámetros
			pstm.setString(1, login);
			pstm.setString(2, clave);
			//5
			rs=pstm.executeQuery();
			//6 validar si existe fila
			if(rs.next()) {
				//7 crear bean
				bean=new Usuario();
				//8
				bean.setCodigo(rs.getInt(1));
				bean.setNombres(rs.getString(2));
				bean.setApellidos(rs.getString(3));
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
	public List<Menu> listarMenus(int codUsu) {
		List<Menu> data=new ArrayList<Menu>();
		Menu bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//1
			cn=MySqlConexion.getConectar();
			//2
			String sql="select a.cod_men, m.des_men, m.url_men from tb_acceso a join tb_menu "
					+ " m on a.cod_men=m.cod_men where a.cod_usu=?";
			//3
			pstm=cn.prepareStatement(sql);
			//4 parámetros
			pstm.setInt(1, codUsu);
			//5
			rs=pstm.executeQuery();
			//6 validar si existe fila
			if(rs.next()) {
				//7 crear bean
				bean=new Menu();
				//8
				bean.setCodigo(rs.getInt(1));
				bean.setDes(rs.getString(2));
				bean.setUrl(rs.getString(3));
				
				data.add(bean);
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
		
		return data;
	}

}
