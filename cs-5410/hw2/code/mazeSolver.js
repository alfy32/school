
var MazeSolver = (function () {
  'use strict';

  var mazeGrid = [[]];
  var width = 1;
  var height = 1;

  var wallList = [];

  var currentWall = {
    row: 0,
    col: 0,
    edge: ''
  };

  var WALL = { 'UP': 0, 'DOWN': 1, 'LEFT': 2, 'RIGHT': 3 };

  function makeCell(row, col) {
    var that = {};

    that.row = row;
    that.col = col;
    that.group = [that];

    that.edges = {
      UP: true,
      DOWN: true,
      LEFT: true,
      RIGHT: true
    };

    that.notPicked = ['UP', 'DOWN', 'LEFT', 'RIGHT'];

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

  function generate(w, h) {
    width = +w || 2;
    height = +h || 2;

    makeGrid();
    makeWallList();

    while(getRandomWall()) {
      if(cellsInDistinctSets()) {
        removeCurrentWall();
        joinCurrentRoomSets();
      }
    }
    return mazeGrid;
  }

  function makeGrid() {
    mazeGrid = [];

    for(var row = 0; row < height; row++) {
      mazeGrid[row] = [];
      for(var col = 0; col < width; col++) {
        mazeGrid[row][col] = makeCell(row, col);
      }
    }

    pickOutsideWalls();
  }

  function pickOutsideWalls() {

    for(var col = 0; col < width; col++) {
      mazeGrid[0][col].pickWall(WALL.UP);
      mazeGrid[height-1][col].pickWall(WALL.DOWN);
    }

    for(var row = 0; row < height; row++) {
      mazeGrid[row][0].pickWall(WALL.LEFT);
      mazeGrid[row][width-1].pickWall(WALL.RIGHT);
    }
  }

  function makeWallList() {
    for(var row = 0; row < height; row++) {
      for(var col = 0; col < width; col++) {
        if(col < width-1) {
          wallList.push({
            cellA: mazeGrid[row][col],
            cellB: mazeGrid[row][col+1],
            edge: 'RIGHT'
          });
        }
        if(row < height-1) {
          wallList.push({
            cellA: mazeGrid[row][col],
            cellB: mazeGrid[row+1][col],
            edge: 'DOWN'
          });
        }
      }
    }
  }

  function getRandomWall() {
    if(wallList.length === 0) return false;

    var random = Math.floor(Math.random() * wallList.length);
    currentWall = wallList.splice(random, 1)[0];

    return true;
  }

  function cellsInDistinctSets() {
    var cellA = currentWall.cellA;
    var cellB = currentWall.cellB;

    for(var i in cellA.group) {
      if(cellA.group[i] == cellB) return false;
    }

    return true;
  }

  function removeCurrentWall() {
    var cellA = currentWall.cellA;
    var cellB = currentWall.cellB;

    var edge = currentWall.edge;
    var oppositeEdge = getOppositeEdge(edge);

    cellA.edges[edge] = false;
    cellB.edges[oppositeEdge] = false;
    cellB.pickWall(WALL[oppositeEdge]);
  }

  function getOppositeEdge(edge) {
    switch(edge) {
      case 'UP':
        return 'DOWN';
      case 'DOWN':
        return 'UP';
      case 'LEFT':
        return 'RIGHT';
      case 'RIGHT':
        return 'LEFT';
    }
  }

  function joinCurrentRoomSets() {
    var cellA = currentWall.cellA;
    var cellB = currentWall.cellB;

    var groupA = cellA.group;
    var groupB = cellB.group;

    for(var i = 0; i < groupB.length; i++) {
      groupB[i].group = groupA;
      groupA.push(groupB[i]);
    }
  }

  function printGrid() {
    var mazeString = '';
    mazeGrid.forEach(function (row, rowIndex) {
      var topWallString = '';
      var bottomWallString = '';
      var rowString = '';
      row.forEach(function (cell, colIndex) {
        rowString += cell.edges.LEFT ? ' |' : '  ';
        rowString += '' + rowIndex + colIndex;
        rowString += cell.edges.RIGHT ? '| ' : '  ';

        topWallString += cell.edges.UP ? '  --  ' : '      ';
        bottomWallString += cell.edges.DOWN ? '  --  ' : '      ';
      });
      mazeString += topWallString + '\n' +
                    rowString + '\n' +
                    bottomWallString + '\n';
    });
    console.log(mazeString);
  }

  return {
    generate: generate,
    print: printGrid
  };
}());