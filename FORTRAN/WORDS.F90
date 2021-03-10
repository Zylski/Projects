! Programmer: Damian Zylski
! Date: 08/07/2020
! System: Windows XP - Compaq digital Fortran
! Program: Words
!
! Purpose: To read a series of words from an input file and output them to
! the user. Then save those words to a different file.

	!Functions


!*ReadWords*****************************************************
! Read words from input file and stores them in array
SUBROUTINE ReadWords(words) 
	!Variables
	CHARACTER(LEN=50),DIMENSION(4581),intent(out) :: words
	INTEGER :: i = 0

	!open file
	OPEN(UNIT = 15, FILE = "words_no_duplicates.txt", IOSTAT = ios, STATUS = "old")

	!read words into array
	DO i = 1,4581
		READ(15,'(A)') words(i)
	END DO

	!close file
	CLOSE(15)

	WRITE (*,*) "Words read from file"

END SUBROUTINE ReadWords

!*PrintWords*****************************************************
! Prints the words from the array
SUBROUTINE PrintWords(words)

	!Variables
	CHARACTER(LEN=50),DIMENSION(4581),intent(in) :: words
	INTEGER :: i = 0
	INTEGER :: n = 0

	!get size of array
	n = SIZE(words)

	!loop and print out the words
	DO i = 1,n
		WRITE (*,*) words(i)
	END DO

	WRITE (*,*) " "

END SUBROUTINE PrintWords

!*WriteWords*****************************************************
! Writes the words to a different file
SUBROUTINE WriteWords(words)

	!Variables
	CHARACTER(LEN=50),DIMENSION(4581),intent(in) :: words
	INTEGER :: i = 0
	INTEGER :: n = 0

	!get size of array
	n = SIZE(words)

	!Create new file
	!open file
	OPEN(UNIT = 16, FILE = "DATA.DAT", IOSTAT = ios, STATUS = "new")

	!loop and print out the words
	DO i = 1,n
		WRITE (16,'(A)') TRIM(words(i))
	END DO

	!close file
	CLOSE(16)
	
	WRITE (*,*) "Words written to file"

END SUBROUTINE WriteWords

!*PROGRAM WORDS*****************************************************
PROGRAM WORDS

IMPLICIT NONE

!Variables
CHARACTER(LEN=50),DIMENSION(4581) :: wordList

!read words from input file
CALL ReadWords(wordList)

!output words to screen
CALL PrintWords(wordList)

!write the words to a different file
CALL WriteWords(wordList)

STOP
END PROGRAM WORDS