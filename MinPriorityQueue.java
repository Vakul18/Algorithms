import java.util.*;



public class HelloWorld {
    public static void main(String[] args) {
        
        MinPriorityQueue q = new HelloWorld().new MinPriorityQueue();
        
        System.out.println("Is empty :" + q.isEmpty());
        
        q.insert(1,1);
        q.insert(2,1);
        q.insert(3,1);
        q.insert(4,1);
        q.insert(5,1);
        q.insert(6,2);
        
        while(!q.isEmpty())
        {
          System.out.println(q.pull());  
        }
        
        q.insert(1,1);
        q.insert(2,1);
        q.insert(3,1);
        q.insert(4,1);
        q.insert(5,1);
        q.insert(6,2);
        
        System.out.println("Is empty :" + q.isEmpty());
        q.updatePriority(6,-1);
        q.updatePriority(5,0);
        
         while(!q.isEmpty())
        {
          System.out.println(q.pull());  
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