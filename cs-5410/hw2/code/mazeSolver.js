
var MazeSolver = (function () {
  'use strict';

  var mazeGrid = [[]];
  var width = 1;
  var height = 1;

  var wallList = [];
  var wallCount;

  var currentWall = {
    row: 0,
    col: 0,
    edge: ''
  };

  function makeCell(row, col) {
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

  function generate(w, h) {
    width = +w || 2;
    height = +h || 2;

    while(getRandomWall()) {
      if(cellsInDistinctSets()) {
        removeCurrentWall();
        joinCurrentRoomSets();
      }
    }
    return mazeGrid;
  }

  function makeWallList(w, h) {
    width = +w || 2;
    height = +h || 2;

    wallCount = 0;
    mazeGrid = [];
    for(var row = 0; row < height; row++) {
      mazeGrid[row] = [];
      for(var col = 0; col < width; col++) {
        mazeGrid[row][col] = makeCell(row, col);
        if(col > 0) addLeftWall(row, col);
        if(row > 0) addUpWall(row, col);
      }
    }
  }

  function addLeftWall(row, col) {
    wallList.push({
      cellA: mazeGrid[row][col],
      cellB: mazeGrid[row][col-1],
      edge: 'LEFT'
    });
    wallCount++;
  }

  function addUpWall(row, col) {
    wallList.push({
      cellA: mazeGrid[row][col],
      cellB: mazeGrid[row-1][col],
      edge: 'UP'
    });
    wallCount++;
  }

  function getRandomWall() {
    if(wallCount === 0) return false;

    var random = Math.floor(Math.random() * wallCount);
    currentWall = wallList.splice(random, 1)[0];

    wallCount--;

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
    var edge = currentWall.edge;
    cellA.edges[edge] = false;
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

  return {
    generate: generate,
    makeMaze: makeWallList
  };
}());