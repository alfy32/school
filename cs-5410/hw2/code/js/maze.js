/* globals $, MazeSolver */
'use strict';

(function() {
  var MARGIN_WIDTH = 5;
  // var TIMER_HEIGHT = 50;
  // var FOOTER_HEIGHT = 50;

  // var availableHeight = window.innerHeight - 2 * MARGIN_WIDTH - TIMER_HEIGHT - FOOTER_HEIGHT;
  // var availableWidth = window.innerWidth - 2 * MARGIN_WIDTH;

  var dimensions = window.location.hash.split('/');
  var mazeWidth = +dimensions[1] || 5;
  var mazeHeight = +dimensions[2] || 7;

  MazeSolver.generate(mazeWidth, mazeHeight);

  MazeSolver.solve();

  var canvas = $('#maze-canvas');

  MazeSolver.draw(canvas[0]);

  MazeSolver.start();

  window.addEventListener('touchstart', function (e) {
    var touch = e.targetTouches[0];

    var x = +touch.pageX - +$('#maze-canvas').position().left;
    var y = +touch.pageY - +$('#maze-canvas').position().top;

    MazeSolver.addEvent(getDirection(x,y));

    // console.log(x,y, width, height, getDirection(x,y));

  }, false);

  function getDirection(x,y) {
    var topLeft = {
      x: +canvas.position().left,
      y: +canvas.position().top - 2 * MARGIN_WIDTH
    };
    var bottomRight = {
      x: canvas.width()-1,
      y: canvas.height()-1
    };
    var bottomLeft = {
      x: +canvas.position().left,
      y: canvas.height()-1
    };
    var topRight = {
      x: canvas.width()-1,
      y: +canvas.position().top - 2 * MARGIN_WIDTH
    };

    var currentPoint = {x:x,y:y};

    var leftOfBackslash = isLeft(topLeft, bottomRight, currentPoint);
    var leftOfForwardSlash = isLeft(bottomLeft, topRight, currentPoint);

    if(leftOfBackslash && leftOfForwardSlash) return 'DOWN';
    if(leftOfBackslash && !leftOfForwardSlash) return 'LEFT';
    if(!leftOfBackslash && !leftOfForwardSlash) return 'UP';
    if(!leftOfBackslash && leftOfForwardSlash) return 'RIGHT';
  }

  function isLeft(a,b,c) {
    return ((b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x)) > 0;
  }

  /////////////// desktop key events //////////////////////

  window.addEventListener('keydown', function (e) {
    e.cancelBubble = true;
    var keyNum = e.which;
    var keyChar = String.fromCharCode(e.which);

    if     (keyNum === 37) MazeSolver.addEvent('LEFT');
    else if(keyNum === 38) MazeSolver.addEvent('UP');
    else if(keyNum === 39) MazeSolver.addEvent('RIGHT');
    else if(keyNum === 40) MazeSolver.addEvent('DOWN');

    else if(keyChar === 'A') MazeSolver.addEvent('LEFT');
    else if(keyChar === 'W') MazeSolver.addEvent('UP');
    else if(keyChar === 'D') MazeSolver.addEvent('RIGHT');
    else if(keyChar === 'S') MazeSolver.addEvent('DOWN');

    else if(keyChar === 'J') MazeSolver.addEvent('LEFT');
    else if(keyChar === 'I') MazeSolver.addEvent('UP');
    else if(keyChar === 'L') MazeSolver.addEvent('RIGHT');
    else if(keyChar === 'K') MazeSolver.addEvent('DOWN');

    else if(keyChar === 'H') MazeSolver.addEvent('HINT');
    else if(keyChar === 'B') MazeSolver.addEvent('BREAD');
    else if(keyChar === 'P') MazeSolver.addEvent('PATH');
    else if(keyChar === 'Y') MazeSolver.addEvent('DOWN');

    return true;

    // console.log(String.fromCharCode(e.which), e.which);
  });

  /////////////// buttons //////////////////////

  $('#HINT').click(addEvent('HINT'));
  $('#BREAD').click(addEvent('BREAD'));
  $('#PATH').click(addEvent('PATH'));
  $('#SCORE').click(addEvent('SCORE'));

  function addEvent(event) {
    return function() {
      MazeSolver.addEvent(event);
    };
  }

}());