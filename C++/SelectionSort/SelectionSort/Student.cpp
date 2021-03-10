//Student Class implementation file
//Damian Zylski
//09/13/2020

#include <iostream>
#include <string>
#include "Person.h"
#include "Student.h"
using namespace std;

//constructors
Student::Student() : Person()
{
	studentID = 0;
	major = "Blank";
	level = "Blank";
}

Student::Student(int id, string m, string l, string n) : Person(n)
{
	studentID = id;
	major = m;
	level = l;
}

Student::~Student()
{

}

//Accessors
int Student::getID()
{
	return studentID;
}

string Student::getMajor()
{
	return major;
}

string Student::getLevel()
{
	return level;
}

//Mutators
void Student::setID(int id)
{
	studentID = id;
}

void Student::setMajor(string m)
{
	major = m;
}

void Student::setLevel(string l)
{
	level = l;
}

//write Output
void Student::writeOutput()
{
	Person::writeOutput();
	cout << "Student ID: " << studentID << endl;
	cout << "Major: " << major << endl;
	cout << "Level: " << studentID << endl << endl;
}

//Equal
bool Student::sameStudent(Student s)
{
	if (this->getID() == s.getID())
	{
		return true;
	}
	else
	{
		return false;
	}
}

//compareTo
int Student::compareTo(Student s)
{
	if (this->getID() > s.getID())
	{
		return 1;
	}
	else if (this->getID() < s.getID())
	{
		return -1;
	}
	else if (this->getID() == s.getID())
	{
		return 0;
	}
	else
	{
		return 0;
	}
}

string Student::toString()
{
	return "" + studentID;
}
