import java.util.Random;

public class Main {
	private static final int SIZE = 1207959552;		// (4831838208/4 = 4.5GB/sizeof(int)) Amount of elements in the standard_overhead array. 4,5GB allocated but not used memory
	private static final int ARRAYSIZE = 50000;		// Amount of elements in the additional_overhead array
	private static final int ITERATIONS = 10000;	// iterations over additional_overhead array
	private static long startTime = 0;
	private static long endTime = 0;
	private static int[] standard_overhead;
	private static A[] additional_overhead;

	/*
	 * Fills a position of additional_overhead array with a random A or B object
	 */	
	private static void fillStandardOverhead(int index) {
		int p  = new Random().nextInt();
		standard_overhead[index] = p;
	}
	
	/*
	 * Fills a position of additional_overhead array with a random A or B object
	 */
 	private static void fillArray(int index) {
		int value = new Random().nextInt(10);
		int p  = new Random().nextInt();
		if (p%4==0) {
			A a = new A(value);
			additional_overhead[index] = a;
		}
		else {
			B b = new B(value);
			additional_overhead[index] = b;
		}
	}	

	/*
	 * Adds to the current sum of array the sum of the object in the i-th position of the additional_overhead array
	 */
 	private static int computeSum(int sum, int i) {
		return (sum+additional_overhead[index].sum());
	}

	/*
	 * Calls randomly either add or sub method for the object in the i-th position of the additional_overhead array
	 */
 	private static void doRandomComputations(int i) {
		int p  = new Random().nextInt();
		if (p%4==0) { // pithanotita 25%
			additional_overhead[index].add();
		}
		else { // pithanotita 75%
			additional_overhead[index].sub();
		}
	}

	/*
	 * Allocates additional_overhead array, fills it with random objects, deos 
	 */
	private static void modifyArray (int iter) {
		additional_overhead = new A[ARRAYSIZE];
		for (int i = 0;  i< ARRAYSIZE; i++) {
			fillArray(i);
		}
		for (int i = 0;  i< ARRAYSIZE; i++) {
			doRandomComputations(i);
		}
		int sum=0;
		for (int i = 0;  i< ARRAYSIZE; i++) {
			sum = computeSum(sum, i);
		}
		System.out.println((iter+1)+") Sum = "+sum);
	}

	public static void main(String[] args) {
		startTime = System.currentTimeMillis();
		/* 4,5GB allocated but not used memory */
		standard_overhead  = new int[SIZE];
		for (int i=0; i<SIZE; i++) {
			fillStandardOverhead(i);
		}
		long t1 = System.currentTimeMillis();
		/* For ITERATIONS iterations doing computation in the additional_overhead array */
		for (int iterations=0; iterations<ITERATIONS; iterations++) {
			modifyArray(iterations);
		}
		long t2 = System.currentTimeMillis();
		endTime = System.currentTimeMillis();
		System.out.println("Critical section time: " + (t2-t1)/1000 + " sec");
		System.out.println("Total time Bjava: " + (endTime-startTime)/1000 + " sec");
		System.exit(0);
	}
}