var circle = {
		radius: 4,
		get diameter() { return this.radius * 2; },
		set diameter(value) { this.radius = value / 2; }
};

console.log('Radius: ' + circle.radius);
console.log('Diameter: ' + circle.diameter);

circle.diameter = 4;

console.log('Radius: ' + circle.radius);
console.log('Diameter: ' + circle.diameter);

for (var property in circle ) {
	console.log('Property: ' + property);
}