/* globals $, MYGAME */

MYGAME.scores = (function () {
  'use strict';

  var highscores = MYGAME.persistence.get('highscores');

  var scoresDiv = $('.scores');

  $('#clear').click(function () {
    MYGAME.persistence.remove('highscores');
    scoresDiv.empty();
  });

  for(var level = 1; level <= 3; level++) {
    if(highscores[level]) {
      addLevel(level);

      highscores[level].sort(numericSort);

      for(var score = 0; score < 3; score++) {
        var index = highscores[level].length - 1 - score;

        if(index >= 0) {
          addScore(level, highscores[level][index]);
        }
      }
    }
  }

  function numericSort(left, right) {
    return left - right;
  }

  function addLevel(level) {
    var div = $('<div>');

    div.text('Level ' + level);
    div.attr('class', 'level');

    scoresDiv.append(div);
  }

  function addScore(level, score) {
    var div = $('<div>');

    div.text(score);
    div.attr('class', 'score');

    scoresDiv.append(div);
  }
}());