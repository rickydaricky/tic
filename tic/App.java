package tic;

import engine.Application;
import engine.Rectangle;
import engine.Screen;
import engine.UIElement;
import engine.support.Vec2d;


/**
 * This is your Tic-Tac-Toe top-level, App class.
 * This class will contain every other object in your game.
 */
public class App extends Application {

  public App(String title) {
    super(title);
  }

  public App(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen) {
    super(title, windowSize, debugMode, fullscreen);

    // add a new screen and make that active screen

    Screen mainScreen = new Screen("mainScreen");
    mainScreen.setActive();

    System.out.print("Ricky is godly!!!");

    Rectangle rect = new Rectangle("Line 1", 10, 10, 40, 40);
    mainScreen.addUIElement(rect);


    // work back and forth
  }

}
