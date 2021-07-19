<%-- 
    Document   : novo
    Created on : Jul 18, 2021, 3:27:04 PM
    Author     : Tiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FESC</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css">
    </head>
    <body>
	<h1>Novo Procedimento</h1>
	<form name="formProcedimento" action="insert">
		<table>
			<tr>
                            <td>
                                <input type="text" name="procedimento" placeholder="Procedimento"
					class="Caixa1">
                            </td>
			</tr>
			<tr>
                            <td>
                                <input type="text" name="idade" placeholder="Idade"
                                class="Caixa2">
                            </td>
			</tr>
			<tr>
                            <td>
                                <select id="sexo" name="sexo">
                                    <option value="FEMININO">FEMININO</option>
                                    <option value="MASCULINO">MASCULINO</option>
                                </select>                                
                            </td>
			</tr>
                        <tr>
                            <td>
                                <div>
                                    <p>Permite: </p>
                                    <div>                         
                                        <input type="radio" id="sim" name="permitido">
                                        <label for="sim">Sim</label>

                                        <input type="radio" id="nao" name="permitido">
                                        <label for="nao">Não</label>  
                                    </div>

                                    <br>
                                </div>

                            </td>
			</tr>
		</table>
		<input type="button" value="Adicionar" class="Botao1" onclick="validar()">
                <a href="${pageContext.request.contextPath}/main" class="Botao2">Voltar</a>
	</form>
        <script src="${pageContext.request.contextPath}/scripts/validator.js" type="text/javascript"></script>
    </body>
</html>
