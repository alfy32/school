using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Messages;

namespace AgentCommon
{
    public class Envelope
    {
        public Message message { get; set; }
        public String address { get; set; }

        public Envelope(Message message, String address)
        {
            this.message = message;
            this.address = address;
        }
    }
}
