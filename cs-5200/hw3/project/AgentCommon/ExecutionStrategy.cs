using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Messages;

namespace AgentCommon
{
    public abstract class ExecutionStrategy : BackgroundThread
    {
        #region Static Members
        private static Dictionary<int, ExecutionStrategy> StrategyPool = new Dictionary<int, ExecutionStrategy>();

        public static void StartConversation(Envelope envelope)
        {
            int conversationId = envelope.message.ConversationId.SeqNumber;

            MessageQueue messageQueue = ConversationMessageQueues.getQueue(conversationId);

            ExecutionStrategy executionStrategy = null;

            switch (envelope.message.MessageTypeId())
            {
                case Message.MESSAGE_CLASS_IDS.JoinGame:
                    executionStrategy = new JoinGameExecutionStrategy(conversationId);
                    break;
            }

            if (executionStrategy != null)
            {
                StrategyPool.Add(conversationId, executionStrategy);

                executionStrategy.Start();
            }            
        }
        #endregion

        protected MessageQueue messageQueue;

        public ExecutionStrategy(int conversationId)
        {
            messageQueue = ConversationMessageQueues.getQueue(conversationId);
        }

        public override String ThreadName()
        {
            return "ExecutionStrategy";
        }

        protected override void Process()
        {
            while (keepGoing)
            {
                while (keepGoing && !suspended)
                {
                    Execute();
                }
            }
        }

        protected abstract void Execute();
    }
}
