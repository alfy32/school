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

    that.which = spec.which;

    that.getCenter = function () {
      return spec.center;
    };

    that.checkClick = function(mouse) {
      var dx = mouse.x - spec.center.x;
      var dy = mouse.y - spec.center.y;
      var radius = spec.width/2;
      that.clicked = dx*dx + dy*dy <= radius*radius;
    };

    that.checkTouch = function(touch) {
      var dx = touch.x - spec.center.x;
      var dy = touch.y - spec.center.y;
      var radius = spec.width/2;
      that.clicked = dx*dx + dy*dy <= radius*radius;
    };

    that.offScreen = function() {
      return spec.center.y - spec.height/2 > canvas.height;
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

  function Text() {
    var that = {};

    that.draw = function(text) {
      context.save();

      context.translate(canvas.width/2, canvas.height/2);

      context.fillStyle = 'yellow';
      context.font = '100px Verdana';
      context.fillText(text, -50, -50);

      context.restore();
    };

    return that;
  }

  //------------------------------------------------------------------
  //
  // Expose an ability to draw an image/texture on the canvas.
  //
  //------------------------------------------------------------------
  function drawImage(spec) {
    context.save();

    context.translate(spec.center.x, spec.center.y);
    context.rotate(spec.rotation);
    context.translate(-spec.center.x, -spec.center.y);

    context.drawImage(
      spec.image,
      spec.center.x - spec.size/2,
      spec.center.y - spec.size/2,
      spec.size, spec.size);

    context.restore();
  }

  return {
    clear: clear,
    resize: resize,
    Texture: Texture,
    Text: Text,
    drawImage: drawImage
  };
}());