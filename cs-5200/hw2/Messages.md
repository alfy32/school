Users and Their Goals
=====================

Game Promoter
-------------

**Actions**

* Create New Game
  * Default Params
  * Custom Params
* Announce Game to potential players
* Allow registration of players

Player
------

**Actions**

* Register one or more agents
  * Must register at least one Brilliant Student

Referee
-------

**Actions**

* Start Game
* Stop Game
* Remove errant agents

=============================

Functional Requirements
=======================

Promoter
--------

* Create Game

Game
----

* Track settings

=============================

Messages
========

**Game**

Protocol/Conversation | Initiator     | Recipients      | Pattern       |
--------------------- | ------------- | --------------- | ------------- |
CreateGame            | Promoter
GetParameters         | Promoter      | Game            | Request-Reply |
EditParameters        | Promoter      | Game            | Request-Reply |
AnnounceGame          | Promoter      | Players         | One Way       |
Register              | Real Player   | Game            | Request-Reply |
StopGame              | Referee       | Game            | Request-Reply |
RemoveAgent           | Referee       | Game            | Request-Reply |

**Playing Field**

1. Accept Excuse Generator Placement
2. Accept Whining Spinner Placement
3. Allow Brilliant student placement before game start
4. Accept new Zombie professor on edge of field any time
5. move request
6. throw bomb

**Clock Tower**

* Send Time Tick



**Brilliant Student**

Protocol/Conversation | Initiator     | Recipients      | Pattern       |
--------------------- | ------------- | --------------- | ------------- |
GetParameters         | Student       | Game            | Request-Reply |
GetPlayingField       | Student       | Game            | Request-Reply |
GetLayout             | Student       | PlayingField    | Request-Reply |
ZombieList            | PlayingField  | PlayingField    | Request-Reply |
StudentList           | Student       | PlayingField    | Request-Reply |
ExcuseGeneratorList   | Student       | PlayingField    | Request-Reply |
WhiningSpinnerList    | Student       | PlayingField    | Request-Reply |
GetExcuse             | Student       | ExcuseGenerator | Request-Reply |
GetTwine              | Student       | WhiningSpinner  | Request-Reply |
ThrowBomb             | Student       | PlayingField    | Request-Reply |
Move                  | Student       | PlayingField    | Request-Reply |
ExchangeInfo          | Student       | Student         | Request-Reply |
Die                   | PlayingField  | Student         | Request-Reply |


**Excuse Generators**

Protocol/Conversation | Initiator       | Recipients      | Pattern       |
--------------------- | --------------- | --------------- | ------------- |
GetParameters         | ExcuseGenerator | Game            | Request-Reply |

*Listen to any conversation
**Must Respond to requests



**Whining Spinners**

Protocol/Conversation | Initiator       | Recipients      | Pattern       |
--------------------- | --------------- | --------------- | ------------- |
GetParameters         | WhiningSpinner  | Game            | Request-Reply |

*Listen to any conversation
**Must Respond to requests



**Monitor**

Playing Field Keeps it up to date.

Tracks Hit Points.



**Zombie Professors**

Protocol/Conversation | Initiator     | Recipients      | Pattern       |
--------------------- | ------------- | --------------- | ------------- |
GetParameters         | Zombie        | Game            | Request-Reply |
CreateZombie          | Game          | Zombie          | Request-Reply |
HitZombie             | Game          | Zombie          | Request-Reply |
Eat                   | Zombie        | Game            | Request-Reply |
Eat                   | Game          | Student,WhiningSpinner,ExcuseGenerator | Request-Reply |
