<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />
	<c:url value='/ADMIN/setproduct' var="url"></c:url>
	<c:set var="s" value="${pageContext.request.contextPath}" />

<div class="container">

	<form:form class="form-horizontal" action='${url}' method="POST"
		modelAttribute="product" enctype="multipart/form-data">
		
		
		<c:if test="${edit}">
						<div class="form-group">
							<label class="col-md-4 control-label" for="catid">Product Id</label>
							<div class="col-md-5">
								<form:input type="text" class="form-control input-md"
									path="productId" readonly="true" />
							</div>
						</div>
					</c:if>
		
		<div class="form-group">
			<c:if test="${status}">
				<span class="alert alert-danger">Failed to Process the data</span>
			</c:if>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="proname">product
				Name</label>
			<div class="col-md-5">
				<form:input type="text" id="proname" name="proname" placeholder=""
					class="form-control input-md" path="productName" />
					<form:errors path="productName" cssStyle="color:red"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">product
				Description</label>
			<div class="col-md-5">
				<form:input type="text" id="prodesc" name="prodesc" placeholder=""
					class="form-control input-md" path="productDescription" />
					
					<form:errors path="productDescription" cssStyle="color:red"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">Category</label>
			<div class="col-md-5">
				<form:select path="catid" class="form-control input-md">
					<c:forEach var="c" items="${catlist}">
						<form:option value="${c.categoryId }"
							class="form-control input-md">${c.categoryName}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>


		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">product
				Quantity</label>
			<div class="col-md-5">
				<form:input type="text" id="desc" name="catdesc" placeholder=""
					class="form-control input-md" path="quantity" />
					
					<form:errors path="quantity" cssStyle="color:red"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">product
				Price</label>
			<div class="col-md-5">
				<form:input type="text" id="catdesc" name="catdesc" placeholder=""
					class="form-control input-md" path="price" />
					
					<form:errors path="price" cssStyle="color:red"></form:errors><br>
					<form:input class="form-control input-md" type="file"
							name="fileToUpload" id="fileToUpload" path="pimage"></form:input>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label" for="submit"></label>
			<div class="col-md-4">
				<input type="submit" id="submit" name="submit"
					class="btn btn-success">
			</div>
			<div class="col-md-4">
				<a class='btn btn-success' href="${s}/ADMIN/products">Reset</a>
			</div>
		</div>

	</form:form>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Image</th>
				<th>Product Details</th>
				<th class="text-center">Edit</th>
				<th class="text-center">Delete</th>
			</tr>
		</thead>
		<c:forEach items="${productlist}" var="pl">
			<tr>
				<td><img src="${CR}/${pl.productId}.jpg" width="100" height="100"/></td>
				<td>
				<ul>
				<li>Product Name ${pl.productName}</li>
				<li>Product Description ${pl.productDescription}</li>
				<li>Price &#8377 ${pl.price}</li>
				<li>Quantity ${pl.quantity}</li>
				</ul>
				</td>
				<td class="text-center"><a class='btn btn-info btn-xs' href="${s}/ADMIN/editprod/${pl.productName}"><span
						class="glyphicon glyphicon-edit"></span> Edit</a></td>
				<td class="text-center"><a href="${s}/ADMIN/delprod/${pl.productName}"
					class="btn btn-danger btn-xs"><span
						class="glyphicon glyphicon-remove"></span> Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</div>