userNameRequired.style.display = "none";
userPasswordRequired.style.display = "none";

function clearFcnLog() {
	if (userName.value != "") {
		userNameRequired.style.display = "none";
		userName.style.border = "solid 1px #D3D3D3"
	}
	if (userPassword.value != "") {
		userPasswordRequired.style.display = "none";
		userPassword.style.border = "solid 1px #D3D3D3"
	}
}

var checkRequiredLog = function() {
	var fail = false;
	var userNameRequired = document.getElementById('userNameRequired');
	var userPasswordRequired = document.getElementById('userPasswordRequired');
	var userName = document.getElementById('userName');
	var userPassword = document.getElementById('userPassword');
	
	if (userName.value == "") {
		userNameRequired.style.display = "inline";
		userName.style.border = "solid 1px red"
		fail = true;
	}
	if (userPassword.value == "") {
		userPasswordRequired.style.display = "inline";
		userPassword.style.border = "solid 1px red"
		fail = true;
	}
	return !fail;
}
setInterval('clearFcnLog()', 100);