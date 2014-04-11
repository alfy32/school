/*global MYGAME */

MYGAME.screens['high-scores'] = (function() {
	'use strict';

	function initialize() {
		document.getElementById('id-high-scores-back').addEventListener(
			'click',
			function() { MYGAME.game.showScreen('main-menu'); },
			false);
	}

	function run() {
		//
		// I know this is empty, there isn't anything to do.
		var highScoresList = document.getElementById('high-scores-list');
		var highScores = MYGAME.persistence.get('highScores');

		highScoresList.innerHTML = '';

		for(var i in highScores) {
			var li = document.createElement('li');
			li.innerText = highScores[i];
			highScoresList.appendChild(li);
		}
	}

	return {
		initialize : initialize,
		run : run
	};
}());
