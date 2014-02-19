using System;
using System.Collections.Generic;
using System.Text;

namespace Common
{
    public delegate void GraphDataSetChangedHandler();

    public class GraphDataSet
    {
        private GraphData[] graphDataSets = null;
        private int columns;

        public int Rows { get; set; }
        public object GraphLock { get; set; }

        public event GraphDataSetChangedHandler SetupChanged;
        public event GraphDataSetChangedHandler DataChanged;

        public GraphDataSet(List<string> statsDef, int rows, int columns)
        {
            GraphLock = new object();
            Initialize(statsDef, rows, columns);
        }

        #region Public Methods

        public void Initialize(List<string> statsDef, int rows, int columns)
        {
            this.Rows = rows;
            this.columns = columns;
            SetupGraphData(statsDef, columns);
            if (SetupChanged!=null)
                SetupChanged();
        }

        public int Count
        {
            get { return (graphDataSets == null) ? 0 : graphDataSets.Length; }
        }

        public int Columns
        {
            get { return columns; }
            set
            {
                foreach (GraphData data in graphDataSets)
                    data.NumberOfColumns = value;

                if (SetupChanged != null)
                    SetupChanged();
            }
        }

        public GraphData this[int index]
        {
            get { return (graphDataSets==null || index<0 || index>=graphDataSets.Length) ? null : graphDataSets[index]; }
        }

        public GraphData this[string code]
        {
            get
            {
                GraphData result = null;
                for (int i=0; i<graphDataSets.Length && result==null; i++)
                {
                    if (graphDataSets[i].StatsCode==code)
                        result = graphDataSets[i];
                }
                return result;
            }
        }

        public void RollForward()
        {
            foreach (GraphData data in graphDataSets)
                data.RollForward();
            if (DataChanged != null)
                DataChanged();
        }

        #endregion

        #region Helper Functions

        private void SetupGraphData(List<string> statsDef, int columns)
        {
            if (statsDef != null && columns > 0)
            {

                GraphData[] newGraphDataSets = new GraphData[statsDef.Count];
                for (int i = 0; i < statsDef.Count; i++)
                {
                    if (!string.IsNullOrEmpty(statsDef[i]))
                    {
                        string[] tmp = statsDef[i].Split(new char[] { '|' });
                        string statsCode = tmp[0].Trim();
                        string statsLabel = (tmp.Length > 1) ? tmp[1].Trim() : statsCode;
                        newGraphDataSets[i] = new GraphData(statsCode, statsLabel, columns);
                    }
                }

                lock (GraphLock)
                {
                    graphDataSets = newGraphDataSets;
                }
            }
        }

        #endregion
    }
}
