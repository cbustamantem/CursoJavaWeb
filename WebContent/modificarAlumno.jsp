<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="py.edu.ucsa.dto.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="googlebot" content="Index, Follow" />
<meta name="robots" content="all" />
<meta name="author" content="cbm" />
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap-responsive.css"/>
<link rel="shortcut icon" href="favicon.ico" />
<link href="css/formulario.css" rel="stylesheet" type="text/css" />
<link href="css/programacion.css" rel="stylesheet" type="text/css" />
<link href="css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/selectivizr-min.js"></script>
<script type="text/javascript" src="js/modernizr-2.5.3.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<!--flex-->
<link rel="stylesheet" type="text/css" href="css/flexpaper.css" />    
<script type="text/javascript"  src="js/bootstrap-buttons.js"></script>
<script type="text/javascript"  src="js/bootstrap-dropdown.js"></script>
<script type="text/javascript"  src="js/services.js"></script>
<link href="css/basic.css" rel="stylesheet" type="text/css" />
<!--Fin Flex--> 
<!--[if IE]>
    <style>
        #txt_busqueda{ border:0; }
    </style>
<![endif]-->
<!--[if IE 7]>
    <style>
        .headerbottom{
            height:110px;
        }
        
        #contenedor{ padding:0; }

    </style>
<![endif]-->
<!-- Productos-Detalles -  Fancybox -->
<script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>          
<link type="text/css" href="js/easySlider/screen.css" rel="stylesheet" media="screen" />      
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="js/superfish/superfish.css" media="screen">
<link rel="stylesheet" type="text/css" href="js/superfish/superfish-vertical.css" media="screen">
<script type="text/javascript" src="js/superfish/hoverIntent.js"></script>
<script type="text/javascript" src="js/superfish/superfish.js"></script>
<script type="text/javascript" src="js/DT_bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="css/DT_bootstrap.css">
<script type="text/javascript"  src="js/jquery.simplemodal.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado de Alumnos</title>
</head>
<body>
<div class="navbar navbar-fixed-top" style='margin-bottom:30px;'>
  <div class="navbar-inner">
    <div class="container">
	<!--navigation does here-->
	<nav>
		<ul class='nav pull-righ'>
		<li class='dropdown'><a href="menu.html" >Inicio</a></li>
		<li><a href="AlumnoServlet" >Lista Alumnos</a></li>
		<li><a href="CerrarSession" >Salir</a></li>
		</ul>
	</nav>
			
    </div>
  </div>
</div> 
<br></br>
<form action="AlumnoServlet" method="get">
<h2>Modificar Alumnos</h2>
<table border="1">
<tr>
<th>#Nro</th>
<th>Nombre </th>
<th>Apellido </th>
<th>Opciones </th>
</tr>
<% AlumnoDTO alumno  =(AlumnoDTO) session.getAttribute("alumno"); %>
<% System.out.println("Imprimiendo datos del Alumno" ); 
	if (alumno == null)
	{
		%>
		<h2> Error en la operacion</h2>
		<%	
	//	System.exit(1);
	}
	else
	{
%>
<% System.out.println("Alumno:#" + alumno.getId() + " " + alumno.getNombre() +  " " + alumno.getApellido()); %>

	<tr>
		<td>
		 <input type="text"  readonly name="idAlumno" id="idAlumno" value="<%=alumno.getId()%>"/>
		</td>
		<td>
			<input type="text" name="nombre" id="nombre" value="<%=alumno.getNombre()%>"/>		
		</td>
		<td>
			<input type="text" name="apellido" id="apellido" value="<%=alumno.getApellido()%>"/>
		</td>
		<td>
		
		<input type="hidden" name="accion" value="editar"></input>
			<input type="hidden" name="registrar" value="true"></input>
			
			<input type="submit" name="actualizar"   id="actualizar" value="Actualizar"/>
		</td> 
	</tr>
	<%					
	}
%>
</table>
</form>
</body>
</html>