using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Messages;

namespace AgentCommon
{
    class Envelope
    {
        Message message { get; set; }
        string address { get; set; }
    }
}
