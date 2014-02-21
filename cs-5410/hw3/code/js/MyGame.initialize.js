/*global MYGAME, $, performance, requestAnimationFrame */

MYGAME.initialize = function initialize() {
  'use strict';

  var myMouse = MYGAME.input.Mouse();

  MYGAME.lastTimeStamp = performance.now();
  var money = 0;

  window.onresize = MYGAME.graphics.resize;

  var objects = [];
  var addObjectRate = 2000;
  var MAX_OBJECTS = 5;
  var timeSinceLastAddObject = 0;

  function addObject() {
    var randomObject = Math.floor(Math.random() * 4);

    console.log(randomObject);

    if(randomObject === 0) randomObject = 'US';
    else if(randomObject === 1) randomObject = 'roman';
    else if(randomObject === 2) randomObject = 'canadian';
    else if(randomObject === 3) randomObject = 'clock';

    randomObject = MYGAME.graphics.objects[randomObject]();

    myMouse.registerCommand('mousedown', function (e){
      if(randomObject.clicked({
        x: e.clientX, 
        y: e.clientY
      })) {
        console.log(randomObject, e.clientX, e.clientY);
        explodeObject(randomObject);
      }
    });

    objects.push(randomObject);
  }

  function explodeObject(obj) {
    console.log('clicked');
    removeObject(obj);
  }

  function removeObject(obj) {
    console.log('remove')
    var index = objects.indexOf(obj);
    objects.splice(index, 1);
  }

  //------------------------------------------------------------------
  //
  // This is the Game Loop function!
  //
  //------------------------------------------------------------------
  var MAX_FRAME_RATE = 1000/30;

  function gameLoop(time) {
    MYGAME.elapsedTime = time - MYGAME.lastTimeStamp;
    MYGAME.lastTimeStamp = time;

    update(MYGAME.elapsedTime);
    render();

    setTimeout(function() {
      requestAnimationFrame(gameLoop);
    }, MAX_FRAME_RATE - MYGAME.elapsedTime);
  }

  function update(elapsedTime) {
    myMouse.update(elapsedTime);

    if(objects.length < MAX_OBJECTS)
      timeSinceLastAddObject += elapsedTime;

    // console.log(timeSinceLastAddObject, addObjectRate);

    if(timeSinceLastAddObject > addObjectRate) {
      timeSinceLastAddObject -= addObjectRate;
      addObject();
    }

    for(var i in objects) {
      if(objects[i].offScreen()) removeObject(objects[i]);
      else {
        objects[i].moveDown(elapsedTime);
        objects[i].draw();
      }
    }

    $('.money').text(money);
  }

  function render() {
    MYGAME.graphics.clear();

    for(var i in objects) {
      objects[i].draw();
    }
  }

  MYGAME.graphics.resize();
  requestAnimationFrame(gameLoop);
};