Input
-----

* Collect input from user/other "things" - *internet, gps, accelerometer, ...*
* Subscriber pattern
  * have keyboard model - other things want to know when a certain key is pressed so they subscribe to the model. The model listens to the keys and tells the subsriber when the specific key is pressed.
* Queue Model
  * Put the events in a queue and comsume them as you can