<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="resources/css/mycss.css">
<script src="resources/js/myjs.js">	</script>

<title>Blush Boutique - ${title}</title>
<style>
html {
	height:100%;
}

body {
    padding-top: 70px;
    height:100%;
}


.carousel-inner img {
      width: 100%;
      height: 100%;
  }

.wrapper {	
	min-height:100%;
	position: relative;
}


.content {
	
	padding-bottom: 100px; /*height of the footer*/	
}

.navbar-custom {
	background-color:#EE897F;
    color:#ffffff;
  	border-radius:0;
  	min-height:50px
}
  
.navbar-custom .navbar-nav > li > a {
  	color:#fff;
  	padding-left:20px;
  	padding-right:20px;
}
.navbar-custom .navbar-nav > .active > a, .navbar-nav > .active > a:hover, .navbar-nav > .active > a:focus {
    color: #ffffff;
	background-color:transparent;
}
      
.navbar-custom .navbar-nav > li > a:hover, .nav > li > a:focus {
    text-decoration: none;
    background-color: #D11141;
}
      
.navbar-custom .navbar-brand {
padding-top:0;
  	color:#eeeeee;
}
.navbar-custom .navbar-toggle {
  	background-color:#eeeeee;
}
.carousel-caption{
color:#D11141;
}
.footer {
   position: absolute;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: #EE897F;
   color: white;
   text-align: center;
   
   
}
.button{
 background-color: #D11141;
 color:white;

}

</style>
</head>

    <%@ include file = "navbar.jsp" %>
<body>
<div class="wrapper">
<div class="content">
<c:if test="${userClickHome == true }">
<%@ include file="home.jsp" %>
</c:if>
<c:if test="${userClickAbout == true }">
<%@ include file="about.jsp" %>
</c:if>

<c:if test="${userClickContact == true }">
<%@ include file="contact.jsp" %>
</c:if>


<c:if test="${userClickCategory == true }">
<%@ include file="Category.jsp" %>
</c:if>

<c:if test="${userClickProduct == true }">
<%@ include file="hotel.jsp" %>
</c:if>

<c:if test="${userClickLogin == true }">
<%@ include file="login.jsp" %>
</c:if>

<c:if test="${userClickRegistration == true }">
<%@ include file="registration.jsp" %>
</c:if>

<c:if test="${userClickHotelsGrid == true }">
<%@ include file="hotelgrid.jsp" %>
</c:if>

<c:if test="${userClickHotelscat == true }">
<%@ include file="hotelgrid.jsp" %>
</c:if>

<c:if test="${userClickPayment == true }">
<%@ include file="Payment.jsp" %>
</c:if>

<c:if test="${userClickCheckout == true }">
<%@ include file="Address.jsp" %>
</c:if>

<c:if test="${userClickAllBookings == true }">
<%@ include file="AllBookings.jsp" %>
</c:if>

<c:if test="${userClickChangePassword == true }">
<%@ include file="changepassword.jsp" %>
</c:if>


<c:if test="${userClickAllBookings== true }">
<%@ include file="singlebooking.jsp" %>
</c:if>

<c:if test="${userClickInvoice == true }">
<%@ include file="invoice.jsp" %>
</c:if>

<c:if test="${userClickWish == true }">
<%@ include file="wishlist.jsp" %>
</c:if>


</div>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>
