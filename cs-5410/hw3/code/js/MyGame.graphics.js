/*global MYGAME */

MYGAME.graphics = (function() {
  'use strict';

  var canvas = document.getElementById('canvas-main'),
  context = canvas.getContext('2d');

  function clear() {
    context.save();
    context.setTransform(1, 0, 0, 1, 0, 0);
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.restore();
  }

  function resize() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
  }

  /*
  * Required Spec info:
  *  - image
  *  - center
  *  - width
  *  - rotation
  *  - moveRate
  *  - rotateRate
  */
  function Texture(spec) {
    var that = {};

    that.clicked = function(x, y) {
      return x > spec.center.x - spec.width/2 &&
             x < spec.center.x + spec.width/2 &&
             y > spec.center.y - spec.height/2 &&
             y < spec.center.y + spec.height/2;
    };

    that.offScreen = function() {
      return spec.center.y + spec.height/2 > canvas.height;
    };

    that.rotateRight = function(elapsedTime) {
      spec.rotation += spec.rotateRate * (elapsedTime / 1000);
    };

    that.rotateLeft = function(elapsedTime) {
      spec.rotation -= spec.rotateRate * (elapsedTime / 1000);
    };

    that.moveLeft = function(elapsedTime) {
      spec.center.x -= spec.moveRate * (elapsedTime / 1000);
    };

    that.moveRight = function(elapsedTime) {
      spec.center.x += spec.moveRate * (elapsedTime / 1000);
    };

    that.moveUp = function(elapsedTime) {
      spec.center.y -= spec.moveRate * (elapsedTime / 1000);
    };

    that.moveDown = function(elapsedTime) {
      spec.center.y += spec.moveRate * (elapsedTime / 1000);
    };

    that.moveTo = function(center) {
      spec.center = center;
    };

    that.draw = function() {
      context.save();

      context.translate(spec.center.x, spec.center.y);
      context.rotate(spec.rotation);
      context.translate(-spec.center.x, -spec.center.y);

      context.drawImage(
        spec.image,
        spec.center.x - spec.width/2,
        spec.center.y - spec.height/2,
        spec.width, spec.height);

      context.restore();
    };

    return that;
  }

  function getRandomLocation(width, height) {
    width += 10; // add padding on the sides.
    return {
      x: Math.random() * (window.innerWidth) ,
      y: 10//-height/2
    };
  }

  function getSpec(image, height, width) {
    return {
      image: image,
      center: getRandomLocation(width,height),
      width: width,
      height: height,
      rotation: 0,
      moveRate: 20,
      rotateRate: 0
    };
  }

  function canadian() {
    var image = new Image();
    image.src = 'img/Coin-Canadian-Dollar.png';
    var width = 30;
    var height = 15;

    return MYGAME.graphics.Texture(getSpec(image, height, width));
  }

  function roman() {
    var image = new Image();
    image.src = 'img/Coin-Roman.png';
    var width = 30;
    var height = 15;

    return MYGAME.graphics.Texture(getSpec(image, height, width));
  }

  function US() {
    var image = new Image();
    image.src = 'img/Coin-US-Dollary.png';
    var width = 30;
    var height = 15;

    return MYGAME.graphics.Texture(getSpec(image, height, width));
  }

  function clock() {
    var image = new Image();
    image.src = 'img/Clock.png';
    var width = 30;
    var height = 15;

    return MYGAME.graphics.Texture(getSpec(image, height, width));
  }

  var objects = {
    canadian: canadian,
    roman: roman,
    US: US,
    clock: clock
  };

  return {
    clear: clear,
    resize: resize,
    Texture: Texture,
    objects: objects
  };
}());