public class FetchElement{
	public int ThirdMax(int[] nums){
		TryGetMaxLessThan(nums,Int32.MaxValue,out int firstMax);
		if(!TryGetMaxLessThan(nums,firstMax,out int secondMax))
			return firstMax;
		if(!TryGetMaxLessThan(nums,secondMax,out int thirdMax))
			return firstMax;
		return thirdMax;
	}

	private bool TryGetMaxLessThan(int[] nums,int ceilingValue,out int maxValue){
		maxValue = Int32.MinValue;
		bool maxValueFound = false;
		for(int i = 0 ; i<nums.Length;i++){
			if(nums[i]>=maxValue && ceilingValue>nums[i])
			{
				maxValue = nums[i];
				maxValueFound = true;
			}
			
		}
		return maxValueFound;
	}
 
	
}
