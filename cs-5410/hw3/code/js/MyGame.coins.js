/*global MYGAME, Random */

MYGAME.coins = (function() {
  'use strict';

  var canvas = document.getElementById('canvas-main');
  var PADDING = 60;
  var PIG_SPACE = 100;

  var coins = [];
  var availableCoins = [];
  var settings = {
    clockValue: 0,
    score: 0,
    countDown: -1,
    dropRate: 200,
    moveRate: 400,
    rotateRate: 3,
    rotateRateDist: 1,
    rotation: 50
  };

  var images = {
    canadianCoin: new Image(),
    romanCoin: new Image(),
    usCoin: new Image(),
    clock: new Image()
  };

  images.canadianCoin.src = 'img/Coin-Canadian-Dollar.png';
  images.romanCoin.src = 'img/Coin-Roman.png';
  images.usCoin.src = 'img/Coin-US-Dollary.png';
  images.clock.src = 'img/Clock.png';

  function update(time) {
    if(timeToAdd(time)) addCoin();

    for(var i in coins) {
      if(coins[i].onScreen) {
        checkStatus(coins[i], +i);
        coins[i].moveDown(time);
        coins[i].rotateRight(time);
      }
    }

    return settings.score;
  }

  function render() {
    for(var i in coins) {
      if(coins[i].onScreen) {
        coins[i].draw();
      }
    }
  }

  function timeToAdd(time) {
    if(!availableCoins.length) return false;

    settings.countDown -= time;
    if(settings.countDown < 0) {
      settings.countDown = Random.nextGaussian(settings.dropRate, 20);
      return true;
    }

    return false;
  }

  function addCoin() {
    var rand = Random.nextRange(0, availableCoins.length);
    var index = availableCoins.splice(rand, 1)[0];
    coins[index].onScreen = true;
    coins[index].clicked = false;
    coins[index].moveTo({
      x: Random.nextRange(PADDING, canvas.width - PADDING - PIG_SPACE),
      y: -50
    });
  }

  function checkStatus(coin, id) {
    if(coin.clicked) {
      availableCoins.push(id);
      coin.onScreen = false;
      coin.clicked = false;

      if(coin.which === 'US') settings.score += 10;
      else if(coin.which === 'ROMAN') settings.score += 50;
      else if(coin.which === 'CANADIAN') settings.score = 0;
      else {
        for(var i = 0; i < settings.clockValue; ++i) {
          createCoin(usCoin);
        }
      }
    }
    else if(coin.offScreen()) {
      availableCoins.push(id);
      coin.onScreen = false;
    }
  }

  function createCoin(coinFunc) {
    var coin = coinFunc();
    MYGAME.coins.registerEvents(coin);
    availableCoins.push(coins.length);
    coins.push(coin);
  }

  function clearCoins() {
    coins.length = 0;
    availableCoins.length = 0;
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
    shuffle(coins);
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
      moveRate: Random.nextGaussian(settings.moveRate,10),
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
    render: render
  };
}());