/*jslint browser: true, white: true */
/*global CanvasRenderingContext2D, requestAnimationFrame, console, MYGAME */
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

	//
	// Place a 'clear' function on the Canvas prototype, this makes it a part
	// of the canvas, rather than making a function that calls and does it.
	CanvasRenderingContext2D.prototype.clear = function() {
		this.save();
		this.setTransform(1, 0, 0, 1, 0, 0);
		this.clearRect(0, 0, canvas.width, canvas.height);
		this.restore();
	};
	
	function clear() {
		context.clear();
	}
	
	function Particle(spec) {
		var that = {};
		
		spec.width = 10;
		spec.height = 10;
		spec.fill = 'rgba(255, 255, 255, 1)'; 
		spec.stroke = 'rgba(0, 0, 0, 1)';
		spec.alive = 0;
		
		that.update = function(elapsedTime) {
			//
			// Update how long it has been alive
			spec.alive += elapsedTime;
			
			//
			// Update its position
			spec.position.x += (elapsedTime * spec.speed * spec.direction.x);
			spec.position.y += (elapsedTime * spec.speed * spec.direction.y);
			
			//
			// Rotate proportional to its speed
			spec.rotation += spec.speed / 500;
			
			//
			// Return true if this particle is still alive
			return (spec.alive < spec.lifetime);
		};

		that.draw = function() {
			context.save();
			context.translate(spec.position.x + spec.width / 2, spec.position.y + spec.height / 2);
			context.rotate(spec.rotation);
			context.translate(-(spec.position.x + spec.width / 2), -(spec.position.y + spec.height / 2));
			
			context.fillStyle = spec.fill;
			context.fillRect(spec.position.x, spec.position.y, spec.width, spec.height);
			
			context.strokeStyle = spec.stroke;
			context.strokeRect(spec.position.x, spec.position.y, spec.width, spec.height);

			context.restore();
		};
		
		return that;
	}

	return {
		clear : clear,
		Particle : Particle
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
	
	var particles = [];

	//------------------------------------------------------------------
	//
	// This is the Game Loop function!
	//
	//------------------------------------------------------------------
	MYGAME.gameLoop = function(time) {
		MYGAME.graphics.clear();
		
		var particle = 0,
			aliveParticles = [],
			//
			// Compute elapsed time in seconds
			elapsedTime = (time - MYGAME.lastTimeStamp) / 1000;
		MYGAME.lastTimeStamp = time;
		
		//
		// Go through and update each of the currently alive particles
		aliveParticles.length = 0;
		for (particle = 0; particle < particles.length; particle++) {
			//
			// A return value of true indicates this particle is still alive
			if (particles[particle].update(elapsedTime)) {
				aliveParticles.push(particles[particle]);
			}
		}
		particles = aliveParticles;

		//
		// Generate some new particles
		for (particle = 0; particle < 8; particle++) {
			var p = {
				position: {x: 300, y: 300},
				direction: Random.nextCircleVector(),
				speed: Random.nextGaussian(50, 25),		// pixels per second
				rotation: 0,
				lifetime: Random.nextGaussian(3, 1)		// seconds
			};
			
			particles.push(MYGAME.graphics.Particle(p));
		}
		
		//
		// Draw the remaining particles
		for (particle = 0; particle < particles.length; particle++) {
			particles[particle].draw();
		}
		
		requestAnimationFrame(MYGAME.gameLoop);
	};
	
	//
	// Set the initial timestamp
	MYGAME.lastTimeStamp = performance.now();

	requestAnimationFrame(MYGAME.gameLoop);
};
