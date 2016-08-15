taskNameRequired.style.display = "none";
taskBodyRequired.style.display = "none";

function clearFcnEditTask() {
	if (nameOfEditedTask.value.trim() != "") {
		taskNameRequired.style.display = "none";
		nameOfEditedTask.style.border = "solid 1px #D3D3D3"
	}
	if (bodyOfEditedTask.value.trim() != "") {
		taskBodyRequired.style.display = "none";
		bodyOfEditedTask.style.border = "solid 1px #D3D3D3"
	}
}

var checkRequiredEditTask = function() {
	var fail = false;
	var taskNameRequired = document.getElementById('taskNameRequired');
	var taskBodyRequired = document.getElementById('taskBodyRequired');
	var nameOfEditedTask = document.getElementById('nameOfEditedTask');
	var bodyOfEditedTask = document.getElementById('bodyOfEditedTask');
	
	if (nameOfEditedTask.value.trim() == "") {
		taskNameRequired.style.display = "inline";
		nameOfEditedTask.style.border = "solid 1px red"
		fail = true;
	}
	if (bodyOfEditedTask.value.trim() == "") {
		taskBodyRequired.style.display = "inline";
		bodyOfEditedTask.style.border = "solid 1px red"
		fail = true;
	}

	return !fail;
}
setInterval('clearFcnEditTask()', 100);