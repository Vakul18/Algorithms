'''
# Sample code to perform I/O:

name = input()                  # Reading input from STDIN
print('Hi, %s.' % name)         # Writing output to STDOUT

# Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
'''
runningSum = 0
# Write your code here
def GetVowelCount(string):
    c = 0
    for i in range(0,len(string)):
         if IsVowel(string[i]):
            c=c+1  
    return c

def IsVowel(c):
    return (c=='a') or (c=='A') or (c=='e') or (c=='E') or c == 'o' or c=='O' or c=='i'or c =='I'or c =='u' or c =='U'

def SumVowels(string,sumBoth):
    global runningSum
    strLen = len(string) 
    if(strLen>1):
        v1 = int(IsVowel(string[0])) + SumVowels(string[1:],False)
        if sumBoth or (strLen==2) :
            SumVowels(string[:-1],True)
        runningSum = runningSum + v1
        #print(string + ' : ' + str(v1))
        #if sumBoth or (strLen==2) :
         #   print('String1 = ' + string[1:] + ' String2 = ' + string[:-1]  + ' Sum value = ' + str(v1) + ' RunningSum value = ' + str(runningSum))
        #else :
            #print('String = ' + string[1:] + ' Sum value = ' + str(v1) + ' RunningSum value = ' + str(runningSum))
        return v1
    else:
        if IsVowel(string):
            #runningSum = runningSum + 1
            #print('String = ' + string  + ' Sum value = 1' + ' RunningSum value = ' + str(runningSum))
            return 1
        else:
            return 0

try:
    
    testCases = int(input())
    
    for t in range(0,testCases) :
        string = input()
        
        SumVowels(string,True)
        #print('checkpoint 1 s=' + str(runningSum))
        runningSum = runningSum+ GetVowelCount(string)
        print(runningSum)
        runningSum = 0
except BaseException as error:
    print('Exception occured : {}'.format(error))
        
