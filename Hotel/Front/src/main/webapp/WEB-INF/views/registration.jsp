<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value='/setcustomer' var="url"></c:url>
	<div class="container">
    <div class="row col-md-6 col-md-offset-2 custyle">
    <form:form class="form-horizontal" method="post"
					modelAttribute="customer" action="${url}">
  
  <div class="form-group">
  <c:if test="${status }">
  <span class="alert alert-danger">Failed to process the data</span>
  </c:if>
  </div>
		 			<div class="form-group">
						<form:label class="control-label" path="emailId">Enter EmailID</form:label>
						<div class="controls">
							<form:input class="form-control input-xlarge" path="emailId" />
							<form:errors path="emailId" cssStyle="color:red"></form:errors>
						</div>
					</div>
    <div class="form-group">
      
      <label class="control-label"  >Name</label>
      <div class="controls">
        <form:input type="text"  class="form-control input-xlarge" path="name" />
        <form:errors path="name" cssStyle="color:red"></form:errors>
        </div>
    </div>
   
    <div class="form-group">
      
      <label class="control-label"  >Password</label>
      <div class="controls">
        <form:input type="password"  class="form-control input-xlarge" path="password" />
        <form:errors path="password" cssStyle="color:red"></form:errors>
        </div>
    </div>
    
    <div class="form-group">
      
      <label class="control-label"  >Phone Number</label>
      <div class="controls">
        <form:input type="text"  class="form-control input-xlarge" path="phoneNo" />
        <form:errors path="phoneNo" cssStyle="color:red"></form:errors>
        </div>
    </div>
 
    <div class="form-group">
      <label class="control-label"  for="submit"></label>
      <div class="controls">
        <input type="submit" id="submit" name="submit" class="btn btn-success">
       
      </div>
    </div>
  
</form:form>
 </div>
 </div>