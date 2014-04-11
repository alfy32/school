/*globals Modernizr, yepnope, console */

var MYGAME = {
	audio : {},
	images : {},
	screens : {},

	status : {
		preloadRequest : 0,
		preloadComplete : 0
	}
};

//------------------------------------------------------------------
//
// Wait until the browser 'onload' is called before starting to load
// any external resources.  This is needed because a lot of JS code
// will want to refer to the HTML document.
//
//------------------------------------------------------------------
window.addEventListener('load', function() {
	'use strict';

	console.log('Loading resources...');
	Modernizr.load([
		{
			load : [
				// images
				'preload!img/Dollar-Sign.png',

				// sounds
				'preload!audio/cha-ching.wav',

				// libs
				'preload!js/random.js',
				'preload!js/persistence.js',
				'preload!js/particle-system.js',

				// game
				'preload!js/game.js',
				'preload!js/input.js',

				// Graphics
				'preload!js/graphics.js',
				'preload!js/graphics.money.js',

				// screens
				'preload!js/screens.about.js',
				'preload!js/screens.gameplay.js',
				'preload!js/screens.help.js',
				'preload!js/screens.highscores.js',
				'preload!js/screens.mainmenu.js'


			],
			complete : function() {
				console.log('All files requested for loading...');
			}
		}
	]);
}, false);

//
// Extend yepnope with our own 'preload' prefix that...
// * Tracks how many have been requested to load
// * Tracks how many have been loaded
// * Places images into the 'images' object
yepnope.addPrefix('preload', function(resource) {
	'use strict';

	console.log('preloading: ' + resource.url);

	MYGAME.status.preloadRequest += 1;

	var isImage = /.+\.(jpg|png|gif)$/i.test(resource.url);
  var isAudio = /.+\.(wav|mp3)$/i.test(resource.url);

	resource.noexec = isImage || isAudio;

	resource.autoCallback = function(e) {
		if (isImage) {
			var image = new Image();
			image.src = resource.url;
			MYGAME.images[resource.url] = image;
		} else if (isAudio) {
			var audio = new Audio(resource.url);
      MYGAME.audio[resource.url] = audio;
		}

		MYGAME.status.preloadComplete += 1;

		//
		// When everything has finished preloading, go ahead and start the game
		if (MYGAME.status.preloadComplete === MYGAME.status.preloadRequest) {
			console.log('Preloading complete!');
			MYGAME.game.initialize();
		}
	};

	return resource;
});
