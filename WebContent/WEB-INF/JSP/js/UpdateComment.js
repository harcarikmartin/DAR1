commentRequired.style.display = "none";

function clearFcnEditComment() {
	if (editedComment.value.trim() != "") {
		commentRequired.style.display = "none";
		editedComment.style.border = "solid 1px #D3D3D3"
	}
}

var checkRequiredEditComment = function() {
	var fail = false;
	var commentRequired = document.getElementById('taskNameRequired');
	var editedComment = document.getElementById('editedComment');
	
	if (editedComment.value.trim() == "") {
		commentRequired.style.display = "inline";
		editedComment.style.border = "solid 1px red"
		fail = true;
	}
	
	return !fail;
}
setInterval('clearFcnEditComment()', 100);