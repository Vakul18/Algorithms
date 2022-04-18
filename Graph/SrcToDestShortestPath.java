// { Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;
class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int N, M, x, y;
            String S[] = read.readLine().split(" ");
            N = Integer.parseInt(S[0]);
            M = Integer.parseInt(S[1]);
            int a[][] = new int[N][M];
            for (int i = 0; i < N; i++) {
                String s[] = read.readLine().split(" ");
                for (int j = 0; j < M; j++) a[i][j] = Integer.parseInt(s[j]);
            }
            String s1[] = read.readLine().split(" ");
            x = Integer.parseInt(s1[0]);
            y = Integer.parseInt(s1[1]);
            Solution ob = new Solution();
            System.out.println(ob.shortestDistance(N, M, a, x, y));
        }
    }
}// } Driver Code Ends


// User function Template for Java

class Solution {
    class Coord
    {
        public int x;
        public int y;
        public int dist;
        
        public Coord(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
    int shortestDistance(int N, int M, int A[][], int X, int Y) {
        // code here
        Queue<Coord> q = new  LinkedList<Coord>();
        boolean[][] visited = new boolean[N][M];
        if(A[0][0]==0)
            return -1;
        
        q.add(new Coord(0,0));
        while(q.size()>0)
        {
            Coord c = q.remove();
            if(visited[c.x][c.y])
                continue;
            
            if(c.x == X && c.y == Y)
                return c.dist;
                
            visited[c.x][c.y] = true;
            ArrayList<Coord> adj = getNeighbours(c,N,M,A);
            for(Coord nei : adj)
            {
                nei.dist = c.dist+1;
                q.add(nei);
            }
        }
        
        return -1;
    }
    
    ArrayList<Coord> getNeighbours(Coord c, int N, int M, int A[][])
    {
        int[][] offset = {{0,-1},{-1,0}, {0,1}, {1,0}};
        ArrayList<Coord> adj = new ArrayList<Coord>();
        for(int i = 0 ; i< 4 ; i++)
        {
            int x = c.x + offset[i][0];
            int y = c.y + offset[i][1];
            
            if((x>=0 && x<N) && (y>=0 && y<M) && (A[x][y]==1))
            {
                adj.add(new Coord(x,y));
            }
            
        }
        return adj;
    }
};




