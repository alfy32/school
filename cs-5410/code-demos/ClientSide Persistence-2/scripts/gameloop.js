/*jslint browser: true, white: true */
// ------------------------------------------------------------------
// 
// This is the game module.  Everything about the game is located in 
// this object.
//
// ------------------------------------------------------------------
var MYGAME = {
	persistence : (function () {
		'use strict';
		
		function add(key, value) {
			localStorage[key] = value;
		}
		
		function remove(key) {
			localStorage.removeItem(key);
		}

		function report() {
			var node = document.getElementById('div-console'),
				item,
				key;
			
			node.innerHTML = '';
			for (item = 0; item < localStorage.length; item++) {
				key = localStorage.key(item);
				node.innerHTML += ('Key: ' + key + ' Value: ' + localStorage[key] + '<br/>');
			}
			node.scrollTop = node.scrollHeight;
		}

		return {
			add : add,
			remove : remove,
			report : report
		};
	}())
};

function addValue() {
	'use strict';
	
	MYGAME.persistence.add(
		document.getElementById('id-key').value,
		document.getElementById('id-value').value);

	MYGAME.persistence.report();
}

function removeValue() {
	'use strict';
	
	MYGAME.persistence.remove(document.getElementById('id-key').value);
	MYGAME.persistence.report();
}
