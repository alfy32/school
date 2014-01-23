/* jshint node:true */
'use strict';

var express = require('express');
var http    = require('http');

var app = express();

app.set('port', process.argv[2] || 3000);

// all environments
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(express.cookieParser('Type secret here'));
app.use(express.session());
app.use(app.router);
app.use(express.static('../cs-5410'));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

http.createServer(app).listen(app.get('port'), function () {
  console.log('Express server listening on port ' + app.get('port')
    + ' in environment ' + app.get('env'));
});

console.log(__dirname);