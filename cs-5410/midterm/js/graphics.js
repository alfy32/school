/*global MYGAME, $, CanvasRenderingContext2D, performance */

MYGAME.graphics = (function() {
	'use strict';

	var canvas = document.getElementById('canvas-main'),
		context = canvas.getContext('2d');

	//
	// Place a 'clear' function on the Canvas prototype, this makes it a part
	// of the canvas, rather than making a function that calls and does it.
	CanvasRenderingContext2D.prototype.clear = function() {
		this.save();
		this.setTransform(1, 0, 0, 1, 0, 0);
		this.clearRect(0, 0, canvas.width, canvas.height);
		this.restore();
	};

	function clear() {
		context.clear();
	}

	function Marker(spec) {
		var that = {
			center: {
				x: spec.center.x,
				y: spec.center.y
			},
			get right() { return that.center.x + that.width/2; },
			set right(x) { that.center.x = x - that.width/2; },
			get left() { return that.center.x - that.width/2; },
			set left(x) { that.center.x = x + that.width/2; },
			width: 5,
			height: spec.height,
			speed: spec.speed,
			direction: spec.direction,
			rotation: 0
		};

		that.moveLeft = function (elapsedTime) {
			that.center.x -= that.speed * (elapsedTime/1000);
		};

		that.moveRight = function (elapsedTime) {
			that.center.x += that.speed * (elapsedTime/1000);
		};

		that.draw = function() {
			context.save();

			context.translate(that.center.x, that.center.y);
			context.rotate(that.rotation);
			context.translate(-that.center.x, -that.center.y);

			context.beginPath();
			context.lineWidth = 2;
			context.strokeStyle = 'black';
			context.rect(
				that.center.x - that.width/2,
				that.center.y - that.height/2,
				that.width, that.height
			);
			context.stroke();

			context.beginPath();
			context.rect(
				that.center.x - that.width/2,
				that.center.y - that.height/2,
				that.width, that.height
			);
			context.fillStyle = 'yellow';
			context.fill();

			context.restore();
		};

		return that;
	}

	function Meter(spec) {
		var that = {
			center: {
				x: spec.center.x,
				y: spec.center.y
			},
			width: spec.width,
			height: spec.height,
			get left() { return that.center.x - that.width/2; },
			set left(x) { that.center.x = x + that.width/2; },
			get right() { return that.center.x + that.width/2; },
			set right(x) { that.center.x = x - that.width/2; },
			level: 1,
			region: {
				size: 0.3,
				get left() { return spec.center.x - spec.width * that.region.size; },
				get right() { return spec.center.x + spec.width * that.region.size; },
			},
			money: 0,
			moneyRate: 1.0
		};

		that.marker = new Marker({
			center: {
				x: spec.center.x - spec.width/2 + 10,
				y: spec.center.y
			},
			height: spec.height,
			speed: spec.width,
			direction: 'right'
		});

		that.reset = function() {
			that.money = 0;
			that.moneyRate = 1.0;
			that.level = 1;
			that.region.size = 0.3;
		};

		// var lastHit = 0;
		// var hitTime = 500;

		that.hit = function () {
			// if(performance.now() - lastHit < hitTime) return true;
			// lastHit = performance.now();

			if(that.marker.left > that.region.left && that.marker.right < that.region.right){
				that.nextLevel();
				return true;
			} else {
				console.log('Game Over');
				return false;
			}
		};

		that.nextLevel = function () {
			that.money += that.moneyRate;
			MYGAME.graphics.money.explode(that.marker.center);

			if(that.level < 6) {
				that.level++;
				that.region.size -= 0.05;
				that.moneyRate += 0.2;
			}
		};

		that.update = function (elapsedTime) {
			if(that.marker.direction === 'left') that.marker.moveLeft(elapsedTime);
			if(that.marker.direction === 'right') that.marker.moveRight(elapsedTime);

			if(that.marker.right > that.right) {
				that.marker.direction = 'left';
				that.marker.right = that.right - 1;
			}

			if(that.marker.left < that.left) {
				that.marker.direction = 'right';
				that.marker.left = that.left + 1;
			}
		};

		that.draw = function() {
			document.getElementById('money').innerText = that.money;

			context.save();

			context.translate(that.center.x, that.center.y);
			context.rotate(that.rotation);
			context.translate(-that.center.x, -that.center.y);

			context.beginPath();
			context.lineWidth = 4;
			context.rect(
				that.center.x - that.width/2,
				that.center.y - that.height/2,
				that.width, that.height
			);
			context.strokeStyle = 'black';
			context.stroke();

			context.beginPath();
			context.rect(
				that.center.x - that.width/2,
				that.center.y - that.height/2,
				that.width, that.height
			);
			context.fillStyle = 'blue';
			context.fill();

			context.restore();

			that.drawShadedRegion();
			that.marker.draw();
		};

		that.drawShadedRegion = function() {
			context.save();

			context.translate(that.center.x, that.center.y);
			context.rotate(that.rotation);
			context.translate(-that.center.x, -that.center.y);

			context.beginPath();
			context.lineWidth = 2;
			context.rect(
				that.region.left,
				that.center.y - that.height/2,
				that.region.right - that.region.left, that.height
			);
			context.strokeStyle = 'black';
			context.stroke();

			context.beginPath();
			context.rect(
				that.region.left,
				that.center.y - that.height/2,
				that.region.right - that.region.left, that.height
			);
			context.fillStyle = 'lime';
			context.fill();

			context.restore();
		};

		return that;
	}

	function Text(spec) {
		var that = {};

		that.updateRotation = function(angle) {
			spec.rotation += angle;
		};

		//------------------------------------------------------------------
		//
		// This returns the height of the specified font, in pixels.
		//
		//------------------------------------------------------------------
		function measureTextHeight(spec) {
			context.save();

			context.font = spec.font;
			context.fillStyle = spec.fill;
			context.strokeStyle = spec.stroke;

			var height = context.measureText('m').width;

			context.restore();

			return height;
		}

		//------------------------------------------------------------------
		//
		// This returns the width of the specified font, in pixels.
		//
		//------------------------------------------------------------------
		function measureTextWidth(spec) {
			context.save();

			context.font = spec.font;
			context.fillStyle = spec.fill;
			context.strokeStyle = spec.stroke;

			var width = context.measureText(spec.text).width;

			context.restore();

			return width;
		}

		that.draw = function() {
			context.save();

			context.font = spec.font;
			context.fillStyle = spec.fill;
			context.strokeStyle = spec.stroke;
			context.textBaseline = 'top';

			context.translate(spec.pos.x + that.width / 2, spec.pos.y + that.height / 2);
			context.rotate(spec.rotation);
			context.translate(-(spec.pos.x + that.width / 2), -(spec.pos.y + that.height / 2));

			context.fillText(spec.text, spec.pos.x, spec.pos.y);
			context.strokeText(spec.text, spec.pos.x, spec.pos.y);

			context.restore();
		};

		//
		// Compute and expose some public properties for this text.
		that.height = measureTextHeight(spec);
		that.width = measureTextWidth(spec);
		that.pos = spec.pos;

		return that;
	}

	function drawImage(spec) {
		context.save();

		context.translate(spec.center.x, spec.center.y);
		context.rotate(spec.rotation);
		context.translate(-spec.center.x, -spec.center.y);

		context.drawImage(
			spec.image,
			spec.center.x - spec.size/2,
			spec.center.y - spec.size/2,
			spec.size, spec.size);

		context.restore();
	}

	return {
		clear: clear,
		drawImage: drawImage,
		Meter: Meter,
		Text: Text
	};
}());
