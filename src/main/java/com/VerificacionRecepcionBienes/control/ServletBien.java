package com.VerificacionRecepcionBienes.control;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VerificacionRecepcionBienes.entidad.Bien;
import com.VerificacionRecepcionBienes.entidad.Proveedor;
import com.VerificacionRecepcionBienes.services.BienService;
import com.VerificacionRecepcionBienes.services.ProveedorService;

/**
 * Servlet implementation class ServletBien
 */
@WebServlet("/ServletBien")
public class ServletBien extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BienService servicio;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBien() {
        super();
        servicio= new BienService();
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
			listarBien(request,response);
		else if(accion.equals("REGISTRAR"))
			registrarBien(request,response);
		else if(accion.equals("ELIMINAR"))
			eliminarBien(request,response);

	}

	private void eliminarBien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RECUPERAR EL CODIGO A ELIMINAR
		String cod;
		cod=request.getParameter("codigoEliminar");
		//eliminar el metodo deletebyId
		int salida;
		salida=servicio.eliminarPorId(Integer.parseInt(cod));
		//
		if(salida>0) {
			//crear atributo MENSAJE
			request.setAttribute("MENSAJE", "Bien Eliminado...");
			//
			//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
			listarBien(request, response);
		}
		else {
			request.setAttribute("MENSAJE", "Error en la Eliminación...");
			//
			//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
			listarBien(request, response);
		}
		
	}

	private void registrarBien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// varibales
		String codigo,codprovee,des,canti,pre,fechape,fechaentre,estado,codorden;
		//leer los controles del formulario
		codigo=request.getParameter("codigobien");
		codprovee=request.getParameter("codigoproveeedor");
		des=request.getParameter("descripcion");
		canti=request.getParameter("cantidad");
		pre=request.getParameter("precio");
		fechape=request.getParameter("fechapedido");
		fechaentre=request.getParameter("fechaentrega");
		estado=request.getParameter("estado");
		codorden=request.getParameter("codigoorden");
		//crear un objeto de la clase proveedor
		Bien bean=new Bien();
		
		bean.setCod_provee(Integer.parseInt(codprovee));
		bean.setDescripcion_bien(des);
		bean.setCanti_bien(Integer.parseInt(canti));
		bean.setPrecio_bien(Double.parseDouble(pre));
		bean.setFechapedi_bien(Date.valueOf(fechape));
		bean.setFechaentre_bien(Date.valueOf(fechaentre));
		bean.setEstado_bien(estado);
		bean.setCod_orden(Integer.parseInt(codorden));
		//validar variable codigo
		if(Integer.parseInt(codigo)==0) {
			//invocar el metodo save y enviar bean
			int salida;
			salida=servicio.registrar(bean);
			if(salida>0) {
				//crear atributo MENSAJE
				request.setAttribute("MENSAJE", "Bien Registrado...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarBien(request, response);
			}
			else {
				request.setAttribute("MENSAJE", "Error en el Registro...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarBien(request, response);
			}
			
		}
		else {
			//invocar el metodo update y enviar bean
			bean.setCod_bien(Integer.parseInt(codigo));
			int salida;
			salida=servicio.actualizar(bean);
			if(salida>0) {
				//crear atributo MENSAJE
				request.setAttribute("MENSAJE", "Bien Actualizado...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarBien(request, response);
			}
			else {
				request.setAttribute("MENSAJE", "Error en la Actualización...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarBien(request, response);
			}
		}
		
	}

	private void listarBien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//invocar al metodo lisAll()
		List<Bien> lista= servicio.listTodos();
		//crear un atributo y guardar el valor de lista
		request.setAttribute("bienes", lista);
		//enviar atributo proveedores a la pagina Proveedor.jsp
		request.getRequestDispatcher("/Bien.jsp").forward(request, response);
		
	}

}
