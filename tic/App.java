package tic;

import engine.Application;
import engine.Screen;
import engine.UIElement;
import engine.UIElements.Button;
import engine.UIElements.Circle;
import engine.UIElements.Rectangle;
import engine.UIElements.Text;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * This is your Tic-Tac-Toe top-level, App class.
 * This class will contain every other object in your game.
 */
public class App extends Application {

  double screenWidth = 1200;
  double screenHeight = 800;
  long timeSinceStart = 0;
  long timeSincePlay = 0;
  double gap = 200;
  double base = 100;
  int size = 3;
  boolean xTurn = true;
  boolean gameOver = true;
  Screen mainScreen = new Screen("mainScreen");
  Screen xoScreen = new Screen("xoScreen");
  Screen homeScreen = new Screen("homeScreen");
  HashMap<Integer, Integer> chosen = new HashMap<>();
  Set<Integer> p1 = new HashSet<>();
  Set<Integer> p2 = new HashSet<>();
  Set<Set<Integer>> conditions = new HashSet<>();


  List<Screen> screens = new ArrayList<>();

  public App(String title) {
    super(title);
  }

  public App(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen) {
    super(title, windowSize, debugMode, fullscreen);

    // add a new screen and make that active screen

    for (int i = 1; i <= 4; i++) {
      Rectangle line = new Rectangle("Line " + Integer.toString(i),
              base + gap * (i - 1), base, base + gap * (i - 1), base + gap * 3, 5,
              Color.BLUE, Color.RED);
      mainScreen.addUIElement(line);
    }

    for (int i = 1; i <= 4; i++) {
      Rectangle line = new Rectangle("Line " + Integer.toString(i + 4),
              base, base + gap * (i - 1), base + gap * 3, base + gap * (i - 1), 5,
              Color.BLUE, Color.RED);
      mainScreen.addUIElement(line);

      Rectangle restartButtonRect = new Rectangle("restartButtonRect", screenHeight/2.5, screenWidth*3/5,
              screenHeight/2, screenWidth*3/5, screenHeight/16,
              Color.TURQUOISE, Color.BLACK);
      Text restartButtonText = new Text("restartButtonText", screenHeight/2.5, screenWidth*3/5,
              "Restart Game", Color.FIREBRICK);
      Button restartButton = new Button("restartButton", restartButtonRect, restartButtonText);
      mainScreen.addUIElement(restartButton);
    }

    screens.add(mainScreen);

    // work back and forth
  }

  /**
   * Called periodically and meant to draw graphical components.
   *
   * @param g a {@link GraphicsContext} object used for drawing.
   */
  @Override
  protected void onDraw(GraphicsContext g) {

    for (Screen s : screens) {
      if (s.getActive()) {
        for (UIElement ele : s.getElements()) {
          g = ele.convert(g);
        }
      }
    }
    super.onDraw(g);
  }

  /**
   * Called periodically and used to update the state of your game.
   *
   * @param nanosSincePreviousTick approximate number of nanoseconds since the previous call
   */
  @Override
  protected void onTick(long nanosSincePreviousTick) {
    timeSinceStart += nanosSincePreviousTick;
  }


  /**
   * Returns the box that it is in.
   *
   * @param x the x position of the mouse click
   * @param y the y position of the mouse click
   * @return an integer that represents the box the player selected
   */
  public int boxReturner(double x, double y) {

    int answerX = 0;
    int answerY = 0;

    for (int i = 1; i <= 3; i++) {
      if ((x > base + gap * (i - 1)) && (x < base + gap * (i))) {
        answerX = i;
      }
      if ((y > base + gap * (i - 1)) && (y < base + gap * (i))) {
        answerY = i;
      }
    }

    int ans = answerX + answerY * size - size;
    if (ans > 9 || ans < 1 ) return 0;
    return ans;
  }

  /**
   * Called when the mouse is clicked.
   *
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseClicked(MouseEvent e) {

    System.out.println("x: " + e.getX() + " y: " + e.getY());

    // X and Os for game screen

    if (mainScreen.getActive()) {
      int boxNum = boxReturner(e.getX(), e.getY());
      System.out.println(boxNum);
      if (boxNum != 0) drawXO(boxNum);
      checkVictory();
      System.out.println(p1);
      System.out.println(p2);
      System.out.println(conditions);
    }

    // check if a button was pressed on the homeScreen

    if (homeScreen.getActive()) {
      for (UIElement ui : homeScreen.getElements()) {
        if (ui instanceof Button && Screen.between(e.getX(), e.getY(), (Button) ui)) {
          System.out.println("Button Pressed!");
          String t = ((Button) ui).getTitle();
          switch (t) {
            case "startButton":
              homeScreen.setInactive();
              mainScreen.setActive();
              xoScreen.setActive();
              gameOver = false;
              break;
            default:
              break;
          }
        }
      }
    }

    if (mainScreen.getActive()) {
      for (UIElement ui : mainScreen.getElements()) {
        if (ui instanceof Button && Screen.between(e.getX(), e.getY(), (Button) ui)) {
          System.out.println("Button Pressed!");
          String t = ((Button) ui).getTitle();
          switch (t) {
            case "restartButton":
              xoScreen.clear();
              p1 = new HashSet<>();
              p2 = new HashSet<>();
              gameOver = false;
              for (int i = 1; i <= size * size; i++) {
                chosen.put(i, 0);
              }
          }
        }
      }
    }


  }

  /**
   * drawXO
   *
   * @param boxNum the box number that was clicked on
   */
  void drawXO(int boxNum) {

    int column = boxNum % size;
    if (column == 0) column = size;
    int row = (boxNum - column) / size + 1;

    System.out.println("chosen: " + chosen);
    System.out.println("boxNum: " + boxNum);
    if (chosen.get(boxNum) == 0 && !gameOver) {
      if (xTurn) {
        Rectangle x1 = new Rectangle("x1", base + gap * (column - 0.75),
                base + gap * (row - 0.75), base + gap * (column - 0.25), base + gap * (row - 0.25),
                2, Color.BROWN, Color.BROWN);
        Rectangle x2 = new Rectangle("x2", base + gap * (column - 0.25),
                base + gap * (row - 0.75), base + gap * (column - 0.75), base + gap * (row - 0.25),
                2, Color.BROWN, Color.BROWN);
        xTurn = false;
        xoScreen.addUIElement(x1);
        xoScreen.addUIElement(x2);
        chosen.put(boxNum, 1);
        p1.add(boxNum);
      } else {
        Circle c = new Circle("c", base + gap * (column - 0.85), base + gap * (row - 0.85),
                gap / 1.5, gap / 1.5, 10, Color.BLUE, Color.BLUE);
        xoScreen.addUIElement(c);
        xTurn = true;
        chosen.put(boxNum, 2);
        p2.add(boxNum);
      }
      screens.add(xoScreen);
    }
  }

  /**
   * Runs when somebody wins. Takes in the player who won.
   *
   * @param player either p1 or p2
   */
  void victory(String player) {
    if (player.equals("p1")) {
      System.out.println("X's Wins!");
      gameOver = true;
    }
    if (player.equals("p2")) {
      System.out.println("O's Wins!");
      gameOver = true;
    }
  }

  /**
   * Checks if someone has won.
   */
  void checkVictory() {
    for (Set<Integer> cond : conditions) {
      if (p1.containsAll(cond)) victory("p1");
      if (p2.containsAll(cond)) victory("p2");
    }
  }

  /**
   * Called when the app is starting up.s
   */
  @Override
  protected void onStartup() {

    homeScreen.setActive();

    Text titleWords = new Text("title", screenWidth / 2.1, screenHeight / 4,
            "Tic-Tac-Toe", Color.BLACK);
    homeScreen.addUIElement(titleWords);

    // Buttons
    Rectangle startButtonRect = new Rectangle("startButtonRect", screenHeight/2.5, screenWidth/2.5,
            screenHeight/1.5, screenWidth/2.5, screenHeight/16,
            Color.TURQUOISE, Color.BLACK);
    Text startButtonText = new Text("startButtonText", screenHeight/2.5, screenWidth/2.5,
            "Start Game", Color.FIREBRICK);
    Button startButton = new Button("startButton", startButtonRect, startButtonText);
    homeScreen.addUIElement(startButton);

    screens.add(homeScreen);

    for (int i = 1; i <= size * size; i++) {
      chosen.put(i, 0);
    }
    // three in a row
    for (int i = 1; i <= size; i++) {
      Set<Integer> cond = new HashSet<>();
      for (int j = 1; j <= size; j++) {
        cond.add(i * size + j - size);
      }
      conditions.add(cond);
    }
    // three in a column
    for (int i = 1; i <= size; i++) {
      Set<Integer> cond = new HashSet<>();
      for (int j = 1; j <= size; j++) {
        cond.add(i + (j * size) - size);
      }
      conditions.add(cond);
    }
    // diagonals side 1
    Set<Integer> condD1 = new HashSet<>();
    for (int j = 1; j <= size; j++) {
      condD1.add(1 + (j * (size + 1) - (size + 1)));
    }
    conditions.add(condD1);

    // diagonals side 2
    Set<Integer> condD2 = new HashSet<>();
    for (int j = 1; j <= size; j++) {
      condD2.add(size + (j * (size - 1) - (size - 1)));
    }
    conditions.add(condD2);
  }

  /**
   * Called when the window is resized.
   *
   * @param newSize the new size of the drawing area.
   */
  @Override
  protected void onResize(Vec2d newSize) {
    screenWidth = newSize.x;
    screenHeight = newSize.y;
    System.out.println("Screen Width: " + screenWidth + " Screen Height: " + screenHeight);
  }

}
