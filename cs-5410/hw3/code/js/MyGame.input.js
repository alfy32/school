/*global MYGAME */

MYGAME.input = (function() {
  'use strict';

  function Keyboard() {
    var that = {
      keys : {},
      handlers : {}
    };

    function keyPress(e) {
      that.keys[e.keyCode] = e.timeStamp;
    }

    function keyRelease(e) {
      delete that.keys[e.keyCode];
    }

    that.registerCommand = function(key, handler) {
      if(that.handlers.hasOwnProperty(key) === false)
        that.handlers[key] = [];

      that.handlers[key].push(handler);
    };

    that.unregisterCommand = function(key, handler) {
      if(that.handlers.hasOwnProperty(key) === true) {
        var index = that.handlers[key].indexOf(handler);

        if(index === -1)
          return false;

        that.handlers[key].splice(index, 1);
      } else {
        return false;
      }
    };

    that.update = function(elapsedTime) {
      var key, handler;

      for (key in that.keys) {
        for(handler in that.handlers[key]) {
          that.handlers[key][handler](elapsedTime);
        }
      }
    };

    //
    // These are used to keep track of which keys are currently pressed
    window.addEventListener('keydown', keyPress.bind(that));
    window.addEventListener('keyup', keyRelease.bind(that));

    return that;
  }

  function Mouse() {
    var that = {
      mouseDown: [],
      mouseUp: [],
      mouseMove: [],
      handlersDown: [],
      handlersUp: [],
      handlersMove: []
    };

    function mouseDown(e) {
      that.mouseDown.push(e);
    }

    function mouseUp(e) {
      that.mouseUp.push(e);
    }

    function mouseMove(e) {
      that.mouseMove.push(e);
    }

    that.update = function(elapsedTime) {
      var event,
          handler;
      //
      // Process the mouse events for each of the different kinds of handlers
      for (event = 0; event < that.mouseDown.length; event++) {
        for (handler = 0; handler < that.handlersDown.length; handler++) {
          that.handlersDown[handler](that.mouseDown[event], elapsedTime);
        }
      }

      for (event = 0; event < that.mouseUp.length; event++) {
        for (handler = 0; handler < that.handlersUp.length; handler++) {
          that.handlersUp[handler](that.mouseUp[event], elapsedTime);
        }
      }

      for (event = 0; event < that.mouseMove.length; event++) {
        for (handler = 0; handler < that.handlersMove.length; handler++) {
          that.handlersMove[handler](that.mouseMove[event], elapsedTime);
        }
      }

      //
      // Now that we have processed all the inputs, reset everything back to the empty state
      that.mouseDown.length = 0;
      that.mouseUp.length = 0;
      that.mouseMove.length = 0;
    };

    that.registerCommand = function(type, handler) {
      if (type === 'mousedown') {
        that.handlersDown.push(handler);
      }
      else if (type === 'mouseup') {
        that.handlersUp.push(handler);
      }
      else if (type === 'mousemove') {
        that.handlersMove.push(handler);
      }
    };

    window.addEventListener('mousedown', mouseDown.bind(that));
    window.addEventListener('mouseup', mouseUp.bind(that));
    window.addEventListener('mousemove', mouseMove.bind(that));

    return that;
  }

  function Touch() {
    var that = {
      touchStart: [],
      touchEnd: [],
      touchMove: [],
      handlersStart: [],
      handlersEnd: [],
      handlersMove: []
    };

    function touchStart(e) {
      that.touchStart.push(e);
    }

    function touchEnd(e) {
      that.touchEnd.push(e);
    }

    function touchMove(e) {
      that.touchMove.push(e);
    }

    that.update = function(elapsedTime) {
      var event,
          handler;

      for (event = 0; event < that.touchStart.length; event++) {
        for (handler = 0; handler < that.handlersStart.length; handler++) {
          that.handlersStart[handler](that.touchStart[event], elapsedTime);
        }
      }

      for (event = 0; event < that.touchEnd.length; event++) {
        for (handler = 0; handler < that.handlersEnd.length; handler++) {
          that.handlersEnd[handler](that.touchEnd[event], elapsedTime);
        }
      }

      for (event = 0; event < that.touchMove.length; event++) {
        for (handler = 0; handler < that.handlersMove.length; handler++) {
          that.handlersMove[handler](that.touchMove[event], elapsedTime);
        }
      }

      //
      // Now that we have processed all the inputs, reset everything back to the empty state
      that.touchStart.length = 0;
      that.touchEnd.length = 0;
      that.touchMove.length = 0;
    };

    that.registerCommand = function(type, handler) {
      if (type === 'mousedown') {
        that.handlersStart.push(handler);
      }
      else if (type === 'mouseup') {
        that.handlersEnd.push(handler);
      }
      else if (type === 'mousemove') {
        that.handlersMove.push(handler);
      }
    };

    window.addEventListener('touchstart', touchStart.bind(that));
    window.addEventListener('touchend', touchEnd.bind(that));
    window.addEventListener('touchmove', touchMove.bind(that));

    return that;
  }

  return {
    Keyboard: Keyboard,
    Mouse: Mouse,
    Touch: Touch
  };
}());

//------------------------------------------------------------------
//
// Source: http://stackoverflow.com/questions/1465374/javascript-event-keycode-constants
//
//------------------------------------------------------------------
if (typeof KeyEvent === 'undefined') {
  var KeyEvent = {
    KEY_CANCEL: 3,
    KEY_HELP: 6,
    KEY_BACK_SPACE: 8,
    KEY_TAB: 9,
    KEY_CLEAR: 12,
    KEY_RETURN: 13,
    KEY_ENTER: 14,
    KEY_SHIFT: 16,
    KEY_CONTROL: 17,
    KEY_ALT: 18,
    KEY_PAUSE: 19,
    KEY_CAPS_LOCK: 20,
    KEY_ESCAPE: 27,
    KEY_SPACE: 32,
    KEY_PAGE_UP: 33,
    KEY_PAGE_DOWN: 34,
    KEY_END: 35,
    KEY_HOME: 36,
    KEY_LEFT: 37,
    KEY_UP: 38,
    KEY_RIGHT: 39,
    KEY_DOWN: 40,
    KEY_PRINTSCREEN: 44,
    KEY_INSERT: 45,
    KEY_DELETE: 46,
    KEY_0: 48,
    KEY_1: 49,
    KEY_2: 50,
    KEY_3: 51,
    KEY_4: 52,
    KEY_5: 53,
    KEY_6: 54,
    KEY_7: 55,
    KEY_8: 56,
    KEY_9: 57,
    KEY_SEMICOLON: 59,
    KEY_EQUALS: 61,
    KEY_A: 65,
    KEY_B: 66,
    KEY_C: 67,
    KEY_D: 68,
    KEY_E: 69,
    KEY_F: 70,
    KEY_G: 71,
    KEY_H: 72,
    KEY_I: 73,
    KEY_J: 74,
    KEY_K: 75,
    KEY_L: 76,
    KEY_M: 77,
    KEY_N: 78,
    KEY_O: 79,
    KEY_P: 80,
    KEY_Q: 81,
    KEY_R: 82,
    KEY_S: 83,
    KEY_T: 84,
    KEY_U: 85,
    KEY_V: 86,
    KEY_W: 87,
    KEY_X: 88,
    KEY_Y: 89,
    KEY_Z: 90,
    KEY_CONTEXT_MENU: 93,
    KEY_NUMPAD0: 96,
    KEY_NUMPAD1: 97,
    KEY_NUMPAD2: 98,
    KEY_NUMPAD3: 99,
    KEY_NUMPAD4: 100,
    KEY_NUMPAD5: 101,
    KEY_NUMPAD6: 102,
    KEY_NUMPAD7: 103,
    KEY_NUMPAD8: 104,
    KEY_NUMPAD9: 105,
    KEY_MULTIPLY: 106,
    KEY_ADD: 107,
    KEY_SEPARATOR: 108,
    KEY_SUBTRACT: 109,
    KEY_DECIMAL: 110,
    KEY_DIVIDE: 111,
    KEY_F1: 112,
    KEY_F2: 113,
    KEY_F3: 114,
    KEY_F4: 115,
    KEY_F5: 116,
    KEY_F6: 117,
    KEY_F7: 118,
    KEY_F8: 119,
    KEY_F9: 120,
    KEY_F10: 121,
    KEY_F11: 122,
    KEY_F12: 123,
    KEY_F13: 124,
    KEY_F14: 125,
    KEY_F15: 126,
    KEY_F16: 127,
    KEY_F17: 128,
    KEY_F18: 129,
    KEY_F19: 130,
    KEY_F20: 131,
    KEY_F21: 132,
    KEY_F22: 133,
    KEY_F23: 134,
    KEY_F24: 135,
    KEY_NUM_LOCK: 144,
    KEY_SCROLL_LOCK: 145,
    KEY_COMMA: 188,
    KEY_PERIOD: 190,
    KEY_SLASH: 191,
    KEY_BACK_QUOTE: 192,
    KEY_OPEN_BRACKET: 219,
    KEY_BACK_SLASH: 220,
    KEY_CLOSE_BRACKET: 221,
    KEY_QUOTE: 222,
    KEY_META: 224
  };
}