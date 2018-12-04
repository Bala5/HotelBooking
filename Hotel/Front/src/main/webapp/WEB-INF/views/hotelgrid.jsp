<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />
<c:set var="s" value="${pageContext.request.contextPath}" />


<div class="container">
	<div class="row">
		<div class="col-md-12">
			<c:forEach var="pl" items="${productlist}">

				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<h4 class="text-center">
							<span class="label label-info">${pl.productName}</span>
						</h4>
						<img src="${CR}/${pl.productId}.jpg" class="img-responsive">
						<div class="caption">
							<div class="row">
								<div class="col-md-6">
									<div class="price">
										<h4>&#8377 ${pl.price} /-</h4>
									</div>
								</div>
				
								<div class="col-md-6">
								
								
									<a href='${s}/info/${pl.productName}'
										class="btn btn-primary btn-product"><span
										class="glyphicon glyphicon-info-sign"></span> Info</a>
										
								</div>
							</div>

							<p></p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>

</div>