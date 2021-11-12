import collections

class MongeArray:
    def __init__(self,arr,m,n):
        self.Arr = arr
        self.r = m
        self.c = n
        self.__minDict = {}
        self.__findEachRowMin([i for i in range(0,self.r) ])

    def __findEachRowMin(self,rowList):
        #print(f"rowList : {rowList}")
        if(len(rowList)==1):
             self.__findMin(rowList[0],0,self.c-1,1)
        else:
            oddList = []
            evenList = []
            totalRows = len(rowList)
            for i in range(0,totalRows):
                if i%2 == 1:
                    evenList.append(rowList[i])
                else :
                    oddList.append(rowList[i])
            #print(f'oddList : {oddList} , evenList : {evenList}')
            self.__findEachRowMin(evenList)
            self.__processOddList(rowList)
                 
    def __findMin(self,rowIndex,start,end,step):
        minIdx = start
        min = self.Arr[rowIndex][start]
        i = minIdx
        while i<=end:
            if min>self.Arr[rowIndex][i]:
                min  = self.Arr[rowIndex][i]
                minIdx = i
            i=i+step
        self.__minDict[rowIndex] = minIdx

    def __processOddList(self,rowList):
        #print(rowList)
        #print(self.__minDict)
        i = self.__minDict[rowList[1]]
        idxMin = i
        firstRow = rowList[0]
        self.__findMin(firstRow,idxMin,0,-1)
       
        n = len(rowList)
        i = 2
        while i< n-1:
            rowIndex = rowList[i]
            idxMin = self.__minDict[rowList[i-1]]
            idxMax = self.__minDict[rowList[i+1]]
            self.__findMin(rowIndex,idxMin,idxMax,1)
            i=i+2

        if n%2==1:
            startIdx =  self.__minDict[rowList[n-2]]
            self.__findMin(rowList[n-1],startIdx,self.c-1,1)
              
    def printMin(self):
        print(collections.OrderedDict(sorted(self.__minDict.items())))
                        
arr = [[10,17,13,28,23],[17,22,16,29,23],[24,28,22,34,24],[11,13,6,17,7],[45,44,32,37,23],[36,33,19,21,6],[75,66,51,53,34]]
mArr = MongeArray(arr,7,5)
mArr.printMin()
