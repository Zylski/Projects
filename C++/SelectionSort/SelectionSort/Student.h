//Person class header
//Damian Zylski
//09/13/2020
#ifndef STUDENT_H
#define STUDENT_H
#pragma once

#include <string>
using namespace std;

class Student : public Person
{
	//Attributes
	private:
		int studentID;
		string major;
		string level;
	public:
		//constructors and destructors
		Student();
		Student(int id, string m, string l, string n);
		~Student();

		//accessors
		int getID();
		string getMajor();
		string getLevel();

		//mutators
		void setID(int id);
		void setMajor(string m);
		void setLevel(string l);

		//Writeoutput
		void writeOutput();

		//equal
		bool sameStudent(Student s);

		//CompareTo
		int compareTo(Student s);

		//toString
		string toString();

};
#endif

