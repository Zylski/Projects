# Programmer: Damian Zylski
# Date:       08/29/2020
# System:     Windows 7 - Pycharm Community
# Program:    People Work
#
# Purpose:    To read in student objects from a file, sort them, and output them to the user.

import sys
import string
from Person import Person
from Student import Student

# *getPeople*********************************************************************************************************
# Gets people objects from file
def getPeople(studentList):

    # Variables
    ID = 0
    major = ""
    level = ""
    name = ""
    studentData = [] # the whole line of student data
    # Open file
    with open("Student.dat","r") as file:
        for line in file:
            # Read student objects from file
            studentData = line.split()
            ID = int(studentData[0])
            major = studentData[1]
            level = studentData[2]
            name = studentData[3] + " " + studentData[4]

            # Create new student object
            s = Student(name, ID, major, level)

            # Add student object to student list
            studentList.append(s)
    # Close the file
    file.close()

    return studentList

# *sortStudents*********************************************************************************************************
# sorts the students in the list
def sortStudents(studentList):
    # sort the students
    studentList.sort(key=lambda s: s.studentID, reverse=False)
    None

# *printStudents*********************************************************************************************************
# Prints the students from the list
def printStudents(studentList):
    # Variables
    i = 0
    size = len(studentList)

    # Loop and print out the students
    for i in range(0, size):
        studentList[i].writeOutput()
        print("")

    None

# *Main**************************************************************************************************************
def main():

    # Create student list
    studentList = []

    # Get students from file
    studentList = getPeople(studentList)

    # Sort Student objects
    sortStudents(studentList)

    # Output Student objects
    printStudents(studentList)

    sys.exit(0)
    None

# Run main
if __name__ == "__main__":
    main()

