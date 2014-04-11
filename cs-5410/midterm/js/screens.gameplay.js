/*jslint browser: true, white: true, plusplus: true */
/*global MYGAME, console, KeyEvent, requestAnimationFrame, performance */
MYGAME.screens['game-play'] = (function() {
	'use strict';

	var mouseCapture = false,
		myMouse = MYGAME.input.Mouse(),
		myKeyboard = MYGAME.input.Keyboard(),
		myMeter = null,
		myText = null,
		myMoney = MYGAME.graphics.money,
		cancelNextRequest = false,
		gameOver = false,
		gameOverCount = 0;

	function initialize() {
		console.log('game initializing...');

		myMeter = MYGAME.graphics.Meter( {
			center: { x: 170, y: 400 },
			width: 300,
			height: 50,
			meter: {
				x: 10,
				speed: 50
			}
		});

		myText = MYGAME.graphics.Text({
			text : 'Game Over!',
			font : '32px Arial, sans-serif',
			fill : 'rgba(150, 0, 0, 1)',
			stroke : 'rgba(255, 0, 0, 1)',
			pos : {x : 80, y : 150},
			rotation : 0
		});

var lastHit = 0;
var hitTime = 500;
		//
		// Create the keyboard input handler and register the keyboard commands
		myKeyboard.registerCommand(KeyEvent.DOM_VK_A, function (elapsedTime) {
if(performance.now() - lastHit < hitTime) return;
lastHit = performance.now();

			if(!myMeter.hit(elapsedTime)) {
				gameOver = true;
			}
		});

		myKeyboard.registerCommand(KeyEvent.DOM_VK_ESCAPE, function() {
			//
			// Stop the game loop by canceling the request for the next animation frame
			cancelNextRequest = true;
			//
			// Then, return to the main menu
			MYGAME.game.showScreen('main-menu');
		});
	}

	//------------------------------------------------------------------
	//
	// This is the Game Loop function!
	//
	//------------------------------------------------------------------
	function gameLoop(time) {
		MYGAME.elapsedTime = time - MYGAME.lastTimeStamp;
		MYGAME.lastTimeStamp = time;

		myKeyboard.update(MYGAME.elapsedTime);
		myMouse.update(MYGAME.elapsedTime);

		if(!gameOver) {
			myMeter.update(MYGAME.elapsedTime);
			myMoney.update(MYGAME.elapsedTime);
		} else {
			cancelNextRequest = true;
		}

		MYGAME.graphics.clear();
		myMeter.draw();
		myMoney.render();
		if(gameOver) myText.draw();

		if (!cancelNextRequest) {
			requestAnimationFrame(gameLoop);
		} else {
			var highScores = MYGAME.persistence.get('highScores');
			var win = false;

			if(!highScores.length) {
				highScores = [];
				win = true;
			}

			console.log(myMeter.money);
			for(var i in highScores) {
				if(myMeter.money > highScores[i]) {
					win = true;
					break;
				}
			}

			if(win) {
				highScores.push(myMeter.money);
				highScores.sort(function(a, b) { return a < b; });
				if(highScores.length > 5) highScores = highScores.splice(0, 5);
				MYGAME.persistence.set('highScores', highScores);
			}

			setTimeout(function() {
				MYGAME.game.showScreen('main-menu');
			}, 1000);
		}
	}

	function run() {
		MYGAME.lastTimeStamp = performance.now();
		//
		// Start the animation loop
		gameOverCount = 0;
		gameOver = false;
		myMoney.reset();
		if(myMeter) myMeter.reset();
		cancelNextRequest = false;
		requestAnimationFrame(gameLoop);
	}

	return {
		initialize : initialize,
		run : run
	};
}());
