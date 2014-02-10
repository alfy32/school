/* globals MazeSolver, $, performance, requestAnimationFrame */
'use strict';

// for safari
var performance = performance || Date;
var requestAnimationFrame = requestAnimationFrame || function(cb) { setTimeout(function(){cb(Date.now());},0); };

(function(that){
  var lastTime;
  var won = false;
  var timeLabel;

  that.start = function () {
    that.time = 0;
    lastTime = performance.now();
    that.solve();
    requestAnimationFrame(gameLoop);
  };

  function gameLoop(time) {
    var deltaTime = time - lastTime;
    if(deltaTime > 1000 * 1000) deltaTime = 0;

    update(deltaTime);
    render();

    lastTime = time;

    requestAnimationFrame(gameLoop);
  }

  function update(deltaTime) {
    that.time += deltaTime;

    while(that.eventQueue.length) {
      var theEvent = that.eventQueue.pop();

      if(
        theEvent === 'UP' || theEvent === 'DOWN' ||
        theEvent === 'LEFT' || theEvent === 'RIGHT'
      ) {
        if(that.canMove(theEvent)) that.makeMove(theEvent);
      } else {
        that.toggleControl(theEvent);
      }

      if(win()) won = true;
    }
  }

  function render() {
    if(!won) {
      var ms = that.time % 1000;
      var seconds = Math.floor(that.time/1000 % 60);
      var minutes = Math.floor(that.time/(1000*60) % 60);

      timeLabel = pad2Digits(minutes) + ':' + pad2Digits(seconds);

      $('.maze-timer').text(timeLabel);
    } else {
      $('.maze-timer').text('Finished in ' + timeLabel);
    }

    that.draw('maze-canvas');
  }

  function pad2Digits(value) {
    return (+value < 10) ? '0' + value : value;
  }

  function win() {
    return that.player.x === that.width-1 && that.player.y === that.height-1;
  }

}(MazeSolver));