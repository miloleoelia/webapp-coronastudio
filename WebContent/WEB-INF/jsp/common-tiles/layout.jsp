<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
"http://www.w3.org/TR/html4/loose.dtd">    
<html>    
<head>    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
<title><tiles:insertAttribute name="title" ignore="true" /></title>   

<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/Chart.css' />">
<script src="<c:url value='/static/js/jquery-3.4.1.min.js' />" ></script>
<script src="<c:url value='/static/js/bootstrap.min.js' />" ></script>
 
</head>    
<body>       
	<div class="container pt-2">
	  <tiles:insertAttribute name="body" /> 
	</div>
    <tiles:insertAttribute name="footer" />
</body>    
</html>   