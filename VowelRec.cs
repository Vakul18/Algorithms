using System;

public class TestClass
{
    static Int64 runningSum = 0;
    
    public static void Main()
    {
        int testCases = Convert.ToInt32(Console.ReadLine());
        
        for(int i=0;i<testCases;i++)
        {
            string str = Console.ReadLine();
            SumVowels(str,true);
            runningSum = runningSum+GetVowelCount(str);
            Console.WriteLine(runningSum);
            runningSum = 0;
        }
    }
    
    public static int SumVowels(string str,bool sumBoth)
    {
        int strLen = str.Length;
        if(strLen>1)
        {
            int v1 = (IsVowel(str[0])?1:0) + SumVowels(str.Substring(1,strLen-1),false);
            if(sumBoth || (strLen==2))
                SumVowels(str.Substring(0,strLen-1),true);
        
            runningSum += v1;
            return v1;
        }   
        else
        {
            if(IsVowel(str[0]))
                return 1;
            else
                return 0;
        }
    }
    
    public static int GetVowelCount(string str)
    {
        int c  =0;
        for(int i =0;i<str.Length;i++)
        {
            if(IsVowel(str[i]))
            {
                c++;
            }
        }
        return c;
    }
    
    public static bool IsVowel(char c)
    {
        return (c=='a') || (c=='A') || (c=='e') || (c=='E') || c == 'o' || c=='O' || c=='i'|| c =='I'|| c =='u' || c =='U';
    }
}