package veggie.screen;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import veggie.model.Move;
import veggie.textReader.FileIO;
/**
 * 
 * @author Alex and William
 * 
 * A surface on which the other screens and graphical elements are drawn on.
 *
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher
{

	/**
	 * ratio for when the screen is resized
	 */
	public float ratioX, ratioY;

	public PFont font;

	private Screen activeScreen;

	private ArrayList<Screen> screens;

	// used to hold key codes from the keyboard
	protected ArrayList<Integer> keys;

	protected Map<String, PImage> lettuceAssets;
	protected Map<String, PImage> tomatoAssets;
	protected Map<String, PImage> veggieKingAssets;


	protected Map<String, PImage> assets;
	protected Map<Integer, Move> moves;
	


	/**
	 * initializes fields
	 */
	public DrawingSurface()
	{
		lettuceAssets = new HashMap<String, PImage>();
		assets = new HashMap<String, PImage>();
		moves = new HashMap<Integer, Move>();
		tomatoAssets = new HashMap<String, PImage>();
		veggieKingAssets = new HashMap<String, PImage>();


		screens = new ArrayList<Screen>();

		keys = new ArrayList<Integer>();

		Menu screen1 = new Menu(this);
		screens.add(screen1);

		Instructions screen2 = new Instructions(this);
		screens.add(screen2);

		PlatformMode screen3 = new PlatformMode(this);
		screens.add(screen3);
		
		GameOver screen4 = new GameOver(this);
		screens.add(screen4);

		switchScreen(ScreenSwitcher.MENU);
	}

	/**
	 * initializes the size of the screen
	 */
	public void settings()
	{
		// size(DRAWING_WIDTH, DRAWING_HEIGHT, P2D);
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
		pixelDensity(1);
	}

	/**
	 * runs setup() in the different screens
	 */
	public void setup()
	{
		font = loadFont("PokemonGB-12.vlw");
		textFont(font);

		lettuceAssets.put("attack", new Gif(this, "images" + FileIO.fileSep + "lettuce-sprite-attack.gif"));
		lettuceAssets.put("run", new Gif(this, "images" + FileIO.fileSep + "lettuce-sprite-run.gif"));
		lettuceAssets.put("bounce", new Gif(this, "images" + FileIO.fileSep + "lettuce-sprite-bounce.gif"));
		lettuceAssets.put("hurt", new Gif(this, "images" + FileIO.fileSep + "lettuce-sprite-hurt.gif"));
		lettuceAssets.put("frozen", loadImage("images" + FileIO.fileSep + "lettuce-sprite-frozen.png"));

		assets.put("hit", new Gif(this, "images" + FileIO.fileSep + "hit-effect.gif"));
		assets.put("background1", loadImage("images" + FileIO.fileSep + "clouds.png"));
		assets.put("logo", loadImage("images" + FileIO.fileSep + "veggie-tales-logo.png"));
		assets.put("crit", new Gif(this, "images" + FileIO.fileSep + "crit.gif"));
		assets.put("background2", loadImage("images" + FileIO.fileSep + "apocolypse-background.png"));//"apocolypse-background.png"));

		tomatoAssets.put("bounce", new Gif(this, "images" + FileIO.fileSep + "tomato-sprite-bounce.gif"));
		tomatoAssets.put("run", new Gif(this, "images" + FileIO.fileSep + "tomato-sprite-bounce.gif"));
		tomatoAssets.put("attack", new Gif(this, "images" + FileIO.fileSep + "tomato-sprite-attack.gif"));
		tomatoAssets.put("hurt", new Gif(this, "images" + FileIO.fileSep + "tomato-sprite-hurt.gif"));
		tomatoAssets.put("frozen", loadImage("images" + FileIO.fileSep + "tomato-sprite-frozen.png"));


		veggieKingAssets.put("bounce", new Gif(this, "images" + FileIO.fileSep + "veggie-king-sprite-bounce.gif"));
		veggieKingAssets.put("run", new Gif(this, "images" + FileIO.fileSep + "veggie-king-sprite-bounce.gif"));
		veggieKingAssets.put("attack", new Gif(this, "images" + FileIO.fileSep + "veggie-king-sprite-attack.gif"));
		veggieKingAssets.put("hurt", new Gif(this, "images" + FileIO.fileSep + "veggie-king-sprite-hurt.gif"));
		veggieKingAssets.put("frozen", loadImage("images" + FileIO.fileSep + "veggie-king-sprite-frozen.png"));


		// reading moveList file
		FileIO translator = new FileIO();
		try
		{
			ArrayList<String> temp = FileIO.readFile("res" + FileIO.fileSep + "moveList.txt");
			int i = 1;
			for(String s : temp)
			{
				Move m = translator.translateMoveList(s);
				moves.put(i, m);
				i++;
			}

		} catch(IOException e)
		{
			e.printStackTrace();
		}


		surface.setResizable(true);
		for(Screen s : screens)
			s.setup();

	}

	/**
	 * draws the screen
	 */
	public void draw()
	{
		ratioX = (float) width / activeScreen.DRAWING_WIDTH;
		ratioY = (float) height / activeScreen.DRAWING_HEIGHT;

		pushMatrix();

		scale(ratioX, ratioY);

		activeScreen.draw();

		popMatrix();

	}

	/**
	 * returns the current x and y coordinate correctly modified by screen size
	 * 
	 * @param actual the Point
	 * @return the new Point that is at the correct position with relation to screen
	 *         size
	 */
	public Point actualCoordinates(Point actual)
	{
		return new Point((int) (actual.getX() / ratioX), (int) (actual.getY() / ratioY));
	}

	@Override
	/**
	 * switches the screen to the indicated one
	 * 
	 * @param i the screen to switch to
	 */
	public void switchScreen(int i)
	{
		activeScreen = screens.get(i);
	}

	/**
	 * records the key that is pressed
	 */
	public void keyPressed()
	{
		keys.add(keyCode);
	}

	/**
	 * records which key has been released
	 */
	public void keyReleased()
	{
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	/**
	 * records the key that is pressed
	 * 
	 * @param code the code of the key that is pressed
	 * @return returns the keycode of the keys pressed
	 */
	public boolean isPressed(Integer code)
	{
		return keys.contains(code);
	}

	/**
	 * records the mouse being pressed
	 */
	public void mousePressed()
	{
		activeScreen.mousePressed();
	}

	/**
	 * records the mouse the mouse being moved
	 */
	public void mouseMoved()
	{
		activeScreen.mouseMoved();
	}

	/**
	 * records the mouse being dragged
	 */
	public void mouseDragged()
	{
		activeScreen.mouseDragged();
	}

	/**
	 * records the mouse being released
	 */
	public void mouseReleased()
	{
		activeScreen.mouseReleased();
	}

	/** Adds a Screen to the DrawingSurface and changes it to that Screen
	 * 
	 * @param temp the Screen that is to be added.
	 */
	public void addScreen(Screen temp)
	{
		temp.setup();
		screens.add(temp);
		activeScreen = temp;
	}

	/** Switches the Screen to the designated Screen number.
	 * 
	 * @param i the screen number
	 */
	public void removeScreen(int i)
	{
		screens.remove(screens.size() - 1);
	}
	
	/**
	 * Resets all game screens.
	 */
	public void gameOver() {
		this.setup();
	}
}
