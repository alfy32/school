var circle = {
		radius: 4
};

Object.defineProperty(circle, 'diameter', {
	/* value: circle.radius * 2,
	writable: true, */
	get: function() { return circle.radius * 2; },
	set: function(value) { circle.radius = value / 2; },
	enumerable: true,
	configurable: true
});

console.log('Radius: ' + circle.radius);
console.log('Diameter: ' + circle.diameter);

circle.diameter = 4;

console.log('Radius: ' + circle.radius);
console.log('Diameter: ' + circle.diameter);

console.log('--- Before ---');
for (var property in circle ) {
	console.log('Property: ' + property);
}

delete circle.radius;

console.log('--- After ---');
for (var property in circle ) {
	console.log('Property: ' + property);
}

console.log(circle.diameter);
