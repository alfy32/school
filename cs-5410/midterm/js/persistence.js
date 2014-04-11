/* globals MYGAME */

MYGAME.persistence = (function () {
  'use strict';

  function set(key, value) {
    localStorage[key] = JSON.stringify(value);
  }

  function get(key) {
    return JSON.parse(localStorage[key]);
  }

  function remove(key) {
    localStorage.removeItem(key);
  }

  return {
    set: set,
    get: get,
    remove: remove
  };
}());