/* globals MazeSolver, console */
'use strict';

(function(that){

  var row = 0, col = 0;

  that.solve = function (theMaze) {
    that.maze = theMaze;

    if(atExit()) return true;
    else return goUp() || goDown() || goLeft() || goRight();
  };

  function atExit() {
    return row == 19 && col == 19;
  }

  function inMaze(x, y) {
    console.log(x, y);
    return x >= 0 && x < that.width && y >= 0 && y < that.height;
  }

  function markPath(x,y) {
    that.maze[y][x].path = true;
  }

  function unMarkPath(x,y) {
    that.maze[y][x].path = false;
  }

  function visitSquare(x, y) {
    that.maze[y][x].visited = true;
  }

  function visited(x, y) {
    return that.maze[y][x].visited;
  }

  function goUp() {
    var x = row;
    var y = col-1;
    console.log('goUp', x, y);

    if(!inMaze(x,y)) return false;
    if(visited(x,y)) return false;
    visitSquare(x,y);

    console.log('moving');

    col--;

    markPath(x,y);

    if(atExit(x,y)) return true;
    else if(goUp() || goDown() || goLeft() || goRight()) return true;

    col++;

    unMarkPath(x, y);
  }

  function goDown() {
    var x = row;
    var y = col+1;
    console.log('goDown', x, y);

    if(!inMaze(x,y)) return false;
    if(visited(x,y)) return false;
    visitSquare(x,y);

    console.log('moving');

    col++;

    markPath(x,y);

    if(atExit(x,y)) return true;
    else if(goUp() || goDown() || goLeft() || goRight()) return true;

    col--;

    unMarkPath(x, y);
  }

  function goLeft() {
    var x = row-1;
    var y = col;
    console.log('goLeft', x, y);

    if(!inMaze(x,y)) return false;
    if(visited(x,y)) return false;
    visitSquare(x,y);

    console.log('moving');

    row--;

    markPath(x,y);

    if(atExit(x,y)) return true;
    else if(goUp() || goDown() || goLeft() || goRight()) return true;

    row++;

    unMarkPath(x, y);
  }

  function goRight() {
    var x = row+1;
    var y = col;
    console.log('goRight', x, y);

    if(!inMaze(x,y)) return false;
    if(visited(x,y)) return false;
    visitSquare(x,y);

    console.log('moving');

    row++;

    markPath(x,y);

    if(atExit(x,y)) return true;
    else if(goUp() || goDown() || goLeft() || goRight()) return true;

    row--;

    unMarkPath(x, y);
  }

}(MazeSolver));