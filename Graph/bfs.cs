class Solution {

    // Function to return Breadth First Traversal of given graph.
  public
    List<int> bfsOfGraph(int V, List<List<int>> adj) {
       var op = new List<int>();
       
        HashSet<int> hs = new HashSet<int>();
        var q = new Queue<int>();
        
        q.Enqueue(0);
        hs.Add(0);
        op.Add(0);
        while(q.Count>0)
        {
            int u = q.Dequeue();
            foreach(int v in adj[u])
            {
                if(hs.Contains(v))
                    continue;
                op.Add(v);
                hs.Add(v);
                q.Enqueue(v);
            }
        }
        
        return op;
    }
    

}
