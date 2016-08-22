rfName.style.display = "none";
	rfPass.style.display = "none";
	rfPassCheck.style.display = "none";
	rfBirthdate.style.display = "none";
	
	function clearFcnReg() {
		if (userNameReg.value.trim() != "") {
			rfName.style.display = "none";
			userNameReg.style.border = "solid 1px #D3D3D3"
		}
		if (userPassReg.value.trim() != "") {
			rfPass.style.display = "none";
			userPassReg.style.border = "solid 1px #D3D3D3"
		}
		if (userPassRegCheck.value.trim() != "") {
			rfPassCheck.style.display = "none";
			userPassRegCheck.style.border = "solid 1px #D3D3D3"
		}
		if (birthdate.value.trim() != "") {
			rfBirthdate.style.display = "none";
			birthdate.style.border = "solid 1px #D3D3D3"
		}
	}
	
function checkDate() {
	var selectedDate = document.getElementById('birthdate').value;
	var now = new Date();
	var userLang = document.documentElement.lang; 
	month = '' + (now.getMonth() + 1), day = '' + now.getDate(), year = now
			.getFullYear();
	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	if (selectedDate > [ year, month, day ].join('-')) {
		if (userLang == "sk"){
			alert("D\u00E1tum narodenia sa mus\u00ED nach\u00E1dza\u0165 v minulosti");
			birthdate.value = '';
		} else if (userLang == "en"){
			alert("Selected date of birth must be in past");
			birthdate.value = '';
		}
		
		
	}
}
	 
	 function formatDate(date) {
		    var d = new Date(date),
		        month = '' + (d.getMonth() + 1),
		        day = '' + d.getDate(),
		        year = d.getFullYear();

		    if (month.length < 2) month = '0' + month;
		    if (day.length < 2) day = '0' + day;

		    return [year, month, day].join('-');
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
		if (userNameReg.value.trim()== "") {
			rfName.style.display = "inline";
			userNameReg.style.border = "solid 1px red"
			fail = true;
		}
		if (userPassReg.value.trim() == "") {
			rfPass.style.display = "inline";
			userPassReg.style.border = "solid 1px red"
			fail = true;
		}
		if (userPassRegCheck.value.trim() == "") {
			rfPassCheck.style.display = "inline";
			userPassRegCheck.style.border = "solid 1px red"
			fail = true;
		}
		if (birthdate.value.trim() == "") {
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