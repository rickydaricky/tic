package tic;

import engine.Application;
import engine.Screen;
import engine.UIElement;
import engine.UIElements.Circle;
import engine.UIElements.Rectangle;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


/**
 * This is your Tic-Tac-Toe top-level, App class.
 * This class will contain every other object in your game.
 */
public class App extends Application {

  long timeSinceStart = 0;
  long timeSincePlay = 0;
  double gap = 200;
  double base = 100;
  int size = 3;
  boolean xTurn = true;
  Screen mainScreen = new Screen("mainScreen");
  Screen xoScreen = new Screen("xoScreen");


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
    }

//    Rectangle x1 = new Rectangle("x1", base + gap * (1 - 0.75),
//            base + gap * (1 - 0.75), base + gap * (1 - 0.25), base + gap * (1 - 0.25),
//            2, Color.BROWN, Color.BROWN);
//    mainScreen.addUIElement(x1);
//
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
      for (UIElement ele : s.getElements()) {
        g = ele.convert(g);
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
   * @param x
   * @param y
   * @return
   */
  public int boxReturner(double x, double y) {

    int answerX = 0;
    int answerY = 0;

    for (int i = 1; i <= 4; i++) {
      if ((x > base + gap * (i - 1)) && (x < base + gap * (i))) {
        answerX = i;
      }
      if ((y > base + gap * (i - 1)) && (y < base + gap * (i))) {
        answerY = i;
      }
    }

    return answerX + answerY * size - size;
  }

  /**
   * Called when the mouse is clicked.
   *
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseClicked(MouseEvent e) {
    System.out.println("x: " + e.getX() + " y: " + e.getY());
    int boxNum = boxReturner(e.getX(), e.getY());
    System.out.println(boxNum);
    if (boxNum != 0) drawXO(boxNum);
  }

  /**
   * drawXO
   * @param boxNum the box number that was clicked on
   */
  void drawXO(int boxNum) {

    int column = boxNum % size;
    if (column == 0) column = size;
    int row = (boxNum - column) / size + 1;

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
    } else {
      Circle c = new Circle("c", base + gap * (column - 0.85), base + gap * (row - 0.85),
              gap/1.5, gap/1.5, 10, Color.BLUE, Color.BLUE);
      xoScreen.addUIElement(c);
      xTurn = true;
    }
    screens.add(xoScreen);
    System.out.println("screen size: " + screens.size());
  }


}
