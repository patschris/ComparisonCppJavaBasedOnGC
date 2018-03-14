# ComparisonCppJavaBasedOnGC 
The purpose of this project is to show the pros and the cons of autonomous garbage collection comparing to manual memory management. 
In order to achieve this, I created two pairs of similar programs. In the pair A, A.java is faster than A.cpp due to GC. In the pair B, 
b.java is slower than a b.cpp due to GC. <br/><br/>
In order to see the pros of GC, I used the following strategy in the pair A: I allocated a list of 20000000 integers. Then, I deleted a
large amount of elements. Only 1 per 2500 elements stayed alive. The remaining elements in Java had good locality because of GC. In C++ 
the remaining elements had bad locality because of the absence of GC. As a result, computations in the final list that contains the 
remaining elements are significantly quicker in Java.<br/><br/>
In order to see the cons of GC, I used the following strategy in the pair B: I allocated a 4.5GB array that I never used during the
program. Then, I allocated a second large array to make some computations. Because of the large amount of allocated memory, the gc is
trying to sweep grabage very often in Java. That makes Java much slower tha C++ in this case.<br/><br/>
CPU Intel Core i5-7200U CPU @ 2.5GHz x 4, 8GB RAM, Ubuntu 16.04 LTS<br/>
Java 1.8 server VM, heap size used 7 GB<br/>
g++ 5.4.0
