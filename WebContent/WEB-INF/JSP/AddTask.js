taskNameRequired.style.display = "none";
taskBodyRequired.style.display = "none";

function clearFcnAddTask() {
	if (nameOfTask.value != "") {
		taskNameRequired.style.display = "none";
		nameOfTask.style.border = "solid 1px #D3D3D3"
	}
	if (bodyOfTask.value != "") {
		taskBodyRequired.style.display = "none";
		bodyOfTask.style.border = "solid 1px #D3D3D3"
	}
}

var checkRequiredAddTask = function() {
	var fail = false;
	var taskNameRequired = document.getElementById('taskNameRequired');
	var taskBodyRequired = document.getElementById('taskBodyRequired');
	var nameOfTask = document.getElementById('nameOfTask');
	var bodyOfTask = document.getElementById('bodyOfTask');
	
	if (nameOfTask.value == "") {
		taskNameRequired.style.display = "inline";
		nameOfTask.style.border = "solid 1px red"
		fail = true;
	}
	if (bodyOfTask.value == "") {
		taskBodyRequired.style.display = "inline";
		bodyOfTask.style.border = "solid 1px red"
		fail = true;
	}

	return !fail;
}
setInterval('clearFcnAddTask()', 100);