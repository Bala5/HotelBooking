<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />
<c:set var="s" value="${pageContext.request.contextPath}" />



<div class="container">
	<div class="row">
		<div class="col-xs-8">
		<c:if test="${empty(Paymentlist)}">
		<h2 class="jumbotron">Booking Is Empty</h2>
		<a href="${contextRoot}/productgrid" type="button"
									class="btn btn-primary btn-sm"> <span
									class="glyphicon glyphicon-share-alt"></span> Continue shopping
								</a>
								
		
		</c:if>
		<c:if test="${!empty(Paymentlist)}">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<div class="row">
							<div class="col-xs-6">
								<h5>
									<span class="glyphicon glyphicon-shopping-cart"></span>
									Shopping Cart
								</h5>
							</div>
							<div class="col-xs-6">
								<a href="${contextRoot}/productgrid" type="button"
									class="btn btn-primary btn-sm btn-block"> <span
									class="glyphicon glyphicon-share-alt"></span> Continue shopping
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<c:set var="totalPrice" value="0"></c:set>
				<c:forEach var="ss" items="${Paymentlist}">


					<div class="panel-body">
						<div class="row">
							<div class="col-xs-2">
								<img src="${CR}/${ss.pid}.jpg" class="img-responsive">
							</div>
							<div class="col-xs-4">
								<h4 class="product-name">
									<strong>${ss.pname}</strong>
								</h4>
							</div>
							<div class="col-xs-6">
								<div class="col-xs-3 text-right">
									<h6>
										<strong>${ss.pprice} <span class="text-muted">x</span></strong>
									</h6>
								</div>
								<div class="col-xs-3">
									<h6>
										<strong>${ss.qty}</strong>
									</h6>
								</div>
								
								<div class="col-xs-4">
									<h6>
										<strong>${ss.total}</strong>
									</h6>
									<c:set var="totalPrice" value="${totalPrice + ss.total}"></c:set>
								</div>
								<div class="col-xs-2">

									<a href="${contextRoot}/info/${ss.pname}"
										class="btn btn-link btn-xs"><i
										class="glyphicon glyphicon-edit"></i></a> <a
										href="${contextRoot}/deletecart/${ss.id}" type="button"
										class="btn btn-link btn-xs"> <span
										class="glyphicon glyphicon-trash"> </span>
									</a>
								</div>
							</div>
						</div>
					</div>

					<hr>
				</c:forEach>

				
			</div>
			<div class="panel-footer">
				<div class="row text-center">
					<div class="col-xs-9">
						<h4 class="text-right">
							Total &#8377 ${totalPrice}
						</h4>
					</div>
					<div class="col-xs-3">
						<a href="${contextRoot}/Address" type="button" class="btn btn-success btn-block">
							Checkout</a>
					</div>
				</div>
			</div>
			</c:if>
		</div>
	</div>
</div>
