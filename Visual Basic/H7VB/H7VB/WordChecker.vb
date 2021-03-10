' Programmer: Damian Zylski
' Date: July 07, 2020
' System: Visual Studio 2017 - Windows 10 Pro
' Project: H7
'
' Purpose: To check a series of words to see if you move the first letter to the back, will
' it still be the same word when spelled backwards. An example Is banana. Moving the b to 
' the back results in the same word when spelled backwards.

Option Strict On
Option Explicit On

Public Class WordChecker

    'Variables
    Dim words As String


    '***Explain********************************************************************************************
    'Explains how to use the program to user
    Sub Explain()
        'The text to display
        Dim ex As String
        ex = "This program will take in a number of words as specified and will check" & vbCrLf &
            "whether the word will be the same when the first letter is moved to" & vbCrLf &
            "the back and spelled backwards. One such word is Banana."
        MsgBox(ex, MsgBoxStyle.Information, "Instructions")
    End Sub

    '***RandGen********************************************************************************************
    'Generates a random number
    Function RandGen() As Integer
        'variables
        Dim num As Integer
        Dim rand As New System.Random(TimeOfDay.Second)

        num = rand.Next(0, 100)
        Console.Write(num)
        Return num
    End Function

    '***GetNumWords********************************************************************************************
    'Calculates the number of words entered by the user
    Function GetNumWords(words As String) As Integer
        'variables
        Dim numWords As Integer = 0
        Dim subWords As String = words

        'loop and figure out the number of words
        While words.Length() > 0
            numWords = numWords + 1

            'if one word remains
            If subWords.IndexOf(" ").Equals(-1) Then
                Exit While
                'for multiple words
            Else
                subWords = subWords.Substring(subWords.IndexOf(" ") + 1)
            End If
        End While

        Return numWords
    End Function

    '***GetWords********************************************************************************************
    'Gets the words entered by user from input
    Function GetWords() As String
        'variables
        Dim words As String = ""

        'get words from text box
        words = WordsInput.Text()

        'remove trailing spaces
        words = words.Trim

        Return words
    End Function

    '***CheckWords********************************************************************************************
    'checks each of the words entered by the user
    Sub CheckWords(words As String)
        'variables
        Dim word As String = ""
        Dim c As Char
        Dim moved As String = ""
        Dim reversed As String = ""
        Dim wordCounter As Integer = 1
        Dim numWords As Integer
        Dim i As Integer
        Dim len As Integer

        'clear outputbox
        OutputBox.Items.Clear()

        'get input from text box
        words = GetWords()

        'get the number of words
        numWords = GetNumWords(words)

        'check if contents of text box is empty
        If words.Length().Equals(0) Then
            'output message saying nothing was entered
            OutputBox.Items.Add("No words were entered")
        Else
            'otherwise check the words
            While numWords > 0
                'for a single word
                If words.IndexOf(" ").Equals(-1) Then

                    'get the first character of the word
                    c = words.Chars(0)

                    'move that letter to the back of word
                    moved = words.Substring(1) + c

                    'get the length of the word
                    len = moved.Length() - 1

                    'reverse that word
                    For i = len To 0 Step -1
                        reversed = reversed + moved.Chars(i)
                    Next i

                    'output results
                    OutputBox.Items.Add("Word " & wordCounter & ". Original: " & words &
                                        ", First letter moved: " & moved & ", Reversed: " & reversed)
                    'check if word is the same
                    If words.ToUpper.Equals(reversed.ToUpper) Then
                        OutputBox.Items.Add(words & " is equal to " & reversed)
                    Else
                        OutputBox.Items.Add(words & " is not equal to " & reversed)
                    End If

                Else 'for multiple words

                    'get the next word
                    word = words.Substring(0, words.IndexOf(" "))

                    Console.Write(word.Length())

                    'get the first character of the word
                    c = word.Chars(0)

                    'move that letter to the back of word
                    moved = word.Substring(1) + c

                    'get the length of the word
                    len = moved.Length() - 1

                    'reverse that word
                    For i = len To 0 Step -1
                        reversed = reversed + moved.Chars(i)
                    Next i

                    'output results
                    OutputBox.Items.Add("Word " & wordCounter & ". Original: " & word &
                                        ", First letter moved: " & moved & ", Reversed: " & reversed)
                    'check if word is the same
                    If word.ToUpper.Equals(reversed.ToUpper) Then
                        OutputBox.Items.Add(word & " is equal to " & reversed)
                    Else
                        OutputBox.Items.Add(word & " is not equal to " & reversed)
                    End If

                    'add white space
                    OutputBox.Items.Add("")

                    'grab next word
                    words = words.Substring(words.IndexOf(" ") + 1)
                End If
                'reset variables and raise counters
                wordCounter = wordCounter + 1
                numWords = numWords - 1
                moved = ""
                reversed = ""
                word = ""
            End While
        End If
        'reset variables
        wordCounter = 1
        numWords = 0
        moved = ""
        reversed = ""
        word = ""
        words = ""
        WordsInput.Clear()

    End Sub
    '***ButtonExplain********************************************************************************************
    Private Sub ButtonExplain_Click(sender As Object, e As EventArgs) Handles ButtonExplain.Click
        'this provides program instructions

        'call the intructions
        Explain()
    End Sub
    '***ButtonExit********************************************************************************************
    Private Sub ButtonExit_Click(sender As Object, e As EventArgs) Handles ButtonExit.Click
        'closes the program

        'close the program
        Me.Close()
    End Sub
    '***ButtonCheck********************************************************************************************
    Private Sub ButtonCheck_Click(sender As Object, e As EventArgs) Handles ButtonCheck.Click
        'checks the words entered
        CheckWords(words)
    End Sub
End Class
