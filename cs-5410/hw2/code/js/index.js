/* globals $ */
'use strict';

var MazeSolver = (function() {

  $(document).ready(function() {
    $('#new').click(goTo('size.html'));

    $('#scores').click(goTo('scores.html'));

    $('#credits').click(goTo('credits.html'));
  });

  function goTo(page) {
    return function() {
      window.location = page;
    };
  }

}());