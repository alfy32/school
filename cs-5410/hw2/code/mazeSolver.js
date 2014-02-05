
var MazeSolver = (function () {
  'use strict';

  var walls;

  var currentWall = {
    row: 0,
    col: 0,
    edge: ''
  };

  function generate(maze) {
    walls = maze.walls;

    while(getRandomWall()) {
      if(cellsInDistinctSets()) {
        removeCurrentWall();
        joinCurrentRoomSets();
      }
    }
  }

  function getRandomWall() {
    if(walls.length === 0) return false;

    var random = Math.floor(Math.random() * walls.length);
    currentWall = walls.splice(random, 1)[0];

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
    generate: generate
  };
}());