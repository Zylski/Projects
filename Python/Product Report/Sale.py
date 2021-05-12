# Damian Zylski
# 04/05/2021
# Sale class

class Sale:

     # Constuctor
    def __init__(self, saleID = 0, productID = 0, teamID = 0, quantity = 0):
        self.__saleID = saleID
        self.__productID = productID
        self.__teamID = teamID
        self.__quantity = quantity

    # Getters
    @property
    def saleID(self):
        return self.__saleID

    @property
    def productID(self):
        return self.__productID

    @property
    def teamID(self):
        return self.__teamID

    @property
    def quantity(self):
        return self.__quantity

    # Setters
    @saleID.setter
    def saleID(self, s):
        self.__saleID = s

    @productID.setter
    def productID(self, p):
        self.__productID = p

    @teamID.setter
    def teamID(self, t):
        self.__teamID = t

    @quantity.setter
    def quantity(self, q):
        self.__quantity = q

    # Deleters
    @saleID.deleter
    def saleID(self):
        del self.__saleID

    @productID.deleter
    def productID(self):
        del self.__productID

    @teamID.deleter
    def teamID(self):
        del self.__teamID

    @quantity.deleter
    def quantity(self):
        del self.__quantity

    # Write Output
    def writeOutput(self):
        print("Sales ID: %d\n"
              "Product ID: %d\n"
              "Team ID: %d\nQuantity: %d\n" % (self.__saleID, self.__productID, self.__teamID, self.__quantity))

    # Equal
    def __eq__(self, s):
        if not isinstance(s, Sale):
            return NotImplemented
        return self.saleID == s.__saleID
    # Less than
    def __lt__(self, s):
        if not isinstance(s, Sale):
            return NotImplemented
        return self.saleID < s.__saleID
    # Greater than
    def __gt__(self, s):
        if not isinstance(s, Sale):
            return NotImplemented
        return self.saleID > s.__saleID

    # CompareTo
    def compareTo(self, s):
        if not isinstance(s, Sale):
            return NotImplemented
        if self.__saleID.__gt__(s.__saleID):
            return 1
        elif self.__saleID.__lt__(s.__saleID):
            return -1
        elif self.__saleID.__eq__(s.__saleID):
            return 0
        else:
            return 0
