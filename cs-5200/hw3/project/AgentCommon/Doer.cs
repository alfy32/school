using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Messages;

namespace AgentCommon
{
    public class Doer : BackgroundThread
    {
        #region Private Members
        private Communicator communicator;
        private MessageQueue messageQueue;
        #endregion

        #region Constructors
        public Doer(Communicator communicator, MessageQueue messageQueue)
        {
            this.communicator = communicator;
            this.messageQueue = messageQueue;
        }
        #endregion

        public override string ThreadName()
        {
            return "Doer";
        }

        protected override void Process()
        {
            while (keepGoing)
            {
                while (keepGoing && !suspended)
                {
                    if(messageQueue.hasItems())
                    {
                        Envelope envelope = messageQueue.pop();
                        Envelope envelopeToSend = ExecutionStrategy.executeMessage(envelope);

                        communicator.Send(envelopeToSend);
                    }
                }
            }
        }
    }
}
