//Person Class implementation file
//Damian Zylski
//08/13/2020

#include "Person.h"
#include <iostream>
#include <string>
using namespace std;

//Constructors
Person::Person()
{
	name = "Blank";
}

Person::Person(string n)
{
	name = n;
}

//Destructor
Person::~Person()
{

}

//Getters
string Person::getName()
{
	return name;
}
//Setters
void Person::setName(string n)
{
	name = n;
}
//Write Output
void Person::writeOutput()
{
	cout << "Name: " << name << endl;
}
//Equal
bool Person::equal(Person p)
{
	if (this->name.compare(p.name) == 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}
//Compare To
int Person::compareTo(Person p)
{
	if (this->name.compare(p.name) > 0)
	{
		return 1; //greater than
	}
	else if (this->name.compare(p.name) < 0)
	{
		return -1; //less than
	}
	else if (this->name.compare(p.name) == 0)
	{
		return 0; //equal
	}
	else
	{
		return 0; //default
	}
}