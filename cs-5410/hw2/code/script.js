/* global MazeSolver */
'use strict';

function generateClick() {
  var width = document.getElementById('width').value;

  MazeSolver.generate(width, width);
  MazeSolver.print();
}