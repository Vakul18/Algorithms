import java.util.*;
import java.lang.*;

public class Main
{
	public static void main(String[] args) {
		int N = 3;
		int[] T = {95,89,155};
		int[] C = {25,23,1};
		int[] P = {1,1,13};
		int Ccon = 24;
		int Pcon = 12;
		
		int Ct = 0;
		int Pt = 0;
		int Tt =0;
		for(int i : C)
		{
		    Ct += i;
		}
		
		for(int i : P)
		{
		    Pt += i;
		}
		
		for(int i : T)
		{
		    Tt += i;
		}
		
		System.out.println(bruteForce(N,T,P,C,Tt,Pt,Ct,Pcon,Ccon));
	}
	
	public static int bruteForce(int N,int[] T, int[] P , int[] C, int Tv,int Pv, int Cv, int Pc , int Cc)
	{
	    //System.out.println("N : " + N + " Ct : " + Ct + " Pt:" + Pt);
	    if(N==0)
	    {
	        return Tv;
	    }
	    
	    if((Pv - P[N-1] >= Pc) && (Cv - C[N-1] >= Cc))
	    {
	        return Math.min(bruteForce(N-1,T,P,C,Tv - T[N-1],Pv - P[N-1],Cv - C[N-1],Pc,Cc) , bruteForce(N-1,T,P,C,Tv,Pv,Cv,Pc,Cc));
	    }
	    else
	    {
	        return bruteForce(N-1,T,P,C,Tv,Pv,Cv,Pc,Cc);
	    }
	}
}