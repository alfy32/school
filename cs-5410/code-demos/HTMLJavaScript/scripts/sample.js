window.onload = function(){
	console.log('The page has loaded');
	requestAnimationFrame(myLoop);
};


function buttonPressed() {
	console.log('The button was pressed');
	var input = document.getElementById('id-input').value;
	console.log('The value is: ' + input);
}


function myLoop(time) {
	console.log(time);
	requestAnimationFrame(myLoop);	
}
