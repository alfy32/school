using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Delegates
{
    public class Widget
    {
        public delegate void IntMethod(int param);

        public event IntMethod SizeChanged;

        private int size = 10;
        private int height = 20;

        public Widget() {}

        public int Size
        {
            get { return size; }
            set
            {
                size=value;
                if (SizeChanged != null)
                    SizeChanged(size);
            }
        }

        public int Height
        {
            get { return height; }
            set { height=value; }
        }

        public void Expand(int amount)
        {
            Size += amount;
            Height += amount;
        }

        public void Contract(int amount)
        {
            Size -= amount;
            Height -= amount;
        }

    }
}
