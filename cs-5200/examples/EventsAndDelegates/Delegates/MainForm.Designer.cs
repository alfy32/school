namespace Delegates
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.expandButton = new System.Windows.Forms.RadioButton();
            this.contractButton = new System.Windows.Forms.RadioButton();
            this.actionGroup = new System.Windows.Forms.GroupBox();
            this.executeButton = new System.Windows.Forms.Button();
            this.sizeLabel = new System.Windows.Forms.Label();
            this.sizeValue = new System.Windows.Forms.Label();
            this.sizeValue2 = new System.Windows.Forms.Label();
            this.sizeLabel2 = new System.Windows.Forms.Label();
            this.sizeValue3 = new System.Windows.Forms.Label();
            this.sizeLabel3 = new System.Windows.Forms.Label();
            this.actionGroup.SuspendLayout();
            this.SuspendLayout();
            // 
            // expandButton
            // 
            this.expandButton.AutoSize = true;
            this.expandButton.Location = new System.Drawing.Point(20, 19);
            this.expandButton.Name = "expandButton";
            this.expandButton.Size = new System.Drawing.Size(60, 17);
            this.expandButton.TabIndex = 0;
            this.expandButton.TabStop = true;
            this.expandButton.Text = "expand";
            this.expandButton.UseVisualStyleBackColor = true;
            this.expandButton.CheckedChanged += new System.EventHandler(this.expandButton_CheckedChanged);
            // 
            // contractButton
            // 
            this.contractButton.AutoSize = true;
            this.contractButton.Location = new System.Drawing.Point(20, 54);
            this.contractButton.Name = "contractButton";
            this.contractButton.Size = new System.Drawing.Size(64, 17);
            this.contractButton.TabIndex = 1;
            this.contractButton.TabStop = true;
            this.contractButton.Text = "contract";
            this.contractButton.UseVisualStyleBackColor = true;
            this.contractButton.CheckedChanged += new System.EventHandler(this.contractButton_CheckedChanged);
            // 
            // actionGroup
            // 
            this.actionGroup.Controls.Add(this.expandButton);
            this.actionGroup.Controls.Add(this.contractButton);
            this.actionGroup.Location = new System.Drawing.Point(22, 29);
            this.actionGroup.Name = "actionGroup";
            this.actionGroup.Size = new System.Drawing.Size(124, 83);
            this.actionGroup.TabIndex = 2;
            this.actionGroup.TabStop = false;
            this.actionGroup.Text = "Action";
            // 
            // executeButton
            // 
            this.executeButton.Location = new System.Drawing.Point(166, 42);
            this.executeButton.Name = "executeButton";
            this.executeButton.Size = new System.Drawing.Size(75, 23);
            this.executeButton.TabIndex = 3;
            this.executeButton.Text = "Execute";
            this.executeButton.UseVisualStyleBackColor = true;
            this.executeButton.Click += new System.EventHandler(this.executeButton_Click);
            // 
            // sizeLabel
            // 
            this.sizeLabel.AutoSize = true;
            this.sizeLabel.Location = new System.Drawing.Point(19, 136);
            this.sizeLabel.Name = "sizeLabel";
            this.sizeLabel.Size = new System.Drawing.Size(33, 13);
            this.sizeLabel.TabIndex = 4;
            this.sizeLabel.Text = "Size: ";
            // 
            // sizeValue
            // 
            this.sizeValue.AutoSize = true;
            this.sizeValue.Location = new System.Drawing.Point(58, 136);
            this.sizeValue.Name = "sizeValue";
            this.sizeValue.Size = new System.Drawing.Size(88, 13);
            this.sizeValue.TabIndex = 5;
            this.sizeValue.Text = "(size shows here)";
            // 
            // sizeValue2
            // 
            this.sizeValue2.AutoSize = true;
            this.sizeValue2.Location = new System.Drawing.Point(58, 167);
            this.sizeValue2.Name = "sizeValue2";
            this.sizeValue2.Size = new System.Drawing.Size(88, 13);
            this.sizeValue2.TabIndex = 7;
            this.sizeValue2.Text = "(size shows here)";
            // 
            // sizeLabel2
            // 
            this.sizeLabel2.AutoSize = true;
            this.sizeLabel2.Location = new System.Drawing.Point(19, 167);
            this.sizeLabel2.Name = "sizeLabel2";
            this.sizeLabel2.Size = new System.Drawing.Size(33, 13);
            this.sizeLabel2.TabIndex = 6;
            this.sizeLabel2.Text = "Size: ";
            // 
            // sizeValue3
            // 
            this.sizeValue3.AutoSize = true;
            this.sizeValue3.Location = new System.Drawing.Point(58, 197);
            this.sizeValue3.Name = "sizeValue3";
            this.sizeValue3.Size = new System.Drawing.Size(88, 13);
            this.sizeValue3.TabIndex = 9;
            this.sizeValue3.Text = "(size shows here)";
            // 
            // sizeLabel3
            // 
            this.sizeLabel3.AutoSize = true;
            this.sizeLabel3.Location = new System.Drawing.Point(19, 197);
            this.sizeLabel3.Name = "sizeLabel3";
            this.sizeLabel3.Size = new System.Drawing.Size(33, 13);
            this.sizeLabel3.TabIndex = 8;
            this.sizeLabel3.Text = "Size: ";
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 262);
            this.Controls.Add(this.sizeValue3);
            this.Controls.Add(this.sizeLabel3);
            this.Controls.Add(this.sizeValue2);
            this.Controls.Add(this.sizeLabel2);
            this.Controls.Add(this.sizeValue);
            this.Controls.Add(this.sizeLabel);
            this.Controls.Add(this.executeButton);
            this.Controls.Add(this.actionGroup);
            this.Name = "MainForm";
            this.Text = "Main Form";
            this.actionGroup.ResumeLayout(false);
            this.actionGroup.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.RadioButton expandButton;
        private System.Windows.Forms.RadioButton contractButton;
        private System.Windows.Forms.GroupBox actionGroup;
        private System.Windows.Forms.Button executeButton;
        private System.Windows.Forms.Label sizeLabel;
        private System.Windows.Forms.Label sizeValue;
        private System.Windows.Forms.Label sizeValue2;
        private System.Windows.Forms.Label sizeLabel2;
        private System.Windows.Forms.Label sizeValue3;
        private System.Windows.Forms.Label sizeLabel3;
    }
}

