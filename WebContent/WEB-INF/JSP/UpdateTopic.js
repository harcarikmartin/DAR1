topicNameRequired.style.display = "none";
topicVisibilityRequired.style.display = "none";

function clearFcnEditTopic() {
	if (editTopic.value != "") {
		topicNameRequired.style.display = "none";
		editTopic.style.border = "solid 1px #D3D3D3"
	}
//	if (addPrivate.checked = true && addPublic.checked != true) {
//		topicVisibilityRequired.style.display = "none";
//	}
//	if (addPublic.checked = true && addPrivate.checked != true) {
//		topicVisibilityRequired.style.display = "none";
//	}
}

var checkRequiredEditTopic = function() {
	var fail = false;
	var topicNameRequired = document.getElementById('topicNameRequired');
//	var topicVisibilityRequired = document.getElementById('topicVisibilityRequired');
	var editTopic = document.getElementById('editTopic');
//	var addPrivate = document.getElementById('addPrivate');
//	var addPublic = document.getElementById('addPublic');
	
	if (editTopic.value == "") {
		topicNameRequired.style.display = "inline";
		editTopic.style.border = "solid 1px red"
		fail = true;
	}
//	if (addPrivate.checked = false && addPublic.checked != false) {
//		topicVisibilityRequired.style.display = "inline";
//		fail = true;
//	}
//	if (addPublic.checked = false && addPrivate.checked != false) {
//		topicVisibilityRequired.style.display = "inline";
//		fail = true;
//	}
	return !fail;
}
setInterval('clearFcnEditTopic()', 100);