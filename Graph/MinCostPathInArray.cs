// { Driver Code Starts
//Initial Template for C#

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DriverCode
{

    class GFG
    {
        static void Main(string[] args)
        {
            int testcases;// Taking testcase as input
            testcases = Convert.ToInt32(Console.ReadLine());
            while (testcases-- > 0)// Looping through all testcases
            {

                int n = Convert.ToInt32(Console.ReadLine());
                List<List<int>> grid = new List<List<int>>();
                for (int i = 0; i < n; i++)
                {
                    grid.Add(new List<int>());
                }
                for (int i = 0; i < n; i++)
                {
                    var ip = Console.ReadLine().Trim().Split(' ');
                    for (int j = 0; j < n; j++)
                    {
                        grid[i].Add(int.Parse(ip[j]));
                    }
                }
                Solution obj = new Solution();
                var res = obj.minimumCostPath(ref grid);
                Console.WriteLine(res);
            }

        }
    }
}// } Driver Code Ends


//User function Template for C#

class Solution
{

    class Coord
    {
        public int x { get; set; }
        public int y { get; set; }
    }

    //Function to return the minimum cost to react at bottom
    //right cell from top left cell.
    public int minimumCostPath(ref List<List<int>> grid)
    {
        // Code here
        int n = grid.Count;
        bool[,] visited = new bool[n, n];
        int[,] dist = new int[n, n];



        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                dist[i, j] = Int32.MaxValue;
            }
        }
        dist[0, 0] = grid[0][0];
        PriorityQueue pq = new PriorityQueue(n);
        pq.Add(new Coord { x = 0, y = 0 }, dist[0, 0]);

        int[,] offset = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

        while (!pq.IsEmpty())
        {

            Node node = pq.Remove();
            Coord c = node.C;

            if (visited[c.x, c.y])
                continue;

            visited[c.x, c.y] = true;

            for (int i = 0; i < 4; i++)
            {
                int x1 = c.x + offset[i, 0];
                int y1 = c.y + offset[i, 1];
                if (!IsSafe(x1, y1, n))
                    continue;
                if (dist[x1, y1] > (node.Value + grid[x1][y1]))
                {
                    /* if(dist[x1,y1] != Int32.MaxValue)
                     {
                         pq.Remove(x1,y1);
                     }*/
                    dist[x1, y1] = node.Value + grid[x1][y1];
                    pq.Add(new Coord { x = x1, y = y1 }, dist[x1, y1]);
                }


            }

        }

        return dist[n - 1, n - 1];


    }

    public bool IsSafe(int x, int y, int n)
    {
        if (x < 0 || x >= n || y < 0 || y >= n)
        {
            return false;
        }
        return true;
    }

    class Node
    {
        public Coord C { get; set; }
        public int Value { get; set; }
    }

    class PriorityQueue
    {


        List<Node> list = new List<Node>();
        int[,] loc;

        public PriorityQueue(int n)
        {
            loc = new int[n, n];
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    loc[i, j] = -1;
                }
            }

        }

        public bool IsEmpty()
        {
            return list.Count == 0;
        }

        public void Add(Coord c, int value)
        {
            var n = new Node { C = c, Value = value };
            list.Add(n);
            loc[c.x, c.y] = list.Count - 1;
            UpHeapify(list.Count - 1);

        }

        public void Remove(int x, int y)
        {
            if (loc[x, y] == -1)
            {
                return;
            }
            int i = loc[x, y];
            swap(i, list.Count - 1);
            loc[list[list.Count - 1].C.x, list[list.Count - 1].C.y] = -1;
            list.RemoveAt(list.Count - 1);
            if (i == list.Count)
                return;
            if (IsEmpty())
                return;


            if (list[i].Value < list[(i-1) / 2].Value)
            {
                UpHeapify(i);
            }
            else
            {
                DownHeapify(i);
            }
        }

        public Node Remove()
        {
            var n = list[0];
            swap(0, list.Count - 1);
            loc[list[list.Count - 1].C.x, list[list.Count - 1].C.y] = -1;
            list.RemoveAt(list.Count - 1);
            DownHeapify(0);
            return n;
        }

        private void DownHeapify(int i)
        {
            if (IsEmpty())
                return;

            int r = (2 * i) + 2;
            int l = (2 * i) + 1;
            if (r < list.Count)
            {
                int rv = list[r].Value;
                int lv = list[l].Value;
                int v = list[i].Value;
                if (rv < v && rv <= lv)
                {
                    swap(r, i);
                    DownHeapify(r);
                }
                else if (lv < v && lv <= rv)
                {
                    swap(l, i);
                    DownHeapify(l);
                }

            }
            else if (l < list.Count)
            {
                int lv = list[l].Value;
                int v = list[i].Value;
                if (lv < v)
                {
                    swap(l, i);
                    DownHeapify(l);
                }
            }
        }

        private void UpHeapify(int i)
        {
            if (i == 0)
            {
                return;
            }
            int parent = (i-1)/2;
            if (list[parent].Value > list[i].Value)
            {
                swap(parent, i);
                UpHeapify(parent);
            }

        }

        private void swap(int i1, int i2)
        {
            Node temp = list[i1];
            list[i1] = list[i2];
            list[i2] = temp;
            loc[list[i1].C.x, list[i1].C.y] = i1;
            loc[list[i2].C.x, list[i2].C.y] = i2;
        }

    }

}
