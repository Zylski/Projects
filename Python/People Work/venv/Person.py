# Person Class
# Damian Zylski
# 08/29/2020
class Person:

    # Contructor
    def __init__(self, name = "Blank"):
        self.__name = name

    # Getters
    @property
    def name(self):
        return self.__name

    # Setters
    @name.setter
    def name(self, n):
        self.__name = n

    # Deleters
    @name.deleter
    def name(self):
        del self.__name

    # WriteOutput
    def writeOutput(self):
        print("Name: %s" % self.__name)

    # Equal
    def __eq__(self, p):
        if not isinstance(p, Person):
            return NotImplemented
        # check if objects are equal
        return self.__name == p.__name

    # CompareTo
    def compareTo(self, p):
        if not isinstance(p, Person):
            return NotImplemented
        # compare objects by name
        if self.__name.__gt__(p.__name):
            return 1
        elif self.__name.__lt__(p.__name):
            return -1
        elif self.__name.__eq__(p.__name):
            return 0
        else:
            return 0

