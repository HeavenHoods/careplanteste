/* global formProcedimento */

function validar() {
	let procedimento = formProcedimento.procedimento.value;
	let idade = formProcedimento.idade.value;
        let sexo = formProcedimento.sexo.value;
        let permitido = formProcedimento.permitido.value;
	if (procedimento === "") {
		alert('Preencha o campo Procedimento');
		formProcedimento.procedimento.focus();
		return false;
	} else if (idade === "") {
		alert('Preencha o campo Idade');
		formProcedimento.idade.focus();
		return false;
   	} else if (sexo === "") {
		alert('Preencha o campo Sexo');
		formProcedimento.sexo.focus();
		return false;
        } else if (permitido === "") {
        	alert('Preencha o campo Permitido');
		formProcedimento.permitido.focus();
		return false;
	} else {
		document.forms["formProcedimento"].submit();
	}
}