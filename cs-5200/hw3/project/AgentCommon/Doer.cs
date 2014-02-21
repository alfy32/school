using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AgentCommon
{
    public class Doer : BackgroundThread
    {
        public override string ThreadName()
        {
            return "Doer";
        }

        protected override void Process()
        {

        }
    }
}
