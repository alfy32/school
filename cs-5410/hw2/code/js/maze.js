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

  var canvas = $('#maze-canvas');

  var width = canvas.width();
  var height = canvas.height();

  MazeSolver.draw(canvas[0]);

  MazeSolver.start();

  window.addEventListener('touchstart', function (e) {
    var touch = e.targetTouches[0];

    var x = +touch.pageX - +$('#maze-canvas').position().left;
    var y = +touch.pageY - +$('#maze-canvas').position().top;

    MazeSolver.move(getDirection(x,y));

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

    if(e.which === 37) {// Left
      MazeSolver.move('LEFT');
    } else if(e.which === 38) {// UP
      MazeSolver.move('UP');
    } else if(e.which === 39) {// Right
      MazeSolver.move('RIGHT');
    } else if(e.which === 40) {// Down
      MazeSolver.move('DOWN');
    }

    switch(String.fromCharCode(e.which)) {
      case 'A':
        MazeSolver.move('LEFT');
        break;
      case 'S':
        MazeSolver.move('DOWN');
        break;
      case 'D':
        MazeSolver.move('RIGHT');
        break;
      case 'W':
        MazeSolver.move('UP');
        break;
    }

    return true;

    // console.log(String.fromCharCode(e.which), e.which);
  });

}());