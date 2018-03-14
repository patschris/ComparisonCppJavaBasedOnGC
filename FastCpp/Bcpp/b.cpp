#include <iostream>
#include <cstdlib>
#include <ctime>
#include <list>
#include <iterator>

using namespace std;

#define SIZE 4831838208 	// 4,5GB allocated but not used memory
#define ARRAYSIZE 50000		// Amount of elements in the additional_overhead array
#define ITERATIONS 10000	// iterations over additional_overhead array

class A {
	public:
		int i;
		A(int ii=0) {
			i=ii;
		}	
		virtual void add(void) {
			i++;
		}
		virtual void sub(void) {
			i--;
		}
		/* Sum of A's fields */
		virtual int sum(void) {
			return i;
		}
};

class B : public A {
	public:
		int j;
		B(int jj=0) : A(jj/2+1) {
			j=jj;
		}
		virtual void add(void) {
			A::add();
			j++;
		}
		virtual void sub(void) {
			A::sub();
			j--;
		}
		/* Sum of B's fields */
		virtual int sum(void) {
			return (i+j);
		}
};


/********** Global variables **********/
/**/	int *standard_overhead;	    /**/
/**/	A *additional_overhead;     /**/
/**************************************/

/*
 * Fills a position of standard_overhead array with a random integer
 */
void fillStandardOverhead(int index) {
	int p  = rand();
	standard_overhead[index] = p;
}

/*
 * Fills a position of additional_overhead array with a random A or B object
 */
void fillArray(int index) {
	int value  = rand()%10;
	int p = rand();
	if (p%4==0) {
		A a(value);
		additional_overhead[index] = a;
	}
	else {
		B b(value);
		additional_overhead[index]=b;
	}
}

/*
 * Adds to the current sum of array the sum of the object in the i-th position of the additional_overhead array
 */
int computeSum(int sum, int i) {
	return (sum + additional_overhead[i].sum());
}

/*
 * Calls randomly either add or sub method for the object in the i-th position of the additional_overhead array
 */
void doRandomComputations(int i) {
	int p = rand();
	if (p%4 == 0) { // probability 25%
		additional_overhead[i].add();
	}
	else { // probability 75%
		additional_overhead[i].sub();
	} 
}

/*
 * Allocates additional_overhead array, fills it with random objects, deos 
 */
void modifyArray(int iter) {
	additional_overhead = new A[ARRAYSIZE];
	for (int i = 0; i < ARRAYSIZE; i++) { 
		fillArray(i);
	}
	for (int i = 0; i < ARRAYSIZE; i++) {
		doRandomComputations(i);
	}
	int sum = 0;
	for (int i = 0; i < ARRAYSIZE; i++){
		sum = computeSum(sum,i);
	}
	cout << iter+1 << ") Sum = " << sum << endl;
	delete[] additional_overhead;
}

int main(void) {
	clock_t begin = clock();
	srand(time(NULL));
	/* 4,5GB allocated but not used memory */
	unsigned long long size = SIZE/sizeof(int);
	standard_overhead = new int[size];
	for (unsigned int i = 0; i < size; i++) {
		fillStandardOverhead(i);
	}
	clock_t t1 = clock();
	/* For ITERATIONS iterations doing computation in the additional_overhead array */
	for (int iterations = 0; iterations<ITERATIONS; iterations++) {
		modifyArray(iterations);
	}
	clock_t t2 = clock();
	delete[] standard_overhead;
	clock_t end = clock();
	cout << "Critical section time: " << (t2-t1)/CLOCKS_PER_SEC << " sec" << endl;
	cout << "Total time Acpp: " << (end - begin)/ CLOCKS_PER_SEC << " sec" << endl;
	return 0;
}
