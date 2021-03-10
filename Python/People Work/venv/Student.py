# Student Class
# Damian Zylski
# 08/29/2020

from Person import Person

class Student(Person):

    # Contructor
    def __init__(self, name = "Blank", studentID = 0, major = "Blank", level = "blank"):
        super(Student, self).__init__(name)
        self.__studentID = studentID
        self.__major = major
        self.__level = level


    # Getters
    @property
    def studentID(self):
        return self.__studentID

    @property
    def major(self):
        return self.__major

    @property
    def level(self):
        return self.__level

    # Setters
    @studentID.setter
    def studentID(self, id):
        self.__studentID = id

    @major.setter
    def major(self, m):
        self.__major = m

    @level.setter
    def level(self, l):
        self.__level = l

    # Deleters
    @studentID.deleter
    def studentID(self):
        del self.__studentID

    @major.deleter
    def major(self):
        del self.__major

    @level.deleter
    def level(self):
        del self.__level

    # WriteOutput
    def writeOutput(self):
        super().writeOutput()
        print("Student ID: %s" % self.__studentID)
        print("Major: %s" % self.__major)
        print("Level: %s" % self.__level)

    # Equal
    def __eq__(self, s):
        if not isinstance(s, Student):
            return NotImplemented
        # check if objects are equal
        return self.__studentID == s.__studentID

    # CompareTo
    def compareTo(self, s):
        if not isinstance(s, Student):
            return NotImplemented
        # compare objects by name
        if self.__studentID.__gt__(s.__studentID):
            return 1
        elif self.__studentID.__lt__(s.__studentID):
            return -1
        elif self.__studentID.__eq__(s.__studentID):
            return 0
        else:
            return 0

