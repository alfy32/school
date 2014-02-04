
var Game1 = {
  lastTime: 0,
  timers: [],

  initialize: function () {
    Game1.lastTime = performance.now();

    Game1.timers.length = 0; // clear timers

    Game1.bindTimerFormSubmit();

    requestAnimationFrame(Game1.gameLoop);
  },

  update: function (deltaTime) {
    for(var i in Game1.timers) {
      var timer = Game1.timers[i];

      timer.timePassed += deltaTime;

      if(timer.timePassed > timer.interval) {
        timer.render = true;
        timer.timePassed -= timer.interval;
        timer.times--;

        if(timer.times < 0) {
          delete Game1.timers[i];
        }
      }
    }
  },

  render: function () {
    for(var i in Game1.timers) {
      var timer = Game1.timers[i];

      if(timer.render) {
        timer.render = false;
        Game1.fireTimer(timer.name, timer.times);
      }
    }
  },

  gameLoop: function (currentTime){
    var deltaTime = currentTime - Game1.lastTime;

    Game1.update(deltaTime);
    Game1.render();

    Game1.lastTime = currentTime;
    requestAnimationFrame(Game1.gameLoop);
  },

  bindTimerFormSubmit: function() {
    $('#add-timer-form').submit(function (e) {
      e.preventDefault();

      var name = $('#name').val();
      var interval = $('#interval').val();
      var times = $('#times').val();

      Game1.addTimer(name, interval, times);

      e.target.reset();

      $('#name').focus();

      return false;
    });
  },

  addTimer: function(name, interval, times) {
    Game1.timers.push({
      name: name,
      interval: interval,
      times: times,
      render: false,
      timePassed: 0
    });
  },

  fireTimer: function (name, remaining) {
    var outputDiv = $('.timer-display');

    var div = $('<div>');

    div.text('Timer: ' + name + ' (' + remaining + ' remaining)');

    outputDiv.append(div);

    outputDiv.scrollTop(outputDiv.scrollTop()+div.height());
  }
};

Game1.initialize();