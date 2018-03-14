#include <iostream>
#include <cstdlib>
#include <ctime>
#include <list>

#define ELEMENTS 20000000	// amount of elements in the initial list
#define ITERATIONS 40000	// iterations over the final list after the deletions
#define F 2500				// 1 per F stays alive

using namespace std;


/************** Global variables **************/
/**/			list<int*> l;				/**/
/**/		list<int*>::iterator it;		/**/
/**/	int num, counter, minimum, maximum;	/**/
/**********************************************/

/* 
 * Construct and insert a new object into the list
 */
void insertNewElement() {
	l.push_back(new int(rand()%400));
}

/* 
 * Delete (F-1) per F nodes  
 */
void removeNodes (void) {
	if (counter != num) {
		list<int*>::iterator it2 = it;
		delete *it2;
		it = l.erase(it2);	
	}
	else {
		it++;
	}
	counter++;
	if (counter == F) { 
		counter = 0;
		num = rand()%F;
	}
}

/*
 * Check if n is prime
 */
bool isPrime(int n) {
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
bool isOdd(int n) {
	return (n%2!=0);
}

/*
 * Marks as minimum the current node if it is smaller than the previous minimum
 */
void findMin(void) {
	if (minimum > *(*it)) minimum = *(*it);
}

/*
 * Marks as maximum the current node if it is greater than the previous maximum
 */
void findMax(void) {
	if (maximum < *(*it)) maximum = *(*it);
}

/*
 * Add or subtract a random number to/from the current node
 */
void changeValue(void) {
	if (rand()%2 > 0) *(*it) = *((*it))+rand()%5;
	else *(*it) = *((*it))-rand()%3;
}

/*
 * Make some computations over the list and print the results
 * Find the amount of prime and odd numbers, the sum of the list's numbers and modify the contents of the list  
 */
void modifyList (int index) {
	int primes = 0;
	for (it = l.begin(); it != l.end(); it++) {
		if (isPrime(*(*it))) primes++;
	}
	int odds = 0;
	for (it = l.begin(); it != l.end(); it++) {
		if (isOdd(*(*it))) odds++;
	}
	long int sum = 0;
	for (it = l.begin(); it != l.end(); it++) {
		sum = sum + *(*it);
	}

	minimum = 400+2*ITERATIONS;
	for (it = l.begin(); it != l.end(); it++) {
		findMin();
	}

	maximum = -4*ITERATIONS;
	for (it = l.begin(); it != l.end(); it++) {
		findMax();
	}

	cout << index+1 << ") My list has " << primes << " primes and " << odds << " odd numbers. List's sum = " << sum << ". Min value: " << minimum << ". Max value: " << maximum << endl;
	for (it = l.begin(); it != l.end(); it++) {
		changeValue();
	}
}

int main(void) {
	clock_t begin = clock();
	srand(time(NULL));
	for (int i = 0; i < ELEMENTS; i++) {
		insertNewElement();
	}
	/* 1 per F stays alive */
	num = rand()%F;
	it = l.begin();
	while (it!=l.end()) {
		removeNodes();
	}
	clock_t t1 = clock();
	/* For ITERATIONS iterations doing computation in the final list */
	for (int i = 0; i < ITERATIONS; i++) {
		modifyList(i);
	}
	cout << "List's size is " << l.size() << endl;
	clock_t t2  = clock();
	clock_t end = clock();
	it = l.begin();
	while (it!=l.end()) {
		list<int*>::iterator it2 = it;
		delete *it2;
		it = l.erase(it2);
	}
	
	cout << "Critical section time: " << (t2 - t1)/ CLOCKS_PER_SEC << " sec" << endl;
	cout << "Total time Acpp: " << (end - begin)/ CLOCKS_PER_SEC << " sec" << endl;

	return 0;
}