
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

  var WALL = {
    UP: 0,
    DOWN: 1,
    LEFT: 2,
    RIGHT: 3
  };

  function makeCell(row, col) {
    var that = {
      row: row,
      col: col
    };

    that.group = [that];

    that.edges = {
      UP: true,
      DOWN: true,
      LEFT: true,
      RIGHT: true
    };

    that.notPicked = ['UP', 'DOWN', 'LEFT', 'RIGHT'];

    that.pickRandomWall = function () {
      if(that.notPicked.length) {
        var random = Math.floor(Math.random() * that.notPicked.length);

        return that.notPicked.splice(random, 1)[0];
      }
      return false;
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
      printGrid();
    }
  }

  function makeGrid() {
    for(var row = 0; row < height; row++) {
      mazeGrid[row] = [];
      for(var col = 0; col < width; col++) {
        mazeGrid[row][col] = makeCell(row, col);
      }
    }

    pickOutsideWalls();
  }

  function pickOutsideWalls() {
    var row = 0, col = 0;

    for(col = 0; col < width; col++) {
      mazeGrid[0][col].pickWall(WALL.UP);
      mazeGrid[height-1][col].pickWall(WALL.DOWN);
    }

    for(row = 0; row < height; row++) {
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
      if(cellA.group[i] == cellB)
        return false;
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



// MazeSolver.generate(5,5);
// MazeSolver.print();











/*

Initial State

xxxxx
x1x2x
xxxxx
x3x4x
xxxxx

*/

// function union(wallA, wallB) {
//   if(wallA == sets[wallA]) {

//   }
// }

// function distinct(a, b) {
//   for(var i = 0; i < a.length; i++) {
//     for(var j = 0; j < b.length; j++) {
//       if(a[i] == b[j])
//         return false;
//     }
//   }

//   return true;
// }

// var walls = [
//   [1,2],
//   [1,3],
//   [2,4],
//   [3,4]
// ];

// var sets = [0,1,2,3,4];

// while(walls.length) {
//   var wall = walls.pop();

//   console.log('wall, set', JSON.stringify(wall), JSON.stringify(sets))
//   if(sets[wall[0]] != sets[wall[1]]) {
//     union(wall[0], wall[1]);
//   }

// }

// sets
// walls

// var maze = [
//   [
//     { name: 'A', right: true, down: true},
//     { name: 'B', right: true, down: true},
//     { name: 'C', right: false, down: true}
//   ],
//   [
//     { name: 'D', right: true, down: true},
//     { name: 'E', right: true, down: true},
//     { name: 'F', right: false, down: true}
//   ],
//   [
//     { name: 'G', right: true, down: false},
//     { name: 'H', right: true, down: false},
//     { name: 'I', right: true, down: false}
//   ],
// ];





// function getRandom(range) {
//   return Math.floor(Math.random() * range);
// }

// function generate(w, h) {
//   var width = w || 1;
//   var height = h || 1;

//   var grid = [];
//   for(var row = 0; row < height; row++) {
//     grid[row] = [];
//     for(var col = 0; col < width; col++) {
//       grid[row][col] = Cell();
//     }
//   }

//   do {
//     var row = getRandom(height);
//     var col = getRandom(width);
//     var c1 = grid[row][col];
//     var edge = c1.pickEdge();



//   } while ();
// }


//   // randomly hop around in the grid removing random walls until
//   // there is exactly one path from any cell to any other cell.
//   var done = false;
//   while(!done) {
//     var row = this.pickRow(h);
//     var cell = this.pickColumn(w);
//     var edge = this.pickEdge();

//     // get a reference to the cell object we've chosen.
//     var c1 = this.at(cell, row);
//     var c2;

//     switch(edge) {
//     case 0: // up
//       // if we're in the top row it does not make
//       // sense to remove the top wall of the cell. as
//       // long as we are not in row 0:
//       //  - check if the cell above is in the same
//       //  group as this cell.
//       //  - if not, remove the top wall of the
//       //  cell and merge the cells into the same
//       //  group.
//       //
//       // follow this process for each of the other
//       // cases.
//       if(row > 0) {
//         c2 = this.at(cell, row - 1);
//         if(!c1.grp.contains(c2)) {
//           c1.edges[0] = false;
//           c2.edges[2] = false;
//           this.merge(c1, c2);
//         }
//       }
//       break;
//     case 1: // right
//       // if we're in the last column we can't go right
//       if(cell < w - 1) {
//         c2 = this.at(cell + 1, row);
//         if(!c1.grp.contains(c2)) {
//           c1.edges[1] = false;
//           c2.edges[3] = false;
//           this.merge(c1, c2);
//         }
//       }
//       break;
//     case 2: // down
//       // if we're in the last row we can't go down
//       if(row < h - 1) {
//         c2 = this.at(cell, row + 1);
//         if(!c1.grp.contains(c2)) {
//           c1.edges[2] = false;
//           c2.edges[0] = false;
//           this.merge(c1, c2);
//         }
//       }
//       break;
//     case 3: // left
//       // if we're in the first column we can't go left
//       if(cell > 0) {
//         c2 = this.at(cell - 1, row);
//         if(!c1.grp.contains(c2)) {
//           c1.edges[3] = false;
//           c2.edges[1] = false;
//           this.merge(c1, c2);
//         }
//       }
//       break;
//     }

//     // if all the cells are in the same group we are done.
//     if(c1.grp.length == w * h)
//       done = true;
//   }

//   // because there is exactly one path from any node to any other
//   // node, it does not matter where we chose to enter the maze or
//   // exit the maze. randomly choose a start and finish.
//   this.start = this.pickRow(h);
//   this.finish = this.pickRow(h);
// }

// this.merge = function(c1, c2) {
//     // get the current cell groups.
//     var grp1 = c1.grp;
//     var grp2 = c2.grp;

//     // add each cell in group 2 to group 1
//     var i = grp2.length;
//     while(i--)
//     {
//       // assign the group 1 array to each cell in group 2.
//       grp2[i].grp = grp1;
//       // add each cell in group 2 to the end of the group 1
//       // array.
//       grp1.push(grp2[i]);
//     }

//     // this method is much faster than using the Array concat()
//     // method, though concat() would probably be more readable.
//   }
