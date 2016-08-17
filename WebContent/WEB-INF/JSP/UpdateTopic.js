topicNameRequired.style.display = "none";
topicVisibilityRequired.style.display = "none";

function clearFcnEditTopic() {
	if (editTopic.value.trim() != "") {
		topicNameRequired.style.display = "none";
		editTopic.style.border = "solid 1px #D3D3D3"
	}

}

var checkRequiredEditTopic = function() {
	var fail = false;
	var topicNameRequired = document.getElementById('topicNameRequired');
	var editTopic = document.getElementById('editTopic');

	if (editTopic.value.trim() == "") {
		topicNameRequired.style.display = "inline";
		editTopic.style.border = "solid 1px red"
		fail = true;
	}
	return !fail;
}
setInterval('clearFcnEditTopic()', 100);