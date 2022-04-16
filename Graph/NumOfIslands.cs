// { Driver Code Starts
// Initial Template for C#

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DriverCode {

class GFG {
    static void Main(string[] args) {
        int testcases; // Taking testcase as input
        testcases = Convert.ToInt32(Console.ReadLine());
        while (testcases-- > 0) // Looping through all testcases
        {
            var ip = Console.ReadLine().Trim().Split(' ');
            int n = int.Parse(ip[0]);
            int m = int.Parse(ip[1]);
            List<List<int>> grid = new List<List<int>>();
            for (int i = 0; i < n; i++) {
                grid.Add(new List<int>());
            }
            for (int i = 0; i < n; i++) {
                ip = Console.ReadLine().Trim().Split(' ');
                for (int j = 0; j < m; j++) {
                    grid[i].Add(int.Parse(ip[j]));
                }
            }
            Solution obj = new Solution();
            var res = obj.numIslands(ref grid);
            Console.WriteLine(res);
        }
    }
}
}// } Driver Code Ends


// User function Template for C#

class Solution {
    // Function to find the number of islands.
  
public
    int numIslands(ref List<List<int>> grid) {
        // Code here
        
        int c = 0;
        var visited = new List<List<bool>>();
        
        for(int i = 0; i< grid.Count ; i++)
        {
            var l = new List<bool>();
            visited.Add(l);
            for(int j=0; j<grid[i].Count; j++)
            {
                l.Add(false);
            }
        }
        
        for(int i = 0; i< grid.Count ; i++)
        {
            for(int j=0; j<grid[i].Count; j++)
            {
                if(grid[i][j]==0)
                    continue;
                var rec = new bool[grid.Count , (grid[i].Count)];
                if(!dfs(grid,visited,i,j,grid.Count,grid[i].Count,rec))
                {
                    c++;
                }
            }
        }
        
        return c;
        
    }
    
    public bool dfs(List<List<int>> grid, List<List<bool>> visited,int i , int j, int n , int m,bool[,] rec)
    {
        if(visited[i][j] && !rec[i,j])
        {
            return true;
        }
        
        if(rec[i,j])
            return false;
        
        visited[i][j] = true;
        rec[i,j] = true;
        
        List<Tuple<int,int>> adj = GetAdj(i,j,grid, n,m);
        
        
        foreach(var t in adj)
        {
            //Console.WriteLine("i,j : " +i + "," + j + " tuple : " + t.Item1 + " , " + t.Item2 );
            if(dfs(grid,visited,t.Item1,t.Item2,n,m,rec))
            {
               return true; 
            }
            
        }
        
        return false;
    }
    
    public List<Tuple<int,int>> GetAdj(int i , int j, List<List<int>> grid, int n , int m)
    {
        var adj = new List<Tuple<int,int>>();
        int[] off = {-1,0,1};
        
        for(int k = 0 ; k<3 ; k++)
        {
            int x = i + off[k];
            if(!(x>=0 && x<n))
                continue;
            for(int l = 0 ; l<3 ; l++)
            {
                if(k==1 && l==1)
                {
                    continue;
                }
                int y = j + off[l];
                if(!(y>=0 && y<m))
                    continue;
                if(grid[x][y] == 1)
                    adj.Add(new Tuple<int,int>(x,y));
            }
            
            
        }
        
        return adj;
        
    }
}
