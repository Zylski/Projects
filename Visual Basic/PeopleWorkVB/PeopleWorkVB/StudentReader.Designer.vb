<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class StudentDatabase
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
        Me.OutputBox = New System.Windows.Forms.ListBox()
        Me.ButtonFile = New System.Windows.Forms.Button()
        Me.ButtonExplain = New System.Windows.Forms.Button()
        Me.OFD = New System.Windows.Forms.OpenFileDialog()
        Me.ButtonOutput = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'OutputBox
        '
        Me.OutputBox.FormattingEnabled = True
        Me.OutputBox.ItemHeight = 16
        Me.OutputBox.Location = New System.Drawing.Point(12, 167)
        Me.OutputBox.Name = "OutputBox"
        Me.OutputBox.Size = New System.Drawing.Size(420, 292)
        Me.OutputBox.TabIndex = 0
        '
        'ButtonFile
        '
        Me.ButtonFile.Location = New System.Drawing.Point(12, 113)
        Me.ButtonFile.Name = "ButtonFile"
        Me.ButtonFile.Size = New System.Drawing.Size(131, 37)
        Me.ButtonFile.TabIndex = 1
        Me.ButtonFile.Text = "Open File"
        Me.ButtonFile.UseVisualStyleBackColor = True
        '
        'ButtonExplain
        '
        Me.ButtonExplain.Location = New System.Drawing.Point(149, 113)
        Me.ButtonExplain.Name = "ButtonExplain"
        Me.ButtonExplain.Size = New System.Drawing.Size(136, 37)
        Me.ButtonExplain.TabIndex = 2
        Me.ButtonExplain.Text = "Explain"
        Me.ButtonExplain.UseVisualStyleBackColor = True
        '
        'OFD
        '
        Me.OFD.FileName = "OpenFileDialog1"
        '
        'ButtonOutput
        '
        Me.ButtonOutput.Location = New System.Drawing.Point(291, 113)
        Me.ButtonOutput.Name = "ButtonOutput"
        Me.ButtonOutput.Size = New System.Drawing.Size(138, 37)
        Me.ButtonOutput.TabIndex = 3
        Me.ButtonOutput.Text = "Output Students"
        Me.ButtonOutput.UseVisualStyleBackColor = True
        '
        'StudentDatabase
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(8.0!, 16.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(441, 471)
        Me.Controls.Add(Me.ButtonOutput)
        Me.Controls.Add(Me.ButtonExplain)
        Me.Controls.Add(Me.ButtonFile)
        Me.Controls.Add(Me.OutputBox)
        Me.Name = "StudentDatabase"
        Me.Text = "Student Database Reader"
        Me.ResumeLayout(False)

    End Sub

    Friend WithEvents OutputBox As ListBox
    Friend WithEvents ButtonFile As Button
    Friend WithEvents ButtonExplain As Button
    Friend WithEvents OFD As OpenFileDialog
    Friend WithEvents ButtonOutput As Button
End Class
