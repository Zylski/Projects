! Programmer: Damian Zylski
! Date: 07/12/2020
! System: Windows XP - Compaq digital Fortran
! Program: H7
!
! Purpose: To input a series of words and checks that if you move the first 
!		 letter to the back and reverse the word, will it still be the 
!		 same word. One such word is banana.

	!Functions
	
	!*Explain**********************************************************
	!Explains what the program does
	subroutine Explain()
	!output instructions
	write (*,*) "This program takes a series of words from input and"
	write (*,*) "checks that if you move the first letter to the back"
	write (*,*) "and reverse the word, will it still be the same"
	write (*,*) "word. One such word is banana."
	write (*,*) " "
	end subroutine Explain

	!*RandGen**********************************************************
	!outputs random num
	subroutine RandGen()
	!variables
	real  :: r(1) 
	integer       :: i(1)
	
	!seed
	call random_seed()
	
	!random num
	call random_number(r)

	! Uniform distribution requires floor
	i = floor( r*100 )
	
	!output random num
	!print *, i

	end subroutine RandGen

	!*GetWords*********************************************************
	!gets the words from input
	function GetWords() result(words)
	character(len=32000) :: words 

	!initialize numWords
	words = ""

	!prompt for input
	write (*,*) "Please enter the words"
	!read in the words
	read(*, '(A)') words
	write (*,*) " "

	end function GetWords

	!*GetNumWords*********************************************************
	!calculates number of words from input
	function GetNumWords(words) result(numWords)
	!variables
	character(len=32000), intent(in) :: words !input
	integer :: numWords!output
	integer :: i = 0
	integer :: ind
	integer :: l = 0; !length of string
	character(len=32000) :: subWords = ""

	!initialize numWords
	numWords = 0

	!set subwords equal to words
	subWords = trim(words)

	!try to get index of space
	ind = index(trim(subWords), " ")

	!loop and count words if more than one
	do while (ind .ne. 0)
		!increment words
		numWords = numWords + 1

		!get length of string
		l = len_trim(subWords)
		
		!get substring of next word
		subWords = subWords(ind + 1:l)

		!get next index
		ind = index(trim(subWords), " ")
		
	end do

	!check if there's only one word
	if (len_trim(subWords) .gt. 0 .and. ind .eq. 0) then
		numWords = numWords + 1
	end if

	end function GetNumWords

	!*CheckWords*****************************************************
	!checks the words to see if the match
	subroutine CheckWords(inWords,numWords) 
	!variables
	character(len=32000) :: inWords
	integer :: numWords
	character(len=32000) :: words = ""
	character(len=32000) :: word = ""
	character(len=32000) :: moved = ""
	character(len=32000) :: reversed = ""
	character(len=32000) :: output = ""
	character :: c = "Z"
	integer :: ind = 0
	integer :: curr = 0
	integer :: wordCounter = 1
	integer :: i = 0
	integer :: z = 0
	logical :: check = .false.

	!set subwords equal to input words
	words = inWords

	!get index of first space if it exists
	ind = index(trim(words)," ")

	!loop and check all the words
	do i = 1, numWords
		!for single word
		if (ind .eq. 0) then
			!grab the word
			word = trim(words(1:len_trim(words)))
			!get the first character
			c = word(1:2)
			!Move that character to the end of string
			moved = trim(word(2:len_trim(word))) // c
			!set curr to last index of moved
			curr = len_trim(moved)
			!reverse the word
			do curr = curr, 1,-1
				c = trim(moved(curr:curr))
				reversed = trim(reversed) // c
			end do
			!output results
			output = "Original: " // trim(word)
			output = trim(output) // ", Moved: " // trim(moved)
			output = trim(output) // ", Reversed: " // trim(reversed)
			write (*,*) wordCounter,". ",trim(output)
			
			!check if words are equal
			if(word .eq. reversed) then
	write (*,*) "          ",trim(word)," is equal to ",trim(reversed)
			write (*,*) " "
			else
	write(*,*)"        ",trim(word)," is not equal to ",trim(reversed)
			write (*,*) " "
			end if
		!for multiple words
		else
			!grab the word
			word = trim(words(1:ind))
			!get the first character
			c = word(1:2)
			!Move that character to the end of string
			moved = trim(word(2:len_trim(word))) // c
			!set curr to last index of moved
			curr = len_trim(moved)
			!reverse the word
			do curr = curr, 1,-1
				c = trim(moved(curr:curr))
				reversed = trim(reversed) // c
			end do
			!output results
			output = "Original: " // trim(word)
			output = trim(output) // ", Moved: " // trim(moved)
			output = trim(output) // ", Reversed: " // trim(reversed)
			write (*,*) wordCounter,". ",trim(output)
			!check if words are equal
			if(word .eq. reversed) then
	write (*,*) "          ",trim(word)," is equal to ",trim(reversed)
			write (*,*) " "
			else
	write(*,*)"        ",trim(word)," is not equal to ",trim(reversed)
			write (*,*) " "
			end if

			!get next word
			words = words(ind + 1:len_trim(words))
			!get next index
			ind = index(trim(words), " ")
			!increment counter and reset variables
			wordCounter = wordCounter + 1
			output = ""
			moved = ""
			reversed = ""
			word = ""
		end if
	end do

	end subroutine CheckWords

	!*PROGRAM H7*****************************************************
	program H7

	implicit none

	!define variables
	integer :: numWords = 0
	character(len=32000) :: words = ""
	character(len=32000) :: GetWords
	integer :: GetNumWords
	

	!output program explanation
	call Explain()

	!get the words from input
	words = GetWords()

	!figure out the number of words
	numWords = GetNumWords(words)
	
	!output Random number
	call RandGen()

	!check the words
	call CheckWords(words,numWords)


	stop
	end program H7