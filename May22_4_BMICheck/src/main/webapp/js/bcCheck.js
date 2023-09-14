function bcCheck() {
	var nameField = document.bcForm.n;
	var heightField = document.bcForm.h;
	var weightField = document.bcForm.w;
	var photoField = document.bcForm.p;
	if (isEmpty(nameField)) {
		alert("�̸�?")
		return false;
	}
	if (isEmpty(heightField) || isNotNum(heightField)
		|| heightField.value > 3) {
		alert("Ű?")
		return false;
	}
	if (isEmpty(weightField) || isNotNum(weightField)) {
		alert("������?");
		return false;
	}
	if (isEmpty(photoField)
		|| (isNotType(photoField, "png") && isNotType(photoField, "jpg"))) {
		alert("����?"); return false;
	}
	return true;
}