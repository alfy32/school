/* globals console, performance, requestAnimationFrame */
'use strict';

var MazeSolver = (function(){

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

  var width, height;

  function generate(w, h) {
    width = w;
    height = h;
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

  var CELL_WIDTH = 25;
  var CELL_HEIGHT = CELL_WIDTH;

  function draw(maze) {
    canvas.width = canvas.height = CELL_WIDTH * maze.length;
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.beginPath();
    context.rect(1, 1, canvas.width-2, canvas.height-2);
    context.stroke();

    for(var row in maze) {
      for(var col in maze[row]) {
        drawCell(+row, +col, maze[row][col]);
      }
    }

    drawPlayer();
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

  var player = {
    x: 0,
    y: 0,
    width: CELL_WIDTH - 12
  };

  function drawPlayer() {
    var x = (player.x * CELL_WIDTH) + CELL_WIDTH/2;
    var y = player.y * CELL_HEIGHT + CELL_HEIGHT/2;

    var fillStyle = context.fillStyle;

    context.beginPath();
    context.arc(x, y, player.width/2, 0*Math.PI, 2*Math.PI);
    context.fillStyle = 'black';
    context.lineWidth = 5;
    context.stroke();
    context.fillStyle = 'gray';
    context.fill();

    context.fillStyle = fillStyle;
  }

  var maze;

  function generateClick() {
    var size = +document.getElementById('width').value;

    maze = generate(size, size);
    draw(maze);

    gameLoop(performance.now());
  }

  function keyDown(e) {
    if(e.which === 13) generateClick();
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

  var lastTime = performance.now();

  function gameLoop(time) {
    var diffTime = time - lastTime;

    update(diffTime);
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
    draw(maze);
  }

   function canMove(move) {
    var x = player.x;
    var y = player.y;

    if(move == 'UP') {

      if(y === 0) return false;
      if(maze[y][x].walls.up) return false;

    } else if(move == 'DOWN') {

      if(y === height-1) return false;
      if(maze[y+1][x].walls.up) return false;

    } else if(move == 'LEFT') {

      if(x === 0) return false;
      if(maze[y][x].walls.left) return false;

    } else if(move == 'RIGHT') {

      if(x === width-1) return false;
      if(maze[y][x+1].walls.left) return false;
    }

    return true;
  }

  function makeMove(move) {
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

  return {
    generateClick: generateClick,
    keyDown: keyDown
  };

}());