' Programmer: Damian Zylski
' Date: August 20, 2020
' System: Visual Studio 2017 - Windows 10 Pro
' Project: Student Database VB
'
' Purpose: To read several students from a file and create student objects from them. 

Option Strict On
Option Explicit On

Public Class StudentDatabase

    'Variables
    Dim studentList As New List(Of Student)
    Dim fileName As String = ""

    'Functions
    '***Explain********************************************************************************************
    'Explains how to use the program to user
    Sub Explain()

        'The text to display
        Dim ex As String
        ex = "This program will read in students from a file and output them"

        'Output program instructions
        MsgBox(ex, MsgBoxStyle.Information, "Program Instructions")

    End Sub

    '***getStudents********************************************************************************************
    'reads students from input file
    Function getStudents(fileName As String) As List(Of Student)
        'variables
        Dim students As New List(Of Student)
        Dim s As Student
        Dim id As Integer
        Dim major As String
        Dim level As String
        Dim name As String


        'open the file
        Dim reader As IO.StreamReader
        reader = My.Computer.FileSystem.OpenTextFileReader(fileName)

        'loop and read the students
        Do Until reader.EndOfStream
            id = Convert.ToInt32(reader.ReadLine())
            major = reader.ReadLine()
            level = reader.ReadLine()
            name = reader.ReadLine()
            'create new student object
            s = New Student(name, id, major, level)
            'add to student list
            students.Add(s)
        Loop

        'close reader
        reader.Close()

        Return students
    End Function
    '***printStudents********************************************************************************************
    'print students from list
    Sub printStudents(students As List(Of Student))
        'variables
        Dim n As Integer = 0
        Dim i As Integer = 0

        'get size of list
        n = students.Count() - 1

        'clear box
        OutputBox.Items.Clear()

        'loop and print the students
        For i = 0 To n
            OutputBox.Items.Add("Name: " & students(i).getName())
            OutputBox.Items.Add("Student ID: " & students(i).getID())
            OutputBox.Items.Add("Major: " & students(i).getMajor())
            OutputBox.Items.Add("Level: " & students(i).getLevel())
            OutputBox.Items.Add("")
            students(i).writeOutput()
            students(i).gpa = 4.0
            Console.WriteLine("GPA: " & students(i).gpa)
            Console.WriteLine("")
        Next
    End Sub

    '**ButtonExplain*****************************************************************************************
    Private Sub ButtonExplain_Click(sender As Object, e As EventArgs) Handles ButtonExplain.Click
        Explain()
    End Sub
    '**ButtonFile*****************************************************************************************
    Private Sub ButtonFile_Click(sender As Object, e As EventArgs) Handles ButtonFile.Click
        'Clear output box
        OutputBox.Items.Clear()

        'Set directory
        OFD.InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments)

        'Show dialog for opening files
        OFD.ShowDialog()

        'get the name of the file
        fileName = OFD.FileName
        fileName = fileName.Substring(fileName.LastIndexOf("\") + 1)

        'check that the correct file was opened
        If fileName.Equals("Student.dat") Then
            OutputBox.Items.Add("Student.dat loaded successfully")
        Else
            OutputBox.Items.Add("Incorrect file was chosen!")
        End If
    End Sub
    '**ButtonOutput*****************************************************************************************
    Private Sub ButtonOutput_Click(sender As Object, e As EventArgs) Handles ButtonOutput.Click
        'Check if correct file is loaded
        If fileName.Equals("Student.dat") Then

            'get the words from input file
            studentList = getStudents(OFD.FileName)

            'output the words
            printStudents(studentList)

        Else
            'Give an error message
            MsgBox("Correct file must be loaded to output words!", MsgBoxStyle.Critical, "Error!")
        End If
    End Sub
End Class
