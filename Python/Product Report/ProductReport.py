# Programmer: Damian Zylski
# Date:       04/05/2020
# System:     Windows 7 - Pycharm Community
# Program:    Product Report
#
# Purpose:    To create a product report from Product and Sales input files and write that information to
# a file.

import string
import sys
import logging
from Product import Product
from Sale import Sale
from Report import Report

# *getProducts***************************************************************************************************
# Gets list of products from input file and returns dictionary of products
def getProducts(productFile):
    # variables
    productDict = {} # A dictionary makes more sense for this solution
    fileList = None
    lineList = None

    # open file
    file = open(productFile,"r")
    # read from file into a list, storing all products
    fileList = file.readlines()
    # close file
    file.close()

    # go through each line in file and create objects, adding objects to product dictionary
    # First we get the number of products
    size = len(fileList)
    # then we loop through each product
    for i in range(0, size):
        # for each product, we split each comma seperated item into a sublist
        lineList =  fileList[i].split(',')
        # Create new product from that sublist
        p = Product(int(lineList.pop(0)),lineList.pop(0),float(lineList.pop(0)),int(lineList.pop(0)))
        # Add new product to main product dictionary
        productDict.update({str(p.productID): p})
    # return product dictionary
    return productDict

# *getSales***************************************************************************************************
# Gets list of sales from input file and returns list of sales
def getSales(salesFile):
    # variables
    salesList = []
    fileList = None
    lineList = None

    # open file
    file = open(salesFile,"r")
    # read from file into a list, storing all sales
    fileList = file.readlines()
    # close file
    file.close()

    # go through each line in file and create objects, adding objects to sales list
    # First we get the number of sales
    size = len(fileList)
    # then we loop through each sale
    for i in range(0, size):
        # for each sale, we split each comma seperated item into a sublist
        lineList =  fileList[i].split(',')
        # Create new sale from that sublist
        s = Sale(int(lineList.pop(0)),int(lineList.pop(0)),int(lineList.pop(0)),int(lineList.pop(0)))
        # Add new product to main product list
        salesList.append(s)

    # return product List
    return salesList

# *writeReport***************************************************************************************************
# Creates a report file and writes report to it
def writeReport(productDict, salesList, reportFile):

    # Variables
    salesSize = len(salesList)
    totalUnits = 0
    grossRevenue = 0
    reportList = []

    # Write header for report file
    file = open(reportFile, "w")
    file.write("Name,GrossRevenue,TotalUnits");
    # close file
    file.close()



    # Loop through sales list and create report objects
    for i in range(0, salesSize):
        # Grab next sale from list
        s = salesList[i]
        # Get matching product from product dict
        p = productDict.get(str(s.productID))
        # Calculate total units
        totalUnits = s.quantity * p.lotSize
        # Calculate gross revenue
        grossRevenue = p.price * totalUnits
        # Create report object
        r = Report(p.name, grossRevenue, totalUnits)
        # Add report object to list
        reportList.append(r)

    # Loop trough report list and append to output file with calculations for each sale
    # Reopen file for appending
    file = open(reportFile, "a")
    # Get number of reports items
    reportSize = len(reportList)
    # Sort report
    reportList.sort()
    reportList.reverse()

    # Loop and append to file
    for i in range(0, reportSize):
        file.write("\n%s,%.2f,%d" % (reportList[i].name, reportList[i].revenue, reportList[i].totalUnits))

    # close file
    file.close()

    # Output success message
    print("Report file generated successfully.")

    None

# *main***************************************************************************************************
def main():
    # Variables
    error = False
    productFile = None
    saleFile = None
    reportFile = None

    # get file names from command line arguments
    try:
        productFile = sys.argv[1]
        saleFile = sys.argv[2]
        reportFile = sys.argv[3]
    except IndexError as e:
        logger = logging.getLogger(__name__)
        logger.error("Invalid Argument!")
        print("Invalid Argument!")
        print(e)
        error = True

    try: # Going to catch a lot of errors below here!
        # Get product dict from product master file
        productDict = getProducts(productFile)

        # Get sales list from sale file
        salesList = getSales(saleFile)
    except FileNotFoundError as e:
        logger = logging.getLogger(__name__)
        logger.error("File not found!")
        print("File not found!")
        print(e)
        error = True
    except IndexError as e:
        logger = logging.getLogger(__name__)
        logger.error("Index Error!")
        print("Index Error!")
        print(e)
        error = True
    except ValueError as e:
        logger = logging.getLogger(__name__)
        logger.error("Invalid Type Error!")
        print("Invalid Type Error!")
        print(e)
        error = True
    except MemoryError as e:
        logger = logging.getLogger(__name__)
        logger.error("Ran out of RAM! Consider writing a better program!")
        print("Ran out of RAM! Consider writing a better program!")
        print(e)
        error = True
    except AttributeError as e:
        logger = logging.getLogger(__name__)
        logger.error("Assignment went wrong!")
        print("Assignment went wrong!")
        print(e)
        error = True
    except KeyError as e:
        logger = logging.getLogger(__name__)
        logger.error("Dictionary element not found!")
        print("Dictionary element not found!")
        print(e)
        error = True

    # Create report list
    try:
        if not error:
            writeReport(productDict, salesList, reportFile)
        else:
            print("\nSomething went wrong, check files and input")
    except FileNotFoundError as e:
        logger = logging.getLogger(__name__)
        logger.error("File not found!")
        print("File not found!")
        print(e)
    except IndexError as e:
        logger = logging.getLogger(__name__)
        logger.error("Index Error!")
        print("Index Error!")
        print(e)
    except ValueError as e:
        logger = logging.getLogger(__name__)
        logger.error("Invalid Type Error!")
        print("Invalid Type Error!")
        print(e)
    except MemoryError as e:
        logger = logging.getLogger(__name__)
        logger.error("Ran out of RAM! Consider writing a better program!")
        print("Ran out of RAM! Consider writing a better program!")
        print(e)
    except AttributeError as e:
        logger = logging.getLogger(__name__)
        logger.error("Assignment went wrong!")
        print("Assignment went wrong!")
        print(e)
    except KeyError as e:
        logger = logging.getLogger(__name__)
        logger.error("Dictionary element not found!")
        print("Dictionary element not found!")
        print(e)

    None

if __name__ == "__main__":
    main()