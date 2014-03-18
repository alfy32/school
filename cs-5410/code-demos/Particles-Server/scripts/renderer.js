/*jslint browser: true, white: true */
/*global CanvasRenderingContext2D, requestAnimationFrame, console, MYGAME, performance, particleSystem */

//
// Polyfill for performance.now
// Source: https://gist.github.com/paulirish/5438650
/* (function(){
	if (typeof window.performance === 'undefined') {
		window.performance = {};
	}
	
	if (!window.performance.now) {
		var nowOffset = Date.now();
		if (window.performance.timing && window.performance.timing.navigationStart) {
			nowOffset = window.performance.timing.navigationStart;
		}
		
		window.performance.now = function now() {
			return Date.now() - nowOffset;
		};
	}
}()); */
//
// Polyfill for performance.now
// Source: http://gent.ilcore.com/2012/06/better-timer-for-javascript.html
window.performance = window.performance || {};
performance.now = (function() {
  return performance.now       ||
         performance.mozNow    ||
         performance.msNow     ||
         performance.oNow      ||
         performance.webkitNow ||
         function() { return new Date().getTime(); };
})();

// ------------------------------------------------------------------
// 
// This is the game object.  Everything about the game is located in 
// this object.
//
// ------------------------------------------------------------------
MYGAME.graphics = (function() {
	'use strict';
	
	var canvas = document.getElementById('canvas-main'),
	context = canvas.getContext('2d');

	//------------------------------------------------------------------
	//
	// Place a 'clear' function on the Canvas prototype, this makes it a part
	// of the canvas, rather than making a function that calls and does it.
	//
	//------------------------------------------------------------------
	CanvasRenderingContext2D.prototype.clear = function() {
		this.save();
		this.setTransform(1, 0, 0, 1, 0, 0);
		this.clearRect(0, 0, canvas.width, canvas.height);
		this.restore();
	};
	
	//------------------------------------------------------------------
	//
	// Expose a 'clear' method for the canvas.
	//
	//------------------------------------------------------------------
	function clear() {
		context.clear();
	}
	
	//------------------------------------------------------------------
	//
	// Expose an ability to draw an image/texture on the canvas.
	//
	//------------------------------------------------------------------
	function drawImage(spec) {
		context.save();
		
		context.translate(spec.center.x, spec.center.y);
		context.rotate(spec.rotation);
		context.translate(-spec.center.x, -spec.center.y);
		
		context.drawImage(
			spec.image,
			spec.center.x - spec.size/2,
			spec.center.y - spec.size/2,
			spec.size, spec.size);
		
		context.restore();
	}

	return {
		clear : clear,
		drawImage : drawImage
	};
}());

//------------------------------------------------------------------
//
// This function performs the one-time game initialization.
//
//------------------------------------------------------------------
MYGAME.initialize = function initialize() {
	'use strict';

	console.log('game initializing...');
	
	//
	// One particle system for the fire particles
	var particlesFire = MYGAME.particleSystem( {
		image : MYGAME.images['textures/fire.png'],
		center: {x: 300, y: 300},
		speed: {mean: 50, stdev: 25},
		lifetime: {mean: 4, stdev: 1}
	});
	
	//
	// Another particle system for the smoke particles
	var particlesSmoke = MYGAME.particleSystem( {
		image : MYGAME.images['textures/smoke.png'],
		center: {x: 300, y: 300},
		speed: {mean: 50, stdev: 25},
		lifetime: {mean: 4, stdev: 1}
	});
	
	//------------------------------------------------------------------
	//
	// This is the Game Loop function!
	//
	//------------------------------------------------------------------
	MYGAME.gameLoop = function(time) {
		MYGAME.graphics.clear();
		
		//
		// Compute elapsed time in seconds
		var elapsedTime = (time - MYGAME.lastTimeStamp) / 1000;
		MYGAME.lastTimeStamp = time;
		
		//
		// Update the current set of particles
		particlesFire.update(elapsedTime);
		particlesSmoke.update(elapsedTime);
		
		//
		// Render the current set of particles
		particlesFire.render();
		particlesSmoke.render();
		
		//
		// Generate some new particles
		particlesFire.create();
		particlesFire.create();
		particlesSmoke.create();
		
		requestAnimationFrame(MYGAME.gameLoop);
	};
	
	//
	// Set the initial time stamp
	MYGAME.lastTimeStamp = performance.now();

	requestAnimationFrame(MYGAME.gameLoop);
};
