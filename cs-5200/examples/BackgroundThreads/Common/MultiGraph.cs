using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;

namespace Common
{
    public partial class MultiGraph : UserControl
    {
        private GraphDataSet graphDataSet;

        private int rowValue;
        private int rowIncrement = 10;
        private int standardMargin = 16;
        private float tickMargin = 1.0F;

        private int minGraphHeight;
        private int minGraphWidth;
        private int graphInnerHeight;
        private int graphOuterHeight;
        private int graphInnerWidth;
        private int graphOuterWidth;
        private float colInnerWidth;
        private float colOuterWidth;
        private int rowOuterHeight;
        private Brush labelBrush;
        private Brush columnBrush;
        private Brush rowLabelBrush;
        private Bitmap image;
        private Graphics graphics;

        private GraphDataSetChangedHandler setupChangeHandler = null;
        private GraphDataSetChangedHandler dataChangeHandler = null;

        public MultiGraph()
        {
            InitializeComponent();
            setupChangeHandler = new GraphDataSetChangedHandler(ComputeDisplaySettings);
            dataChangeHandler = new GraphDataSetChangedHandler(DataChanged);
        }

        #region Public Properties
        public int RowIncrement
        {
            get { return rowIncrement; }
            set
            {
                rowIncrement = value;
                this.ComputeDisplaySettings();
            }
        }

        public int StandardMargin
        {
            get { return standardMargin; }
            set
            {
                standardMargin = value;
                this.ComputeDisplaySettings();
            }
        }

        public float TickMargin
        {
            get { return tickMargin; }
            set
            {
                tickMargin = value;
                this.ComputeDisplaySettings();
            }
        }

        public GraphDataSet GraphDataSet
        {
            get { return graphDataSet; }
            set 
            {
                if (graphDataSet != null)
                {
                    graphDataSet.DataChanged -= dataChangeHandler;
                    graphDataSet.SetupChanged -= setupChangeHandler;
                }

                graphDataSet = value;

                if (graphDataSet != null)
                {
                    ComputeDisplaySettings();
                    graphDataSet.DataChanged += dataChangeHandler;
                    graphDataSet.SetupChanged += setupChangeHandler;
                }
            }
        }

        #endregion

        #region Event Handlers
        private void MultiGraph_Load(object sender, EventArgs e)
        {
            labelBrush = new SolidBrush(this.ForeColor);
            rowLabelBrush = new SolidBrush(Color.White);
            ComputeDisplaySettings();
        }

        private void DataChanged()
        {
            RedrawGraphs();
            this.Invalidate();
        }

        private void MultiGraph_Resize(object sender, EventArgs e)
        {
            this.ComputeDisplaySettings();
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            base.OnPaint(e);
            if (graphDataSet != null && graphDataSet.Count>0)
            {
                lock (graphDataSet.GraphLock)
                {
                    if (image != null)
                        e.Graphics.DrawImage(image, 0, 0);
                }
            }
        }

        #endregion

        #region Private Helper Methods
        private void ComputeDisplaySettings()
        {
            if (graphDataSet != null && graphDataSet.Count > 0)
            {
                lock (graphDataSet.GraphLock)
                {
                    minGraphHeight = 4 * graphDataSet.Rows + 2 * standardMargin;
                    graphOuterHeight = Math.Max(minGraphHeight, (ClientSize.Height - standardMargin) / graphDataSet.Count);
                    minGraphWidth = 4 * graphDataSet.Columns + 2 * standardMargin;
                    graphOuterWidth = Math.Max(minGraphWidth, ClientSize.Width);

                    this.Location = new Point(0, 0);

                    graphInnerHeight = graphOuterHeight - 2 * standardMargin;
                    graphInnerWidth = graphOuterWidth - 2 * standardMargin;
                    colOuterWidth = graphInnerWidth / graphDataSet.Columns;
                    colInnerWidth = colOuterWidth - 2 * tickMargin;

                    rowOuterHeight = graphInnerHeight / graphDataSet.Rows;
                    //_logger.Debug("In ComputeDisplaySetting, leaving critical section");

                    int height = graphDataSet.Count * graphOuterHeight + standardMargin;
                    image = new Bitmap(graphOuterWidth, height);
                    graphics = Graphics.FromImage(image);

                    if (ClientSize.Width != graphOuterWidth || ClientSize.Height != height)
                        this.Size = new Size(graphOuterWidth, height);
                }
                RedrawGraphs();
                this.Invalidate();
            }
        }

        private void RedrawGraphs()
        {
            if (graphDataSet != null && graphDataSet.Count > 0 && labelBrush!=null)
            {
                lock (graphDataSet.GraphLock)
                {
                    //_logger.Debug("In RedrawGraphs, entering critical section");
                    graphics.Clear(this.BackColor);
                    for (int i = 0; i < graphDataSet.Count; i++)
                    {
                        //_logger.DebugFormat("Draw graph {0}", i);
                        int xOffset = standardMargin;
                        int yOffset = standardMargin + i * graphOuterHeight + standardMargin;
                        DrawGraph(graphDataSet[i], xOffset, yOffset);
                    }
                    //_logger.Debug("In RedrawGraphs, leaving critical section");
                }
            }
        }

        private void DrawGraph(GraphData data, int xOffset, int yOffset)
        {
            if (graphDataSet.Rows>0)
            {
                float maxValue = Math.Max(10, data.ComputeMaxValue);
                double tmp = maxValue / rowIncrement;
                maxValue = Convert.ToSingle(Math.Floor(tmp)*rowIncrement + (Math.Ceiling(tmp) - Math.Floor(tmp))*rowIncrement);
                rowValue = Convert.ToInt32(maxValue / graphDataSet.Rows);

                float scale = graphInnerHeight / maxValue;

                graphics.DrawString(data.StatsLabel, this.Font, labelBrush, xOffset, yOffset - this.Font.Height - 4);
                graphics.FillRectangle(Brushes.Black, xOffset, yOffset, graphInnerWidth, graphInnerHeight);
                DrawGridLines(xOffset, yOffset);
                DrawData(data, xOffset, yOffset, scale);
            }
        }

        private void DrawGridLines(int xOffset, int yOffset)
        {
            for (int i = 1; i < graphDataSet.Rows; i++)
            {
                int y = yOffset + (graphInnerHeight - i * rowOuterHeight);
                graphics.DrawLine(Pens.Gray, xOffset, y, xOffset + graphInnerWidth - 1, y);

                int rowLabel = i * rowValue;
                graphics.DrawString(rowLabel.ToString(), this.Font, rowLabelBrush, xOffset, y - this.Font.Height);
            }
        }

        private void DrawData(GraphData data, int xOffset, int yOffset, float scale)
        {
            for (int colIndex = 0; colIndex < graphDataSet.Columns; colIndex++)
            {
                int fadeValue = 255-Convert.ToInt32(200*(graphDataSet.Columns - colIndex)/graphDataSet.Columns);
                DrawColumn(data[colIndex], fadeValue, xOffset + colIndex * colOuterWidth + tickMargin, yOffset, scale);
            }
        }

        private void DrawColumn(float colValue, int fadeValue, float xOffset, float yOffset, float scale)
        {
            if (colValue > 0)
            {
                float columnHeight = colValue * scale;
                float percentOfMax = columnHeight / graphInnerHeight;
                float columnTop = Math.Max((float)0, graphInnerHeight - columnHeight);

                Color barColor = Color.FromArgb(fadeValue, Color.YellowGreen);
                columnBrush = new SolidBrush(barColor);

                graphics.FillRectangle(columnBrush, Convert.ToInt32(xOffset + tickMargin),
                                                    Convert.ToInt32(yOffset + columnTop),
                                                    Convert.ToInt32(colInnerWidth),
                                                    Convert.ToInt32(columnHeight));
            }
        }
        #endregion


    }
}
