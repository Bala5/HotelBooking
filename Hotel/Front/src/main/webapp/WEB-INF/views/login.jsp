<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<div class="container">

	<div class="row col-md-6 col-md-offset-2 custyle">
		<c:if test="${loginerror}">
			<span class="alert alert-danger">Invalid UserName/Password</span>
		</c:if>
		<c:if test="${regsucces}">
			<span class="alert alert-success">Registered Successfully</span>
		</c:if>
<br/><br/><br/>
		<form class="form-horizontal" action='perform_login' method="POST">


			<div class="form-group">
				<!-- E-mail -->
				<label class="control-label" for="email">E-mail</label>
				<div class="controls">
					<input type="text" id="usename" name="usename" placeholder=""
						class="form-control input-xlarge">
				</div>
			</div>

			<div class="form-group">
				<!-- Password-->
				<label class="control-label" for="password">Password</label>
				<div class="controls">
					<input type="password" id="userpassword" name="userpassword"
						placeholder="" class="form-control input-xlarge">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="submit"></label>
				<div class="controls">
					<input type="submit" id="submit" value="Sign In" name="submit"
						class="btn btn-success">
				</div>

			</div>
			<div class="form-group">
				<label class="control-label">New User?</label>
				<div class="controls">
					<a href="${contextRoot}/registration" class="btn btn-primary">Sign
						Up</a>

				</div>
				<a data-toggle="modal" data-target="#myModal">Forgot Password?</a>
			</div>
		</form>
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Password Rest</h4>
					</div>
					<div class="modal-body">
						<form class="form-signin"
							action='<c:url value='/resetpassword'></c:url>' method="POST">
							<div class="form-group">
								<label class="control-label">Enter Email</label> <input
									type="text" id="j_username" name="j_username"
									class="form-control" placeholder="Email" required autofocus>
							</div>
							<button class="btn button " type="submit">Reset</button>
							<br>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>