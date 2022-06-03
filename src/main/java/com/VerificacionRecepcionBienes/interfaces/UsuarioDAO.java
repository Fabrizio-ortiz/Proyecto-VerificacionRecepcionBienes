package com.VerificacionRecepcionBienes.interfaces;

import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Menu;
import com.VerificacionRecepcionBienes.entidad.Usuario;

public interface UsuarioDAO {
	public Usuario iniciarSesion(String login,String clave);
	public List<Menu> listarMenus(int codUsu);

}
