# Word Ladder

A program to solve word ladder puzzles such as [Weaver](https://wordwormdormdork.com/).

The input is two four-letter words, and the output is a list of words, where a given word is one letter different from the previous word. 

## Demo

Type into the command line ``make demo`` to compile and run the program. When prompted, enter two four-letter words. If a word ladder is possible, a list of words will be printed.

## Implementation

This word ladder solver utilizes a Queue via a circular array, and a map of strings. Breadth-first-search is performed on the map to build paths between words.
