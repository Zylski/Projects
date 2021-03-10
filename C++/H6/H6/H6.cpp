/* Programmer: Damian Zylski
* Project:    H6
* Date:       06/18/2020
* System:     Windows 10 Pro - Visual Studio 2017
*
* Purpose:    To read in a series of grades and determine the number of letter grades,
 * the average, the max and the min score. A negative number will be a signal to quit.
*/

#include <iostream>
#include <iomanip>
#include <math.h>
#include <string>
#include <string.h>
#include <ctype.h>
#include <algorithm>
#include <iterator>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

//*Explain*************************************************************************************
//explains program function to the user
void explain()
{
	cout << "This program will read in grades and determine the number of each" << endl;
	cout << "letter grade, the highest and lowest scores, and the average." << endl;
	printf("\n");
	return;
}

//*isNumeric***********************************************************************************
//checks if a string input is numeric
bool isNumeric(string input)
{
	//variables
	bool check = true;
	int i = 0;

	//check if first char is - sign
	if (input[0] == '-')
	{
		//skip first character
		i = 1;
	}

	//check each character in string
	for (i = i; i < input.length(); i++)
	{
		if (!isdigit(input[i]))
		{
			check = false;
		}
	}
	return check;
}

//*calcNumGrades*******************************************************************************
//calculates the number of grades
int calcNumGrades(int grades [], int len)
{
	//Variables
	int numGrades = 0;
	int i = 0;

	//sum up the total of all grades
	
	for (i = 0; i < len; i++)
	{
		numGrades = numGrades + grades[i];
	}
	return numGrades;
}

//*calcAverage*********************************************************************************
//calculates the average of the grades
double calcAverage(int sum, int numGrades)
{
	//variables
	double average = 0.0;

	//calculate the average
	average = (double)sum / (double)numGrades;

	return average;
}

//*calcGrades**********************************************************************************
//the main function for calculating grades from input. Collects grades until signal to quit
void calcGrades()
{
	//Variables
	int grade = 0;
	string input = "";
	int numGrades = 0;
	int grades[5] = {0,0,0,0,0};
	bool number = false;
	int sum = 0;
	int high = 0;
	int low = 100;
	double average = 0.0;
	int i = 0;

	//ask for first grade outside of the loop
	//loop until input is correct
	do
	{
		cout << "Please enter a grade: (negative # to quit)" << endl;
		cin >> input;

		//check if input is numeric
		if (isNumeric(input))
		{
			number = true;
			grade = stoi(input);
		}
		else
		{
			number = false;
			cout << "Incorrect input" << endl << endl;
		}
	} while (!number);

	//main loop for collecting grades, quit if number is negative
	while (grade >= 0)
	{
		//for A grades
		if (grade >= 90 && grade <= 100)
		{
			//keep track of the number of each grade
			grades[0]++;
		}
		//for B grades
		else if (grade >= 80 && grade <= 89)
		{
			grades[1]++;
		}
		//for C grades
		else if (grade >= 70 && grade <= 79)
		{
			grades[2]++;
		}
		//for D grades
		else if (grade >= 60 && grade <= 69)
		{
			grades[3]++;
		}
		//for F grades
		else if (grade <= 59)
		{
			grades[4]++;
		}
		else
		{
			//if grade over 100
			cout << "This class doesn't give extra credit!" << endl;
		}

		//add to sum, and check max and min if grade over 0
		if (grade <= 100)
		{
			//check for highest grade
			high = max(high, grade);

			//check for lowest grade
			low = min(low, grade);

			//add grade to sum
			sum = sum + grade;
		}

		//ask for next grade inside of the loop
		//loop until input is correct
		do
		{
			cout << endl << "Please enter a grade: (negative # to quit)" << endl;
			cin >> input;

			//check if input is numeric
			if (isNumeric(input))
			{
				number = true;
				grade = stoi(input);
			}
			else
			{
				number = false;
				cout << "Incorrect input" << endl << endl;
			}
		} while (!number);
	}

	//calculate number of grades
	numGrades = calcNumGrades(grades, (sizeof(grades) / sizeof(*grades)));

	//calculate average
	average = calcAverage(sum, numGrades);

	//output grade results
	cout << endl << "Total number of grades: " << numGrades << endl;
	cout << "Number of A's: " << grades[0] << endl;
	cout << "Number of B's: " << grades[1] << endl;
	cout << "Number of C's: " << grades[2] << endl;
	cout << "Number of D's: " << grades[3] << endl;
	cout << "Number of F's: " << grades[4] << endl;
	cout << "The highest score is: " << high << endl;
	cout << "The lowest score is: " << low << endl;
	cout << fixed << setprecision(2);
	cout << "The average is: " << average << endl << endl;
	
	return;
}

//*Main****************************************************************************************
int main()
{
	//output program details to user.
	explain();
	
	//begin collecting and calculating grades
	calcGrades();

	system("pause");
	return 0;
}