function oCheck() {
	var nInput = document.oddForm.n;
	
	if (isEmpty(nInput) || isNotNum(nInput)) {
		alert("?");
		return false;
	} 
	return true;
}