/*
Programmer: Damian Zylski
Date:       04/06/2014
System:     Windows Vista - Visual studio 2005
Program:    #5 - "1024"
Purpose:    This program plays the game 1024. The player selects the board size and then
uses the A, S, D, and W keys for movement. Two like numbers next to each other will combine 
and new random numbers will generate on the board. The game ends when the numbers combine to 
form 1024.

*******************************************************************************************
NOTE: This program is a mess. It represents some very early work from me and I believe I have come
a long way compared to this. Will fix this up one day. This should have been handled with loops
instead of constantly calling the functions again. 3/8/2021
*******************************************************************************************

*/

//include the necessary libraries
#include <iostream>
#include <iomanip>   // used for setting output field size using setw
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <ctype.h>
#include <string.h>

using namespace std;

//--------------------------------------------------------------------------------------
//The linked list
typedef struct Node
{
	int move;       //stores each move
	int score;      //stores the score
	int * board; //stores the board

	struct Node * pNext;
	struct Node * pPrev; 

} Node;

//--------------------------------------------------------------------------------------
//function to display user data
char ProgramData(){
	printf("Programmer: Damian Zylski\n");
	printf("Program:    #5 - 1024\n");
	printf("System:     Windows XP");
	printf("UIC CS 141, Spring 2014\n");
    printf("Welcome to 1024 and More.\n");
    printf("This program is based off of Gabriele Cirulli's game online at \n");
    printf("     bit.ly/great2048---\n\n");
return 'z';
}
//--------------------------------------------------------------------------------------
//function to display game instructions
char Instructions(){
	printf("For each move enter a direction as a letter key, as follows:\n");
    printf("    W\n");
    printf("  A S D\n");
    printf("where A=left,W=up, D=right and S=down.\n\n");
    printf("After a move, when two identical valued tiles come together they\n");
    printf("join to become a new single tile with the value of the sum of the\n");
    printf("two originals. This value gets added to the score.  On each move\n"); 
    printf("one new randomly chosen value of 2 or 4 is placed in a random open\n");
    printf("square.  User input of r restarts the game, and x exits the game.\n\n");
return 'z';
}

float Communism(){

	//     OPENING THE FILE
	//-------------------------------
	// call the file
	FILE *pInputFile;        // file pointer 
	char *theFile; // The file
	char *pArry;

	//store speech in dynamic array
	theFile = (char *) malloc ( 2000 * sizeof(char) );

	//pArry = speech;

	int i = 0;                 // loop counter 
   char inputFileName[] = "hammer.txt"; 

   // Associate the actual file name with file pointer and try to open it 
   //printf("\nOpening file hammer.txt.\n\n");

   pInputFile = fopen(inputFileName, "r");
   // Verify input file was found
   if( pInputFile == NULL) {
      printf("Attempt to open file %s failed.  Exiting ... \n", inputFileName);
      exit( -1);       
   }
   
   // Read from the file into the array.  Space in front of %c is NOT important!
   while( fscanf(pInputFile, "%c", &theFile[ i]) != EOF)  {
	   
      i++;
   }

   fclose( pInputFile);   // close input file

   for(i = 0; i < 1170; i++){
		   printf("%c", theFile[i]);
   }

   printf("\n");

   free(theFile);

	return 0.1;
}
//--------------------------------------------------------------------------------------
//function to get the board size
int BoardSize(int *size){

//integer size equals the size of the board

	int i = 0; //counter

//ask for input
printf("Enter the size board you want, between 4 and 12:  ");

for(i = 0; i < 1; i){ //loop for error checking
//read in input
scanf("%d", size);

//error check
if(*size < 4 || *size > 12){
	
i = 0;
//display error message if wrong value is input
printf("\n");
printf("    ***ERROR! VALUE MUST BE BETWEEN 4 AND 12!***\n\n");
printf("Enter the size board you want, between 4 and 12:  ");

}
//if value is correct, break out of the loop
if(*size >= 4 && *size <= 12){
break;
}
}
return *size;
}

//----------------------------------------------------------------------------------
//Function to make the board
int *MakeBoard( int boardSize, int boardCounter){

	int i = 0;          //Counter
	int *aBoard; //the board

	if(boardCounter == 0){
		aBoard = NULL;
	//Dynamically create the board
	aBoard = new int[boardSize * boardSize]; 

	//printf("\n\n");

	//Initialize each value in the array
	for(i=0; i <= (boardSize*boardSize); i++){
		aBoard[i] = '.';
		
	}
	}
	if(boardCounter == 1){
		free(aBoard);
		}
	return aBoard;
}

//--------------------------------------------------------------------------------------
//function to generate random numbers
int RandomGenerator(int * gameBoard, int size, int random, int randomCounter){

	int randomPosition = 0;   //Random position on board
	int newRandom = 0;        //new random number
	int random2 = 0;          //a second initial random
	int i = 0;

			//Generate a random number

			srand((int)time(0)); //this statement ensures the numbers are always random

			random = rand() % 5; //Randomize 1st number
			if(randomCounter == 0){
				random2 = rand() % 5; //and 2nd initially
			}

			//to make sure first and second random number is 2 or 4
			if(random == 0 || random == 1 || random == 2){
				random = 2;
			}
			else{
				random = 4;
			}
			if(randomCounter == 0){
				if(random2 == 0 || random2 == 1 || random2 == 2){
				random2 = 2;
			}
			else{
				random2 = 4;
			}
			}
			//srand((int)time(0)); //this statement ensures the numbers are always random
			//srand((int)time(0));

			while(i==0){
			randomPosition = rand() % ((size * size)-1);

			newRandom = randomPosition;
			//inserts random number and replaces previous space with period
			if(gameBoard[randomPosition] == '.'){
				gameBoard[randomPosition] = random;
				if(randomCounter == 0){
					gameBoard[rand() % (size * size)] = random2;
				}
				break;
			}
			}

	return random;
}

//--------------------------------------------------------------------------------------
//Function to check for win
int CheckForWin(int * gameBoard, int size, int score){

	int i = 0;

	//struct holding the winning board values
	typedef struct WinningNumber{
		int size4;
		int size5;
		int size6;
		int size7;
		int size8;
		int size9;
		int size10;
		int size11;
		int size12;
	} winning;
	winning win;

	win.size4 = 1024;
	win.size5 = 2048;
	win.size6 = 4096;
	win.size7 = 8192;
	win.size8 = 16384;
	win.size9 = 32768;
	win.size10 = 65536;
	win.size11 = 131072;
	win.size12 = 262144;

	//CHECK FOR WIN! //EDIT: This should have been a switch statement. Not sure why it was in a struct. Future me is shaking his head.
	for(i=0;i<(size*size); i++){
		if(size == 4){
			if(gameBoard[i] == win.size4){
				printf("\n\nYOU SCORED %d\n\n", win.size4);
				printf("  ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");

				
				system("pause");
				goto stop;
				stop:
				exit(EXIT_SUCCESS);
			}

		}
		if(size == 5){
			if(gameBoard[i] == win.size5){
				printf("\n\nYOU SCORED %d\n\n", win.size5);
				printf("         ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");
				
				system("pause");
				exit(EXIT_SUCCESS);
			}

		}
		if(size == 6){
			if(gameBoard[i] == win.size6){
				printf("\n\nYOU SCORED %d\n\n", win.size6);
				printf("         ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");
				
				system("pause");
				exit(EXIT_SUCCESS);
			}

		}
		if(size == 7){
			if(gameBoard[i] == win.size7){
				printf("\n\nYOU SCORED %d\n\n", win.size7);
				printf("         ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");
				
				system("pause");
				exit(EXIT_SUCCESS);
			}

		}
		if(size == 8){
			if(gameBoard[i] == win.size8){
				printf("\n\nYOU SCORED %d\n\n", win.size8);
				printf("         ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");
				
				system("pause");
				exit(EXIT_SUCCESS);
			}

		}
		if(size == 9){
			if(gameBoard[i] == win.size9){
				printf("\n\nYOU SCORED %d\n\n", win.size9);
				printf("         ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");
				
				system("pause");
				exit(EXIT_SUCCESS);
			}

		}
		if(size == 10){
			if(gameBoard[i] == win.size10){
				printf("\n\nYOU SCORED %d\n\n", win.size10);
				printf("         ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");
				
				system("pause");
				exit(EXIT_SUCCESS);
			}

		}
		if(size == 11){
			if(gameBoard[i] == win.size11){
				printf("\n\nYOU SCORED %d\n\n", win.size11);
				printf("         ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");
				
				system("pause");
				exit(EXIT_SUCCESS);
			}

		}
		if(size == 12){
			if(gameBoard[i] == win.size12){
				printf("\n\nYOU SCORED %d\n\n", win.size12);
				printf("         ***YOU WIN***\n\n");
				printf("CONGRATULATIONS!!!\n\n");
				
				system("pause");
				exit(EXIT_SUCCESS);
			}

		}

	}

	return 0;
}

//--------------------------------------------------------------------------------------
//function to print the board
int PrintBoard(int * gameBoard, int size){

	int i = 0;
	int stop = (size*size);

	printf("\n");
		for(i=0;i<stop;i++){
			if(i % size == 0){
				printf("\n");
				}
			//to print as characters
			if(isdigit((char)gameBoard[i]) == 0 ){
				if(gameBoard[i] == '.'){
					printf(" %c   ", gameBoard[i]);
					continue;
				}
				//then print remainder as integers
			printf(" %d   ", gameBoard[i]);
			}
		}
		printf("\n\n");

	return 0;
}

//--------------------------------------------------------------------------------------
//DECLARATIONS!
int Left(int *gameBoard,int size, int i, int randomCounter, int score, int move);

int Up(int *gameBoard,int size, int i, int randomCounter, int score, int move);

int Right(int *gameBoard,int size, int i, int randomCounter, int score, int move);

int Down(int *gameBoard,int size, int i, int randomCounter, int score, int move);

void PrintList(struct Node * pHead, int boardSize);

struct Node * RecordMove(struct Node * pHead, int size, int gameMove, int gameScore, int * board);

struct Node * Undo(struct Node * pHead, int ** gameBoard, int size, int * gameScore, int * gameMove);

//--------------------------------------------------------------------------------------
//function to determine direction of movement
char GetDirection(int * &gameBoard, int size, int score,int boardCounter, int * pBoard, int move){

	char direction = 'A';      //the variable for direction
	int i = 0;                 //counter variable
	int value = 0;             //value in the board
	int position = 0;          //position in the board
	struct Node * pHead = NULL;
	
	CheckForWin(gameBoard, size, score);
	
	printf("%d. Your move: ", move);
	
	//read in input
	scanf(" %c", &direction);

	printf("\n");
	
	//to make lowercase entries uppercase
	direction = toupper(direction);

	//if value is correct, break out of the "recursion"
	if(direction == 'A' || direction == 'S' || direction == 'D' || direction == 'W' || direction == 'P' || direction == 'X' || direction == 'R' || direction == 'Z' || direction == 'U' || direction == 'L'){
		//run the function corresponding to the input direction
		switch (direction) {
			case 'A': //if input is A
				//LEFT DIRECTION FUNCTION
				
				return Left(gameBoard, size, 0,0, score,move);
				break;
			case 'S': //if input is S
				//DOWN DIRECTION
				
				return Down(gameBoard, size,(size*size)-(size+1) ,0, score,move);
				break;
			case 'D': //if input is D
				//RIGHT DIRECTION
				
				return Right(gameBoard, size,(size*size)-1 ,0, score,move);
				break;

			case 'W': //if input is W
				//UP DIRECTION

				return Up(gameBoard, size, 0,0, score,move);
				break;

			case 'X': //if input is X
				//EXITS THE GAME
				printf("\n\n*******************\n\n");
				printf("\n\n    GAME OVER\n\n");
				printf("\n\n   NOW EXITING...\n\n");
				break;

			case 'R': //if input is R
				//RESTARTS THE GAME
				gameBoard = MakeBoard(size, 0);
				RandomGenerator(gameBoard,size,0,0);
				return GetDirection(gameBoard, size, 0, 1, NULL, 0);
				break;

			case 'P': //if input is P
				//INPUTS SPECIFIC VALUE INTO BOARD
				scanf(" %d %d", &position, &value);

				gameBoard[position] = value;

				printf("\n***VALUE ADDED***\n\n\n");
				
				return GetDirection(gameBoard, size, score, 1, NULL, move);
				break;
			case 'Z': //if input is Z
				// Communism!
				system("cls");
				Communism();
				return GetDirection(gameBoard, size, score, 1, NULL, move);
				break;
			case 'U': //if input is U
				if(move > 1){
					pHead = Undo(pHead,&gameBoard,size,&score,&move);  // to undo a move
					for(i=0;i<(size*size);i++){
					gameBoard[i] = pHead->board[i]; //initialize board elements to previous version
					}
					}
					else{
						//error check
						printf("\n\n***CANNOT UNDO PAST INITIAL BOARD MOVE***\n\n");
						system("pause");
					}
					//continue; //skip the move test
					break;
			case 'L': //if input is L
				PrintList(pHead, size);    // test case to print list contents
				break;
		}
		
	}

	//error check

	else{
		//display error message if wrong value is input
		printf("\n");
		printf("    ***ERROR! INPUT MUST BE A,S,D, or W!***\n\n");
		return GetDirection(gameBoard, size, score, 1, NULL,move);
	}
return direction;
}
//------------------------------------------------------------------------------------
//function for left direction movements
int Left(int *gameBoard,int size, int i, int randomCounter, int score,int move){
	//randomCounter keeps track of when random numbers should be generated
	int *pBoard = NULL;     //pointer to board
	int random = 0;         //the random number
	int randomPosition = 0; //random board Position
	int newRandom = 0;      //A new random number
	int stop = (size*size); //Where the board stops
	int newNum = 666;       //a new number
	int sum = 0;            //value that is used for the sum of two numbers
	int j = 0; //used for checking for end of game
	int spaceCount = 0; //for counting spaces
	struct Node * pHead = NULL;
	
	
	//loop and add numbers
		//looks for a number
		if(gameBoard[i] != '.'){
			
			//the code below adds the numbers together
			if(gameBoard[i] == gameBoard[i-1]  ){
				sum = gameBoard[i];
				gameBoard[i-1] = (sum * 2);
				score = score + (sum * 2); //Your score!
				newNum = (i-1);
				gameBoard[i] = '.';
			
				//Generate a random number
				randomCounter = 1;
				}
		//check for GAME OVER
			else{
				for(j=0;j<size*size;j++){
					if(gameBoard[j]!= '.'){
						spaceCount = spaceCount + 1;
							
					}
					if(spaceCount == (size*size) ){

					printf("\n\n***GAME OVER!***\n\n");
					return 1;
					}
				}
			}
				//TO SHIFT NUMBERS
			
					//checks if it isn't next to a new line and next space is free
					if((i) % size != 0  && gameBoard[i-1] == '.' ){
						//then the number is sent backwards through the array until condition is met
						while((i) % size != 0 && gameBoard[i-1] == '.'){
						randomCounter = 1; //signal to generate random numbers
						gameBoard[i-1] = gameBoard[i];
						gameBoard[i] = '.';
						i--;	
						}
				}
			}
		
		if(i != stop)
		{
		Left(gameBoard, size, i+1,randomCounter, score, move);
		}



	//once the function goes through all values on the board
	
		//Clear the screen
		system("cls"); 
		printf("Score: %d", score);
		//generates random number once and only if move is made

				//only while the Random counter is 1
				while(randomCounter == 1){
					RandomGenerator(gameBoard, size, 5, 1);
					//increase move
					move = move+1;
					break;
				}
		//then print the board

				PrintBoard(gameBoard, size);

		
		
		//go back and get the next direction
		randomCounter = 0;

		//Record the board into the linked list
		pHead = RecordMove(pHead, size, move, score, gameBoard);


	GetDirection(gameBoard, size, score,1,NULL,move);
	

return 0;
}
//----------------------------------------------------------------------------------
//Function for up movements
int Up(int *gameBoard,int size, int i, int randomCounter, int score, int move){
	//randomCounter keeps track of when random numbers should be generated
	int *pBoard = NULL;
	int random = 0;         //the random number
	int randomPosition = 0; //random board Position
	int newRandom = 0;      //A new random number
	int stop = (size*size); //Where the board stops
	int newNum = 666;       //a new number
	int sum = 0;            //value that is used for the sum of two numbers
	int j = 0; //Counting spaces
	int spaceCount = 0;
	struct Node * pHead = NULL;
	
		//looks for a number
		if(gameBoard[i] != '.'){
			//the code below adds the numbers together
			if(gameBoard[i] == gameBoard[i-size] ){
				sum = gameBoard[i];
				gameBoard[i-size] = (sum * 2);
				score = score + (sum * 2); //Your score!
				newNum = (i-size);
				gameBoard[i] = '.';

				//Generate a random number
				randomCounter = 1;
				}
			//check for GAME OVER
			else{
				for(j=0;j<size*size;j++){
					if(gameBoard[j]!= '.'){
						spaceCount = spaceCount + 1;
							
					}
					if(spaceCount == (size*size) ){

					printf("\n\n***GAME OVER!***\n\n");
					return 1;
					}
				}
			}
				//TO SHIFT NUMBERS
			
					//checks if it isn't next to a new line and next space is free
					if( gameBoard[i-size] == '.' ){
						//then the number is sent backwards through the array until condition is met
						while( gameBoard[i-size] == '.'){
						randomCounter = 1; //signal to generate random numbers
						gameBoard[i-size] = gameBoard[i];
						gameBoard[i] = '.';
						i = i - size;	
				}
			}
		}
	if(i != stop)
	{
	return Up(gameBoard, size, i+1,randomCounter, score,move);
	}


	//once the function goes through all values on the board

		//Clear the screen
		system("cls"); 
		printf("Score: %d", score);
		//generates random number once and only if move is made


				//only while the Random counter is 1
				while(randomCounter == 1){
					//Generate a random number
					RandomGenerator(gameBoard, size, 5, 1);
					//increase move number
					move = move + 1;
					break;
				}
		//then print the board

		PrintBoard(gameBoard, size);

		//Record the board into the linked list
		pHead = RecordMove(pHead, size, move, score, gameBoard);

		//go back and get the next direction
	GetDirection(gameBoard, size, score, 1,NULL,move);
	


return 0;
}
//----------------------------------------------------------------------------------
//Function for Right movements
int Right(int *gameBoard,int size, int i, int randomCounter, int score,int move){

//randomCounter keeps track of when random numbers should be generated
	int *pBoard = NULL;
	int random = 0;         //the random number
	int randomPosition = 0; //random board Position
	int newRandom = 0;      //A new random number
	int stop = (0);           //Where the board stops
	int newNum = 666;       //a new number
	int sum = 0;            //value that is used for the sum of two numbers
	int j = 0;
	int spaceCount = 0;
	struct Node * pHead = NULL;
	
		//looks for a number
		if(gameBoard[i] != '.'){
			//the code below adds the numbers together
			if(gameBoard[i] == gameBoard[i+1] && i < (size*size)-1 ){
				sum = gameBoard[i];
				gameBoard[i+1] = (sum * 2);
				score = score + (sum * 2); //Your score!
				newNum = (i+1);
				gameBoard[i] = '.';

				//Generate a random number
				randomCounter = 1;
				}
			//check for GAME OVER
			else{
				for(j=0;j<size*size;j++){
					if(gameBoard[j]!= '.'){
						spaceCount = spaceCount + 1;
							
					}
					if(spaceCount == (size*size) ){

					printf("\n\n***GAME OVER!***\n\n");
					return 1;
					}
				}
			}
				//TO SHIFT NUMBERS
					//checks if it isn't next to a new line and next space is free
					if( (i) % size != size-1  && gameBoard[i+1] == '.' && i < (size*size)-1  ){
						//then the number is sent backwards through the array until condition is met
						while( (i) % size != size-1  &&gameBoard[i+1] == '.' && i < (size * size)-1 ) {
						randomCounter = 1; //signal to generate random numbers
						gameBoard[i+1] = gameBoard[i];
						gameBoard[i] = '.';
						i++;	
				}
			}
		}
	if(i > stop)
	{
	Right(gameBoard, size, i-1,randomCounter, score,move);
	}


	//once the function goes through all values on the board

		//Clear the screen
		system("cls"); 
		printf("Score: %d", score);
		//generates random number once and only if move is made


				//only while the Random counter is 1
				while(randomCounter == 1){
					//Generate a random number
					RandomGenerator(gameBoard, size, 5, 1);
					//increase move
					move = move +1;
					break;
				}
		//then print the board

		PrintBoard(gameBoard, size);

		//Record the board into the linked list
		pHead = RecordMove(pHead, size, move, score, gameBoard);

		//go back and get the next direction
	GetDirection(gameBoard, size, score,1,NULL,move);
	

return 0;
}

//---------------------------------------------------------------------------------------
//Function for Down movement
int Down(int *gameBoard,int size, int i, int randomCounter, int score,int move){
//randomCounter keeps track of when random numbers should be generated
	int *pBoard = NULL;
	int random = 0;         //the random number
	int randomPosition = 0; //random board Position
	int newRandom = 0;      //A new random number
	int stop = (-1);           //Where the board stops
	int newNum = 666;       //a new number
	int sum = 0;            //value that is used for the sum of two numbers
	int j = 0;
	int spaceCount = 0;
	struct Node * pHead = NULL;
	
		//looks for a number
		if(gameBoard[i] != '.' && i > -1){
			//the code below adds the numbers together
			if(gameBoard[i+size] == gameBoard[i]  && i < (size*size)+1){
				sum = gameBoard[i];
				gameBoard[i+size] = (sum * 2);
				score = score + (sum * 2); //Your score!
				newNum = (i+size);
				gameBoard[i] = '.';
				//move=move
			

				//Generate a random number
				//RandomGenerator(gameBoard, size, 0, 1);
				randomCounter = 1; //signal to generate random numbers
				}
			//check for GAME OVER
			else{
				for(j=0;j<size*size;j++){
					if(gameBoard[j]!= '.'){
						spaceCount = spaceCount + 1;
							
					}
					if(spaceCount == (size*size) ){

					printf("\n\n***GAME OVER!***\n\n");
					return 1;
					}
				}
			}
				//TO SHIFT NUMBERS
			
					//checks if it isn't next to a new line and next space is free
			if( (i) < (size*size) - size  && gameBoard[i+size] == '.'  ){

						
						//then the number is sent backwards through the array until condition is met
						while( (i) < (size*size) - size  && gameBoard[i+size] == '.'   ){
							
							randomCounter = 1; //signal to generate random numbers
						
						gameBoard[i+size] = gameBoard[i];
						gameBoard[i] = '.';
						i = i + size;
						}
					}
		}
	if(i >= stop)
	{
	Down(gameBoard, size, i-1,randomCounter, score,move);
	}


	//once the function goes through all values on the board

		//Clear the screen
		system("cls"); 
		printf("Score: %d", score);
		//generates random number once and only if move is made


				//only while the Random counter is 1
				while(randomCounter == 1){
					//Generate a random number
					RandomGenerator(gameBoard, size, 5, 1);
					//increase move
					move = move+1;
					break;
				}
		//then print the board

		PrintBoard(gameBoard, size);

		//Record the board into the linked list
		pHead = RecordMove(pHead, size, move, score, gameBoard);

		//go back and get the next direction
	GetDirection(gameBoard, size, score, 1,NULL,move);
	

return 0;
}

//---------------------------------------------------------------------------------------
//function to add links to the linked list
struct Node * RecordMove(struct Node * pHead, int size, int gameMove, int gameScore, int * board)
{
	struct Node * pTemp;		   //temp pointer node passed to pHead
	int i = 0;					   //counter
	int maxSize = (size * size);   //max board size
	pTemp = new struct Node;       //A new node
	
	//begin recording data
	pTemp->move = gameMove;        //for the move
	pTemp->score = gameScore;      //for the score
	pTemp->board = (int*)malloc(maxSize * sizeof(int)); //allocate new board size

	for(i=0; i<maxSize; i++)
	{
		pTemp->board[i] = board[i]; //record board
	}
	pTemp->pNext = pHead;           //set pNext to point at pHead

	if(gameMove == 1){
		pTemp->pPrev = 0;       //pPrev points to previous node
	}
	else{
		pTemp->pPrev = pTemp;

		pHead = pTemp;           //pTemp becomes the head of the list
	}

	return pHead;
}

//---------------------------------------------------------------------------------------
//function to undo moves
struct Node * Undo(struct Node * pHead, int ** gameBoard, int size, int * gameScore, int * gameMove){

	//delete the head

	int i = 0;
	Node * pTemp;         //temporary node that will become pHead
    pTemp = new Node;
    pTemp = pHead;        //pHead becomes pTemp node
	pHead = pHead->pNext; //pHead becomes next node
	delete(pTemp);        //free memory from the node

	//Begin reverting to previous move

	*gameMove = pHead->move;     //set move to previous value
	*gameScore = pHead->score;   //set score to previous value

	delete(*gameBoard);              //free the board memory

	*gameBoard = new int[size*size]; //allocate some new memory for it

	return pHead;
}

//---------------------------------------------------------------------------------------
//function to print linked list contents in order
void PrintList(struct Node * pHead, int boardSize)
{
struct Node * pTemp = pHead;
    int number = 0;
    while(pTemp != NULL)
	{
		std::cout << "\nList Member: \n" << number << std::endl;
		printf("	Move: %d \n", pTemp->move);
		printf("	Score: %d \n\n", pTemp->score);

		cout << "\n" << endl;
    for( int row=0; row<boardSize; row++) {
        cout << "   ";
        for( int col=0; col<boardSize; col++ ) {
            int current = row*boardSize + col;  // 1-d index corresponding to row & col
            cout << setw( 6);    // set output field size to 6 (Requires #include <iomanip> )
            // display '.' if board value is 0
            if( pTemp->board[ current] == 0) {
               cout << '.';
            }
            else {
               cout << pTemp->board[ current];
            }
        }
        cout << "\n\n";
    }
		//printf("	Board: %d \n", pTemp->board[0]);

        pTemp = pTemp->pNext;
        ++number;
    }

	system("pause");

}

//---------------------------------------------------------------------------------------
int main() {

	//Data Dictionary
	int *board=NULL;   //The board file
	int score = 0;     //the score
	int move = 1;      //move number
	int i=0;           //A counter variable
	int boardSize = 0; //size of board
	int r1 = 0;		   //first initial random number
	int r2 = 0;        //second initial random number
	int test = 0;      //TEST VARIABLE//
	struct Node * pHead = NULL; //head of the list

	//Display Program Data
	ProgramData();

	//Display Game Instructions
    Instructions();

	//Get the board size
	BoardSize(&boardSize);

    //Display message saying when the game ends
    printf("Game ends when you reach 1024.\n\n");

	//Make the board
	board = MakeBoard( boardSize, 0);

	//Insert the random Numbers
	RandomGenerator(board, boardSize, r1, 0);
		
	//and display the board!
	PrintBoard(board, boardSize);

	//Record the board into the linked list
	pHead = RecordMove(pHead, boardSize, move, score, board);

	//ask user to input direction
	GetDirection(board, boardSize, score,0,NULL, move);

	system("pause");
	return 0;
}

