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
Excuswe | Excuse Generator
Whine   | Whining Spinner
Monitor | Monitor
Zombie  | Zombie Professor

The communication between the actors and the basic components will be described.


Conversations, Communication Patterns, and Messages
---------------------------------------------------

Table 1 lists the possible types of conversations involved in the system. It describes the protocol, initiator, participants, and pattern involved in the conversations. Figures 2-4 illustrate the *Request-Reply* pattern.

### Table 1 - Converstations and Protocols

ID | Protocol/Conversation | Initiator | Recipients    | Pattern       |
---| --------------------- | --------- | ------------- | ------------- |
01 | Register              | Any Agent | Field         | Request-Reply |
02 | ClockTick             | Clock     | All Agents    | One-Way       |
03 | Move                  | Stud      | Field         | Request-Reply |
04 | GetParameters         | Any Agent | Field         | Request-Reply |
05 | GetField              | Student   | Field         | Request-Reply |
06 | GetLayout             | Student   | Field         | Request-Reply |
07 | ListZombies           | Student   | Field         | Request-Reply |
08 | ListStudents          | Student   | Field         | Request-Reply |
09 | ListExcuses           | Student   | Field         | Request-Reply |
10 | ListWhines            | Student   | Field         | Request-Reply |
11 | GetResource           | Student   | Excuse,Whine  | Request-Reply |
12 | ThrowBomb             | Student   | Field         | Request-Reply |
13 | DiscussTarget         | Student   | Student       | Request-Reply |
14 | TakeHit               | Field     | All Agents    | Request-Reply |
