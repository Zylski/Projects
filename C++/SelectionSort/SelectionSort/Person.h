//Person class header
//Damian Zylski
//08/13/2020
#ifndef PERSON_H
#define PERSON_H
#pragma once

#include <string>
using namespace std;

class Person
{
	//Attibutes
	//private
private:
	string name;
	//public
public:
	//Constructors
	//Default
	Person();

	Person(string n);

	//destructor
	~Person();

	//Getters
	string getName();

	//Setters
	void setName(string n);

	//Write Output
	void writeOutput();

	//equal
	bool equal(Person p);

	//Compare To
	int compareTo(Person p);

};

#endif
