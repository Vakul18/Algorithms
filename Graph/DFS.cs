
class Solution {
    // Function to return a list containing the DFS traversal of the graph.
  public
    List<int> dfsOfGraph(int V, List<List<int>> adj) {
        // Code here()l
       var op = new List<int>();
       
        HashSet<int> hs = new HashSet<int>();
        
        op.Add(0);
        hs.Add(0);
        dfs(op,adj,0,hs);
        
        return op;
    }
    
    public void dfs(List<int> op, List<List<int>> adj, int u, HashSet<int> hs)
    {
        foreach(int v in adj[u])
        {
            if(hs.Contains(v))
                continue;
            op.Add(v);
            hs.Add(v);
            dfs(op,adj,v,hs);
        }
    }
    
}
