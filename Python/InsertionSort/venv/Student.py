# Student Class
# Damian Zylski
# 09/14/2020

from Person import Person

class Student(Person):

    # Constructor
    def __init__(self, studentID = 0, major = "blank", level = "blank", name = "blank"):
        super(Student,self).__init__(name)
        self.__studentID = studentID
        self.__major = major
        self.__level = level

    # Accessors
    @property
    def studentID(self):
        return self.__studentID

    @property
    def major(self):
        return self.__major

    @property
    def level(self):
        return self.__level

    # Mutators
    @studentID.setter
    def studentID(self, ID):
        self.__studentID = ID

    @major.setter
    def major(self, m):
        self.__major = m

    @level.setter
    def level(self, l):
        self.__level = l

    # deleters
    @studentID.deleter
    def studentID(self):
        del self.__studentID

    @major.deleter
    def major(self):
        del self.__major

    @level.deleter
    def level(self):
        del self.__level

    # writeOutput
    def writeOutput(self):
        super().writeOutput()
        print("StudentID: %d" % self.__studentID)
        print("Major: %s" % self.__major)
        print("Level: %s" % self.__level)
        print("")

    # equal
    def __eq__(self, s):
        if not isinstance(s,Student):
            return NotImplemented
        # check if objects are equal
        return self.__studentID == s.__studentID

    # compareTo
    def compareTo(self, s):
        if not isinstance(s, Student):
            return NotImplemented
        # Compare objects
        if self.__studentID.__gt__(s.__studentID):
            return 1
        elif self.__studentID.__lt__(s.__studentID):
            return -1
        elif self.__studentID.__eq__(s.__studentID):
            return 0
        else:
            return 0

    # toString
    def toString(self):
        return "" & self.__studentID
