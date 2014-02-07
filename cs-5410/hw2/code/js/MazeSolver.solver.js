/* globals MazeSolver */
'use strict';

(function(that){

  var pos = { x: 0, y: 0 };

  that.path = {};

  that.solve = function () {
    that.path = {};

    pos.x = 0;
    pos.y = 0;

    solve(0,0);

    that.path[(that.width-1) + ',' + (that.height-1)] = true;
  };

  function solve(x, y) {
    if(cantMove(x,y)) return false;
    if(solved(x,y)) return true;
    if(alreadyBeenThere(x,y)) return false;

    pos.x = x;
    pos.y = y;
    that.path[x + ',' + y] = true;

    if(solve(x+1,y) || solve(x-1, y) || solve(x,y+1) || solve(x,y-1))
      return true;

    that.path[x + ',' + y] = false;
    return false;
  }

  function cantMove(x,y) {
    if(x < 0 || x >= that.width) return true;
    if(y < 0 || y >= that.height) return true;

    if(x-pos.x === 1 && that.maze[y][x].walls.left) return true;
    if(pos.x-x === 1 && that.maze[y][x+1].walls.left) return true;
    if(y-pos.y === 1 && that.maze[y][x].walls.up) return true;
    if(pos.y-y === 1 && that.maze[y+1][x].walls.up) return true;

    return false;
  }

  function solved(x,y) {
    if(x === that.width-1 && y === that.height-1) return true;
  }

  function alreadyBeenThere(x,y) {
    return that.path[x + ',' + y];
  }

}(MazeSolver));