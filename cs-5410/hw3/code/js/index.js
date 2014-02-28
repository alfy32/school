/* globals $ */

(function() {
  'use strict';

  $('#new').click(goToUrl('coinDrop.html'));
  $('#scores').click(goToUrl('scores.html'));
  $('#credits').click(goToUrl('credits.html'));

  function goToUrl(url) {
    return function() {
      window.location = url;
    };
  }

}());