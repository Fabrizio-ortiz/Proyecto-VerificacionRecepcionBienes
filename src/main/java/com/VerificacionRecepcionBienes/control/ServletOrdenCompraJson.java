package com.VerificacionRecepcionBienes.control;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.gson.Gson;

/**
 * Servlet implementation class ServletOrdenCompraJson
 */
@WebServlet("/ServletOrdenCompraJson")
public class ServletOrdenCompraJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrdenCompraJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperar el código del docente que viene cuando se hace clic en el botón editar
		List<OrdenCompra> data=new OrdenCompraService().listTodos();

			//PASO 2: crear objeto de la clase Gson
			Gson gson=new Gson();
			//PASO 3: serializar (convertir de objeto a JSON)
			String json=gson.toJson(data);
			//PASO 4: mostrar el tipo de datos "JSON" en el navegador
			response.setContentType("application/json;charset=UTF-8");
			//PASO 5: proceso de imprimir la variable "json" en el navegador
			PrintWriter salida=response.getWriter();
			salida.println(json);
	}

}
