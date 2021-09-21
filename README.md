# TIC Handin README
#### Fill out this README before turning in this project. Make sure to fill this out again for each assignment!
---
### Banner ID: B01578029
---
### Already uploaded demo onto Slack: https://csci1950n2dga-a2e2707.slack.com/archives/C024G2YJSJG/p1632203773041400
---
## Primary Requirements:
| Requirement | Location in code or steps to view in game  |
|---|---|
| Your handin must meet all global requirements | 1. The project was made in IntelliJ, <br /><br />2. The readme contains all the necessary info <br /><br />3. There is a valid instructions page <br /><br />4. No external libraries <br /><br />5. Engine and Game code are separated into two separate packages <br /><br />6. Engine code is indeed extensible. <br /><br />7. Engine code is located in: Application.java, Screen.java, UIElement.java, and the UIElements package. <br /><br />8. Game code is located under the tic package. <br /><br />9. The code works with all required versions. <br /><br />10. The game scales effectively. <br /><br />11. The game can indeed maintain at least 20 fps at all times. <br /><br />12. Demo is linked above. |
| Your handin only crashes under exceptional circumstances (edge cases) | I haven't been able to get it to crash yet. |
| Your engine must draw the current screen on every “draw” event (originating from support code). Each screen must be able to define the way in which it is drawn independently from other screens | The game contains multiple Screens, each that can be drawn independently from the other. This is most apparent in the onDraw method in App.java. |
| Your engine must be able to process mouse events (originating from support code) and allow each screen to define how they are handled | Buttons work in the game. Example include pressing "start game" or "restart game" once in-game. |
| Your engine must be able to process keyboard events (originating from the support code) and allow each screen to define how they are handled | Pressing escape while in the game screen will take you back to the main menu. |
| Your current screen must update itself on every “tick” event (originating from the support code) | See: onTick located in App.java |
| Your engine must be able to process resizable windows (originating from support code) and adjust the internal state when the draw area is resized. It must also ensure that size information is preserved when the current screen changes – if the current screen changes after a resize event, the new current screen must be aware of the correct window size as well | See: onResize function within App.java |
| Your engine should have a basic UI toolkit. At a minimum, this toolkit should allow a game to display text and rectangles | See: the UIElements package |
| Your engine must have a correct and easily extensible implementation of a button | See: Button.java |
| Your handin must meet all playtesting requirements | It passes all playtesting requirements.|
| A 3x3 square board must be accessible from the screen once the application is run (either directly or through a menu) | Play the game! |
| An X or an O must appear on a box when that box is clicked | Play the game! Click on an empty space! Or just watch the demo |
| Your game must implement the rules of Tic-Tac-Toe: two players, X and O, take turns marking squares on a 3x3 grid with their respective symbols. If a player succeeds in placing three symbols in a horizontal, vertical, or diagonal row, that player wins. If all the squares are filled without either player completing a row, the game is a draw. Both players can be human players: you do not need an AI opponent for this assignment | Watch the demo or play the game! |
| Have at least two screens: an in-game screen and another screen, such as a title screen | Home screen and game screen |
| Clearly display which player’s turn it is | Shows in text to the right of the board |
| At the end of the game, effectively communicate which player won, or if it was a draw | Will show up as text once a player has won |
| Display the state of the game on a square board that scales with window size. The board must remain square at all times, no matter the window’s aspect ratio | The board is always square! |
| Your game must implement keyboard events (e.g., exit game on escape) | The game returns to the home menu upon pressing escape |
| Your game must never crash. | It doesn't crash! |


## Secondary Requirements:
| Requirement | Location in code or steps to view in game  |
|---|---|
| Your engine must meet all primary engine requirements | True: See above |
| Your buttons should display differently when they are hovered | All the buttons change color when hovered over |
| Each player’s turn should have a time limit. If the player does not make a move when the time expires, it becomes the other player’s turn and the timer resets | Play the game or watch the demo! Countdown available. |
| Show the countdown timer (e.g., shrinking bar, text in seconds) | Visual indeed |
| It must be possible to start a new game without restarting the program | Restart Button available. |

## Extras:
| Requirement | Location in code or steps to view in game  |
|---|---|
| Have UI elements that fill as much space as they can while keeping the board square, instead of drawing UI elements at a fixed size. | UIElements is all written relative to the size of the screen and scale appropriately  
| Let the user pick a larger-size board, such as 4x4, 5x4, or even NxN | N/A
| Add a simple AI player and the option to play against the computer. It could even support multiple difficulties if you do this, remember that the option to play with two humans must still work as in the original requirements. | N/A

--------------------------------------------------------------

Instructions on how to run:
You run Main.java in intellij and the game should launch! Further gameplay instructions should be in the instructions file.

Known bugs: N/A

Hours spent on assignment: 23
