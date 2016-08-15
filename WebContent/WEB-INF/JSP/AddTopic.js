topicNameRequired.style.display = "none";
topicVisibilityRequired.style.display = "none";

function clearFcnAddTopic() {
	if (addTheTopic.value.trim() != "") {
		topicNameRequired.style.display = "none";
		addTheTopic.style.border = "solid 1px #D3D3D3"
	}
//	if (addPrivate.checked = true && addPublic.checked != true) {
//		topicVisibilityRequired.style.display = "none";
//	}
//	if (addPublic.checked = true && addPrivate.checked != true) {
//		topicVisibilityRequired.style.display = "none";
//	}
}

var checkRequiredAddTopic = function() {
	var fail = false;
	var topicNameRequired = document.getElementById('topicNameRequired');
//	var topicVisibilityRequired = document.getElementById('topicVisibilityRequired');
	var addTheTopic = document.getElementById('addTheTopic');
//	var addPrivate = document.getElementById('addPrivate');
//	var addPublic = document.getElementById('addPublic');
	
	if (addTheTopic.value.trim() == "") {
		topicNameRequired.style.display = "inline";
		addTheTopic.style.border = "solid 1px red"
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
setInterval('clearFcnAddTopic()', 100);