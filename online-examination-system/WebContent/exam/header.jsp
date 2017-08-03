<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div id="header">
       <h1><a href="#"><img id="clogo" src="images/logo.png" alt="Oes Admin" /></a></h1>
     
  <!-- top nav start -->
	  
	  <ul id="topnav">
		  <li ><a href="#" ><c:out value="${UserFullName}"/></a></li>
		  <li ><a href="#" title="Logout">Logout</a></li>
	  </ul>
    <!-- topnav end -->
  <ul id="nav">
      <li class="active"><a href="#" >Dashboard</a></li>
      <li><a href="#">Instruction</a></li>
      <li><a href="#">Help</a></li>
   </ul>
  <!-- nav end -->
  
</div>
