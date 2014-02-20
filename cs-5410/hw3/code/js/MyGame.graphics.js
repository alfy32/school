/*global MYGAME */

MYGAME.graphics = (function() {
  'use strict';

  var canvas = document.getElementById('canvas-main'),
  context = canvas.getContext('2d');

  function clear() {
    context.save();
    context.setTransform(1, 0, 0, 1, 0, 0);
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.restore();
  }

  return {
    clear : clear
  };
}());