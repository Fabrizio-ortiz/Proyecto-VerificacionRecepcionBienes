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
<title>Proveedor</title>
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
<h1 class="text-center mt-5">Listado Proveedor</h1>

<c:if test="${requestScope.MENSAJE!=null}">
<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>MENSAJE : </strong> ${requestScope.MENSAJE}
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Nuevo Proveedor</button>
		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">Proveedor</h5>
		      </div>
		      <div class="modal-body">
		        <form id="idRegistra" method="post" action="ServletProveedor?tipo=REGISTRAR">
		         
		          <div class="form-group">
				    <label for="exampleInputEmail1" class="form-label">Código Proveedor</label>
				    <input type="text" class="form-control" name="codigoproveedor" id="idCodigoproveedor" readonly value="0">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail1" class="form-label">Ruc</label>
				    <input type="text" class="form-control" name="ruc" id="idRuc">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Nombre</label>
				    <input type="text" class="form-control" name="nombre" id="idNombre">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Pais</label>
				    <input type="text" class="form-control" name="pais" id="idPais">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Distrito</label>
				    <input type="text" class="form-control" name="distrito" id="idDistrito">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Representante</label>
				    <input type="text" class="form-control" name="representante" id="idRepresentante">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Télefono</label>
				    <input type="text" class="form-control" name="telefono" id="idTelefono">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Correo</label>
				    <input type="text" class="form-control" name="correo" id="idCorreo">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1" class="form-label">Razón Social</label>
				    <input type="text" class="form-control" name="razonsocial" id="idRazonsocial">
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
		        <form id="" method="post" action="ServletProveedor?tipo=ELIMINAR">
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
	                <th>CÓDIGO PROVEEDOR</th>
	                <th>RUC</th>
	                <th>NOMBRE</th>
	                <th>PAIS</th>
	                <th>DISTRITO</th>
	                <th>REPRESENTANTE</th>
	                <th>TELÉFONO</th>
	                <th>CORREO</th>
	                <th>RAZÓN SOCIAL</th>
	                <th></th>
	                <th></th>
	            </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${requestScope.proveedores}" var="row">
	            <tr>
	                <td>${row.cod_provee}</td>
	                <td>${row.ruc_provee}</td>
	                <td>${row.nom_provee}</td>
	                <td>${row.pais_provee}</td>
	                <td>${row.distritro_provee}</td>
	                <td>${row.represen_provee}</td>
	                <td>${row.fono_provee}</td>
	                <td>${row.correo_provee}</td>
	                <td>${row.razonsoci_provee}</td>
	               
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
	let cod,ruc,nom,pais,dis,repre,fono,correo,razon;
	
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	ruc=$(this).parents("tr").find("td")[1].innerHTML;
	nom=$(this).parents("tr").find("td")[2].innerHTML;
	pais=$(this).parents("tr").find("td")[3].innerHTML;
	dis=$(this).parents("tr").find("td")[4].innerHTML;
	repre=$(this).parents("tr").find("td")[5].innerHTML;
	fono=$(this).parents("tr").find("td")[6].innerHTML;
	correo=$(this).parents("tr").find("td")[7].innerHTML;
	razon=$(this).parents("tr").find("td")[8].innerHTML;
	//mostrar en las cajas del modal con id "staticBackdrop" los valores de las variables
	$("#idCodigoproveedor").val(cod);
	$("#idRuc").val(ruc);
	$("#idNombre").val(nom);
	$("#idPais").val(pais);
	$("#idDistrito").val(dis);
	$("#idRepresentante").val(repre);
	$("#idTelefono").val(fono);
	$("#idCorreo").val(correo);
	$("#idRazonsocial").val(razon);
})
//asignar evento click a los botones con clase btn-eliminar
$(document).on("click","#btn-cerrar",function(){
	//limpiar controles del formulario
	 $('#idRegistra').trigger("reset");
	 $('#idRegistra').data("bootstrapValidator").resetForm(true);
	 $('#idCodigoproveedor').val("0");
})
</script>
<script type="text/javascript">    
    $(document).ready(function(){     
        $('#idRegistra').bootstrapValidator({      
        	 fields:{
        		 ruc:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Ruc es necesario"
        			    	},
                            regexp:{
                            	regexp:/^\d{11}$/,
                            	message:'Campo Ruc solo 11 dígitos'
                            }
        			    }
        		 },
        		 nombre:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Nombre es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{3,20}$/,
        			    		message:'Campo nombre solo letras MIN:3 - MAX:20'
        			    	}
        			    }
        		 },
        		 pais:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Pais es necesario",
        			    	},
        			    	regexp:{
        			    		regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{3,20}$/,
        			    		message:'Campo nombre solo letras MIN:3 - MAX:20'
        			    	}
        			    }
        		 },
        		 distrito:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Distrito es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{3,20}$/,
        			    		message:'Campo nombre solo letras MIN:3 - MAX:20'
        			    	}
        			    }
        		 },
        		 representante:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Representante es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{5,20}$/,
        			    		message:'Campo nombre solo letras MIN:5 - MAX:20'
        			    	}
        			    }
        		 },
        		 telefono:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Teléfono es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[0-9]{9,10}$/,
        			    		message:'Campo teléfono debe tener 9 o 10 números'
        			    	}
        			    }
        		 },
        		 correo:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Correo es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/,
        			    		message:'Campo correo debe ser formato bien12@gmail.com'
        			    	}
        			    }
        		 },
        		 razonsocial:{
        			 validators:{
        			    	notEmpty:{
        			    		message:"Campo Razón Social es necesario"
        			    	},
        			    	regexp:{
        			    		regexp:/^[a-zA-Z\ñ\Ñ\s\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{10,30}$/,
        			    		message:'Campo Razón social solo letras MIN:10 - MAX:30'
        			    	}
        			    }
        		 }
        		 		        
        	 }
        });   
			
    });    
</script>
</body>
</html>