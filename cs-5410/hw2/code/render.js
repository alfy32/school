/* global MazeSolver */
'use strict';

MazeSolver.render = (function() {
  var that = {};

  that.canvas = document.getElementById('maze-canvas');
  that.context = that.canvas.getContext('2d');

  that.cellWidth = 50;
  that.lineWidth = 3;

  that.playerMargin = 8;

  that.player = {
    x: 0,
    y: 0,
    width: that.cellWidth - that.playerMargin * 2,
    height: that.cellWidth - that.playerMargin * 2,
    image: new Image()
  };

  that.init = function (width, height, lineColor) {
    that.width = width;
    that.height = height;
    that.lineColor = lineColor;
    that.context.fillStyle = lineColor;
    that.context.lineWidth = that.lineWidth;

    that.canvas.width = that.cellWidth * width;
    that.canvas.height = that.cellWidth * height;
  };

  that.drawMaze = function (maze) {
    that.clear();

    drawBorder();

    for(var row in maze.cells) {
      for(var col in maze.cells[row]) {
        drawCell(maze.cells[row][col]);
      }
    }

    drawPlayer();
  };

  function drawCell(cell) {
    if(cell.edges.UP)   drawWall(cell.row, cell.col, 'UP');
    if(cell.edges.LEFT) drawWall(cell.row, cell.col, 'LEFT');
  }

  function drawWall(row, col, wall) {
    that.context.save();

    that.context.translate(col * that.cellWidth, row * that.cellWidth);
    that.context.beginPath();

    var top = 0, left = 0;
    var bottom = that.cellWidth, right = that.cellWidth;

    if(wall == 'UP') {
      that.context.moveTo(left, top);
      that.context.lineTo(right, top);
    }
    if(wall == 'LEFT') {
      that.context.moveTo(left, top);
      that.context.lineTo(left, bottom);
    }

    that.context.stroke();
    that.context.restore();
  }

  function drawPlayer() {
    that.context.save();

    that.context.translate(
      that.player.x * that.cellWidth + that.cellWidth/2,
      that.player.y * that.cellWidth + that.cellWidth/2
      );

    that.context.beginPath();
    that.context.arc(0, 0, that.player.width/2, 0*Math.PI, 2*Math.PI);
    that.context.fillStyle = 'black';
    that.context.lineWidth = 5;
    that.context.stroke();
    that.context.fillStyle = 'gray';
    that.context.fill();

    that.context.restore();
  }

  function drawBorder() {
    that.context.save();
    that.context.lineWidth = 2 * that.lineWidth;
    that.context.strokeRect(0,0, that.width * that.cellWidth, that.height * that.cellWidth);
    that.context.restore();
  }

  that.clear = function () {
    that.context.clearRect(0,0, that.width * that.cellWidth, that.height * that.cellWidth);
  };

  return that;
}());