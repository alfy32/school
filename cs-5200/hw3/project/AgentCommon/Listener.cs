using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AgentCommon
{
    class Listener : BackgroundThread
    {
        public override string ThreadName()
        {
            return "Lstener";
        }

        protected override void Process()
        {
            
        }
    }
}
