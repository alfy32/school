This project (directory) provides an example of how to encapsulate the implementation on a collection of messages into a maintainable and relatively extensible hierarchy of message classes.  Study the protocol documentation before looking at the sample code.

The top of the hierarchy is the Message Class.  It is an abstract class, meaning
objects cannot be directly instance from this definition.  They have to be
instantiated from one of its specialization.  Its two immediate specializations
are Request and Reply. They are also abstract classes.  A Request is any message that
can start a conversation. A Reply is any other message in a conversation.  Concrete message classes are specializations of either Request or Reply.

Sender called the constructors of the desire concrete message classes.  Receivers
calls the Message.Create factory methods with a ByteList, which in turn call either
the Request.Create or Reply.Create factor methods, which then call the appropriate
concrete Message.Create factory methods.