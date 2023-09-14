function calcCheck() {
	var xInput = document.calcForm.x;
	var yInput = document.calcForm.y;
	
	if(isNotEmpty(xInput)
		|| isNotNum(xInput)
		|| isEmpty(yInput)
		|| isNotNum(yInput)) {
		alert("?");
		return false;
	}
}

function check() {
	var noInput = document.bbForm.no;
	if (isEmpty(noInput) || isNotNum(noInput) || lessThan(noInput, 3)
		|| noInput.value.indexOf("-") != -1
		|| noInput.value.indexOf(".") != -1  
		|| noInput.value[0] == noInput.value[1]
		|| noInput.value[1] == noInput.value[2] 
		|| noInput.value[2] == noInput.value[0]) {
		alert("?");
		noInput.value = "";
		noInput.focus();
		return false;
	}
	// 중복없어야
	return true;
}