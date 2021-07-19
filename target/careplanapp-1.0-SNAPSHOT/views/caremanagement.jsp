<%-- 
    Document   : caremanagement
    Created on : Jul 17, 2021, 12:30:43 PM
    Author     : Tiago
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Procedimentos"%>
<%
	@ SuppressWarnings ("unchecked")
	ArrayList<Procedimentos> lista = (ArrayList<Procedimentos>) request.getAttribute("procedimentos");
%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FESC</title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css">
    </head>
    <body>            
        <h1>Autorizador de Procedimentos</h1>
        <a href="${pageContext.request.contextPath}/novo" class="Botao1">Novo Procedimento</a>
        <a href="${pageContext.request.contextPath}/report" class="Botao2">Relatório</a>
        <p><font color="red">${errorMessage}</font></p>
	<table id="tabela">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>PROCEDIMENTO</th>
                    <th>IDADE</th>
                    <th>SEXO</th>
                    <th>PERMITIDO</th>
                    <th>EXECUTADO</th>
                </tr>			
            </thead>
            <tbody>
                    <%
                            for (int i = 0; i < lista.size(); i++) {
                    %>
                    <tr>
                            <td><%=lista.get(i).getIdcon()%></td>
                            <td><%=lista.get(i).getProcedimento()%></td>
                            <td><%=lista.get(i).getIdade()%></td>
                            <td><%=lista.get(i).getSexo()%></td>
                            <td><%=lista.get(i).getPermitido() == true ? "SIM" : "NÃO"%></td>
                            <td><%=lista.get(i).isExecutado() == true ? "SIM" : "NÃO"%></td>
                            <td>
                                    <a href="select?idcon=<%=lista.get(i).getIdcon()%>"
                                    class="Botao1">Editar</a>
                                    <a href="delete?idcon=<%=lista.get(i).getIdcon()%>"
                                    class="Botao2">Excluir</a>
                                    <a href="executar?idcon=<%=lista.get(i).getIdcon()%>"
                                    class="Botao3">Executar procedimento</a>                                        
                            </td>
                    </tr>
                    <%
                            }
                    %>
            </tbody>
	</table>
                <script src="${pageContext.request.contextPath}/scripts/confirm.js" type="text/javascript" script></script>
    </body>
</html>
