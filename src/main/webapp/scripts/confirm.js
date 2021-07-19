function confirm(idcon) {
	let resposta = confirm("Confirma a exclusão deste procedimento?");
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon;
	}
} 