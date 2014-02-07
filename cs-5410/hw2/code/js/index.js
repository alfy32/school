/* globals $ */
'use strict';

(function() {

  $('button').on('touchend', function (e) {
    e.stopPropagation();

    var button = $(e.target);

    var size = button.html().split('x');
    var width = size[0] || 5;
    var height = size[1] || 5;

    window.location = 'maze.html' + '#/' + width + '/' + height;
  });

}());
