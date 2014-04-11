/*global MYGAME, particleSystem */

MYGAME.graphics.money = (function() {
  'use strict';

  var explosions = [];

  function update(elapsedTime) {
    for(var i in explosions) {
      explosions[i].update(elapsedTime/1000);
    }
  }

  function render() {
    for(var i in explosions) {
      explosions[i].render();
    }
  }

  function reset() {
    explosions.length = 0;
  }

  function explode(center) {
    MYGAME.audio['audio/cha-ching.wav'].play();

    var particles = particleSystem({
      image : MYGAME.images['img/Dollar-Sign.png'],
      center: {
        x: center.x,
        y: center.y
      },
      speed: {mean: 50, stdev: 25},
      lifetime: {mean: 4, stdev: 1}
    }, MYGAME.graphics);

    for(var i = 0; i < 5; i++)
      particles.create();

    explosions.push(particles);
  }

  return {
    update: update,
    render: render,
    reset: reset,
    explode: explode
  };
}());