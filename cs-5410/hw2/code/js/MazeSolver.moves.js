/* globals MazeSolver */
'use strict';

(function(that){

  that.addEvent = function (newEvent) {
    that.eventQueue.push(newEvent);
  };

  that.canMove = function (move) {
    var x = that.player.x;
    var y = that.player.y;

    if(move == 'UP') {

      if(y === 0) return false;
      if(that.maze[y][x].walls.up) return false;

    } else if(move == 'DOWN') {

      if(y === that.height-1) return false;
      if(that.maze[y+1][x].walls.up) return false;

    } else if(move == 'LEFT') {

      if(x === 0) return false;
      if(that.maze[y][x].walls.left) return false;

    } else if(move == 'RIGHT') {

      if(x === that.width-1) return false;
      if(that.maze[y][x+1].walls.left) return false;
    }

    return true;
  };

  that.makeMove = function (move) {
    switch(move) {
      case 'UP':
        that.player.y -= 1;
        break;
      case 'DOWN':
        that.player.y += 1;
        break;
      case 'LEFT':
        that.player.x -= 1;
        break;
      case 'RIGHT':
        that.player.x += 1;
        break;
    }
  };

  that.toggleControl = function(control) {
    if(control == 'HINT')   that.controls.HINT   = !that.controls.HINT;
    if(control == 'BREAD')  that.controls.BREAD  = !that.controls.BREAD;
    if(control == 'PATH')   that.controls.PATH   = !that.controls.PATH;
    if(control == 'SCORE')  that.controls.SCORE  = !that.controls.SCORE;
  };

}(MazeSolver));