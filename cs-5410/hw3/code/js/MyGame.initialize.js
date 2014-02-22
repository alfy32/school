/*global MYGAME, $, requestAnimationFrame */

MYGAME.initialize = function initialize() {
  'use strict';

  var myMouse = MYGAME.input.Mouse();
  var myTouch = MYGAME.input.Touch();

  MYGAME.lastTimeStamp = 0;
  var playing = false;
  var level = 0;
  var countDown = 0;
  var timer = 0;
  var over = false;

  window.onresize = MYGAME.graphics.resize;

  MYGAME.coins.registerEvents = function (coin) {
    myMouse.registerCommand('mousedown', function (e) {
      e.preventDefault();
      coin.checkClick({
        x: e.clientX,
        y: e.clientY
      });
    });

    myTouch.registerCommand('touchstart', function (e) {
      e.preventDefault();
      for(var i in e.changedTouches) {
        var touch = e.changedTouches[i];

        if(touch.clientX) {
          coin.checkTouch({
            x: touch.clientX,
            y: touch.clientY
          });
        }
      }
    });
  };

  var countDownText = MYGAME.graphics.Text();

  //------------------------------------------------------------------
  //
  // This is the Game Loop function!
  //
  //------------------------------------------------------------------
  var MAX_FRAME_RATE = 1000/30;

  function gameLoop(time) {
    MYGAME.elapsedTime = time - MYGAME.lastTimeStamp;
    MYGAME.lastTimeStamp = time;
    timer += MYGAME.elapsedTime;

    update(MYGAME.elapsedTime);
    render();

    setTimeout(function() {
      requestAnimationFrame(gameLoop);
    }, MAX_FRAME_RATE - MYGAME.elapsedTime);
  }

  function update(elapsedTime) {
    myMouse.update(elapsedTime);
    myTouch.update(elapsedTime);

    var money = 0;

    if(over) {
      countDown = 'Win';
    } else {
      if(playing) {
        money = MYGAME.coins.update(elapsedTime);

        if(money >= 100) {
          playing = false;
          timer = 0;
        }
      }
      else {
        countDown = Math.floor(timer/1000) + 1;

        if(countDown > 3) {
          playing = true;
          level++;

          if(level === 1) MYGAME.coins.initLevel(10,3,8,1,5);
          else if(level === 2) MYGAME.coins.initLevel(15,4,12,1,8);
          else if(level === 3) MYGAME.coins.initLevel(20,5,15,1,10);
          else {
            over = true;
            playing = false;
          }
        }
      }

      $('.money').text(money);
    }
  }

  function render() {
    MYGAME.graphics.clear();

    if(playing) MYGAME.coins.render();

    if(!playing) countDownText.draw(countDown);
  }

  MYGAME.graphics.resize();
  requestAnimationFrame(gameLoop);
};