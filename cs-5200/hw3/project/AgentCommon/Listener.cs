using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Common;

namespace AgentCommon
{
    public class Listener : BackgroundThread
    {
        #region Private Data Members
        private Communicator communicator;
        private MessageQueue messageQueue;
        private const int TIMEOUT = 100;
        #endregion

        #region Constructors
        public Listener(Communicator communicator,MessageQueue messageQueue)
        {
            this.communicator = communicator;
            this.messageQueue = messageQueue;
        }
        #endregion

        public override string ThreadName()
        {
            return "Lstener";
        }

        protected override void Process()
        {
          while (keepGoing)
          {
            while (keepGoing && !suspended)
            {
              if (communicator.GetAvailable() > 0)
              {
                Envelope envelope = communicator.Recieve(TIMEOUT);
                int messageNr = envelope.message.MessageNr.SeqNumber;
                int conversationId = envelope.message.ConversationId.SeqNumber;

                if(messageNr == conversationId) {
                  //place on request message queue
                  messageQueue.push(envelope);
                } else {
                  //place on conversation message queue
                  messageQueue.push(envelope);

                  //ignore if ther is no conversation queue
                }                
              }
            }
          }
        }
    }
}
