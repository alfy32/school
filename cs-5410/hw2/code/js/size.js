/* globals $ */
'use strict';

(function() {

  // var sizes = [5,10,15,20,25];

  // function addButtons() {
  //   for(var i in sizes) {
  //     var height, width, ratio;

  //     if(window.innerWidth > window.innerHeight) {
  //       height = +sizes[i];
  //       ratio = window.innerWidth/(window.innerHeight-200);
  //       width = Math.floor(ratio * height);
  //       if(width == height*2) width++;
  //     } else {
  //       width = +sizes[i];
  //       height = width;
  //     }

  //     var button = $('<button>');
  //     button.attr('class', 'main-buttons');
  //     button.text(width + 'x' + height);

  //     bindButtonTouchClick(button);

  //     $('.main-inner').append(button);
  //   }
  // }

  function bindButtonTouchClick() {
      $('button').on('touchend click', function (e) {
      e.stopPropagation();

      var button = $(e.target);

      var size = button.html().split('x');
      var width = size[0] || 5;
      var height = size[1] || 5;

      window.location = 'maze.html' + '#/' + width + '/' + height;
    });
  }

  $(document).ready(bindButtonTouchClick);

}());
