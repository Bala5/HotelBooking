<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="s" value="${pageContext.request.contextPath}" />



<div class="container">
<div class="row">
<div class="col-sm-5">
<c:url value='/setaddress' var="url"></c:url>
		<div class="row col-md-6 col-md-offset-2 custyle">
			<form:form class="form-horizontal" method="post" modelAttribute="address" action="${url}">
				<form:hidden path="addressId"/>
<form:hidden path="cartId"/>
<div class="form-group">
					<label class="control-label" >Enter Name</label>
					<div class="controls">
						<form:input class="form-control input-xlarge" placeholder="Enter Name" path="UserName"/>
						<form:errors path="UserName" cssStyle="color:red"></form:errors>
					</div>
					</div>
				<div class="form-group">
					<label class="control-label" >Enter Address Line 1</label>
					<div class="controls">
						<form:input class="form-control input-xlarge" placeholder="Enter Address Line 1" path="addressLine1"/>
						<form:errors path="addressLine1" cssStyle="color:red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label >Enter Address Line 2</label>
					<div class="controls">
						<form:input class="form-control input-xlarge" placeholder="Enter Address Line 2" path="addressLine2"/>
						<form:errors path="addressLine2" cssStyle="color:red"></form:errors>
					</div>
				</div>

				<div class="form-group">
					<label >Enter City</label>
					<div class="controls">
						<form:input class="form-control input-xlarge" placeholder="Enter City" path="city"/>
			<form:errors path="city" cssStyle="color:red"></form:errors>
					</div>
				</div>

				<div class="form-group">
					<label >Enter State</label>
					<div class="controls">
						<form:input class="form-control input-xlarge"  placeholder="Enter State" path="state"/>
						<form:errors path="state" cssStyle="color:red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label >Enter Pincode</label>
					<div class="controls">
						<form:input class="form-control input-xlarge"  placeholder="Enter Pincode" path="pincode"/>
						<form:errors path="pincode" cssStyle="color:red"></form:errors>
					</div>
				</div>
		
		<div class="form-group">
					<label >Enter Country</label>
					<div class="controls">
						<form:input class="form-control input-xlarge" placeholder="Enter Country" path="country" />
						<form:errors path="country" cssStyle="color:red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<input type="submit" class="btn button">
				</div>
			</form:form>
		</div>

</div>
<div class="col-sm-7">
		<div class="col-md-12">
		
			<c:forEach var="al" items="${addresslist}">

<div class="col-sm-6 col-md-4">
				<div class="thumbnail" >
					<h4 class="text-center"><a href="${s}/invoice/${al.addressId}"><span class="btn button">Deliver Here</span></a></h4>
					
					<div class="caption">
						<div class="row text-center">
							<p>${al.addressLine1}</p>
					<p>${al.addressLine2}</p>
					<p>${al.city}</p>
					<p>${al.state}</p>
					<p>${al.country}</p>
					<p>${al.pincode}</p>
					
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<a href="${s}/editadd/${al.addressId}"
										class="btn button "><span
										class="	glyphicon glyphicon-pencil"></span> </a></div>
							<div class="col-md-6">
								<a href="${s}/deladd/${al.addressId}"
										class="btn button "><span
										class="glyphicon glyphicon-trash"></span> </a>
									</div>

						<p> </p>
					</div>
				</div>
			</div>
			
		</div>

</c:forEach>

</div>

</div>

</div>
</div>