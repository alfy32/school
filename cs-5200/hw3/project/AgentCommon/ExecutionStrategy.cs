using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Messages;

namespace AgentCommon
{
    public class ExecutionStrategy : BackgroundThread
    {
        public void execution() {

        }

        public static void StartConversation(Envelope envelope)
        {
          //How do I start a conversation?
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
                    //Execute the strategy???
                }
            }
        }
    }
}
