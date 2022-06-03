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
<title>Orden Compra</title>
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
<h1 class="text-center mt-5">Listado Orden Compra</h1>

<c:if test="${requestScope.MENSAJE!=null}">
<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>MENSAJE : </strong> ${requestScope.MENSAJE}
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Nuevo Orden de Compra</button>
		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">Orden de Compra</h5>
		      </div>
		      <div class="modal-body">
		        <form id="idRegistra" method="post" action="ServletOrdenCompra?tipo=REGISTRAR">
		         
		          <div class="form-group">
				    <label for="exampleInputEmail1" class="form-label">Código Orden de Compra</label>
				    <input type="text" class="form-control" name="codigoorden" id="idCodigoorden" readonly value="0">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail1" class="form-label">Descripción</label>
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
				    <label for="exampleInputPassword1" class="form-label">Fecha</label>
				    <input type="text" class="form-control" name="fecha" id="idFecha">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Dirección</label>
				    <input type="text" class="form-control" name="direccion" id="idDireccion">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Representante</label>
				    <input type="text" class="form-control" name="representante" id="idRepresentante">
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
		        <form id="" method="post" action="ServletOrdenCompra?tipo=ELIMINAR">
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
	                <th>CÓDIGO ORDEN DE COMPRA</th>
	                <th>DESCRIPCIÓN</th>
	                <th>CANTIDAD</th>
	                <th>PRECIO</th>
	                <th>FECHA</th>
	                <th>DIRECCIÓN</th>
	                <th>REPRESENTANTE</th>
	                <th></th>
	                <th></th>
	            </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${requestScope.ordencompra}" var="row">
	            <tr>
	                <td>${row.codigo_orden}</td>
	                <td>${row.descripcion_orden}</td>
	                <td>${row.cantidad_orden}</td>
	                <td>${row.precio_orden}</td>
	                <td>${row.fecha_orden}</td>
	                <td>${row.direccion_orden}</td>
	                <td>${row.representante_orden}</td>
	               
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
	let cod,des,canti,pre,fech,dir,repre;
	
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	des=$(this).parents("tr").find("td")[1].innerHTML;
	canti=$(this).parents("tr").find("td")[2].innerHTML;
	pre=$(this).parents("tr").find("td")[3].innerHTML;
	fech=$(this).parents("tr").find("td")[4].innerHTML;
	dir=$(this).parents("tr").find("td")[5].innerHTML;
	repre=$(this).parents("tr").find("td")[6].innerHTML;
	//mostrar en las cajas del modal con id "staticBackdrop" los valores de las variables
	$("#idCodigoorden").val(cod);
	$("#idDescripcion").val(des);
	$("#idCantidad").val(canti);
	$("#idPrecio").val(pre);
	$("#idFecha").val(fech);
	$("#idDireccion").val(dir);
	$("#idRepresentante").val(repre);
})
//asignar evento click a los botones con clase btn-eliminar
$(document).on("click","#btn-cerrar",function(){
	//limpiar controles del formulario
	 $('#idRegistra').trigger("reset");
	 $('#idRegistra').data("bootstrapValidator").resetForm(true);
	 $('#idCodigoorden').val("0");
})
</script>
<script type="text/javascript">    
    $(document).ready(function(){     
        $('#idRegistra').bootstrapValidator({      
        	 fields:{
        		 descripcion:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Descripción es necesario"
        			    	},
                            regexp:{
                            	regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{3,40}$/,
                            	message:'Campo Descripción solo letras MIN:3 - MAX:40'
                            }
        			    }
        		 },
        		 cantidad:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Cantidad es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[0-9]{1,1000}$/,
        			    		message:'Solo ingresar números'
        			    	}
        			    }
        		 },
        		 precio:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Precio es necesario",
        			    	},
        			    	regexp:{
        			    		regexp:/^\d+.\d/,
        			    		message:'Campo Precio debe ser numero real'
        			    	}
        			    }
        		 },
        		 fecha:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Fecha es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^((19|20)\d\d)[-/](0?[1-9]|1[012])[-/](0?[1-9]|[12][0-9]|3[01])/,
        			    		message:'Campo Fecha debe tener este formato yyyy-mm-dd'
        			    	}
        			    }
        		 },
        		 direccion:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Direccion es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{5,40}$/,
        			    		message:'Campo Dirección solo letras MIN:5 - MAX:40'
        			    	}
        			    }
        		 },
        		 representante:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Representante es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{3,30}$/,
        			    		message:'Campo Representate solo letras MIN:3 - MAX:30'
        			    	}
        			    }
        		 }  		 		        
        	 }
        });   
			
    });    
</script>
</body>
</html>