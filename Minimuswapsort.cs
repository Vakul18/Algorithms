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
                int[] nums = new int[n];
                var ip = Console.ReadLine().Trim().Split(' ');
                for (int i = 0; i < n; i++)
                {
                    nums[i] = int.Parse(ip[i]);
                }
                Solution obj = new Solution();
                var res = obj.minSwaps(nums);
                Console.WriteLine(res);
            }
        }
    }
}// } Driver Code Ends


//User function Template for C#

class Solution
{


    //Function to find the minimum number of swaps required to sort the array. 
    public int minSwaps(int[] nums)
    {
        // Code here
        int count = 0;
        
        var dict = new Dictionary<int,int>();
        var sorted = new int[nums.Length];
        for(int i = 0 ; i < nums.Length ; i++)
        {
            sorted[i] = nums[i];
            dict.Add(nums[i],i);
        }
        
        sort(sorted,0,sorted.Length-1);
        
        //Console.WriteLine(String.Join(",",sorted));
        
        //Console.WriteLine("after");
        for(int i = 0 ; i < sorted.Length ; i++)
        {
            if(sorted[i] != nums[i])
            {
                //Console.WriteLine("i : " + i);
                int temp = nums[i];
                int newIndex = dict[sorted[i]];
                //Console.WriteLine("new index : " + newIndex);
                nums[i] = sorted[i];
                nums[newIndex] = temp;
                dict[temp] = newIndex;
                dict[sorted[i]] = i;
                count++;
            }
            //Console.WriteLine(String.Join(",",nums));
        }
        
        return count;
        
    }
    
    public void sort(int[] list, int l , int r)
    {
        if(l>r)
            return;
        int p = qSort(list, l ,r);
        sort(list,l,p-1);
        sort(list,p+1,r);
    }
    
    public int qSort(int[] list, int l, int r)
    {
        int index = l-1;
        int i = l;
        
        while(i<r)
        {
            if(list[i] < list[r])
            {
                index++;
                int temp = list[i];
                list[i] = list[index];
                list[index] = temp;
            }
            i++;
        
        }
        index++;
        int temp1 = list[r];
        list[r] = list[index];
        list[index] = temp1;
        return index;
    }
}






