using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace Common
{
    public partial class MonitorForm : Form
    {

        private List<string> displayedStats;
        private List<IMonitorable> monitoredObjects = null;
        private MonitoringStatHandler monitoringDelegate = null;

        private bool loaded = false;

        private int refreshInterval = 250;

        private int graphColumns = 60;
        private int graphRows = 5;

        private int rowIncrement = 10;
        private int standardMargin = 16;

        private Timer redrawTimer;

        public MonitorForm()
        {
            InitializeComponent();
        }

        #region Public Properties
        public int RefreshInterval
        { 
            get { return refreshInterval; }
            set { refreshInterval = value; }
        }

        public int RowIncrement
        {
            get { return rowIncrement; }
            set
            {
                rowIncrement = value;
                statsMultiGraph.RowIncrement = rowIncrement;
            }
        }

        public int StandardMargin
        {
            get { return standardMargin; }
            set
            {
                standardMargin = value;
                statsMultiGraph.StandardMargin = standardMargin;
            }
        }

        public float TickMargin
        {
            get { return statsMultiGraph.TickMargin; }
            set { statsMultiGraph.TickMargin = value; }
        }

        public int GraphColumns
        {
            get { return graphColumns; }
            set
            {
                graphColumns = value;
                SetupMultiGraph();
            }
        }

        public int GraphRows
        {
            get { return graphRows; }
            set
            {
                graphRows = value;
                SetupMultiGraph();
            }
        }

        public List<IMonitorable> MonitoredObjects
        {
            get { return monitoredObjects; }
            set
            {
                if (monitoredObjects != null && monitoredObjects.Count > 0 && monitoringDelegate != null)
                {
                    foreach (IMonitorable mObj in monitoredObjects)
                        mObj.MonitorEvent -= monitoringDelegate;
                }

                monitoringDelegate = null;
                monitoredObjects = value;

                if (monitoredObjects == null)
                    monitoredObjects = new List<IMonitorable>();

                if (monitoredObjects.Count > 0)
                {
                    monitoringDelegate = new MonitoringStatHandler(RecordMonitorData);
                    foreach (IMonitorable mObj in monitoredObjects)
                        mObj.MonitorEvent += monitoringDelegate;
                }
            }
        }

        public List<string> DisplayedStats
        {
            get { return displayedStats; }
            set
            {
                displayedStats = value;
                SetupMultiGraph();
            }
        }

        #endregion

        #region Event Handlers
        private void SetupMultiGraph()
        {          
            if (displayedStats!=null && GraphRows>0 && GraphColumns>0)
                statsMultiGraph.GraphDataSet = new GraphDataSet(displayedStats, GraphRows, GraphColumns);
        }

        private void MonitorForm_Load(object sender, EventArgs e)
        {
            redrawTimer = new Timer();
            redrawTimer.Tick += new EventHandler(RedrawTimer_Tick);
            redrawTimer.Interval = refreshInterval;
            redrawTimer.Start();

            this.SetStyle(ControlStyles.UserPaint, true);
            this.Invalidate();

            loaded = true;
        }

        private void RecordMonitorData(MonitoringStat stat)
        {
            if (this.InvokeRequired)
            {
                MonitoringStatHandler mth = new MonitoringStatHandler(RecordMonitorData);
                this.BeginInvoke(mth, new object[] {stat });
            }
            else
            {
                if (loaded)
                {
                    if (stat != null)
                    {
                        lock (statsMultiGraph.GraphDataSet.GraphLock)
                        {
                            GraphData data = statsMultiGraph.GraphDataSet[stat.Name];
                            if (data != null)
                            {
                                if (stat.IsDelta)
                                    data.TenativeValue += stat.Value;
                                else
                                    data.TenativeValue = stat.Value;
                            }                            
                        }
                    }
                }
            }
        }

        private void MonitorForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (redrawTimer!=null)
                redrawTimer.Stop();

            // Be sure to de-register this form from the monitored objects
            if (MonitoredObjects != null && monitoringDelegate != null)
            {
                foreach (IMonitorable mObj in monitoredObjects)
                    mObj.MonitorEvent -= monitoringDelegate;
            }
        }

        void RedrawTimer_Tick(object sender, EventArgs e)
        {
            if (loaded && statsMultiGraph.GraphDataSet!=null)
                statsMultiGraph.GraphDataSet.RollForward();
        }

        #endregion

    }
}
