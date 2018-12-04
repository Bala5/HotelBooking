<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<form:form class="form-horizontal" action='setcategory' method="POST"
		modelAttribute="category">
		
		<div class="form-group">
			<c:if test="${status}">
				<span class="alert alert-danger">Failed to Process the data</span>
			</c:if>
		</div>
		<c:if test="${edit}">
		 			<div class="form-group"> 
		 				<label class="col-md-4 control-label" for="catid">Category 
		 					Id</label> 
		 				<div class="col-md-5"> 
		 					<form:input type="text" id="catid" name="catid" placeholder="" 
		 						class="form-control input-md" path="categoryId" readonly="true"/>
		 				</div> 
		 			</div>
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label" for="catname">Category
				Name</label>
			<div class="col-md-5">
				<form:input type="text" id="catname" name="catname" placeholder=""
					class="form-control input-md" path="categoryName" />
					<form:errors path="categoryName"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">Category
				Description</label>
			<div class="col-md-5">
				<form:input type="text" id="catdesc" name="catdesc" placeholder=""
					class="form-control input-md" path="categoryDescription" />
					<form:errors path="categoryDescription"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="submit"></label>
			<div class="col-md-4">
				<input type="submit" id="submit" name="submit"
					class="btn btn-success">
			</div>
		</div>

	</form:form>
	<table class="table table-striped">
		<thead>
			<tr>
				
				<th>Category Name</th>
				<th>Description</th>
				<th class="text-center">Edit</th>
				<th class="text-center">Delete</th>
			</tr>
		</thead>
		<c:forEach items="${categorylist}" var="cl">
			<tr>
				
				<td>${cl.categoryName}</td>
				<td>${cl.categoryDescription}</td>
				<td class="text-center"><a class='btn btn-info btn-xs' href="editcat?cname=${cl.categoryName}"><span
						class="glyphicon glyphicon-edit"></span> Edit</a></td>
				<td class="text-center"><a href="delcat?cname=${cl.categoryName}"
					class="btn btn-danger btn-xs"><span
						class="glyphicon glyphicon-remove"></span> Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</div>