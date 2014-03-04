/*global MYGAME, $, KeyEvent, requestAnimationFrame */

MYGAME.initialize = function initialize() {
  'use strict';

  var myMouse = MYGAME.input.Mouse();
  var myTouch = MYGAME.input.Touch();
  var myKeyboard = MYGAME.input.Keyboard();

  MYGAME.lastTimeStamp = 0;

  window.onresize = MYGAME.graphics.resize;

  $('#done').click(function () {
    window.location = 'index.html';
  });

  $('#again').click(function () {
    window.location.reload();
  });

  myKeyboard.registerCommand(KeyEvent.KEY_ESCAPE, function () {
    window.history.back();
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

  var state = 'countDown';
  var states = {
    win: win,
    lose: lose,
    countDown: countDown,
    play: play
  };

  var totalScore = 0;
  var countDownText = '';

  $('.money').text(0);
  var level = 0;
  var countDownMS = 0;

  function win() {
    var div = $('.game-over-div');

    div.find('.message').text('You Won!');
    div.find('.level').text(level);
    div.find('.score').text(totalScore);

    div.removeAttr('hidden');

    MYGAME.persistence.addScore(level, totalScore);

    return true;
  }

  function lose() {
    var div = $('.game-over-div');

   div.find('.message').text('Game Over Man!');
    div.find('.level').text(level);
    div.find('.score').text(totalScore);

    div.removeAttr('hidden');

    MYGAME.persistence.addScore(level, totalScore);

    return true;
  }

  function countDown(time) {
    countDownMS += time;

    countDownText = Math.floor(countDownMS/1000);

    if(countDownText === 0) {
      countDownText = 'Level ' + (level + 1);
    } else if(countDownText > 3) {
      countDownMS = 0;
      state = 'play';
      level++;

      if(level === 1) MYGAME.coins.initLevel(10,3,8,1,5);
      else if(level === 2) MYGAME.coins.initLevel(15,4,12,1,8);
      else if(level === 3) MYGAME.coins.initLevel(20,5,15,1,10);
    }
  }

  function play(time) {
    var money = MYGAME.coins.update(time);
    $('.money').text(money);

    if(MYGAME.coins.gone()) {
      if(money < 100) {
        state = 'lose';
      }
      else {

        if(level === 3)
          state = 'win';
        else
          state = 'countDown';

      }

      totalScore += money;
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

    myKeyboard.update(elapsedTime);
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

    var countDownElement = $('.countdown');

    if(state === 'play') {
      countDownElement.attr('hidden', 'true');
      MYGAME.coins.render();
    }
    else if(state === 'countDown') {
      countDownElement.text(countDownText);
      countDownElement.removeAttr('hidden');
    }
  }

  MYGAME.graphics.resize();
  requestAnimationFrame(gameLoop);
};