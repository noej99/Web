function goBBSDetail(no) {
	location.href = "BBSDetailController?no=" + no;
}
function deleteBBSMsg(no){
	var really = confirm("real");
	if(really) {
		location.href = "BBSDetailController?no=" + no;
	}
}
function deleteDRFile(no) {
	if(confirm("?")) {
		location.href = "DRDeleteController?no=" + no;
	}
}