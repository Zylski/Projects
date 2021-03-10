/* Programmer: Damian Zylski
 * Project:    SelectionSort
 * System:     Windows 10 Pro - Visual Studio 2017
 *
 * Purpose:    To read in student and employee objects from file. They will then be
 * sorted using selection sort and searched for using binary search
 */

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>
#include "Person.h"
#include "Student.h"
using namespace std;

//Comparator for sorting students
bool sortByStudentID(Student a, Student b)
{
	return a.getID() < b.getID();
}

//global var for array size, should have just made this a static var in the classes
int num = 0;

//*getStudents************************************************************************************************
//gets students from files into lists
Student * getStudents()
{
	//Variables
	int i = 0;
	int pass = 0;
	Student * s = NULL;
	Student *studentList = NULL;
	ifstream file;
	int id = 0;
	string m = "";
	string l = "";
	string n = "";

	for (pass = 0; pass < 2; pass++)
	{
		//try to open file
		try
		{
			file.open("Student.dat", ios::in);
		}
		catch (ios_base::failure& e)
		{
			cerr << "File is missing or corrupt!" << endl;
			cerr << e.what() << endl << endl;
			system("pause");
			exit(-1);
		}
		//make sure file opened correctly
		if (file.good())
		{
			//check which pass it is
			if (pass == 0)
			{
				//count number of students
				while (!file.eof())
				{
					file >> id >> m >> l;
					getline(file, n);
					num++;
				}
			}
			else
			{
				//add students to array

				//create new array
				studentList = new Student[num];

				//loop and read in students into array
				while (!file.eof())
				{
					file >> id >> m >> l;
					getline(file, n);
					s = new Student(id,m,l,n);
					studentList[i] = *s;
					i++;
					delete s;
				}

			}
		}

		//close the file
		file.close();
	}
	//return the student array
	return studentList;
}

//*sortStudents************************************************************************************************
//sorts students using selection sort
void sortStudents(Student studentList[], int len)
{
	//variables
	int i = 0;
	int j = 0;
	int smallest = 0; //index of the smallest
	bool swapped = false;
	Student temp = Student();

	//loop and sort the students
	for (i = 0; i < len; i++)
	{
		//set smallest
		smallest = i;

		for (j = i+1; j < len; j++)
		{
			//check if current is smallest
			if (studentList[j].compareTo(studentList[smallest]) < 0)
			{
				//set new smallest
				smallest = j;
				swapped = true;
			}
			
		}

		//swap smallest with first
		if (smallest != i) //only swap if smallest changed
		{
			temp = studentList[i];
			studentList[i] = studentList[smallest];
			studentList[smallest] = temp;
		}
	}
}
//*printStudents************************************************************************************************
//Prints the students to the screen
void printStudents(Student studentList[], int len)
{
	//variables
	int i = 0;

	//loop and print out students
	for (i = 0; i < len; i++)
	{
		studentList[i].writeOutput();
	}
}

//*binarySearch************************************************************************************************
//performs binary search on students
int binarySearch(Student studentList[], int low, int high, Student key)
{
	//variables
	int mid = (high + low) / 2;
	int elem = (high - low) + 1;

	//check if key was found
	if (studentList[mid].sameStudent(key))
	{
		//Key found! return index
		return mid;
	}
	//check if last element was checked
	else if (elem < 2)
	{
		//key not found
		return -1;
	}
	//check if key is higher
	else if (studentList[mid].compareTo(key) > 0)
	{
		cout << "higher" << endl;
		return binarySearch(studentList, low, mid-1, key);
	}
	//check if key is lower
	else if (studentList[mid].compareTo(key) < 0)
	{
		cout << "lower" << endl;
		return binarySearch(studentList, mid + 1, high, key);
	}
	else
	{
		return -1;
	}
}

//*searchStudents************************************************************************************************
//Searches for students using binary search
void searchStudents(Student studentList[], int len)
{
	//variables
	int id = 0;
	int index = 0;
	bool toSearch = true;
	Student * key = NULL;
	//loop and search for students
	while (toSearch)
	{
		//prompt for student id of student to search for
		cout << "Please enter the student ID of the student to search for: " << endl;
		cin >> id;

		//entering neg num will end the search
		if (id >= 0)
		{
			//create new student object to become key. only the ID matters
			key = new Student();
			key->setID(id);

			//perform binary search and look for student
			index = binarySearch(studentList, 0, len, *key);

			//check to if record was found
			if (index >= 0)
			{
				cout << endl << "Student found: " << endl << endl;
				//output student record
				studentList[index].writeOutput();
			}
			else
			{
				//output student not found
				cout << endl << "Student not found " << endl << endl;
			}
		}
		else
		{
			toSearch = false;
		}
	}
}


//*Main*******************************************************************************************************
int main()
{
	//variables
	vector <Student> students();
	Student * studentList = NULL;
	int len = 0;

	//Get students from file
	studentList = getStudents();

	//sort students using selection sort
	sortStudents(studentList, num);

	//print out students
	printStudents(studentList, num);

	//search for students using binary search
	searchStudents(studentList, num);

	system("pause");
	return 0;
}