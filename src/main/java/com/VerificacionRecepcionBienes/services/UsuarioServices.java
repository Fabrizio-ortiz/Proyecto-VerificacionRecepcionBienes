package com.VerificacionRecepcionBienes.services;

import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Menu;
import com.VerificacionRecepcionBienes.entidad.Usuario;
import com.VerificacionRecepcionBienes.fabrica.DAOFactory;
import com.VerificacionRecepcionBienes.interfaces.UsuarioDAO;

public class UsuarioServices {
	private DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	
	private UsuarioDAO objUsuario=fabrica.getUsuarioDAO();
	
	public Usuario loginUsuario(String login,String clave) {
		return objUsuario.iniciarSesion(login, clave);
	}
	public List<Menu> traerMenusPorUsurio(int codUsu){
		return objUsuario.listarMenus(codUsu);
	}

}
