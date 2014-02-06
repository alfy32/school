/* globals console, performance */
'use strict';

function createCell(location) {
  var that = {};

  that.location = location;
  that.set = {};
  that.set[that.location] = that;

  that.walls = {
    up: true,
    left: true
  };

  that.sameSet = function(otherCell) {
    return that.location in otherCell.set;
  };

  that.removeWall = function(otherCell) {
    if(that.location - otherCell.location > 1) {
      that.walls.up = false;
    } else {
      that.walls.left = false;
    }
  };

  that.unionSet = function(otherCell) {
    var set = that.set;
    var otherSet = otherCell.set;

    for(var i in otherSet) {
      otherSet[i].set = set;
      set[otherSet[i].location] = otherSet[i];
    }
  };

  return that;
}

function shuffle(arr) {
  for(var i in arr) {
    var rand = Math.floor(Math.random() * arr.length);

    var temp = arr[i];
    arr[i] = arr[rand];
    arr[rand] = temp;
  }
}

function generate(w, h) {
  var start = performance.now();
  var cellNum = 0;
  var walls = [];
  var maze = [[]];

  for(var row = 0; row < h; row++) {
    maze[row] = [];
    for(var col = 0; col < w; col++) {
      maze[row][col] = createCell(cellNum);

      if(cellNum % w !== 0) {
        walls.push({
          currCell: maze[row][col],
          prevCell: maze[row][col-1]
        });
      }
      if(cellNum >= w) {
        walls.push({
          currCell: maze[row][col],
          prevCell: maze[row-1][col],
        });
      }

      cellNum++;
    }
  }

  shuffle(walls);

  console.log('make', performance.now() - start);

  for(var i in walls) {
    var currCell = walls[i].currCell;
    var prevCell = walls[i].prevCell;

    if(!currCell.sameSet(prevCell)) {
      currCell.removeWall(prevCell);
      currCell.unionSet(prevCell);
    }
  }

  console.log('generate', performance.now() - start);

  return maze;
}

var canvas = document.getElementById('maze-canvas');
var context = canvas.getContext('2d');

var CELL_WIDTH = 10;
var CELL_HEIGHT = CELL_WIDTH;

function draw(maze) {
  canvas.width = canvas.height = CELL_WIDTH * maze.length + 100;
  context.clearRect(0, 0, canvas.width, canvas.height);

  for(var row in maze) {
    for(var col in maze[row]) {
      drawCell(+row, +col, maze[row][col]);
    }
  }
}

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

}

function generateClick() {
  var size = +document.getElementById('width').value;

  var maze = generate(size, size);
  draw(maze);
}

function keyDown(e) {
  if(e.which === 13) generateClick();
}