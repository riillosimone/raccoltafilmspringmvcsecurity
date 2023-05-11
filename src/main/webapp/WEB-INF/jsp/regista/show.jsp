<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />
<title>Visualizza elemento</title>

</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />

	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div class='card'>
				<div class='card-header'>Visualizza dettaglio</div>

				<div class='card-body'>
					<dl class="row">
						<dt class="col-sm-3 text-right">Id:</dt>
						<dd class="col-sm-9">${show_regista_attr.id}</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Nome:</dt>
						<dd class="col-sm-9">${show_regista_attr.nome}</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Cognome:</dt>
						<dd class="col-sm-9">${show_regista_attr.cognome}</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Nickname:</dt>
						<dd class="col-sm-9">${show_regista_attr.nickName}</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Data di nascita:</dt>
						<fmt:parseDate value="${show_regista_attr.dataDiNascita}"
								pattern="yyyy-MM-dd" var="localDateToBeParsed" type="date" />
							<fmt:formatDate pattern="dd/MM/yyyy"
								value="${localDateToBeParsed}" var="parsedDate"/>
						
						<dd class="col-sm-9">${parsedDate}</dd>
					</dl>

					<fmt:parseDate value="${filmItem.dataPubblicazione}"
						pattern="yyyy-MM-dd" var="localDateToBeParsed" type="date" />
					<fmt:formatDate pattern="dd/MM/yyyy" value="${localDateToBeParsed}"
						var="parsedDate" />
					<dl class="row">
						<dt class="col-sm-3 text-right">Sesso:</dt>
						<dd class="col-sm-9">${show_regista_attr.sesso}</dd>
					</dl>



					<!-- end card body -->
				</div>

				<div class='card-footer'>
					<a href="${pageContext.request.contextPath }/regista"
						class='btn btn-outline-secondary' style='width: 80px'> <i
						class='fa fa-chevron-left'></i> Back
					</a>
				</div>
				<!-- end card -->
			</div>

			<!-- end container -->
		</div>

	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>