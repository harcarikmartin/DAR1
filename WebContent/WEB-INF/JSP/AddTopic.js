topicNameRequired.style.display = "none";
topicVisibilityRequired.style.display = "none";

function clearFcnAddTopic() {
	if (addTheTopic.value.trim() != "") {
		topicNameRequired.style.display = "none";
		addTheTopic.style.border = "solid 1px #D3D3D3"
	}
}

var checkRequiredAddTopic = function() {
	var fail = false;
	var topicNameRequired = document.getElementById('topicNameRequired');
	var addTheTopic = document.getElementById('addTheTopic');
	if (addTheTopic.value.trim() == "") {
		topicNameRequired.style.display = "inline";
		addTheTopic.style.border = "solid 1px red"
		fail = true;
	}
	return !fail;
}
setInterval('clearFcnAddTopic()', 100);