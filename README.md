# WordSearch

------------------------------------
INTRODUCTION

A word search puzzle (http://en.wikipedia.org/wiki/Word_search)

This program searches for the words in list P and outputs the coordinates of the words.


------------------------------------
TO COMPILE AND RUN

Step 1: cd into 'WordSearch'. You should see the src and out directory, a readme file, and 'testInput.txt'

Step 2: To compile
	javac -d out src/WordSearch/*.java

Step 3: To run the program
	java -cp ./out WordSearch.Main

Step 4: Input file location.  
		NOTE: You can simply run the test file by entering testInput.txt
		If you are experiencing trouble entering the file location, put input file
		in this (WordSearch) folder or replace the testInput.txt file


------------------------------------
GENERAL OVERVIEW

We are given a grid of characters and a list of words. This program searches for the words in the grid.

b z c
b a a
b z t

Words: {car, cat, bat}



------------------------------------
ALGORITHM: 

INPUTS:
	n = # of rows
	m = # of columns
	p = # of words in dictionary
	M = 2d matrix of characters
	Q = set of words
	D = set of directions
	R = set of results

create L = hashmap< character, list< coorinates >>
create W = hashmap< character, list< strings >>
for i from 1 to n 				//INITIALIZE L hashmap
	for j from 1 to m
		L.put(M[n][m])
for i from 1 to p 				// INITIALIZE W HASHMAP
	W.put(first letter of Q[i], list[ Q[i]] ) 	
for each W.key in W 			// ITERATE THROUGH EACH WORD. GET FIRST LETTER OF WORD
	for each coordinate in L where L.key = W.key  // FIND COORDINATE WITH LETTER
		for each direction in D   				//  SEARCH IN EACH DIRECTION 
			x = seeker(coordinate,direction) 	
			if x != null					// IF WE FIND A MATCH
				add x to R 					// ADD FINDING TO LIST OF RESULTS


------------------------------------
JAVA OBJECTS

WordList: A hashmap of the first letters in each of our words.  

	{c, (cat,car)}
	{b, bat}

LetterMatrix: A hashmap of character locations in the map.  
	{b, {(0,0), (1,0)}
	{z, {(0,1),(2,1)}}
	{c, (0,2)}
		....

Seeker Iterator: Given a direction and a starting point, provides the next location

Once we build these objects, we open up our word list and start searching the map for characters in the Wordlist hashmap.  For example, we start off by searching for 'c'.  We look at our LetterMatrix and find value for 'c' in our Hashmap.  Once we get the coordinates of that value, we use our seeker to search in all directions of that position for a possible match.  We record our matches and continue looking for other words.