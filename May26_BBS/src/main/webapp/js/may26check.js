function bbsWriteCheck() {
	var titleField = document.bbsWriteForm.title;
	var txtField = document.bbsWriteForm.txt;
	
	if(isEmpty(titleField) || isEmpty(txtField)) {
		alert("?");
		titleField.value = "";
		txtField.value = "";
		titleField.focus();
		return false;
	}
	return true;
}
function bbsUpdateCheck() {
	var titleField = document.bbsUpdateForm.title;
	var txtField = document.bbsUpdateForm.txt;
	
	if (isEmpty(titleField) || isEmpty(txtField)) {
		alert("?");
		titleField.value = "";
		txtField.value = "";
		titleField.focus();
		return false;
	}
	return true;
}
function drUploadCheck() {
	var titleField = document.drUploadForm.title;
	var txtField = document.drUploadForm.txt;
	
	if (isEmpty(titleField) || isEmpty(txtField)) {
		alert("?");
		titleField.value = "";
		txtField.value = "";
		titleField.focus();
		return false;
	}
	return true;
}

function drUpdateCheck() {
	var titleField = document.bbsWriteForm.title;
	
	if(isEmpty(titleField)) {
		alert("?");
		titleField.value = "";
		titleField.focus();
		return false;
	}
	return true;
}