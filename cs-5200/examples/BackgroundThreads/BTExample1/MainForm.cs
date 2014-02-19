using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using Common;

namespace BTExample1
{
    public partial class MainForm : Form
    {
        private bool producerStarted = false;
        private bool producerSuspended = false;
        private bool packagerStarted = false;
        private bool packagerSuspended = false;
        private bool consumerStarted = false;
        private bool consumerSuspended = false;

        private Producer producer;
        private Packager packager;
        private Consumer consumer;

        private List<string> queueNames = new List<string> { "Built", "Packaged" };
        private List<string> statsNames = new List<string>();
        private List<IMonitorable> queues = new List<IMonitorable>();

        private MonitorForm monitorForm;

        public MainForm()
        {
            InitializeComponent();
        }

        private void SetupDisplaySettings(bool started, bool suspended, Button startButton, Button suspendButton)
        {
            startButton.Text = (started) ? "Stop" : "Start";
            if (started)
            {
                suspendButton.Visible = true;
                suspendButton.Text = (suspended) ? "Resume" : "Suspend";
            }
            else
                suspendButton.Visible = false;
        }

        private void producerStartStopButton_Click(object sender, EventArgs e)
        {
            producerStarted = !producerStarted;
            if (producerStarted)
                producer.Start();
            else
                producer.Stop();
            SetupDisplaySettings(producerStarted, producerSuspended, producerStartStopButton, producerSuspendResumeButton);
        }

        private void producerSuspendResumeButton_Click(object sender, EventArgs e)
        {
            producerSuspended = !producerSuspended;
            if (producerSuspended)
                producer.Suspend();
            else
                producer.Resume();
            SetupDisplaySettings(producerStarted, producerSuspended, producerStartStopButton, producerSuspendResumeButton);
        }

        private void packagerStartStopButton_Click(object sender, EventArgs e)
        {
            packagerStarted = !packagerStarted;
            if (packagerStarted)
                packager.Start();
            else
                packager.Stop();
            SetupDisplaySettings(packagerStarted, packagerSuspended, packagerStartStopButton, packagerSuspendResumeButton);
        }

        private void packagerSuspendResumeButton_Click(object sender, EventArgs e)
        {
            packagerSuspended = !packagerSuspended;
            if (packagerSuspended)
                packager.Suspend();
            else
                packager.Resume();
            SetupDisplaySettings(packagerStarted, packagerSuspended, packagerStartStopButton, packagerSuspendResumeButton);
        }

        private void consumingStartStopButton_Click(object sender, EventArgs e)
        {
            consumerStarted = !consumerStarted;
            if (consumerStarted)
                consumer.Start();
            else
                consumer.Stop();
            SetupDisplaySettings(consumerStarted, consumerSuspended, consumingStartStopButton, consumingSuspendResumeButton);
        }

        private void consumingSuspendResumeButton_Click(object sender, EventArgs e)
        {
            consumerSuspended = !consumerSuspended;
            if (consumerSuspended)
                consumer.Suspend();
            else
                consumer.Stop();
            SetupDisplaySettings(consumerStarted, consumerSuspended, consumingStartStopButton, consumingSuspendResumeButton);
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            foreach (string name in queueNames)
            {
                MonitoredQueue mQueue = new MonitoredQueue(name);
                queues.Add(mQueue);
                statsNames.Add(mQueue.StatName);
            }

            monitorForm = new MonitorForm();
            monitorForm.MonitoredObjects = queues;
            monitorForm.DisplayedStats = statsNames;
            monitorForm.Show();

            producer = new Producer((MonitoredQueue) queues[0], Convert.ToInt32(productionTime.Value));
            packager = new Packager((MonitoredQueue) queues[0], (MonitoredQueue) queues[1], Convert.ToInt32(packagingTime.Value));
            consumer = new Consumer((MonitoredQueue) queues[1], Convert.ToInt32(consumingTime.Value));
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (monitorForm != null)
                monitorForm.Close();
        }

        private void productionTime_ValueChanged(object sender, EventArgs e)
        {
            producer.SimulatedWorkTime = Convert.ToInt32(productionTime.Value);
        }

        private void packagingTime_ValueChanged(object sender, EventArgs e)
        {
            packager.SimulatedWorkTime = Convert.ToInt32(packagingTime.Value);
        }

        private void consumingTime_ValueChanged(object sender, EventArgs e)
        {
            consumer.SimulatedWorkTime = Convert.ToInt32(consumingTime.Value);
        }

        private void exitButton_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
