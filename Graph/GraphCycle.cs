class Solution {
  private
    bool isCycleUtil(int u, int par, ref bool[] vis, ref List<List<int>> adj) {
        // marking vertex as visited and adding it to output list.
        vis[u] = true;

        // iterating over connected components of the vertex and if any
        // of them is not visited then calling the function recursively.
        for (int i = 0; i < adj[u].Count; i++) {
            if (adj[u][i] == par) continue;
            if (vis[adj[u][i]]) return true;
            if (isCycleUtil(adj[u][i], u, ref vis, ref adj)) {
                return true;
            }
        }
        return false;
    }

    // Function to detect cycle in an undirected graph.
  public
    bool isCycle(int V, List<List<int>> adj) {
        // using a boolean list to mark all the vertices as not visited.
        bool[] vis = new bool[V];
        for (int i = 0; i < V; i++) vis[i] = false;
        for (int i = 0; i < V; i++) {
            // if vertex is not visited, we call the function to detect cycle.
            if (!vis[i]) {
                bool f = isCycleUtil(i, -1, ref vis, ref adj);
                // if cycle is found, we return true.
                if (f) return true;
            }
        }
        return false;
    }
}
