<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Reset Password</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="./navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="reset_password_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Reset Password</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="reset_password_attr" method="post"  action="${pageContext.request.contextPath}/resetpassword/executeresetpassword" novalidate="novalidate" class="row g-3">
					
							
								
								<div class="col-md-3">
									<label for="vecchiaPassword" class="form-label">Vecchia Password <span class="text-danger">*</span></label>
									<spring:bind path="vecchiaPassword">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="vecchiaPassword" id="vecchiaPassword" placeholder="Inserire Vecchia Password"  required>
										<form:errors  path="vecchiaPassword" cssClass="error_field" />
									</spring:bind>
								</div>
								
								
								 
								<div class="col-md-3">
									<label for="nuovaPassword" class="form-label">Nuova Password <span class="text-danger">*</span></label>
									<spring:bind path="nuovaPassword">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="nuovaPassword" id="nuovaPassword" placeholder="Inserire Nuova Password"  required>
									<form:errors  path="nuovaPassword" cssClass="error_field" />
									</spring:bind>
								</div>
								
							<div class="col-md-3">
									<label for="confermaPassword" class="form-label">Conferma Password <span class="text-danger">*</span></label>
									<spring:bind path="confermaPassword">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="confermaPassword" id="confermaPassword" placeholder="Confermare Password"  required>
									<form:errors  path="confermaPassword" cssClass="error_field" />
									</spring:bind>
								</div>
								
								
							
								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form:form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="./footer.jsp" />
	  </body>
</html>