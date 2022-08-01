public class Solution {
    public int LengthOfLongestSubstring(string s) {
        if(String.IsNullOrEmpty(s))
			return 0;
		int i  = 0;
		var set = new HashSet<char>();
		set.Add(s[i]);
		int currentLength = 1;
		int maxLength = 0;
		for(int j = 1 ; j<s.Length;j++){
			if(!set.Contains(s[j])){
				currentLength++;
				set.Add(s[j]);
			}
			else{
				int k = j-1;
				while(s[k]!=s[j]){
					k--;
				}
				
				for(int l = k-1;l>=i;l--){
					set.Remove(s[l]);
				}
				maxLength = Math.Max(currentLength,maxLength);
				i = k+1;
				currentLength = j - i +1;
			}
		}
		maxLength = Math.Max(currentLength,maxLength);
		return maxLength;
    }
}
