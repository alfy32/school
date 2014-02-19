using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;

namespace Common
{
    public class GraphData
    {
        public string StatsCode { get; set; }
        public string StatsLabel { get; set; }
        public float TenativeValue { get; set; }

        private int numberOfColumns = -1;
        private float[] ticks;
        private float maxValue = 0;
        private bool maxValueDirty = true;


        public GraphData(string code, string label, int columns)
        {
            StatsCode = code;
            StatsLabel = label;
            NumberOfColumns = columns;
        }

        public int NumberOfColumns
        {
            get { return numberOfColumns; }
            set
            {
                numberOfColumns = (value<0) ? 0 : value;
                float[] newTicks = new float[numberOfColumns];
                if (ticks != null)
                {
                    for (int i = 0; i < ticks.Length && i < newTicks.Length; i++)
                        newTicks[i] = ticks[i];
                }
                ticks = newTicks;
            }
        }

        public float this[int columnIndex]
        {
            get
            {
                float result = 0;
                if (ticks!=null & columnIndex >= 0 && columnIndex < numberOfColumns)
                    result = ticks[columnIndex];
                return result;
            }
            set
            {
                if (ticks != null && columnIndex >= 0 && columnIndex < numberOfColumns && ticks[columnIndex] != value)
                {
                    ticks[columnIndex] = value;
                    maxValueDirty = true;
                }
            }
        }

        public float ComputeMaxValue
        {
            get
            {
                if (maxValueDirty)
                {
                    maxValueDirty = false;
                    maxValue = 0;
                    for (int colIndex = 0; colIndex < numberOfColumns; colIndex++)
                        if (ticks[colIndex] > maxValue)
                            maxValue = ticks[colIndex];
                }
                return maxValue;
            }
        }

        public void AddToEnd(float value)
        {
            for (int index = 0; index < numberOfColumns - 1; index++)
                ticks[index] = ticks[index + 1];

            ticks[numberOfColumns-1] = value;
            maxValueDirty = true;
        }

        public void RollForward()
        {
            AddToEnd(TenativeValue);
            TenativeValue = 0;
        }

    }
}
