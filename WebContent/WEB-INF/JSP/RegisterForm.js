rfName.style.display = "none";
	rfPass.style.display = "none";
	rfPassCheck.style.display = "none";
	rfBirthdate.style.display = "none";
	
	function clearFcnReg() {
		if (userNameReg.value != "") {
			rfName.style.display = "none";
			userNameReg.style.border = "solid 1px #D3D3D3"
		}
		if (userPassReg.value != "") {
			rfPass.style.display = "none";
			userPassReg.style.border = "solid 1px #D3D3D3"
		}
		if (userPassRegCheck.value != "") {
			rfPassCheck.style.display = "none";
			userPassRegCheck.style.border = "solid 1px #D3D3D3"
		}
		if (birthdate.value != "") {
			rfBirthdate.style.display = "none";
			birthdate.style.border = "solid 1px #D3D3D3"
		}
	}
	var checkRequiredReg = function() {
		var fail = false;
		var userNameReg = document.getElementById('userNameReg');
		var userPassReg = document.getElementById('userPassReg');
		var userPassRegCheck = document.getElementById('userPassRegCheck');
		var birthdate = document.getElementById('birthdate');
		var rfName = document.getElementById('rfName');
		var rfPass = document.getElementById('rfPass');
		var rfPassCheck = document.getElementById('rfPassCheck');
		var rfBirthdate = document.getElementById('rfBirthdate');
		if (userNameReg.value == "") {
			rfName.style.display = "inline";
			userNameReg.style.border = "solid 1px red"
			fail = true;
		}
		if (userPassReg.value == "") {
			rfPass.style.display = "inline";
			userPassReg.style.border = "solid 1px red"
			fail = true;
		}
		if (userPassRegCheck.value == "") {
			rfPassCheck.style.display = "inline";
			userPassRegCheck.style.border = "solid 1px red"
			fail = true;
		}
		if (birthdate.value == "") {
			rfBirthdate.style.display = "inline";
			birthdate.style.border = "solid 1px red"
			fail = true;
		}
		if (fail == true) {
			alert("Required field was not set!");
		}
		return !fail;
	}
	setInterval('clearFcnReg()', 100);
	<%=request.getAttribute("regWrong")%>