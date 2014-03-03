/*global MYGAME, $, requestAnimationFrame */

MYGAME.initialize = function initialize() {
  'use strict';

  var myMouse = MYGAME.input.Mouse();
  var myTouch = MYGAME.input.Touch();

  MYGAME.lastTimeStamp = 0;

  window.onresize = MYGAME.graphics.resize;

  $('#done').click(function () {
    window.location = 'index.html';
  });

  $('#again').click(function () {
    window.location.reload();
  });

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

  var state = 'countDown';
  var states = {
    win: win,
    lose: lose,
    countDown: countDown,
    play: play
  };

  $('.money').text(0);
  var level = 0;
  var countDownMS = 0;

  function win() {



    $('.game-over-div').removeAttr('hidden');

    return true;
  }

  function lose() {


    $('.game-over-div').removeAttr('hidden');

    return true;
  }

  function countDown(time) {
    countDownMS += time;

    var count = Math.floor(countDownMS/1000) + 1;

    if(count > 3) {
      state = 'play';
      level++;

      if(level === 1) MYGAME.coins.initLevel(10,3,8,1,5);
      else if(level === 2) MYGAME.coins.initLevel(15,4,12,1,8);
      else if(level === 3) MYGAME.coins.initLevel(20,5,15,1,10);
    }
    if(level > 3) {
      state = 'win';
    }
  }

  function play(time) {
    var money = MYGAME.coins.update(time);
    $('.money').text(money);

    if(money > 100) {
      state = 'countDown';
    } else if(MYGAME.coins.gone()) {
      state = 'lose';
    }
  }

  //------------------------------------------------------------------
  //
  // This is the Game Loop function!
  //
  //------------------------------------------------------------------
  var MAX_FRAME_RATE = 1000/30;

  function gameLoop(time) {
    var elapsedTime = time - MYGAME.lastTimeStamp;
    MYGAME.lastTimeStamp = time;

    myMouse.update(elapsedTime);
    myTouch.update(elapsedTime);

    var quit = states[state](elapsedTime);
    if(quit) return;

    render();

    setTimeout(function() {
      requestAnimationFrame(gameLoop);
    }, MAX_FRAME_RATE - MYGAME.elapsedTime);
  }

  function render() {
    MYGAME.graphics.clear();

    if(state === 'play') MYGAME.coins.render();
    else if(state === 'countDown') countDownText.draw(Math.floor(countDownMS/1000) + 1);
  }

  MYGAME.graphics.resize();
  requestAnimationFrame(gameLoop);
};