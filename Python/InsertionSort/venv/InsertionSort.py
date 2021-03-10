# Programmer: Damian Zylski
# Date:       09/14/2020
# System:     Windows 7 - Pycharm Community
# Program:    InsertionSort
#
# Purpose:    To read in student objects from a file. The will then be sorted using Insertion Sort
# and search for using binary search

import sys
import string
from Person import Person
from Student import Student

# *getStudents*********************************************************************************************************
# Gets the students from file
def getStudents():
    # variables
    id = 0
    m = ""
    l = ""
    name = ""
    studentData = [] # whole line of student data from file
    studentList = []
    i = 0
    size = 0

    # open file
    with open("Student.dat", "r") as file:
        for line in file:
            # Read student objects from file
            studentData = line.split()
            size = len(studentData)
            for i in range(0,size):
                if i == 0:
                    id = int(studentData[i])
                elif i == 1:
                    m = studentData[i]
                elif i == 2:
                    l = studentData[i]
                elif i > 2:
                    name = name + studentData[i] + " "
            # trim whitespace from name
            name = name.strip()
            # Create new student object
            s = Student(id, m, l, name)
            # Add student to list
            studentList.append(s)
            # reset name
            name = ""

        # close file
        file.close()


    return studentList

# *sortStudents*********************************************************************************************************
# sorts the students using Insertion Sort
def sortStudents(studentList):
    # variables
    i = 0
    j = 0
    size = len(studentList)
    temp = None

    # loop and sort the students
    for i in range(1,size):
        for j in range(i,0,-1):
            # check if previous id number is smaller
            if studentList[j].compareTo(studentList[j-1]) < 0:
                # swap values if current is smaller than previous
                temp = studentList[j-1]
                studentList[j-1] = studentList[j]
                studentList[j] = temp
            else:
                break


# *printStudents*********************************************************************************************************
# prints out students from the list
def printStudents(studentList):
    # Variables
    i = 0
    size = len(studentList)

    #loop and print the students
    for i in range(0,size):
        studentList[i].writeOutput()

# *binarySearch*********************************************************************************************************
# Performs binary search on students
def binarySearch(studentList, low, high, key):
    # Variables
    mid = int((high + low) / 2)
    elem = (high - low) + 1

    # If key matches current index
    if studentList[mid].__eq__(key):
        # key found! return index
        return mid
    elif elem < 2:
        # Key not found
        return -1
    # if key is higher
    elif studentList[mid].compareTo(key) > 0:
        # search lower half
        print("higher")
        return binarySearch(studentList, low, mid-1, key)
    # if key is lower
    elif studentList[mid].compareTo(key) < 0:
        # search upper half
        print("lower")
        return binarySearch(studentList, mid+1, high, key)
    else:
        return -1

# *searchStudents*********************************************************************************************************
# searches for students using id number
def searchStudents(studentList):
    # Variables
    id = 0
    toSearch = True
    index = 0
    size = len(studentList)
    # Loop and search for students
    while toSearch:
        # prompt for input
        id = int(input("Please enter the ID of the student to search for: \n"))

        if id >= 0:
            # Create new student object
            key = Student()
            key.studentID = id

            # search for student
            index = binarySearch(studentList, 0, size, key)

            #check if student was found
            if index >= 0:
                print("Student Found: ")
                print("")
                studentList[index].writeOutput()
            else:
                print("Student Not Found: ")
                print("")
        else:
            toSearch = False

# *Main**************************************************************************************************************
def main():
    # Variables
    studentList = []

    # Get students from file
    studentList = getStudents()

    # Sort Students using inderstion sort
    sortStudents(studentList)

    # Print students out to user
    printStudents(studentList)

    # Search for students
    searchStudents(studentList)

    sys.exit(0)
    None

# run main
if __name__ == "__main__":
    main()