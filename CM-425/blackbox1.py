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
        if number > 0 and number <= 9:
            if number == 1:
                print 1+1
            elif number == 2:
                print 2+2
            elif number == 3:
                print 3+3
            elif number == 4:
                print 4+4
            elif number == 5:
                print 5+5
            elif number == 6:
                print 6+6
            elif number == 7:
                print 7+7
            elif number == 8:
                print 8+8
            elif number == 9:
                print 9+9
        else:
            print 'ERROR 2'
    else:
        print 'ERROR 1'

if __name__ == '__main__':
    main()