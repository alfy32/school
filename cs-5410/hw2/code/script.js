/* global MazeSolver, Maze, requestAnimationFrame */
'use strict';

function keyDown(e) {
  if(e.which === 13)
    generateClick();
}

function generateClick() {
  width = +document.getElementById('width').value;

  maze = Maze.create(width, width);
  maze.init();
  MazeSolver.generate(maze);

  init();
  gameLoop(Date.now());
}

var eventQueue = [];

window.addEventListener('keydown', function (e) {
  e.cancelBubble = true;

  if(e.which === 37) {// Left
    eventQueue.push('LEFT');
  } else if(e.which === 38) {// UP
    eventQueue.push('UP');
  } else if(e.which === 39) {// Right
    eventQueue.push('RIGHT');
  } else if(e.which === 40) {// Down
    eventQueue.push('DOWN');
  }

  switch(String.fromCharCode(e.which)) {
    case 'A':
      eventQueue.push('LEFT');
      break;
    case 'S':
      eventQueue.push('DOWN');
      break;
    case 'D':
      eventQueue.push('RIGHT');
      break;
    case 'W':
      eventQueue.push('UP');
      break;
  }

  return true;

  // console.log(String.fromCharCode(e.which), e.which);
});

var maze;
var width;
var lastTime = Date.now();

function init() {
  MazeSolver.render.init(width, width, 'black');
}

function gameLoop(time) {
  update(time - lastTime);
  render();
  requestAnimationFrame(gameLoop);
}

function update(deltaTime) {
  while(eventQueue.length) {
    var move = eventQueue.pop();

    if(canMove(move)) makeMove(move);
  }
}

function render() {
  MazeSolver.render.drawMaze(maze);
}

function canMove(move) {
  var player = MazeSolver.render.player;
  var x = player.x;
  var y = player.y;

  if(move == 'UP') {

    if(y === 0) return false;
    if(maze.cells[y][x].edges.UP) return false;

  } else if(move == 'DOWN') {

    if(y === maze.height-1) return false;
    if(maze.cells[y+1][x].edges.UP) return false;

  } else if(move == 'LEFT') {

    if(x === 0) return false;
    if(maze.cells[y][x].edges.LEFT) return false;

  } else if(move == 'RIGHT') {

    if(x === maze.width-1) return false;
    if(maze.cells[y][x+1].edges.LEFT) return false;

  }

  return true;
}

function makeMove(move) {
  var player = MazeSolver.render.player;

  switch(move) {
    case 'UP':
      player.y -= 1;
      break;
    case 'DOWN':
      player.y += 1;
      break;
    case 'LEFT':
      player.x -= 1;
      break;
    case 'RIGHT':
      player.x += 1;
      break;
  }
}