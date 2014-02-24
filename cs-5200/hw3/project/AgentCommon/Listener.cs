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
        private Dictionary<EndPoint, MessageQueue> messageQueues;
        private static const int TIMEOUT = 100;
        #endregion

        #region Constructors
        public Listener(Communicator communicator, Dictionary<EndPoint, MessageQueue> messageQueues)
        {
            this.communicator = communicator;
            this.messageQueues = messageQueues;
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
                while (!suspended)
                {
                    if (communicator.GetAvailable() > 0)
                    {
                        Envelope envelope = communicator.Recieve(TIMEOUT);
                        if (messageQueues.ContainsKey(envelope.endPoint))
                        {
                            messageQueues[envelope.endPoint].push(envelope);
                        }
                        else
                        {
                            MessageQueue messageQueue = new MessageQueue();
                            messageQueue.push(envelope);

                            messageQueues.Add(envelope.endPoint, messageQueue);
                        }
                    }
                }
            }
        }
    }
}
