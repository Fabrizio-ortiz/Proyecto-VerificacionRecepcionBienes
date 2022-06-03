package com.VerificacionRecepcionBienes.control;

import java.io.IOException;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VerificacionRecepcionBienes.entidad.OrdenCompra;
import com.VerificacionRecepcionBienes.entidad.Proveedor;
import com.VerificacionRecepcionBienes.services.OrdenCompraService;
import com.VerificacionRecepcionBienes.services.ProveedorService;

/**
 * Servlet implementation class ServletOrdenCompra
 */
@WebServlet("/ServletOrdenCompra")
public class ServletOrdenCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdenCompraService servicio;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrdenCompra() {
        super();
        servicio=new OrdenCompraService();
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
			listarOrdenCompra(request,response);
		else if(accion.equals("REGISTRAR"))
			registrarOrdenCompra(request,response);
		else if(accion.equals("ELIMINAR"))
			eliminarOrdenCompra(request,response);
	}

	private void eliminarOrdenCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RECUPERAR EL CODIGO A ELIMINAR
		String cod;
		cod=request.getParameter("codigoEliminar");
		//eliminar el metodo deletebyId
		int salida;
		salida=servicio.eliminarPorId(Integer.parseInt(cod));
		//
		if(salida>0) {
			//crear atributo MENSAJE
			request.setAttribute("MENSAJE", "Orden de Compra Eliminado...");
			//
			//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
			listarOrdenCompra(request, response);
		}
		else {
			request.setAttribute("MENSAJE", "Error en la Eliminación...");
			//
			//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
			listarOrdenCompra(request, response);
		}
		
	}

	private void registrarOrdenCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// varibales
		String codigo,des,can,pre,fecha,direcc,repre;
		//leer los controles del formulario
		codigo=request.getParameter("codigoorden");
		des=request.getParameter("descripcion");
		can=request.getParameter("cantidad");
		pre=request.getParameter("precio");
		fecha=request.getParameter("fecha");
		direcc=request.getParameter("direccion");
		repre=request.getParameter("representante");
		//crear un objeto de la clase proveedor
		OrdenCompra bean=new OrdenCompra();
		
		bean.setDescripcion_orden(des);
		bean.setCantidad_orden(Integer.parseInt(can));
		bean.setPrecio_orden(Double.parseDouble(pre));
		bean.setFecha_orden(Date.valueOf(fecha));
		bean.setDireccion_orden(direcc);
		bean.setRepresentante_orden(repre);
		//validar variable codigo
		if(Integer.parseInt(codigo)==0) {
			//invocar el metodo save y enviar bean
			int salida;
			salida=servicio.registrar(bean);
			if(salida>0) {
				//crear atributo MENSAJE
				request.setAttribute("MENSAJE", "Orden de Compra Registrado...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarOrdenCompra(request, response);
			}
			else {
				request.setAttribute("MENSAJE", "Error en el Registro...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarOrdenCompra(request, response);
			}
			
		}
		else {
			//invocar el metodo update y enviar bean
			bean.setCodigo_orden(Integer.parseInt(codigo));
			int salida;
			salida=servicio.actualizar(bean);
			if(salida>0) {
				//crear atributo MENSAJE
				request.setAttribute("MENSAJE", "Orden de Compra Actualizado...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarOrdenCompra(request, response);
			}
			else {
				request.setAttribute("MENSAJE", "Error en la Actualización...");
				//
				//request.getRequestDispatcher("/Proveedor.jsp").forward(request, response);
				listarOrdenCompra(request, response);
			}
		}
	}

	private void listarOrdenCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//invocar al metodo lisAll()
		List<OrdenCompra> lista= servicio.listTodos();
		//crear un atributo y guardar el valor de lista
		request.setAttribute("ordencompra", lista);
		//enviar atributo proveedores a la pagina Proveedor.jsp
		request.getRequestDispatcher("/OrdenCompra.jsp").forward(request, response);
		
	}

}
