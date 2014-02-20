/*global MYGAME */

MYGAME.initialize = function initialize() {
  'use strict';

  var myKeyboard = MYGAME.input.Keyboard();
  var myMouse = MYGAME.input.Mouse();
  var myTouch = MYGAME.input.Touch();

  MYGAME.lastTimeStamp = performance.now();

  //------------------------------------------------------------------
  //
  // This is the Game Loop function!
  //
  //------------------------------------------------------------------
  function gameLoop(time) {
    MYGAME.elapsedTime = time - MYGAME.lastTimeStamp;
    MYGAME.lastTimeStamp = time;

    update();
    render();

    requestAnimationFrame(gameLoop);
  };

  function update() {
    myKeyboard.update(MYGAME.elapsedTime);
    myMouse.update(MYGAME.elapsedTime);
    myTouch.update(MYGAME.elapsedTime);
  }

  function render() {
    MYGAME.graphics.clear();;
  }

  requestAnimationFrame(gameLoop);
};