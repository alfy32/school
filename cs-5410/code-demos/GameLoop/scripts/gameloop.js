/*jslint browser: true, white: true */
// ------------------------------------------------------------------
// 
// This is the game object.  Everything about the game is located in 
// this object.
//
// ------------------------------------------------------------------
var MYGAME = {
	lastTimeStamp: performance.now(),
	
	// ------------------------------------------------------------------
	//
	// This function is called by the 'Add Timer' button to add a new timer to
	// the game.
	//
	//------------------------------------------------------------------
	addTimer: function() {
		'use strict';
		var name,
			count,
			interval;
		
		name = document.getElementById('id-timer-name').value;
		count = document.getElementById('id-timer-count').value;
		interval = document.getElementById('id-timer-interval').value;

		MYGAME.timers.add(name, interval, count);
	},
	
	//------------------------------------------------------------------
	//
	// This is the Game Loop function!
	//
	//------------------------------------------------------------------
	gameLoop: function(time) {
		'use strict';

		MYGAME.timers.update(time);
		MYGAME.timers.render();
		MYGAME.lastTimeStamp = time;

		requestAnimationFrame(MYGAME.gameLoop);
	},

	//------------------------------------------------------------------
	//
	// This function performs the one-time game initialization.
	//
	//------------------------------------------------------------------
	initialize: function initialize() {
		'use strict';

		console.log('game initializing...');
		requestAnimationFrame(MYGAME.gameLoop);
	}	
};

//------------------------------------------------------------------
//
// All of the code and data related to handling of the timers is located
// in this function.
//
//------------------------------------------------------------------
MYGAME.timers = (function () {
	'use strict';
	var timers = [],		// Array of the currently active timers.
		renderThese = [];

	// ------------------------------------------------------------------
	//
	// Allows a new timer to be added to the array
	//
	// ------------------------------------------------------------------
	function add(name, interval, count) {
		timers.push( {
			name: name,
			interval: interval,
			count: count,
			startTime: performance.now(),
			elapsed: 0
		});
	}
	
	// ------------------------------------------------------------------
	//
	// This is used to report the timer to the browser
	//
	// ------------------------------------------------------------------
	function reportTimer(timer) {
		var node = document.getElementById('div-console');
		node.innerHTML += ('Timer: ' + timer.name + ' (' + timer.count + ' remaining)' + '<br/>');
		node.scrollTop = node.scrollHeight;
	}

	// ------------------------------------------------------------------
	//
	// Updates the status of all timers in the game.
	//
	// ------------------------------------------------------------------
	function update(time) {
		var index,
			deleteMe = [];
		
		for (index = 0; index < timers.length; index++) {
			timers[index].elapsed += (time - MYGAME.lastTimeStamp);
			if (timers[index].elapsed >= timers[index].interval) {
				timers[index].elapsed = 0;
				timers[index].count--;
				//
				// Debugging statement, not a rendering...in other words, I'm doing what I say
				console.log(timers[index].name + ' - timer hit! (' + timers[index].count + ' remaining)');
				//
				// Add the timer to the list of those that need rendering
				renderThese.push(timers[index]);

				if (timers[index].count === 0) {
					deleteMe[deleteMe.length] = index;
				}
			}
		}

		//
		// Clean up any timers who have expired
		if (deleteMe.length > 0) {
			for (index = deleteMe.length - 1; index >= 0; index -= 1) {
				timers.splice(deleteMe[index], 1);
			}
		}
	}
	
	// ------------------------------------------------------------------
	//
	// Renders each of the timers that need reporting to the console
	//
	// ------------------------------------------------------------------
	function render() {
		var timer;
		
		for (timer = 0; timer < renderThese.length; timer++) {
			reportTimer(renderThese[timer]);
		}
		
		renderThese.length = 0;
	}

	return {
		add: add,
		update: update,
		render: render
	};
}());

//
// Get the game started
MYGAME.initialize();
