# Damian Zylski
# 04/05/2021
# Product class

class Product:

     # Constuctor
    def __init__(self, productID = 0, name = "Blank", price = 0.0, lotSize = 0):
        self.__productID = productID
        self.__name = name
        self.__price = price
        self.__lotSize = lotSize

    # Getters
    @property
    def productID(self):
        return self.__productID

    @property
    def name(self):
        return self.__name

    @property
    def price(self):
        return self.__price

    @property
    def lotSize(self):
        return self.__lotSize

    # Setters
    @productID.setter
    def productID(self, p):
        self.__productID = p

    @name.setter
    def name(self, n):
        self.__name = n

    @price.setter
    def price(self, p):
        self.__price = p

    @lotSize.setter
    def lotSize(self, l):
        self.__lotSize = l

    # Deleters
    @productID.deleter
    def productID(self):
        del self.__productID

    @name.deleter
    def name(self):
        del self.__name

    @price.deleter
    def price(self):
        del self.__price

    @lotSize.deleter
    def lotSize(self):
        del self.__lotSize

    # Write Output
    def writeOutput(self):
        print("Product ID: %d\n"
              "Name: %s\nPrice: %f\nLot Size: %d\n" % (self.__productID, self.__name, self.__price, self.__lotSize))

    # Equal
    def __eq__(self, p):
        if not isinstance(p, Product):
            return NotImplemented
        return self.productID == p.__productID

    # CompareTo
    def compareTo(self, p):
        if not isinstance(p, Product):
            return NotImplemented
        if self.__productID.__gt__(p.__productID):
            return 1
        elif self.__productID.__lt__(p.__productID):
            return -1
        elif self.__productID.__eq__(p.__productID):
            return 0
        else:
            return 0
