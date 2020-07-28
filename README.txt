README
Alex Wang and William Wu


—Header—
Lettuce RPG Platformer


— Introduction—


In a tragic world, you are the only remaining piece of lettuce. Now, everyone wants to catch you for your value.
As you run and run far away from the other vegetables trying to capture you, you will have to fight and tear to survive.


— Instructions—


Run around to escape the enemies. Do not touch them to survive a round. 
Select abilities to use that ability for that turn. 


Platform mode: 
*  WASD - used to move up, left, down, and right, respectively. Space also moves up
Battle mode:
* Click to use ability.

— Feature List—


Must have features:
* Menu with play button and instructions (DONE)
* Player has health bar and can win or die (DONE)
* Two dimensional graphics with basic symbols (DONE)
* Turn based attack system (DONE)
* Players play against a bot (DONE)
* Movement system while not in battle (DONE)
* Platformer with physics (DONE)

Want to have features:
* Item system used for stat boosting
* Potions, etc. (DONE)
* Dialogue boxes similar to those in RPG games
* NPCs to talk and trade with using the item system
* NPC accessibility changes with progress
* Add a twist to battle system
   * Set up grid positioning
   * Clicking to see who wins with certain moves
* Multiscreen (DONE)
* Different bots with different difficulties
* Swap out abilities as player levels up

Stretch features:
* A difficulty level system
* Add additional characters to party
* Multiplayer mode
* 3D environment instead of 2D grid for movement and for battle
* Real-time instead of turn-based
* Music incorporated into game
* Sidescrolling map (DONE)
* Multiple levels


— Class List—
* veggie.screen
   * Screen: abstract class that sets up the drawing width and height along with methods
   * ScreenSwitcher: interface that switches between different screens
   * BattleMode: class that draws the battle between player and the enemy to the screen
   * PlatformMode: draws the platforming part of the game to the screen
   * Menu: draws the menu of the game to the screen
   * Instructions: draws the instructions of the game to the screen 
   * DrawingSurface: draws the different screens.
   * Main: starts the program
   * GameOver: game over screen when you lose
* veggie.model
   * Moves: an attack move that an Entity object can have
   * MovingImage: a shape that adheres to physics such as gravity, consistent with platformers
   * Player: models the player object
   * Stats: the stats that an Entity object can have
* veggie.textReader
   * FileIO: reads in a text file and translates it into different Move objects.


—Responsibilities list—


* Alex
   * The Platforming part of the game
	   * MovingImage
	   * Player
	   * PlatformMode
	   * Main
	   * DrawingSurface
	   * GameOver
	   
   
  
* William
   * The Battle part of the game 
   	   * Stats
	   * Moves
	   * DrawingSurface
	   * Instructions
	   * Menu
	   * FileIO, moveList.txt
	   * BattleMode
	   * Gifs, Images


— Resources — 

* clouds.png = http://tse3.mm.bing.net/th?id=OIP.4_5G3toRzBjrgizBzuvDfAHaFj
* apocolypse-background.png = https://cdn.dribbble.com/users/55792/screenshots/1966764/at_bg.png
* Processing reference
* Shelby Screen Switching Demo
* AnimationDemo