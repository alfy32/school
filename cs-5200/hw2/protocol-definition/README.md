### Brilliant Students VS Zombie Professors

Protocol Definition
===================

Overview
-----------

This document defines the communication protocol to be used in the *Brilliant Student VS Zombie Professors* game.

### Actors

* Game Promoter
* Game Player
* Game Referee

### Basic Components (Agents)

Code    | Name
------- | ----
Field   | Playing Field
Clock   | Clock Tower
Student | Brilliant Student
Excuse  | Excuse Generator
Whine   | Whining Spinner
Monitor | Monitor
Zombie  | Zombie Professor

The communication between the actors and the basic components will be described.


Conversations, Communication Patterns, and Messages
---------------------------------------------------

Table 1 lists the possible types of conversations involved in the system. It describes the protocol, initiator, participants, and pattern involved in the conversations. Figures 2-4 illustrate the *Request-Reply* pattern. Figure 5 illustates the *One-Way* pattern.

### Table 1 - Converstations and Protocols

ID | Protocol       | Initiator | Recipients    | Pattern       | Request Class | Reply Class
---| -------------- | --------- | ------------- | ------------- | ------------- | -----------
01 | Register       | Any Agent | Game          | Request-Reply | Register      | Assignment
02 | ClockTick      | Clock     | All Agents    | One-Way       | ClockTick     | N/A
03 | Move           | Student   | Field         | Request-Reply | Move          | NewLocation
04 | GetParameters  | Any Agent | Game          | Request-Reply | GetParameters | ParameterList
05 | GetField       | Student   | Game          | Request-Reply | GetField      | Field
06 | GetLayout      | Student   | Field         | Request-Reply | GetLayout     | Layout
07 | ListZombies    | Student   | Field         | Request-Reply | ListZombies   | ZombieList
08 | ListStudents   | Student   | Field         | Request-Reply | ListStudents  | StudentList
09 | ListExcuses    | Student   | Field         | Request-Reply | ListExcuses   | ExcuseList
10 | ListWhines     | Student   | Field         | Request-Reply | ListWhines    | WhineList
11 | GetResource    | Student   | Excuse,Whine  | Request-Reply | GetResource   | Recource
12 | ThrowBomb      | Student   | Field         | Request-Reply | ThrowBomb     | Acknowledge
13 | DiscussTarget  | Student   | Student       | Request-Reply | DiscussTarget | TargetStrategy
14 | TakeHit        | Field     | Any Agent     | Request-Reply | TakeHit       | ImHit

### Protocol Description

* Register - Send endPoint, type, and A-Number to Game.
* ClockTick - The clock tower sends out ticks to all agents as a resource.
* Move - Submit move to Field. Recieve new Location.
* GetParameters - Get Game configuration parameters as a list.
* GetField - Get Field endPoint.
* GetLayout - Get Field layout. Includes: width, height.
* ListZombies - Get a list of all Zombies on the field. Includes: ID, endpoint, location, hitPoints, speed.
* ListStudents - Get a list of all Students on the field. Includes: ID, endpoint, location, hitPoints, speed.
* ListExcuses - Get a list of all Excuses on the field. Includes: ID, endpoint, location, hitPoints, speed.
* ListWhines - Get a list of all Whines on the field. Includes: ID, endpoint, location, hitPoints, speed.
* GetResource - Get Whinig Twine From Whine or Excuse from Excues.
* ThrowBomb - Send Field where to throw bomb.
* DiscussTarget - Send message to another Student. Include: Who and where to attack or run and direction.
* TakeHit - Amount of Hit Points lost by agent in attack. Resopd with new Hit Points.


### Figure 01 - Message Classes for Word Guessing

![Figure 01 - Message Classes for Word Guessing](Figure 01 - Message Classes for Word Guessing.png)

### Figure 02 - Successful Request-Reply Communication Pattern

![(Figure 02 - Successful Request-Reply Communication Pattern.png](Figure 02 - Successful Request-Reply Communication Pattern.png)

### Figure 03 - General Timeout Situtation

![Figure 03 - General Timeout Situtation](Figure 03 - General Timeout Situtation.png)

### Figure 04 - Abort Situation

![Figure 04 - Abort Situation](Figure 04 - Abort Situation.png)

### Figure 05 - One-Way Communication Pattern

![Figure 05 - One-Way Communication Pattern](Figure 05 - One-Way Communication Pattern.png)

Message Encoding/Decoding
-------------------------

A message will be decoded in the following way.

Each message will be derived from the abstract class message. Then it will be recursively defined through the class hierarchy. The message class will ass the message and conversation ids. Each class including the message class will add a number to define which base class is to be used next to decode the mesage. All messages will be decoded using the ByteList class.

### Encoding Scheme

All values will be converted to their string representations then converted to bytes.

* Integers - Each integer is written in ascii format. The number of bytes is decided by the possible values.
* Char - Encoded in ascii. One Byte.
* String - Two byte integer to encode the length. Then followed by ascii character values.
* Boolean - One byte true(1) or false(0).
* Array - Two byte count followed by each element is written using it's primitive value's encoding.
* Object - Encoded recursively as the Message Class.