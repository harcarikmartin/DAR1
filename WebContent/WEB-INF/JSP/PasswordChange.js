	opRequired.style.display = "none";
	npRequired.style.display = "none";
	npcRequired.style.display = "none";
	
	function clearPassChange() {
		if (OldPassword.value != "") {
			opRequired.style.display = "none";
			OldPassword.style.border = "solid 1px #D3D3D3"
		}
		if (NewPassword.value != "") {
			npRequired.style.display = "none";
			NewPassword.style.border = "solid 1px #D3D3D3"
		}
		if (NewPasswordConfirm.value != "") {
			npcRequired.style.display = "none";
			NewPasswordConfirm.style.border = "solid 1px #D3D3D3"
		}

	}
	var checkRequiredPassChange = function() {
		var fail = false;
		var OldPassword = document.getElementById('OldPassword');
		var NewPassword = document.getElementById('NewPassword');
		var NewPasswordConfirm = document.getElementById('NewPasswordConfirm');

		var opRequired = document.getElementById('opRequired');
		var npRequired = document.getElementById('npRequired');
		var npcRequired = document.getElementById('npcRequired');


		if (OldPassword.value == "") {
			opRequired.style.display = "inline";
			OldPassword.style.border = "solid 1px red"
			fail = true;
		}
		if (NewPassword.value == "") {
			npRequired.style.display = "inline";
			NewPassword.style.border = "solid 1px red"
			fail = true;
		}
		if (NewPasswordConfirm.value == "") {
			npcRequired.style.display = "inline";
			NewPasswordConfirm.style.border = "solid 1px red"
			fail = true;
		}

		if (fail == true) {
			alert("Required field was not set!");
		}
		return !fail;
	}
	setInterval('clearPassChange()', 100);

	
	
	
	
	
	
	
	
	
	