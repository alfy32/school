using System;
using System.Collections.Generic;
using System.Collections.Concurrent;
using System.Linq;
using System.Text;
using System.Threading;

namespace Common
{
    public class MonitoredQueue : IMonitorable
    {
        private string queueName;
        ConcurrentQueue<Widget> myQueue = new ConcurrentQueue<Widget>();

        private MonitoringStat monitoringStats;
        private MonitoringStat tmpStats;

        private AutoResetEvent stateChanged = new AutoResetEvent(false);

        public event MonitoringStatHandler MonitorEvent;

        public MonitoredQueue(string name)
        {
            queueName = name;
            monitoringStats = new MonitoringStat(string.Format("{0} Queue Size", queueName), 0, false);
            SendMonitoringStates();
        }

        public AutoResetEvent StateChanged
        {
            get { return stateChanged; }
        }

        public string StatName
        {
            get { return monitoringStats.Name; }
        }

        public void Enqueue(Widget widget)
        {
            if (widget != null)
            {
                myQueue.Enqueue(widget);
                monitoringStats.Value = myQueue.Count;
                SendMonitoringStates();
                StateChanged.Reset();
            }
        }

        public Widget Dequeue()
        {
            Widget result;

            if (!myQueue.TryDequeue(out result))
                result = null;
            else
            {
                monitoringStats.Value = myQueue.Count;
                SendMonitoringStates();
                StateChanged.Reset();
            }

            return result;
        }

        private void SendMonitoringStates()
        {
            if (MonitorEvent!=null)
            {
                tmpStats = new MonitoringStat(monitoringStats);
                MonitorEvent(tmpStats);
            }
        }
    }
}
