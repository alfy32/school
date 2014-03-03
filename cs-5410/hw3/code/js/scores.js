/* globals $, MYGAME */

MYGAME.scores = (function () {
  'use strict';

  var highscores = MYGAME.persistence.get('highscores');

  var scoresDiv = $('.scores');

  $('#clear').click(function () {
    MYGAME.persistence.remove('highscores');
    scoresDiv.empty();
  });

  for(var level in highscores) {
    for(var score in highscores[level]) {
      addScore(level, highscores[level][score]);
    }
  }

  function addScore(level, score) {
    scoresDiv.append('<div>level: ' + level + ' score: ' + score + '</div>');
  }
}());