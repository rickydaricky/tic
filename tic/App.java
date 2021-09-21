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
import javafx.scene.input.KeyEvent;
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

  double originalX = 1200;
  double originalY = 800;
  double screenWidth = 1200;
  double screenHeight = 800;
  long timeSinceStart = 0;
  long timeSincePlay = 0;
  double xGap = 200;
  double yGap = 200;
  double base = 100;
  int size = 3;
  boolean xTurn = true;
  boolean gameOver = true;
  Screen mainScreen = new Screen("mainScreen");
  Screen homeScreen = new Screen("homeScreen");
  HashMap<Integer, Integer> chosen = new HashMap<>();
  Set<Integer> p1 = new HashSet<>();
  Set<Integer> p2 = new HashSet<>();
  Set<Set<Integer>> conditions = new HashSet<>();
  long allotedTime = 5000000000L;
  long timeSinceLastChose = 0L;
  double xScale = 1;
  double yScale = 1;


  List<Screen> screens = new ArrayList<>();

  public App(String title) {
    super(title);
  }

  public App(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen) {
    super(title, windowSize, debugMode, fullscreen);

    // add a new screen and make that active screen

    mainScreenDraw();

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

  private void mainScreenDraw() {

    Rectangle homeBackground = new Rectangle("homeBackground", screenWidth / 2,
            0, screenWidth / 2, screenHeight, screenWidth,
            Color.TURQUOISE, Color.TURQUOISE);
    mainScreen.addUIElement(homeBackground);


    for (int i = 1; i <= 4; i++) {
      Rectangle line = new Rectangle("Line " + Integer.toString(i),
              base + xGap * (i - 1), base, base + xGap * (i - 1), base + yGap * 3, 5,
              Color.BLUE, Color.RED);
      mainScreen.addUIElement(line);
    }

    for (int i = 1; i <= 4; i++) {
      Rectangle line = new Rectangle("Line " + Integer.toString(i + 4),
              base, base + yGap * (i - 1), base + xGap * 3, base + yGap * (i - 1), 5,
              Color.BLUE, Color.RED);
      mainScreen.addUIElement(line);

      Rectangle restartButtonRect = new Rectangle("restartButtonRect", screenWidth * 3 / 4,
              screenHeight * 2 / 4, screenWidth * 3.5 / 4, screenHeight * 2 / 4, screenHeight / 16,
              Color.TURQUOISE, Color.ORANGE);
      Text restartButtonText = new Text("restartButtonText", screenWidth * 3 / 4.1, screenHeight * 2 / 3.9,
              "Restart Game", Color.FIREBRICK, 32);
      Button restartButton = new Button("restartButton", restartButtonRect, restartButtonText);
      mainScreen.addUIElement(restartButton);
    }

    Rectangle timerBackgroundRect = new Rectangle("timerBackgroundRect", screenWidth * 3 / 4,
            screenHeight * 1 / 4, screenWidth * 3.5 / 4,
            screenHeight * 1 / 4, screenHeight / 16,
            Color.PURPLE, Color.PURPLE);
    Rectangle timerForeGroundRect = new Rectangle("timerForeGroundRect", screenWidth * 3 / 4,
            screenHeight * 1 / 4, screenWidth * 3.5 / 4 - screenWidth * 0.5 / 4 + screenWidth
            * 0.5 / 4 * (timeSinceLastChose / allotedTime),
            screenHeight * 1 / 4, screenHeight / 16,
            Color.ORANGE, Color.ORANGE);
    mainScreen.addUIElement(timerBackgroundRect);
    mainScreen.addUIElement(timerForeGroundRect);
    screens.add(mainScreen);
  }


  /**
   * Called periodically and used to update the state of your game.
   *
   * @param nanosSincePreviousTick approximate number of nanoseconds since the previous call
   */
  @Override
  protected void onTick(long nanosSincePreviousTick) {
    timeSinceStart += nanosSincePreviousTick;
    if (!gameOver) {
      timeSinceLastChose += nanosSincePreviousTick;
    }
    if (timeSinceLastChose > allotedTime && !gameOver) {
      if (xTurn) {
        victory("p2");
      } else {
        victory("p1");
      }

    }

    Rectangle temp = (Rectangle) mainScreen.get("timerForeGroundRect");
    double y = temp.getY1();
    double tempS = temp.getStrokeWidth();

    mainScreen.removeUIElement("timerForeGroundRect");
    Rectangle timerForeGroundRect = new Rectangle("timerForeGroundRect", screenWidth * 3 / 4,
            y, screenWidth * 3.5 / 4 - screenWidth * 0.5 / 4 + screenWidth
            * 0.5 / 4 * ((double) timeSinceLastChose / (double) allotedTime), y,
            tempS, Color.ORANGE, Color.ORANGE);

    mainScreen.addUIElement(timerForeGroundRect);
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
      if ((x > base + xGap * (i - 1)) && (x < base + xGap * (i))) {
        answerX = i;
      }
      if ((y > base + yGap * (i - 1)) && (y < base + yGap * (i))) {
        answerY = i;
      }
    }

    int ans = answerX + answerY * size - size;
    if (ans > 9 || ans < 1) return 0;
    return ans;
  }


  /**
   * Called when the mouse is moved.
   *
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseMoved(MouseEvent e) {
    hover(e, homeScreen);
    hover(e, mainScreen);
  }

  /**
   * Determines if mouse is hovering over a button in a scene and if so changes its color.
   *
   * @param e mouse event
   * @param s screen
   */
  private void hover(MouseEvent e, Screen s) {
    if (s.getActive()) {
      for (UIElement ui : s.getElements()) {
        if (ui instanceof Button) {
          if (Screen.between(e.getX(), e.getY(), (Button) ui)) {
            ((Button) ui).changeBackgroundColor(Color.LIGHTBLUE);
          } else {
            ((Button) ui).changeBackgroundColor(Color.GOLD);
          }
        }
      }
    }
  }


  /**
   * Called when the mouse is clicked.
   *
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseClicked(MouseEvent e) {

    // X and Os for game screen

    if (mainScreen.getActive()) {
      int boxNum = boxReturner(e.getX(), e.getY());
      System.out.println(boxNum);
      if (boxNum != 0) drawXO(boxNum);
      checkVictory();

      mainScreen.removeUIElement("turnText");

      String content;
      if (xTurn) {
        content = "X's Turn";
      } else {
        content = "O's Turn";
      }
      Text turnText = new Text("turnText", screenWidth * 3 / 5, screenHeight / 2,
              content, Color.BLUEVIOLET, 32);
      mainScreen.addUIElement(turnText);
    }

    // check if a button was pressed on the homeScreen

    if (homeScreen.getActive()) {
      for (UIElement ui : homeScreen.getElements()) {
        if (ui instanceof Button && Screen.between(e.getX(), e.getY(), (Button) ui)) {
          String t = ui.getTitle();
          switch (t) {
            case "startButton":
              homeScreen.setInactive();
              mainScreen.setActive();
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
          String t = ui.getTitle();
          switch (t) {
            case "restartButton":
              clearXO();
              p1 = new HashSet<>();
              p2 = new HashSet<>();
              mainScreen.removeUIElement("p1Win");
              mainScreen.removeUIElement("p2Win");
              mainScreen.removeUIElement("drawText");
              for (int i = 1; i <= size * size; i++) {
                chosen.put(i, 0);
              }
              gameOver = false;
              timeSinceLastChose = 0;
              break;
            default:
              break;
          }
        }
      }
    }


  }


  /**
   * Clears a screen of XO elements
   */
  void clearXO() {

    Screen temp = new Screen("mainScreen");
    for (UIElement ele : mainScreen.getElements()) {
      if (!ele.getTitle().equals("x1") && !ele.getTitle().equals("x2") && !ele.getTitle().equals("c")) {
        temp.addUIElement(ele);
      }
    }
    mainScreen.clear();
    for (UIElement ele : temp.getElements()) {
      mainScreen.addUIElement(ele);
    }

    List<Screen> tempScreens = new ArrayList<>();
    for (Screen s : screens) {
      if (!s.getTitle().equals("mainScreen")) {
        tempScreens.add(s);
      }
    }
    tempScreens.add(mainScreen);
    screens = tempScreens;
  }

  /**
   * Called when a key is pressed.
   *
   * @param e an FX {@link KeyEvent} representing the input event.
   */
  @Override
  protected void onKeyPressed(KeyEvent e) {
    String character = e.getCode().toString();
    if (character.equals("ESCAPE")) {
      homeScreen.setActive();
      mainScreen.setInactive();
      gameOver = true;
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

    if (chosen.get(boxNum) == 0 && !gameOver) {
      if (xTurn) {
        Rectangle x1 = new Rectangle("x1", base + xGap * (column - 0.75),
                base + yGap * (row - 0.75), base + xGap * (column - 0.25), base + yGap * (row - 0.25),
                2, Color.BROWN, Color.BROWN);
        Rectangle x2 = new Rectangle("x2", base + xGap * (column - 0.25),
                base + yGap * (row - 0.75), base + xGap * (column - 0.75), base + yGap * (row - 0.25),
                2, Color.BROWN, Color.BROWN);
        xTurn = false;
        mainScreen.addUIElement(x1);
        mainScreen.addUIElement(x2);
        chosen.put(boxNum, 1);
        p1.add(boxNum);
        timeSinceLastChose = 0L;
      } else {
        Circle c = new Circle("c", base + xGap * (column - 0.85), base + yGap * (row - 0.85),
                xGap / 1.5, yGap / 1.5, 10, Color.BLUE, Color.BLUE);
        mainScreen.addUIElement(c);
        xTurn = true;
        chosen.put(boxNum, 2);
        p2.add(boxNum);
        timeSinceLastChose = 0L;
      }
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
      Text p1Win = new Text("p1Win", screenWidth * 3 / 4, screenHeight / 1.5,
              "X's Wins!", Color.BLACK, 32);
      mainScreen.addUIElement(p1Win);
      timeSinceLastChose = 0;
    }
    if (player.equals("p2")) {
      System.out.println("O's Wins!");
      gameOver = true;
      Text p2Win = new Text("p2Win", screenWidth * 3 / 4, screenHeight / 1.5,
              "O's Wins!", Color.BLACK, 32);
      mainScreen.addUIElement(p2Win);
      timeSinceLastChose = 0;
    }
  }

  /**
   * The game is a draw.
   */
  void draw() {
    gameOver = true;
    Text drawText = new Text("drawText", screenWidth * 3 / 4, screenHeight / 1.5,
            "Draw!", Color.BLACK, 32);
    mainScreen.addUIElement(drawText);
  }

  /**
   * Checks if someone has won.
   */
  void checkVictory() {
    for (Set<Integer> cond : conditions) {
      if (p1.containsAll(cond)) victory("p1");
      if (p2.containsAll(cond)) victory("p2");
    }
    List<Integer> vals = new ArrayList<>();
    for (int i : chosen.values()) {
      vals.add(i);
    }
    if (!vals.contains(0)) draw();
  }

  /**
   * Called when the app is starting up.s
   */
  @Override
  protected void onStartup() {

    homeScreen.setActive();

    Rectangle homeBackground = new Rectangle("homeBackground", screenWidth / 2,
            0, screenWidth / 2, screenHeight, screenWidth,
            Color.TURQUOISE, Color.TURQUOISE);
    homeScreen.addUIElement(homeBackground);

    Text titleWords = new Text("title", screenWidth / 2.2, screenHeight / 4,
            "Tic-Tac-Toe", Color.BLACK, 32);
    homeScreen.addUIElement(titleWords);

    Text turnText = new Text("turnText", screenWidth * 3 / 5, screenHeight / 2,
            "X's Turn", Color.BLUEVIOLET, 32);
    mainScreen.addUIElement(turnText);

    // Buttons
    Rectangle startButtonRect = new Rectangle("startButtonRect", screenWidth / 2.7, screenHeight / 2.5,
            screenWidth / 1.5, screenHeight / 2.5, screenHeight / 16,
            Color.GOLD, Color.GOLD);
    Text startButtonText = new Text("startButtonText", screenWidth / 2.3, screenHeight / 2.45,
            "Start Game", Color.FIREBRICK, 32);
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
    redrawAll();
  }

  /**
   * Redraws everything in a screen
   */
  private void redrawAll() {
    xScale = screenWidth / originalX;
    yScale = screenHeight / originalY;
    xGap = xGap * xScale;
    yGap = yGap * yScale;
    originalX = screenWidth;
    originalY = screenHeight;
    for (Screen s : screens) {
      for (UIElement ele : s.getElements()) {
        ele.update(xScale, xScale);
      }
    }
  }

}
