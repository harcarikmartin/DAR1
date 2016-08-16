commentRequired.style.display = "none";

function clearFcnAddComment() {
	if (comment.value.trim() != "") {
		commentRequired.style.display = "none";
		comment.style.border = "solid 1px #D3D3D3"
	}
}

var checkRequiredAddComment = function() {
	var fail = false;
	var commentRequired = document.getElementById('commentRequired');
	var comment = document.getElementById('comment');
	
	if (comment.value.trim() == "") {
		commentRequired.style.display = "inline";
		comment.style.border = "solid 1px red"
		fail = true;
	}

	return !fail;
}
setInterval('clearFcnAddComment()', 100);