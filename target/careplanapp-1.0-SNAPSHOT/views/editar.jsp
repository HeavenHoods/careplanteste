<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>FESC</title>
<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css">
</head>
<body>
	<h1>Editar Procedimento</h1>
	<form name="formProcedimento" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="caixa3" readonly
					value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="procedimento" class="Caixa1"
					value="<%out.print(request.getAttribute("procedimento"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="idade" class="Caixa2"
					value="<%out.print(request.getAttribute("idade"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="sexo" class="Caixa1"
					value="<%out.print(request.getAttribute("sexo"));%>"></td>
			</tr>
                        <tr>
				<td><input type="text" name="permitido" class="Caixa1"
					value="<%out.print(request.getAttribute("permitido"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
                <a href="${pageContext.request.contextPath}/main" class="Botao2">Voltar</a>
	</form>
        <script src="${pageContext.request.contextPath}/scripts/validator.js" type="text/javascript"></script>
</body>
</html>