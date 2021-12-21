using System;
using System.Collections.Generic;
					
public class Program
{
	public static void Main()
	{
		string s = "adadd";
		
		
		
		Dictionary<string,int> arr = new Dictionary<string,int>();
		
		for(int i = 0; i< s.Length;i++)
		{
			string c = s.Substring(i,1);
			
			if(arr.ContainsKey(c))
			{
				arr[c] = arr[c] + 1;
			}
			else
			{
				arr.Add(c,1);
			}
			
		}
		
		int count = 0;
		bool possible = true;
		foreach(KeyValuePair<string,int> kvp in arr)
		{
			if(kvp.Value%2==1)
			{
				count++;
				if(count>1 )
				{
					possible = false;
					break;
				}
			}
		}
		
		Console.WriteLine("Possible : " + possible);
		
		
	}
}
