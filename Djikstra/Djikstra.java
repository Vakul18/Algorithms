/*
9 14 0
0
1
2
3
4
5
6
7
8
0 1 4
0 7 8
1 2 8
1 7 11
2 8 2
2 3 7
2 5 4
3 4 9
3 5 14
4 5 10
5 6 2
6 7 1
6 8 6
7 8 7
*/


import java.util.*;



public class HelloWorld {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int nv = sc.nextInt();
        int ne = sc.nextInt();
        int s = sc.nextInt();
        
        Graph g = new HelloWorld().new Graph();
        
        for(int i = 0 ; i< nv ; i++)
        {
           int v = sc.nextInt();
           g.addVertex(v);
        }
        
        for(int i = 0 ; i < ne ; i++)
        {
           int v1 = sc.nextInt();
           int v2 = sc.nextInt();
           int w = sc.nextInt();
           
           g.addEdge(v1,v2,w);
        }
        
        
        HashMap<Integer,Integer> dist = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> prev = new HashMap<Integer,Integer>();
        
        djikstra(dist,prev,g,s);
        
        System.out.println("DIST : ");
        
        printHM(dist);
        
        System.out.println("PREV : ");
        
        printHM(prev);
        
        
        
        
       
        
        
    }
    
    public static void printHM(HashMap<Integer,Integer> hm)
    {
      for (Integer name: hm.keySet()) {
        String key = name.toString();
        String value = hm.get(name).toString();
        System.out.println(key + " " + value);
      }
    }
    
    public static void djikstra(HashMap<Integer,Integer> dist,HashMap<Integer,Integer> prev , Graph g, int s)
    {
      MinPriorityQueue q = new HelloWorld().new MinPriorityQueue();
      dist.put(s,0);
      
      for(int v : g.getVertices())
      {
        if(v!=s)
        {
          dist.put(v,Integer.MAX_VALUE);
          prev.put(v,-1);
        }
        
        q.insert(v,dist.get(v));
      }
      
     
      
      while(!q.isEmpty())
      {
        int v = q.pull();
        
        for(Edge u : g.getNeighbours(v))
        {
          int alt = dist.get(v) + u.w;
          
          if(alt < dist.get(u.node))
          {
            dist.put(u.node,alt);
            q.updatePriority(u.node,alt);
            prev.put(u.node,v);
          }
        }
        
      
      }
      
      
      
      
    }
    
    class Edge
    {
        public int node;
        public int w;
        
        public Edge(int node, int w)
        {
          this.node = node;
          this.w = w;
        }
    }
    
    class Graph
    {
      
      HashSet<Integer> _V = new HashSet<Integer>();
      HashMap<Integer,HashSet<Edge>>  _E = new HashMap<Integer,HashSet<Edge>>();
      
      
      public HashSet<Edge> getNeighbours(int v)
      {
        HashSet<Edge> set = new HashSet<Edge>();
        if(_E.containsKey(v))
        {
          HashSet<Edge> es = _E.get(v);
          for(Edge e : es)
          {
            set.add(e);
          }
        }
        
          return set;
        
      }
      
      public void addVertex(int v)
      {
        _V.add(v);
      }
      
      public void addEdge(int v1, int v2,int w)
      {
        addDirEdge(v1,v2,w);
        addDirEdge(v2,v1,w);
      }
      
      public HashSet<Integer> getVertices()
      {
        return (HashSet<Integer>)_V.clone();
      }
      
      private void addDirEdge(int v1,int v2, int w)
      {
        Edge e = new Edge(v2,w);
        if(_E.containsKey(v1))
        {
          HashSet<Edge> set = _E.get(v1);
          set.add(e); 
        }
        else
        {
          HashSet<Edge> set = new HashSet<Edge>();
          set.add(e);
          _E.put(v1,set);
        }
      }
      
    }
    
    class MinPriorityQueue
    {
      
      private class Node
      {
        public int value;
        public int priority;
        
        public Node(int value, int priority)
        {
          this.value = value;
          this.priority = priority;
        }
      }
      
      private class Heap
      {
        ArrayList<Node> _l = new ArrayList<Node>();
        
        public boolean isEmpty()
        {
          return (_l.size() == 0);
        }
        
        public void insert(Node n)
        {
          _l.add(n);
          
          upHeapify(_l.size()-1);
        }
        
        public Node extract()
        {
          Node n = _l.get(0);
          delete(0);
          return n;
        }
        
        public Node peek()
        {
          return _l.get(0);
        }
        
        public void update(Node n)
        {
          int i = 0;
          for( ; i< _l.size();i++)
          {
            Node node = _l.get(i);
            //System.out.println("I2 : " + i);
            if(node.value == n.value )
            {
               //System.out.println("I1 : " + i);
               break;
            }
          }
          
          //System.out.println("I : " + i);
          Node nL = _l.get(i);
          
          if(nL.priority > n.priority)
          {
            nL.priority = n.priority;
            upHeapify(i);
          }
          else
          {
            nL.priority = n.priority;
            downHeapify(i);
          }
          
        }
        
        private void upHeapify(int i)
        {
          int parent = i/2;
          if(_l.get(i).priority < _l.get(parent).priority)
          {
            swap(i,parent);
            
            if(parent == 0)
            {
              return;
            }
            else
            {
              upHeapify(parent);
            }
          }
          else
          {
            return;
          }
          
        }
        
        private void swap(int i1,int i2)
        {
          Node temp = _l.get(i1);
          _l.set(i1,_l.get(i2));
          _l.set(i2,temp);
        }
        
        private void delete(int i)
        {
          int last  = _l.size()-1;
          swap(i,last);
          _l.remove(last);
          downHeapify(i);
        }
        
        private void downHeapify(int i)
        {
          int lChild = 2*i + 1;
          int rChild = 2*i + 2;
          int s = _l.size();
          
          
          if(rChild < s)
          {
            int smallest = min(i,lChild,rChild);
            
            if(i==smallest)
            {
              return;
            }
            else
            {
              swap(i,smallest);
              downHeapify(smallest);
            }
            
          }
          
          if(lChild < s)
          {
            int smallest = i;
            if(_l.get(i).priority > _l.get(lChild).priority)
            {
              smallest = lChild;
              swap(i,smallest);
              downHeapify(smallest);
            }
            
          }
          return;
        }
        
        private int min(int i1,int i2, int i3)
        {
          
          int ret = i1;
          
          int v1 = _l.get(i1).priority;
          int v2 = _l.get(i2).priority;
          int v3 = _l.get(i3).priority;
          
          if(v1 <= v2 && v1 <= v3)
          {
            return i1;
          }
          else if(v2 <= v1 && v2<=v3)
          {
             return i2;
          }
          else
          {
            return i3;
          }
          
        }
        
      }
      
      
      
       private Heap _heap = new Heap();
       
       public boolean isEmpty()
       {
         return _heap.isEmpty();
       }
       
       public void insert(int value, int priority)
       {
         
         Node n = new Node(value,priority);
         
         _heap.insert(n);
         
       }
       
       public int pull()
       {
         return _heap.extract().value;
       }
       
       public int peek()
       {
         return _heap.peek().value;
       }
       
       public void updatePriority(int value, int priority)
       {
         Node n = new Node(value,priority);
         _heap.update(n);
       }
       
       
       
       
    }
    
    
}