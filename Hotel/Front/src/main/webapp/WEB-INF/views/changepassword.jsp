<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value='/cart/updatepassword' var="url"></c:url>

<div class="container">
	<div class="row">
		<div class="form-signin">
			<c:if test="${msg}">
				<span class="alert alert-success">Password updated successfully</span>
			</c:if>
			<c:if test="${msg1}">
				<span class="alert alert-danger">Password not matching</span>
			</c:if>
		</div>

		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<div class="account-wall">

				<form:form class="form-signin" method="post"
					modelAttribute="usercred" action="${url}">
					<div class="form-group">
						<label class="control-label">Enter Old Password</label>
						<div class="controls">
							<input type="password" id="oldpass" name="oldpass"
								class="form-control" placeholder="Old Password" >
							
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">Enter New Password</label>
						<div class="controls">
							<form:input type="password" id="userpassword" name="userpassword"
								class="form-control" placeholder="New Password" path="password" />
							<form:errors path="password" cssStyle="color:red"></form:errors>
						</div>
					</div>
					<button class="btn button" type="submit">Submit</button>
				</form:form>
			</div>

		</div>
	</div>
</div>