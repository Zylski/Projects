<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class WordChecker
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(WordChecker))
        Me.OutputBox = New System.Windows.Forms.ListBox()
        Me.WordsInput = New System.Windows.Forms.RichTextBox()
        Me.ButtonCheck = New System.Windows.Forms.Button()
        Me.ButtonExplain = New System.Windows.Forms.Button()
        Me.ButtonExit = New System.Windows.Forms.Button()
        Me.LabelIn = New System.Windows.Forms.Label()
        Me.PictureBox1 = New System.Windows.Forms.PictureBox()
        Me.PictureBox2 = New System.Windows.Forms.PictureBox()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox2, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'OutputBox
        '
        Me.OutputBox.FormattingEnabled = True
        Me.OutputBox.ItemHeight = 16
        Me.OutputBox.Location = New System.Drawing.Point(12, 333)
        Me.OutputBox.Name = "OutputBox"
        Me.OutputBox.Size = New System.Drawing.Size(548, 180)
        Me.OutputBox.TabIndex = 0
        '
        'WordsInput
        '
        Me.WordsInput.Location = New System.Drawing.Point(99, 36)
        Me.WordsInput.Name = "WordsInput"
        Me.WordsInput.Size = New System.Drawing.Size(374, 182)
        Me.WordsInput.TabIndex = 1
        Me.WordsInput.Text = ""
        '
        'ButtonCheck
        '
        Me.ButtonCheck.Location = New System.Drawing.Point(12, 288)
        Me.ButtonCheck.Name = "ButtonCheck"
        Me.ButtonCheck.Size = New System.Drawing.Size(189, 39)
        Me.ButtonCheck.TabIndex = 2
        Me.ButtonCheck.Text = "Check Words"
        Me.ButtonCheck.UseVisualStyleBackColor = True
        '
        'ButtonExplain
        '
        Me.ButtonExplain.Location = New System.Drawing.Point(207, 288)
        Me.ButtonExplain.Name = "ButtonExplain"
        Me.ButtonExplain.Size = New System.Drawing.Size(175, 39)
        Me.ButtonExplain.TabIndex = 3
        Me.ButtonExplain.Text = "Instructions"
        Me.ButtonExplain.UseVisualStyleBackColor = True
        '
        'ButtonExit
        '
        Me.ButtonExit.Location = New System.Drawing.Point(388, 288)
        Me.ButtonExit.Name = "ButtonExit"
        Me.ButtonExit.Size = New System.Drawing.Size(172, 39)
        Me.ButtonExit.TabIndex = 4
        Me.ButtonExit.Text = "Exit"
        Me.ButtonExit.UseVisualStyleBackColor = True
        '
        'LabelIn
        '
        Me.LabelIn.AutoSize = True
        Me.LabelIn.Location = New System.Drawing.Point(165, 221)
        Me.LabelIn.Name = "LabelIn"
        Me.LabelIn.Size = New System.Drawing.Size(217, 17)
        Me.LabelIn.TabIndex = 5
        Me.LabelIn.Text = "Enter words into the box to check"
        '
        'PictureBox1
        '
        Me.PictureBox1.Image = CType(resources.GetObject("PictureBox1.Image"), System.Drawing.Image)
        Me.PictureBox1.Location = New System.Drawing.Point(12, 36)
        Me.PictureBox1.Name = "PictureBox1"
        Me.PictureBox1.Size = New System.Drawing.Size(81, 96)
        Me.PictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom
        Me.PictureBox1.TabIndex = 6
        Me.PictureBox1.TabStop = False
        '
        'PictureBox2
        '
        Me.PictureBox2.Image = CType(resources.GetObject("PictureBox2.Image"), System.Drawing.Image)
        Me.PictureBox2.Location = New System.Drawing.Point(479, 36)
        Me.PictureBox2.Name = "PictureBox2"
        Me.PictureBox2.Size = New System.Drawing.Size(81, 96)
        Me.PictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom
        Me.PictureBox2.TabIndex = 7
        Me.PictureBox2.TabStop = False
        '
        'WordChecker
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(8.0!, 16.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(572, 526)
        Me.Controls.Add(Me.PictureBox2)
        Me.Controls.Add(Me.PictureBox1)
        Me.Controls.Add(Me.LabelIn)
        Me.Controls.Add(Me.ButtonExit)
        Me.Controls.Add(Me.ButtonExplain)
        Me.Controls.Add(Me.ButtonCheck)
        Me.Controls.Add(Me.WordsInput)
        Me.Controls.Add(Me.OutputBox)
        Me.Name = "WordChecker"
        Me.Text = "Reversal Checker"
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox2, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents OutputBox As ListBox
    Friend WithEvents WordsInput As RichTextBox
    Friend WithEvents ButtonCheck As Button
    Friend WithEvents ButtonExplain As Button
    Friend WithEvents ButtonExit As Button
    Friend WithEvents LabelIn As Label
    Friend WithEvents PictureBox1 As PictureBox
    Friend WithEvents PictureBox2 As PictureBox
End Class
