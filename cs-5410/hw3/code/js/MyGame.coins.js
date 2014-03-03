/*global MYGAME, Random, particleSystem */

MYGAME.coins = (function() {
  'use strict';

  var canvas = document.getElementById('canvas-main');
  var PADDING = 60;
  var PIG_SPACE = 100;

  var particleSystems = [];

  var coinStock = [];
  var coins = [];
  var settings = {
    clockValue: 0,
    score: 0,
    countDown: -1,
    dropRate: 200,
    moveRate: 300,
    moveDist: 50,
    rotateRate: 3,
    rotateRateDist: 1,
    rotation: 50
  };

  var images = {
    canadianCoin: new Image(),
    romanCoin: new Image(),
    usCoin: new Image(),
    clock: new Image(),
    dollarSign: new Image()
  };

  images.canadianCoin.src = 'img/Coin-Canadian-Dollar.png';
  images.romanCoin.src = 'img/Coin-Roman.png';
  images.usCoin.src = 'img/Coin-US-Dollary.png';
  images.clock.src = 'img/Clock.png';
  images.dollarSign.src = 'img/Dollar-Sign.png';

  function update(time) {
    var index;

    if(timeToAdd(time)) addCoin();

    for(index in coins) {
      checkStatus(coins[index], +index);
    }

    for(index in coins) {
      coins[index].moveDown(time);
      coins[index].rotateRight(time);
    }

    // for(index in particleSystems) {
    //   var system = particleSystems[index];

    //   if(system.created > 5) {
    //     delete particleSystems[index];
    //   } else {
    //     system.update(time);
    //     system.render();
    //     system.create();
    //   }
    // }

    return settings.score;
  }

  function render() {
    for(var i in coins) {
      coins[i].draw();
    }
  }

  function timeToAdd(time) {
    if(!coinStock.length) return false;

    settings.countDown -= time;
    if(settings.countDown < 0) {
      settings.countDown = Random.nextGaussian(settings.dropRate, 20);
      return true;
    }

    return false;
  }

  function addCoin() {
    var coin = coinStock.pop();

    coin.clicked = false;
    coin.moveTo({
      x: Random.nextRange(PADDING, canvas.width - PADDING - PIG_SPACE),
      y: -50
    });

    coins.push(coin);
  }

  function checkStatus(coin, index) {
    if(coin.offScreen()) {
      coins.splice(index, 1);
    }
    if(coin.clicked) {
      coins.splice(index, 1);
      addParticles(coin.center);

      if(coin.which === 'US') settings.score += 10;
      else if(coin.which === 'ROMAN') settings.score += 50;
      else if(coin.which === 'CANADIAN') settings.score = 0;
      else if(coin.which === 'CLOCK') {
        for(var i = 0; i < settings.clockValue; ++i) {
          createCoin(usCoin);
        }
        shuffle(coinStock);
      }
    }
  }

  function addParticles(center) {
    var particles = particleSystem( {
        image : images.dollarSign,
        center: center,
        speed: {mean: 0.1, stdev: 0.02},
        lifetime: {mean: 2000, stdev: 100}
      },
      MYGAME.graphics
    );

    particles.created = 0;

    particleSystems.push(particles);
  }

  function createCoin(coinFunc) {
    var coin = coinFunc();
    MYGAME.coins.registerEvents(coin);
    coinStock.push(coin);
  }

  function clearCoins() {
    coins.length = 0;
    coinStock.length = 0;
  }

  function gone() {
    return coinStock.length === 0 && coins.length === 0;
  }

  function initLevel(us, roman, canadian, clocks, clockValue) {
    var i;

    clearCoins();

    settings.clockValue = clockValue;
    settings.score = 0;

    for(i = 0; i < us; ++i) {
      createCoin(usCoin);
    }
    for(i = 0; i < roman; ++i) {
      createCoin(romanCoin);
    }
    for(i = 0; i < canadian; ++i) {
      createCoin(canadianCoin);
    }
    for(i = 0; i < clocks; ++i) {
      createCoin(clock);
    }

    shuffle(coinStock);
  }

  function shuffle(arr) {
    for(var i = 0; i < arr.length; ++i) {
      var random = Random.nextRange(0, arr.length);
      swap(arr, random, i);
    }
  }

  function swap(arr, indexA, indexB) {
    var temp = arr[indexA];
    arr[indexA] = arr[indexB];
    arr[indexB] = temp;
  }

  function canadianCoin() {
    return MYGAME.graphics.Texture({
      image: images.canadianCoin,
      center: {
        x: 0,
        y: 0
      },
      width: 100,
      height: 100,
      rotation: Random.nextGaussian(settings.rotation,3),
      moveRate: Random.nextGaussian(settings.moveRate,settings.moveDist),
      rotateRate: Random.nextGaussian(settings.rotateRate,settings.rotateRateDist),
      which: 'CANADIAN'
    });
  }

  function romanCoin() {
    return MYGAME.graphics.Texture({
      image: images.romanCoin,
      center: {
        x: 0,
        y: 0
      },
      width: 30,
      height: 30,
      rotation: Random.nextGaussian(settings.rotation,3),
      moveRate: Random.nextGaussian(settings.moveRate,10),
      rotateRate: Random.nextGaussian(settings.rotateRate,settings.rotateRateDist),
      which: 'ROMAN'
    });
  }

  function usCoin() {
    return MYGAME.graphics.Texture({
      image: images.usCoin,
      center: {
        x: 0,
        y: 0
      },
      width: 50,
      height: 50,
      rotation: Random.nextGaussian(settings.rotation,3),
      moveRate: Random.nextGaussian(settings.moveRate,10),
      rotateRate: Random.nextGaussian(settings.rotateRate,settings.rotateRateDist),
      which: 'US'
    });
  }

  function clock() {
    return MYGAME.graphics.Texture({
      image: images.clock,
      center: {
        x: 0,
        y: 0
      },
      width: 50,
      height: 50,
      rotation: Random.nextGaussian(settings.rotation,3),
      moveRate: Random.nextGaussian(settings.moveRate,10),
      rotateRate: Random.nextGaussian(settings.rotateRate,settings.rotateRateDist),
      which: 'CLOCK'
    });
  }

  return {
    initLevel: initLevel,
    clearCoins: clearCoins,
    update: update,
    render: render,
    gone: gone
  };
}());