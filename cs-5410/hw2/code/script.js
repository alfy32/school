/* global MazeSolver */
'use strict';

function keyDown(e) {
  if(e.which === 13)
    generateClick();
}

function generateClick() {
  var width = +document.getElementById('width').value;

  MazeSolver.makeMaze(width, width);
  var maze = MazeSolver.generate(width, width);

  drawMaze(maze, width, width);
}

function drawMaze(maze) {
  var canvas = document.getElementById('maze-canvas');
  var context = canvas.getContext('2d');

  var cellWidth = 100;

  canvas.width = cellWidth * maze[0].length;
  canvas.height = cellWidth * maze.length;

  context.clearRect(0,0,canvas.width, canvas.height);
  context.fillStyle = 'black';
  context.lineWidth = 12;
  context.strokeRect(0,0,canvas.width, canvas.height);
  context.lineWidth = 6;

  for(var row in maze) {
    for(var col in maze[row]) {
      drawCell(maze[row][col]);
    }
  }

  function drawCell(cell) {
    context.save();

    var top = 0;
    var left = 0;
    var bottom = cellWidth;
    var right = cellWidth;

    context.translate(cell.col*cellWidth, cell.row*cellWidth);
    context.beginPath();

    if(cell.edges.UP) {
      context.moveTo(left, top);
      context.lineTo(right, top);
    }
    if(cell.edges.DOWN) {
      context.moveTo(left, bottom);
      context.lineTo(right, bottom);
    }
    if(cell.edges.LEFT) {
      context.moveTo(left, top);
      context.lineTo(left, bottom);
    }
    if(cell.edges.RIGHT) {
      context.moveTo(right, top);
      context.lineTo(right, bottom);
    }

    context.stroke();
    context.restore();
  }
}

