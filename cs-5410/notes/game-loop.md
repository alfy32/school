Game Loop
=========

This is the classic game loop used for all games.


**Step 1:** Initialize

* Graphics
* Sounds
* Assets
* Memory

**Step 2:** Collect Input

**Step 3:** Update

* Move things
* AI
* Collision Detection

**Step 4:** Render


*******************************


Rendering
---------

**Realtime**  

* Draws stuff on screen
* Provide high level api
* Screen REsolution, Aspect Ratio, Color Depth
* Fonts

**Before Hand** 

* Offline Rendering - Large maps, particle flow


Input
-----

* Collect input from user/other "things" - *internet, gps, accelerometer, ...*
* Subscriber pattern
  * have keyboard model - other things want to know when a certain key is pressed so they subscribe to the model. The model listens to the keys and tells the subsriber when the specific key is pressed.
* Queue Model
  * Put the events in a queue and comsume them as you can 
