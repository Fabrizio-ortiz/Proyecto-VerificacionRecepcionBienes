package com.VerificacionRecepcionBienes.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VerificacionRecepcionBienes.entidad.Menu;
import com.VerificacionRecepcionBienes.entidad.Usuario;
import com.VerificacionRecepcionBienes.services.UsuarioServices;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioServices servicio=new UsuarioServices();    

    public ServletUsuario() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("INICIAR"))
			loginDelUsuario(request,response);
		else if(tipo.equals("CERRAR"))
			cerrarSesionDelUsuario(request,response);
		
	}


	private void cerrarSesionDelUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//obtener sesión actual
		HttpSession session=request.getSession();
		//invalidar atributos de tipo sesión
		session.invalidate();
		request.setAttribute("MENSAJE", "Sesión terminada");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}


	private void loginDelUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperar cajas
		String vLogin,vClave;
		vLogin=request.getParameter("usuario");
		vClave=request.getParameter("clave");
		//invocar al método loginUsuario
		Usuario bean=servicio.loginUsuario(vLogin, vClave);
		//validar
		if(bean==null) {//credenciales incorrectas
			request.setAttribute("MENSAJE", "Credenciales incorrectas");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else {//todo es OK
			//invocar al método traerMenusPorUsuario
			List<Menu> data=servicio.traerMenusPorUsurio(bean.getCodigo());
			request.getSession().setAttribute("LISTA", data);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
		
	}

}
