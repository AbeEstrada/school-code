import sys

def main():
    """Black-box or functional testing is one in which test 
    conditions are developed based on the program or system’s 
    functionality, i.e., the tester re- quires information 
    about the input data and observed output, but does not know 
    how the program or system works. """
    
    arg = sys.argv
    if arg[1].isdigit():
        number = int(arg[1])
        if len(arg[1]) == 1:
            print number * 2
        else:
            print 'ERROR 2'
    else:
        print 'ERROR 1'

if __name__ == '__main__':
    main()