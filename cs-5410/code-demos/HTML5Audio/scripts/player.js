/*jslint browser: true, white: true */
/*global console, MYGAME */

//------------------------------------------------------------------
//
// This function performs the one-time game initialization.
//
//------------------------------------------------------------------
MYGAME.initialize = function initialize() {
	'use strict';

	console.log('game initializing...');
	
};

MYGAME.playSound = function(whichSound, idComplete) {
	var property = whichSound + '.' + MYGAME.audioExt,
		element = document.getElementById(idComplete);

	element.innerHTML = 'playing';
	MYGAME.sounds[property].addEventListener('ended', function() {
		element.innerHTML = 'ended';
	});
	
	MYGAME.sounds[whichSound + '.' + MYGAME.audioExt].play();
};
