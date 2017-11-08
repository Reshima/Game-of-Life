# Game-of-Life
This program runs a simulation of a game using a game board.
The rules of the Game Of Life are as follows:
* For alive cells (X):
*			Each cell with less than two neighbors, who are alive, dies of loneliness in the next generation.
*			Each cell with more than three nieghbors, who are alive, dies of overpopulation in the next generation.
*			Each cell with two or three neighbors, who are alive, continues to live in the next generation.
*	For dead cells (0):
*			Each cell with three neighbors, who are alive, comes to life in the next generation.
*		'Neighbors' are the cells diagonally, vertically and horizontally adjacent.
To compile and run this program; use the commands:
*  "javac Shima_GameOfLife.java"
*   then "java Shima_GameOfLife"
*   once the program is running choose a .life file to denote which board you would like to use
*   then specify how many generations you would like to view.
