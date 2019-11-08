Group Members: Brian Lee, Jacob Hung, Marius Boda

Introduction:
This project is called Hivolts. Assigned to us by Mr. Kuszmaul for AP Computer Science. 
Group members and authors of the code are Brian Lee, Jacob Hung, and Marius Boda. 
The specifications for the project given can be found on Paleyontology.com. 
When we first started on this project we used some of the base code supplied by Mr. Kuszmaul and from our former cow project. 
Then as the project went on we realized that it would be a lot more complicated than it first appeared. 
As a result, our group member Brian started on a new version of the project where he created a 2D array called board which prints a 2D version of the game onto the console. 
Once again as the project evolved we used our knowledge from the cow project and then former Hivolts version we had started with to improve our new version of Hivolts. 

Fulfillment:
Our Hivolts game is able to be run and has all the correct conditions. Mho movement is close to perfect and our player movement is flawless. 
We were able to meet all the criteria that were stated on Paleyontology.com. All randomization can be improved to make it true randomization. 
In the future, we could acquire some help to improve our methods and make randomization better. 

Current Errors: 
Overall, our project had fewer errors than most, but there is one place that we can improve on given more time: 
The random algorithm that we have calculates a random X position and a random Y position on the board, and keeps producing a random number until we get the desired point (X, Y) on the board. 
This random method isn’t efficient since if the numbers that it generates are not completely random, the board will either generate the wrong numbers or have loop for a long time. 
We can fix it by implementing a more complex algorithm that creates numbers while taking the currently occupied positions into consideration (this idea was supplied by Kuszmaul). 
We also do not provide information when you die so it is hard to determine why you died as our game automatically changes to the “you lose” screen.

Overview of Code:
In the class “Game.java”, we have our main method that runs the code. 
The class “Controller.java” contains the method that generates the game. 
It also uses a 2D array that stores all of the game information. 
We use a random number generator to generate random fences and mhos, as well as the starting position. 
In the class “Player.java” contains the code for the player movement. 
Every time we move that is not a jump, we run a method in the “Mhos.java” class that moves the Mhos. 
The class finds the player and runs through different “if” statements that determine how to move the Mhos. 
The class “Draw.java” simply takes the information on the 2D array and turns it into visible graphics. 
It also has methods that show a win or lose screen, and regenerates the board when you want to play again.


Major Challenges: 
Throughout the project, we encountered many challenges. 
The first challenge that we faced was applying what we learned about lists/arrays, and print, and making the program paint our board on the JFrame. 
Another challenge that we faced was that even though our initial code worked, it was not the most efficient and legible, which meant that we needed to divide all the code into different classes and methods that are as small as possible. 
We also had challenges making the graphics for the board. 

Acknowledgments: 
We’ve learned more about lists/arrays and JFrame through a YouTube video series. 
We’ve also learned about JFrame and JPanel on YouTube. 
With the help of Marius, we were able to create nice looking graphics for the fences, mhos, and character.  

Links: 
https://www.youtube.com/watch?v=d3QbptJRln4 
https://www.youtube.com/watch?v=jUdIAgJ7JKo 
https://www.geeksforgeeks.org/switch-statement-in-java/ 

Link to Public Github:
https://github.com/MariusBoda/Hivolts
