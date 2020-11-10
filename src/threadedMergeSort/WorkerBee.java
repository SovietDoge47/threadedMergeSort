package threadedMergeSort;

public class WorkerBee extends Thread
{
	private int[] arr;
	
	public WorkerBee(int[] arr)
	{
		this.arr = arr;
	}
	
	public void run()
	{
		mergeSort(this.arr, 0, this.arr.length-1);
	}
	
	public int[] getArray()
	{
		return arr;
	}
	
	public WorkerBee createLeftArray()
	{
		int[] firstArray = new int[arr.length/2];
		for(int i = 0; i < firstArray.length; i++)
		{
			firstArray[i] = arr[i];
		}
		return new WorkerBee(firstArray);
	}
	
	public WorkerBee createRightArray()
	{
		int[] secondArray = new int[arr.length - (arr.length/2)];
		int pos = arr.length/2;
		for(int i = 0; i < secondArray.length; i++)
		{
			secondArray[i] = arr[pos];
			pos++;
		}
		return new WorkerBee(secondArray);
	}
	
	public WorkerBee mergeArray(WorkerBee firstArray, WorkerBee secondArray)
	{
		int[] mergedArray = new int[(firstArray.getArray().length) + (secondArray.getArray().length)];
		for(int i = 0; i < firstArray.getArray().length; i++)
		{
			mergedArray[i] = firstArray.getArray()[i];
		}
		int pos = (mergedArray.length/2);
		for(int i = 0; i < secondArray.getArray().length; i++)
		{
			mergedArray[pos] = secondArray.getArray()[i];
			pos++;
		}
		return new WorkerBee(mergedArray);
	}
	
	static void mergeSort(int[] ar, int begin, int end)
	{
		if(begin != end)
		{
			int begin1 = begin;
			int end1 = begin + ((end - begin)/2);
			int begin2 = end1 + 1;
			int end2 = end;
			mergeSort(ar, begin1, end1);
			mergeSort(ar, begin2, end2);
			merge(ar, begin1, end1, begin2, end2);
		}
	}
	
	static void merge(int[] ar, int begin1, int end1, int begin2, int end2)
	{
		int[] temp = new int[(end2 - begin1) + 1];
		int pos1 = begin1;
		int pos2 = begin2;
		for(int i = 0; i < temp.length; i++)
		{
			if(pos1 <= end1 && pos2 <= end2)
			{
				if(ar[pos1] < ar[pos2])
				{
					temp[i] = ar[pos1];
					pos1++;
				}
				else
				{
					temp[i] = ar[pos2];
					pos2++;
				}
			}
			else
			{
				//either pos1 or pos2 is off the end of their list and the other guy is the 
				//default winner
				if(pos1 > end1)
				{
					temp[i] = ar[pos2];
					pos2++;
				}
				else
				{
					temp[i] = ar[pos1];
					pos1++;
				}
			}
		} //end of for loop
		//copy temp back over ar from begin1 to end2
		int posInTemp = 0;
		for(int i = begin1; i <= end2; i++)
		{
			ar[i] = temp[posInTemp];
			posInTemp++;
		}
	}
}
