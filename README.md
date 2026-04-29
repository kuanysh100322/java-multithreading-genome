Java Multithreading Genome Generator

This project is a simple Java application that generates genome sequences using the characters A, T, G, and C.

Each sequence has length 10, and the program generates 100 sequences.

The project demonstrates two approaches:

1. Single-thread execution
2. Multi-thread execution (using 5 threads)

In the multi-thread version, the work is divided between threads, and each thread generates a portion of the sequences.

The program measures and compares execution time for both approaches.

Note:
In this case, multithreading is not always faster because the task is small and thread creation adds overhead.

Main concepts used:
- Random sequence generation
- Java threads
- synchronized block
- join()

How to run:
Run the Main class in IntelliJ or compile and run using Java.

Example output:
The program prints several generated sequences and execution time for both versions.