
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />

<div class="container">
	<div class="row">

		
		<c:if test="${empty(booklist)}">
		<h2 class="jumbotron">No Orders </h2>
							<a href="${contextRoot}/hotels" class="btn button"><i class="fa fa-angle-left"></i> Get your first booking</a>
							
		</c:if>
		<c:forEach var="o" items="${booklist}" >
		<div class="panel panel-default">
    <div class="panel-heading"><a href="${contextRoot}/viewbill/${o.paymentid}/${o.addid}" class="btn button">${o.paymentid}</a></div>
    <div class="panel-body">
    
    <div class="col-md-1"><img src="${CR}/${o.pid}.jpg" class="img-responsive" width="50" height="50"></div>
    <div class="col-md-5">${o.pname}</div>
    <div class="col-md-1">${o.qty}</div>
    <div class="col-md-5">&#8377 ${o.subtotal}</div>
    
    </div>
    <div class="panel-footer">Booked on ${o.date}</div>
  </div>
  </c:forEach>
</div>
</div>