/* globals $, MYGAME */

MYGAME.scores = (function () {
  'use strict';

  var score, level;

  var highscores = MYGAME.persistence.get('highscores');
  var topScores = [];

  var scoresDiv = $('.scores');

  $('#clear').click(function () {
    MYGAME.persistence.remove('highscores');
    scoresDiv.empty();
  });

  for(level = 1; level <= 3; level++) {
    if(highscores[level]) {
      addLevel(level);

      highscores[level].sort(numericSort);

      for(score = 0; score < 3; score++) {
        var index = highscores[level].length - 1 - score;

        if(index >= 0) {
          addScore(level, highscores[level][index]);
        }
      }
    }

  }

  topScores.sort(numericSort);

  var last = topScores.length;
  for(score = last - 3; score < last; score++) {
    addTopScore(topScores[score]);
  }

  var div = $('<div>');

  div.text('Top Scores');
  div.attr('class', 'level');

  scoresDiv.prepend(div);

  function addTopScore(score) {
    var div = $('<div>');

    div.text(score);
    div.attr('class', 'score');

    scoresDiv.prepend(div);
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
    topScores.push(score);

    var div = $('<div>');

    div.text(score);
    div.attr('class', 'score');

    scoresDiv.append(div);
  }
}());