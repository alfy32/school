/*global MYGAME */

MYGAME.initialize = function initialize() {
  'use strict';

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
  }

  function render() {
    MYGAME.graphics.clear();
    myTexture.draw();
  }

  requestAnimationFrame(gameLoop);
};