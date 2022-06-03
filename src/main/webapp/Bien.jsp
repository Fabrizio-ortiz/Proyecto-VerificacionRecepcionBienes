<%
	if(request.getSession().getAttribute("LISTA")==null)	
		response.sendRedirect("login.jsp");
%>
<jsp:include page="menu.jsp"></jsp:include> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienes</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">
<style type="text/css">
    	.modal-header{
		color:#fff;
		background: #428bca;
		display: flex;
  		justify-content: center;
  		
	}
	.help-block {
	  		color: red;
	}
	.form-group.has-error .form-control-label {
	  color: red;
	}
	.form-group.has-error .form-control {
	  border: 1px solid red;
	  box-shadow: 0 0 0 0.2rem rgba(250, 16, 0, 0.18);
	}
</style>

</head>
<body>
<div class="container">
<h1 class="text-center mt-5">Listado Bienes</h1>

<c:if test="${requestScope.MENSAJE!=null}">
<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>MENSAJE : </strong> ${requestScope.MENSAJE}
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Nuevo Bien</button>
		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">Bien</h5>
		      </div>
		      <div class="modal-body">
		        <form id="idRegistra" method="post" action="ServletBien?tipo=REGISTRAR">
		         
		          <div class="form-group">
				    <input type="hidden" class="form-control" name="codigobien" id="idCodigobien" readonly value="0">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Codigo Proveedor</label>
				   <select class="form-select"  name="codigoproveeedor" id="idCodigoproveedor">
				   	  <option value="">Seleccione Codigo Proveedor</option>
					</select>
				  </div>	
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Descripción</label>
				    <input type="text" class="form-control" name="descripcion" id="idDescripcion">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Cantidad</label>
				    <input type="text" class="form-control" name="cantidad" id="idCantidad">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Precio</label>
				    <input type="text" class="form-control" name="precio" id="idPrecio">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">fecha Pedido</label>
				    <input type="text" class="form-control" name="fechapedido" id="idFechapedido">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">FechaEntrega</label>
				    <input type="text" class="form-control" name="fechaentrega" id="idFechaentrega">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Estado</label>
				   <select class="form-select"  name="estado" id="idEstado">
				   	  <option value="">Seleccione Estado</option>
					  <option value="Pendiente">Pendiente</option>
					  <option value="Entregado">Entregado</option>
					</select>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Codigo Orden</label>
				   <select class="form-select"  name="codigoorden" id="idCodigoorden">
				   	  <option value="">Seleccione Codigo Orden</option>
					</select>
				  </div>	
				   <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" id="btn-cerrar" data-bs-dismiss="modal">Cerrar</button>
			        <button type="submit" class="btn btn-primary">Grabar</button>
			      </div>
			      				  
				  
				</form>
		      </div>		     
		    </div>
		  </div>
		</div>
		<!-- Modal para eliminar -->
				<div class="modal fade" id="modalEliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">ELIMINAR</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form id="" method="post" action="ServletBien?tipo=ELIMINAR">
				    <input type="hidden" class="form-control" name="codigoEliminar" id="idCodigoEliminar">
				  <h4>¿Seguro de Eliminar un registro?</h4>
				   <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">NO</button>
			        <button type="submit" class="btn btn-primary">SI</button>
			      </div>
			      				  
				  
				</form>
		      </div>		     
		    </div>
		  </div>
		</div>
		
		<div class=mt-3>
		<table id="example" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th>CÓDIGO BIEN</th>
	                <th>CÓDIGO PROVEEDOR</th>
	                <th>DESCRIPCIÓN</th>
	                <th>CANTIDAD</th>
	                <th>PRECIO</th>
	                <th>FECHA PEDIDO</th>
	                <th>FECHA ENTREGA</th>
	                <th>ESTADO</th>
	                <th>CÓDIGO ORDEN</th>
	                <th></th>
	                <th></th>
	            </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${requestScope.bienes}" var="row">
	            <tr>
	                <td>${row.cod_bien}</td>
	                <td>${row.cod_provee}</td>
	                <td>${row.descripcion_bien}</td>
	                <td>${row.canti_bien}</td>
	                <td>${row.precio_bien}</td>
	                <td>${row.fechapedi_bien}</td>
	                <td>${row.fechaentre_bien}</td>
	                <td>${row.estado_bien}</td>
	                <td>${row.cod_orden}</td>
	               
	                <td><button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Editar</button></td>
	                <td><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#modalEliminar">Eliminar</button></td>
	                
	            </tr>
             </c:forEach>
	        </tbody>
	    </table>
		</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>

<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.4.0/js/bootstrapValidator.js"></script>
<script>
$(document).ready(function() {
    $('#example').DataTable();
    leerProveedorJSON();
    leerOrdenCompraJSON();
} );

//asignar evento click a los botones con clase btn-danger
$(document).on("click",".btn-danger",function(){
	//variable para almacenar el codigo del proveedor segun el boton eliminar que se pulso
	let cod;
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	$("#idCodigoEliminar").val(cod);
})
//asignar evento click a los botones con clase btn-success
$(document).on("click",".btn-success",function(){
	//variable
	let cod,codprovee,des,canti,pre,fechape,fechaentre,estado,codorden;
	
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	codprovee=$(this).parents("tr").find("td")[1].innerHTML;
	des=$(this).parents("tr").find("td")[2].innerHTML;
	canti=$(this).parents("tr").find("td")[3].innerHTML;
	pre=$(this).parents("tr").find("td")[4].innerHTML;
	fechape=$(this).parents("tr").find("td")[5].innerHTML;
	fechaentre=$(this).parents("tr").find("td")[6].innerHTML;
	estado=$(this).parents("tr").find("td")[7].innerHTML;
	codorden=$(this).parents("tr").find("td")[8].innerHTML;
	//mostrar en las cajas del modal con id "staticBackdrop" los valores de las variables
	$("#idCodigobien").val(cod);
	$("#idCodigoproveedor").val(codprovee);
	$("#idDescripcion").val(des);
	$("#idCantidad").val(canti);
	$("#idPrecio").val(pre);
	$("#idFechapedido").val(fechape);
	$("#idFechaentrega").val(fechaentre);
	$("#idEstado").val(estado);
	$("#idCodigoorden").val(codorden);
})
//asignar evento click a los botones con clase btn-eliminar
$(document).on("click","#btn-cerrar",function(){
	//limpiar controles del formulario
	 $('#idRegistra').trigger("reset");
	 $('#idRegistra').data("bootstrapValidator").resetForm(true);
	 $('#idCodigobien').val("0");
})
		//crear una función
		function leerProveedorJSON(){
			//usar la función get de JQUERY para leer un servlet que retorna un JSON
			$.get("ServletProveedorJson",function(response){
				//console.log(response);
				//bucle para realizar recorrido sobre "response"
				$.each(response,function(index,item){ //for(Alumno a:lista)
					//adicionar datos al control con atributo "id"==>idCondicion
					$("#idCodigoproveedor").append("<option value='"+item.cod_provee+"'>"+item.nom_provee+"</option>");
				})	
			})	
		}
function leerOrdenCompraJSON(){
	//usar la función get de JQUERY para leer un servlet que retorna un JSON
	$.get("ServletOrdenCompraJson",function(response){
		//console.log(response);
		//bucle para realizar recorrido sobre "response"
		$.each(response,function(index,item){ //for(Alumno a:lista)
			//adicionar datos al control con atributo "id"==>idCondicion
			$("#idCodigoorden").append("<option value='"+item.codigo_orden+"'>"+item.codigo_orden+"</option>");
		})	
	})	
}

$(document).ready(function(){     
    $("#idRegistra").bootstrapValidator({      
    	 fields:{
    		 codigoproveeedor:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo obligatorio'
    		 			}
    		 		}
    		 	},
    		 	descripcion:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo obligatorio'
    		 			},
    		 			regexp:{
    		 				regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{3,45}$/,
    		 				message:'Campo nombre solo letras MIN:3 - MAX:45'
    		 			}
    		 		}
    		 	},
    		 	cantidad:{
    		 		validators:{
     			 		notEmpty:{
     			 			message:'Campo obligatorio.'	
     			 		},
     			 		regexp:{
    			 			regexp:/^[0-9]{1,1000}$/,
    			 			message:'Solo se permite dato numerico entero.'
    			 		}
     			 	}
    		 	},
    		 	precio:{
    		 		validators:{
     			 		notEmpty:{
     			 			message:'Campo obligatorio.'	
     			 		},
     			 		regexp:{
    			 			regexp:/^(\d{1,}|\d{1,}\.\d{1,2})$/,
    			 			message:'Solo se permite nuemros enteros o decimales.'
    			 		}
     			 	}
    		 	},
    		 	fechapedido:{
     			 	validators:{
     			 		notEmpty:{
     			 			message:'Campo obligatorio.'	
     			 		},
     			 		regexp:{
    			    		regexp:/^((19|20)\d\d)[-/](0?[1-9]|1[012])[-/](0?[1-9]|[12][0-9]|3[01])/,
    			    		message:'Campo Fecha debe tener este formato yyyy-mm-dd'
    			    	}
     			 		
     			 		
     			 	}
     		 	 },
     		 	 
     		 	fechaentrega:{
     			 	validators:{
     			 		notEmpty:{      			 				
     			 		},
     			 		regexp:{
    			    		regexp:/^((19|20)\d\d)[-/](0?[1-9]|1[012])[-/](0?[1-9]|[12][0-9]|3[01])/,
    			    		message:'Campo Fecha debe tener este formato yyyy-mm-dd'
    			    	}
     			 		
     			 	}
     		 	 },
     		 	 
    		 	estado:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo obligatorio'
    		 			}
    		 		}
    		 	}
    		 	
    		 
    		 
    		 
    		 
    		 
    	 }
    });   
		
});  

</script>
</body>
</html>