' Class Person
' Damian Zylski
' 08/20/2020
Public Class Person
    'Attributes
    Private name As String

    'Constructors
    Public Sub New()
        name = "Blank"
    End Sub

    Public Sub New(n As String)
        name = n
    End Sub

    'Setters
    Public Sub setName(n As String)
        name = n
    End Sub
    'Getters
    Public Function getName() As String
        Return name
    End Function

    'Output
    Public Sub writeOutput()
        Console.WriteLine("Name: " & name)
    End Sub
    'Equal
    Public Function equal(p As Person) As Boolean
        If (Me.name.Equals(p.name)) Then
            Return True
        Else
            Return False
        End If
    End Function

    'CompareTo
    Public Function compareTo(p As Person) As Integer
        If (Me.name > p.name) Then
            Return 1
        ElseIf (Me.name < p.name) Then
            Return -1
        ElseIf (Me.name.Equals(p.name)) Then
            Return 0
        Else
            Return 0
        End If
    End Function
    'properties
    Public Property citizen As Boolean

End Class
