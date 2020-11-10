package threadedMergeSort;
import java.util.Random;

public class Driver
{
	public static void main(String[] args)
	{
		int[] numArray = new int[10];
		Random r = new Random();
		
		for(int i = 0; i < numArray.length; i++)
		{
			int randomNum = r.nextInt(100);
			numArray[i] = randomNum;
		}
		
		Driver.displayIntArray(numArray);
		
		WorkerBee originalArray = new WorkerBee(numArray);
		WorkerBee leftArray = originalArray.createLeftArray();
		WorkerBee rightArray = originalArray.createRightArray();
		
		leftArray.start();
		rightArray.start();
		
		try
		{
			leftArray.join();
			rightArray.join();
			WorkerBee finalArray = originalArray.mergeArray(leftArray, rightArray);
			WorkerBee.mergeSort(finalArray.getArray(), 0, finalArray.getArray().length-1);
			System.out.println();
			for(int element : finalArray.getArray())
			{
				System.out.print(element + " ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	static void displayIntArray(int[] ar)
	{
		for(int element : ar)
		{
			System.out.print(element + " ");
		}
	}
}
