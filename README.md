#Minimum Triangle Paths

##Background

Consider the following triangle of non-negative integers:

7

6 3

3 8 5

11 2 10 9

A path through the triangle is a sequence of adjacent nodes, one from each row, starting from the top. So, for instance, 7 -> 6 -> 3 -> 11 is a path down the left hand edge of the triangle.

A minimal path is one where the sum of the values in its nodes is no greater than for any other path through the triangle. In this case, 7 + 6 + 3 + 2 = 18 is a minimal path.

We can store the triangle in a text file with each row on a separate line, and spaces between the numbers.

Thus the triangle above would be stored in text format as:

7

6 3

3 8 5

11 2 10 9


##Task

Write a command-line program in Java that reads a text-format triangle from standard input and outputs a minimal path to standard output as follows (using a file containing the triangle above):

$ java MinTrianglePath < testfile.txt

Minimal path is: 7 + 6 + 3 + 2 = 18

An average PC should be able to produce the answer for a 500-line triangle within 0.5 seconds.

##Run

`mvn exec:java -Dexec.mainClass="com.raulavila.triangle.MinTrianglePath" <testfile.txt`
