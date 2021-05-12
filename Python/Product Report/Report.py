# Damian Zylski
# 04/05/2021
# Report class

class Report:

     # Constuctor
    def __init__(self, name = "blank", revenue = 0.0, totalUnits = 0):
        self.__name = name
        self.__revenue = revenue
        self.__totalUnits = totalUnits

    # Getters
    @property
    def name(self):
        return self.__name

    @property
    def revenue(self):
        return self.__revenue

    @property
    def totalUnits(self):
        return self.__totalUnits

    # Setters
    @name.setter
    def name(self, n):
        self.__name = n

    @revenue.setter
    def revenue(self, r):
        self.__revenue = r

    @totalUnits.setter
    def totalUnits(self, t):
        self.__totalUnits = t

    # Deleters
    @name.deleter
    def name(self):
        del self.__name

    @revenue.deleter
    def revenue(self):
        del self.__revenue

    @totalUnits.deleter
    def totalUnits(self):
        del self.__totalUnits

    # Write Output
    def writeOutput(self):
        print("Name: %d\n"
              "Revenue: %d\n"
              "Total Units: %d\n" % (self.__name, self.__revenue, self.__totalUnits))

    # Equal
    def __eq__(self, r):
        if not isinstance(r, Report):
            return NotImplemented
        return self.revenue == r.__revenue
    # Less than
    def __lt__(self, r):
        if not isinstance(r, Report):
            return NotImplemented
        return self.revenue < r.__revenue
    # Greater than
    def __gt__(self, r):
        if not isinstance(r, Report):
            return NotImplemented
        return self.__revenue > r.__revenue
