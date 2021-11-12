import math

minPath = math.inf

class Path:
    def __init__(self,m):
        self.__cost=0
        self.__m = m
        self.__last = -1
        self.__p = []
    
    def append(self,v):
        #print('m[{}][{}] : {}'.format(self.__last,v,m[self.__last][v]))
        if self.__last!= -1:
            self.__cost += m[self.__last][v]
        self.__last = v
        self.__p.append(v)

    def copy(self):
        newPath = Path(self.__m)
        newPath.__cost = self.__cost
        newPath.__m = self.__m
        newPath.__last = self.__last
        newPath.__p = self.__p
        return newPath
    
    def printP(self):
        print(self.__p)
    
    def cost(self):
        return self.__cost


def minP(vSet,path):
    global minPath
    if len(vSet)==1:
        #print('==1 : {}'.format(vSet))
        pathCopy = path.copy()
        pathCopy.append(vSet.pop())
        pathCopy.append(0)
        if minPath > pathCopy.cost():
            minPath = pathCopy.cost()
        #pathCopy.printP()
    else :
        for v in vSet:
            #print('v : {}'.format(v))
            pathCopy = path.copy()
            pathCopy.append(v)
            vSetCopy = vSet.copy()
            vSetCopy.remove(v)
            minP(vSetCopy,pathCopy)
    return

t= int(input())
for i in range(0,t):
    n = int(input())
    m = []
    minPath = math.inf
    for j in range(0,n):
        s = input()
        m.append(list(map(int,s.split())))
    #print(m)
    v= set([*range(1,n)])
    #print(v)
    path = Path(m)
    path.append(0)
    minP(v,path)
    print(minPath)
