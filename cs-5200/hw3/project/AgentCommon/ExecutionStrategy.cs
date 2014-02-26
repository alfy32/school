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

        public static Envelope executeMessage(Envelope envelope)
        {
            return null;
        }

        public override String ThreadName()
        {
            return "ExecutionStrategy";
        }

        protected override void Process()
        {
            
        }
    }
}
