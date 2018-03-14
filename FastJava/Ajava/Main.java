import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Main {
	private static int ELEMENTS = 20000000;		// amount of elements in the initial list
	private static int ITERATIONS = 40000;		// iterations over the final list after the deletions
	private static int F = 2500;				// 1 per F stays alive
	private static int num;
	private static int counter;
	private static int minimum;
	private static int maximum;
	private static LinkedList<Integer> l;
	private static ListIterator<Integer> it;
	
	/* 
	 * Construct and insert a new object into the list
	 */
	private static void insertNewElement() {
		l.add(new Random().nextInt(400));
	}
	
	/* 
 	 * Delete (F-1) per F nodes  
 	 */
	private static void removeNodes() {
		it.next();
		if (counter != num) {
			it.remove();
		}
		counter++;
		if (counter == F) {
			counter = 0;
			num = new Random().nextInt(F);
		}
	}

	/*
	 * Check if n is prime
	 */
	private static boolean isPrime(int n) {
		if (n == 2) return true;
		if (n == 1 || n % 2 == 0) return false;
		for (int i = 3; i*i < n+1; i += 2) {
			if (n % i == 0) return false;
		}
		return true;
	}

	/*
	 * Check if n is odd
	 */
	private static boolean isOdd(int n) {
		return (n%2!=0);
	}

	/*
	 * Marks as minimum the current node if it is smaller than the previous minimum
	 */
	private static void findMin () {
		int a = it.next();
		if (minimum > a) minimum = a;
	}

	/*
	 * Marks as maximum the current node if it is greater than the previous maximum
	 */
	 private static void findMax () {
		int a = it.next();
		if (maximum < a) maximum = a;
	}
		
	/*
	 * Add or subtract a random number to/from the current node
	 */
	private static void changeValue() {
		if (new Random().nextInt(2) > 0) it.set(it.next()+new Random().nextInt(5));
		else it.set(it.next() - new Random().nextInt(3));
	}
	
	/*
	 * Make some computations over the list and print the results
	 * Find the amount of prime and odd numbers, the sum of the list's numbers and modify the contents of the list  
	 */
	private static void modifyList (int index) {
		int primes = 0;
		it = l.listIterator();
		while (it.hasNext()) {			
			if (isPrime(it.next())) primes++;
		}
		int odds = 0;
		it = l.listIterator();
		while (it.hasNext()) {	
			if (isOdd(it.next())) odds++;
		}
		long sum = 0;
		it = l.listIterator();
		while (it.hasNext()) {	
			sum = sum + it.next();
		}
		minimum = 400 + 2*ITERATIONS;
		it = l.listIterator();
		while (it.hasNext()) {	
			findMin();
		}
		maximum = -2*ITERATIONS; 
		it = l.listIterator();
		while (it.hasNext()) {	
			findMax();
		}
		System.out.println((index+1)+") My list has "+primes+" primes and " + odds + " odd numbers. List's sum = "+sum+ ". Min value: "+minimum+". Max value: "+maximum);
		it = l.listIterator();
		while (it.hasNext()) {
			changeValue();
		}
	}
	
	
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		l = new LinkedList<Integer>();
		for (int i = 0; i < ELEMENTS; i++) {
			insertNewElement();
		}
		/* 1 per F stays alive */
		num = new Random().nextInt(F);
		it = l.listIterator();
		counter = 0;
		while (it.hasNext()) {
			removeNodes();
		}
		/* Calling garbage collector in order to improve the locality of the alive objects */
		System.gc();
		long t1 = System.currentTimeMillis();
		/* Gia ITERATIONS epanalipseis kanw prakseis panw stin teliki lista me ta zwntana dedomena */
		for (int i = 0; i < ITERATIONS; i++) {
			modifyList(i);
		}
		System.out.println("My list's size is "+l.size());
		long t2 = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		System.out.println("Critical section time: " + (t2-t1)/1000 + " sec");
		System.out.println("Total time Ajava: " + (endTime-startTime)/1000 + " sec");
	}

}
