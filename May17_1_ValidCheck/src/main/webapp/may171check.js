function check() {
	var idField = document.f.idd;
	var pwField = document.f.pw;	
	var pwChkField = document.f.pwChk;
	var ageField = document.f.age;
	var photoField = document.f.photo;
	
	if (isEmpty(idField)  || lessThan(idField, 5)  || containsHS(idField)) {
		alert("ID�ȹٷ�");		idField.value = "";		idField.focus();
		return false;
	}
	if (isEmpty(pwField) || lessThan(pwField, 5) || notEquals(pwField, pwChkField)
		|| notContains(pwField, "1234567890") || notContains(pwField, "abcd")) {
		alert("PW�ȹٷ�");	pwField.value = "";	pwChkField.value = ""; pwField.focus();
		return false;
	}
	
	if (isEmpty(ageField) || isNotNum(ageField)) {
		alert("����?");	ageField.value = ""; ageField.focus();
		return false;
	}
	
	if (isEmpty(photoField) 
		|| 
		(
			isNotType(photoField, "png") && 
			isNotType(photoField, "jpg") && 
			isNotType(photoField, "gif")
		)
	) {
		alert("����?");	photoField.value = "";
		return false;
	}
	return true;
}




