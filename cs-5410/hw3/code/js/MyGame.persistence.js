
var MYGAME = MYGAME || {};

MYGAME.persistence = (function () {
  'use strict';

  function add(key, value) {
    localStorage[key] = JSON.stringify(value);
  }

  function remove(key) {
    localStorage.removeItem(key);
  }

  function get(key) {
    var value = localStorage[key];

    if(typeof value === 'string')
      value = JSON.parse(value);

    return value;
  }

  return {
    add : add,
    remove : remove,
    get : get
  };
}());