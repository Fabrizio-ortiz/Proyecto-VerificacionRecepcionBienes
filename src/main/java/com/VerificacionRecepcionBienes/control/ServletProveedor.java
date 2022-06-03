package com.VerificacionRecepcionBienes.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VerificacionRecepcionBienes.dao.MySqlProveedorDAO;
import com.VerificacionRecepcionBienes.entidad.Proveedor;
import com.VerificacionRecepcionBienes.services.ProveedorService;

/**
 * Servlet implementation class ServletProveedor
 */
@WebServlet("/ServletProveedor")
public class ServletProveedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //declarar objeto de la clase MySQL DocenteDAO
	private ProveedorService servicio;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProveedor() {
        super();
        servicio= new ProveedorService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//tipo de que debe realizar el Servlet
		String accion;
		//obtener la accion que viene de la pagina proveedor.jsp 
		accion=request.getParameter("tipo");
		//validar accion
		if(accion.equals("LISTAR"))
			listarProveedor(request,response);
		else if(accion.equals("REGISTRAR"))
			registrarProveedor(request,response);
		else if(accion.equals("ELIMINAR"))
			eliminarProveedor(request,response);

	}

	private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RECUPERAR EL CODIGO A ELIMINAR
		String cod;
		cod=request.getParameter("codigoEliminar");
		//eliminar el metodo deletebyId
		int salida;
		salida=servicio.eliminarPorId(Integer.parseInt(cod));
		//
		if(salida>0) {
			//crear atributo MENSAJE
			request.setAttribute("MENSAJE", "Proveedor Eliminado...");
			//
			//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
			listarProveedor(request, response);
		}
		else {
			request.setAttribute("MENSAJE", "Error en la Eliminación...");
			//
			//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
			listarProveedor(request, response);
		}
		
	}

	private void registrarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// varibales
		String codigo,ruc,nom,pais,distrito,represen,fono,correo,razonso;
		//leer los controles del formulario
		codigo=request.getParameter("codigoproveedor");
		ruc=request.getParameter("ruc");
		nom=request.getParameter("nombre");
		pais=request.getParameter("pais");
		distrito=request.getParameter("distrito");
		represen=request.getParameter("representante");
		fono=request.getParameter("telefono");
		correo=request.getParameter("correo");
		razonso=request.getParameter("razonsocial");
		//crear un objeto de la clase proveedor
		Proveedor bean=new Proveedor();
		
		bean.setRuc_provee(ruc);
		bean.setNom_provee(nom);
		bean.setPais_provee(pais);
		bean.setDistritro_provee(distrito);
		bean.setRepresen_provee(represen);
		bean.setFono_provee(fono);
		bean.setCorreo_provee(correo);
		bean.setRazonsoci_provee(razonso);
		//validar variable codigo
		if(Integer.parseInt(codigo)==0) {
			//invocar el metodo save y enviar bean
			int salida;
			salida=servicio.registrar(bean);
			if(salida>0) {
				//crear atributo MENSAJE
				request.setAttribute("MENSAJE", "Proveedor Registrado...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarProveedor(request, response);
			}
			else {
				request.setAttribute("MENSAJE", "Error en el Registro...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarProveedor(request, response);
			}
			
		}
		else {
			//invocar el metodo update y enviar bean
			bean.setCod_provee(Integer.parseInt(codigo));
			int salida;
			salida=servicio.actualizar(bean);
			if(salida>0) {
				//crear atributo MENSAJE
				request.setAttribute("MENSAJE", "Proveedor Actualizado...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarProveedor(request, response);
			}
			else {
				request.setAttribute("MENSAJE", "Error en la Actualización...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarProveedor(request, response);
			}
		}
	}

	private void listarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//invocar al metodo lisAll()
		List<Proveedor> lista= servicio.listTodos();
		//crear un atributo y guardar el valor de lista
		request.setAttribute("proveedores", lista);
		//enviar atributo proveedores a la pagina Proveedor.jsp
		request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
	}

}
