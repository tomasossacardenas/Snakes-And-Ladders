#¡Welcome to Snakes and Ladders!

##About
This project is a console game of the recognized game Snakes and Ladders.

####There are some rules we considered to realize it:
- The code will not to have cycles, everything will be done using recursion
- The code will not  have arrayLists or arrays everything will be done using linked lists
- The winners will be saved in the program using a binary tree so in the way to increase the program efficiency

####How does the game works:
First of all the program will show the user a small menu with three options, the first one is to start a new game, the second one is to see the table positions, and the third one is to exit the program. 

The user must enter number 1 to start a new game, after that the user will enter data of the rows number, columns number, quantity of snakes, quantity of ladders and finally a number (number of players) or a series of symbols indicating the symbol of each player. If the user enters a number the program will generate automatically a random symbol for each user. Then the game will start.

After that the program will show the user the grid with the number of each box and the snakes and ladders on it, remember Snakes will move the user to a lower row, instead Ladders will move the user to a upper row. After thi the program will be waiting for the user to press enter to start playing the game.

All players are going to start the game in the box numbered 1 after the enter pressed by the user the program will roll a dice indicating the number of boxes the user will advance, then the program will show the grid with the new ubication of the user and the program will be waiting a enter to give the turn to the other player.

The game will be doing that until a player fall in the last box when it is going to indicate the player has won and will ask the user whats the name of the player that won so it could save the winner in the list of winners of the program. When the game is over it will shows a menu with three options, play again a new game,  show the list of winners, and exit of the program.

####Technical Conditions
- The program is written in the Java programming language.
- The program is done in the eclipse development environment
- The program requires java language because is a console program, so if you have java download you will be able to play the game in the console.

#Documentation
- Classes Diagram pdf
- Funtional requirements

#Credits
We have based our linked list of boxes based on the explanation and code of the distinguished professor Juan Manuel Reyes in his explanatory video of doubly raised matrix.