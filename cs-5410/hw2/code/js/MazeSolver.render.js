/* globals MazeSolver */
'use strict';

(function (that) {
  var canvas;
  var context;

  var CELL_WIDTH = 25;
  var CELL_HEIGHT = CELL_WIDTH;

  that.player.width = CELL_WIDTH - 12;

  that.draw = function(idOrObject) {
    canvas = 'object' === typeof idOrObject ? idOrObject : document.getElementById(idOrObject);
    context = canvas.getContext('2d');

    canvas.width = CELL_WIDTH * that.width;
    canvas.height = CELL_HEIGHT* that.height;
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.beginPath();
    context.rect(1, 1, canvas.width-2, canvas.height-2);
    context.stroke();

    for(var row in that.maze) {
      for(var col in that.maze[row]) {
        drawCell(+row, +col, that.maze[row][col]);
      }
    }

    if(that.controls.BREAD) drawBread();
    if(that.controls.PATH) drawPath();

    drawPlayer();
  };

  function drawCell(row, col, cell) {
    if(cell.walls.up) {
      context.beginPath();
      context.moveTo(col * CELL_WIDTH, row * CELL_HEIGHT);
      context.lineTo((col+1) * CELL_WIDTH, row * CELL_HEIGHT);
      context.stroke();
    }
    if(cell.walls.left) {
      context.beginPath();
      context.moveTo(col * CELL_WIDTH, row * CELL_HEIGHT);
      context.lineTo(col * CELL_WIDTH, (row+1) * CELL_HEIGHT);
      context.stroke();
    }
    if(cell.visited) drawVisited(col*CELL_WIDTH, row*CELL_HEIGHT);
    if(cell.path) drawPath(col*CELL_WIDTH, row*CELL_HEIGHT);
  }

  function drawPath() {
    var fillStyle = context.fillStyle;
    context.fillStyle = 'green';

    for(var i in that.path) {
      if(that.path[i]) {
        var coords = i.split(',');
        var col = coords[0];
        var row = coords[1];

        var x = col * CELL_WIDTH + CELL_WIDTH/2;
        var y = row * CELL_HEIGHT +  CELL_HEIGHT/2;

        context.beginPath();
        context.arc(x, y, CELL_WIDTH/4, 0*Math.PI, 2*Math.PI);
        context.fill();
      }
    }
    context.fillStyle = fillStyle
  }

  function drawBread() {
    var fillStyle = context.fillStyle;
    context.fillStyle = 'red';

    for(var i in that.bread) {
      if(that.bread[i]) {
        var coords = i.split(',');
        var col = coords[0];
        var row = coords[1];

        var x = col * CELL_WIDTH + CELL_WIDTH/2;
        var y = row * CELL_HEIGHT +  CELL_HEIGHT/2;

        context.beginPath();
        context.arc(x, y, CELL_WIDTH/4, 0*Math.PI, 2*Math.PI);
        context.fill();
      }
    }
    context.fillStyle = fillStyle
  }

  function drawVisited(x,y) {
    x += CELL_WIDTH/2;
    y += CELL_HEIGHT/2;

    var fillStyle = context.fillStyle;
    context.fillStyle = 'blue';

    context.beginPath();
    context.arc(x, y, CELL_WIDTH/4, 0*Math.PI, 2*Math.PI);
    context.fill();

    context.fillStyle = fillStyle;
  }



  function drawPlayer() {
    var x = (that.player.x * CELL_WIDTH) + CELL_WIDTH/2;
    var y = that.player.y * CELL_HEIGHT + CELL_HEIGHT/2;

    var fillStyle = context.fillStyle;

    context.beginPath();
    context.arc(x, y, that.player.width/2, 0*Math.PI, 2*Math.PI);
    context.fillStyle = 'black';
    context.lineWidth = 5;
    context.stroke();
    context.fillStyle = 'gray';
    context.fill();

    context.fillStyle = fillStyle;
  }

}(MazeSolver));