/**
 * 
 */

var withdrawBtn = document.getElementById("withdrawButton"); 
var withdrawInput = document.getElementById("withdrawInput");

var depositBtn = document.getElementById("depositButton");
var depositInput = document.getElementById("depositInput");

withdrawBtn.disabled = true;
depositBtn.disabled = true;


withdrawInput.addEventListener('keyup',function(){
	
	if(withdrawInput.value == ""){
		withdrawBtn.disabled = true;
	}
	
	else{
		withdrawBtn.disabled = false;
	}
})

depositInput.addEventListener('keyup',function(){
	
	if(depositInput.value == ""){
		depositBtn.disabled = true;
	}
	
	else{
		depositBtn.disabled = false;
	}
})


