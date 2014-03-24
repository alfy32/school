using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using MathServiceTestApp.MathService; // Add Reference

namespace MathServiceTestApp
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void btnCalculate_Click(object sender, EventArgs e)
        {
            MathServiceClient client = new MathServiceClient();

            Int32 num1 = Convert.ToInt32(txtNum1.Text);
            Int32 num2 = Convert.ToInt32(txtNum2.Text);


            if (cboOperation.Text == "Add")
            {
                txtResult.Text = client.Add(num1, num2).ToString();
            }
            else if (cboOperation.Text == "Subtract")
            {
                txtResult.Text = client.Subtract(num1, num2).ToString();
            }
            else if (cboOperation.Text == "Multiply")
            {
                txtResult.Text = client.Multiply(num1, num2).ToString();
            }
            else
            {
                txtResult.Text = client.Divide(num1, num2).ToString();
            }
        }
    }
}
