using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AgentCommon
{
    public class MessageQueue
    {
        Queue<Envelope> queue;

        public MessageQueue()
        {
            queue = new Queue<Envelope>();
        }

        public void push(Envelope envelope)
        {
            queue.Enqueue(envelope);
        }

        public Envelope pop()
        {
            return queue.Dequeue();
        }

        public bool hasItems()
        {
            return queue.Count > 0;
        }
    }
}
