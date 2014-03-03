
var MYGAME = MYGAME || {};

MYGAME.persistence = (function () {
  'use strict';

  function add(key, value) {
    localStorage[key] = JSON.stringify(value);
  }

  function remove(key) {
    localStorage.removeItem(key);
  }

  function get(key) {
    var value = localStorage[key];

    if(typeof value === 'string')
      value = JSON.parse(value);

    return value;
  }

  function addScore(level, score) {
    var highscores = get('highscores');

    if(!highscores) highscores = {};

    highscores[level].push(score);

    add('highscores', highscores);
  }

  return {
    add : add,
    remove : remove,
    get : get,
    addScore: addScore
  };
}());