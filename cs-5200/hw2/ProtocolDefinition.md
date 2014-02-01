Protocol Definition
===================

Overview
-----------

This document defines the communication protocol to be used in the *Brilliant Student VS Zombie Professors* game.

### Actors

* Game Promoter
* Game Player
* Game Referee

### Basic Components

* Playing Field
* Clock Tower
* Brilliant Student
* Excuse Generator
* Whining Spinner
* Monitor
* Zombie Professor

The communication between the actors and the basic components will be described.


Conversations, Communication Patterns, and Messages
---------------------------------------------------

Table 1 lists the possible types of conversations involved in the system. It describes the protocol, initiator, participants, and pattern involved in the conversations. Figures 2-4 illustrate the *Request-Reply* pattern.

### Table 1 - Converstations and Protocols

Protocol/Conversation | Initiator     | Recipients      | Pattern       |
--------------------- | ------------- | --------------- | ------------- |
CreateGame            | Promoter      |
GetParameters         | All           | Game            | Request-Reply |
EditParameters        | Promoter      | Game            | Request-Reply |
AnnounceGame          | Promoter      | Players         | One Way       |
Register              | Real Player   | Game            | Request-Reply |
StopGame              | Referee       | Game            | Request-Reply |
RemoveAgent           | Referee       | Game            | Request-Reply |
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
CreateZombie          | Game          | Zombie          | Request-Reply |
HitZombie             | Game          | Zombie          | Request-Reply |
Eat                   | Zombie        | Game            | Request-Reply |
Eat                   | Game          | Student,WhiningSpinner,ExcuseGenerator | Request-Reply |