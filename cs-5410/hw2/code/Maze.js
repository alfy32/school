
var Maze = (function() {
  'use strict';

  function createCell(row, col) {
    var that = {};

    that.row = row;
    that.col = col;
    that.group = [that];

    that.edges = {
      'UP': true,
      'LEFT': true
    };

    that.notPicked = ['UP', 'LEFT'];

    that.pickRandomWall = function () {
      if(that.notPicked.length === 0) return false;

      var random = Math.floor(Math.random() * that.notPicked.length);
      return that.notPicked.splice(random, 1)[0];
    };

    that.pickWall = function (wall) {
      return that.notPicked.splice(wall, 1)[0];
    };

    return that;
  }

  function createMaze(width, height) {
    var that = {};

    that.width = width || 1;
    that.height = height || 1;

    that.cells = [[]];
    that.walls = [];

    that.init = function() {
      var row, col;

      that.cells.length = 0;
      that.walls.length = 0;

      for(row = 0; row < height; row++) {
        that.cells[row] = [];
        for(col = 0; col < width; col++) {
          that.cells[row][col] = createCell(row, col);
          if(col > 0) that.addLeftWall(row, col);
          if(row > 0) that.addUpWall(row, col);
        }
      }
    };

    that.addLeftWall = function (row, col) {
      that.walls.push({
        cellA: that.cells[row][col],
        cellB: that.cells[row][col-1],
        edge: 'LEFT'
      });
    };

    that.addUpWall = function (row, col) {
      that.walls.push({
        cellA: that.cells[row][col],
        cellB: that.cells[row-1][col],
        edge: 'UP'
      });
    };

    return that;
  }

  return {
    create: createMaze,
    createCell: createCell
  };
}());