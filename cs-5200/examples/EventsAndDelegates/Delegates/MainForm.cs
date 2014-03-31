using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Delegates
{
    public partial class MainForm : Form
    {
        private Widget myWidget = new Widget();
        private Widget.IntMethod actionMethod;

        public MainForm()
        {
            InitializeComponent();
            myWidget.SizeChanged += new Widget.IntMethod(DisplayNewSize);
            myWidget.SizeChanged += new Widget.IntMethod(DisplayNewSize2);
            myWidget.SizeChanged += new Widget.IntMethod(DisplayNewSize3);            
        }

        private void expandButton_CheckedChanged(object sender, EventArgs e)
        {
            if (expandButton.Checked)
                actionMethod = new Widget.IntMethod(myWidget.Expand);
        }

        private void contractButton_CheckedChanged(object sender, EventArgs e)
        {
            if (contractButton.Checked)
                actionMethod = new Widget.IntMethod(myWidget.Contract);
        }

        private void executeButton_Click(object sender, EventArgs e)
        {
            actionMethod(10);
        }

        private void DisplayNewSize(int size)
        {
            sizeValue.Text = size.ToString();
        }

        private void DisplayNewSize2(int size)
        {
            sizeValue2.Text = size.ToString();
        }

        private void DisplayNewSize3(int size)
        {
            sizeValue3.Text = size.ToString();
        }

    }
}
