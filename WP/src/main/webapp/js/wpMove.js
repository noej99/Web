function bye() {
	var really = prompt("Ż���Ϸ��� bye�� �Է�");
	if(really == "bye") {
		location.href = "ByeC";
	}
}
function deleteSNSMsg(no) {
	if(confirm("real?")) {
		location.href = "SNSDeleteC?no=" + no;
	}
}
function updateSNSMsg(no, txt) {
	txt = prompt("������ ����", txt);
	if (txt != null && txt.length < 431) {
		location.href = "SNSUpdateC?no=" + no + "&txt=" +txt;
	}
}
function deleteSNSReply(no) {
	if(confirm("real?")) {
		location.href = "SNSReplyDeleteC?no=" + no;
	}
}
function updateSNSReply(no, txt) {
	txt = prompt("������ ����", txt);
	if (txt != null && txt.length < 70) {
		location.href = "SNSReplyUpdateC?no=" + no + "&txt=" +txt;
	}
}

function goBBSDetail(no) {
	location.href = "BBSDC?no=" + no;
}

function deleteBBSMsg(no) {
	var really = confirm("real?");
	if(really) {
		location.href = "BBSDeleteC?no=" + no;
	}
}
function updateBBSMsg(no, title, txt) {
	title = prompt("������ ����", title);
	txt = prompt("������ ����", txt);
	if (title != null && title.length < 70 || txt != null && txt.length < 431) {
	location.href = "BBSUpdateC?no=" + no + "&title=" + title + "&txt=" + txt;
	}
}
function writeReply(no, writer, txt) {
	txt = prompt("���", txt);
	if(txt != null && txt.length < 70) {
		location.href = "BBSReplyWriteC?no=" + no + "&writer=" + writer + "&txt=" + txt;
	}
}