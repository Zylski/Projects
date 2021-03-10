' Class Student
' Damian Zylski
' 08/20/2020
Public Class Student
    Inherits Person

    'Attributes
    Private studentID As Integer
    Private major As String
    Private level As String

    'Constructors
    Public Sub New()
        MyBase.New()
        studentID = 0
        major = "Blank"
        level = "Blank"
    End Sub

    Public Sub New(n As String, id As Integer, m As String, l As String)
        MyBase.New(n)
        studentID = id
        major = m
        level = l
    End Sub

    'Setters
    Public Sub setID(id As Integer)
        studentID = id
    End Sub
    Public Sub setMajor(m As String)
        major = m
    End Sub
    Public Sub setLevel(l As String)
        level = l
    End Sub
    'Getters
    Public Function getID() As Integer
        Return studentID
    End Function
    Public Function getMajor() As String
        Return major
    End Function
    Public Function getLevel() As String
        Return level
    End Function

    'Output
    Public Overloads Sub writeOutput()
        Console.WriteLine("Name: " & MyBase.getName())
        Console.WriteLine("Student ID: " & studentID)
        Console.WriteLine("Major: " & major)
        Console.WriteLine("Level: " & level)
    End Sub
    'Equal
    Public Overloads Function equal(s As Student) As Boolean
        If (Me.studentID.Equals(s.studentID)) Then
            Return True
        Else
            Return False
        End If
    End Function

    'CompareTo
    Public Overloads Function compareTo(s As Student) As Integer
        If (Me.studentID > s.studentID) Then
            Return 1
        ElseIf (Me.studentID < s.studentID) Then
            Return -1
        ElseIf (Me.studentID = s.studentID) Then
            Return 0
        Else
            Return 0
        End If
    End Function

    Public Property gpa As Double

End Class

