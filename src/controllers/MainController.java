package controllers;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import models.MainModel;
import views.MainFrame;

public class MainController {
  MainFrame mainFrame;
  MainModel mainModel;

  JButton[] buttons;

  enum Dir {
    UP, DOWN, LEFT, RIGHT, NONE
  }

  final int WIDTH;
  final int HEIGHT;
  
  public MainController() {
    this.mainFrame = new MainFrame();
    this.mainModel = new MainModel();
    
    buttons = mainFrame.buttons;

    WIDTH = mainFrame.IMAGE_WIDTH;
    HEIGHT = mainFrame.IMAGE_HEIGHT;

    this.setEvents();
    new MenuController(mainFrame);
  }

  private void actionHandler(ActionEvent action) {
    JButton clickedButton = (JButton) action.getSource();

    checkDirection(clickedButton);
  }

  private void setEvents() {
    for (int i = 0; i < buttons.length; i++) {
      buttons[i].addActionListener(action -> actionHandler(action));
    }
  }

  private void checkDirection(JButton button) {

    int x = button.getLocation().x;
    int y = button.getLocation().y;

    System.out.println(x + " " + y);

    Dir direction = Dir.NONE;

    // check if button can move to the right

    if (x < WIDTH * 3 && canMoveToRight(x, y)) {
      direction = Dir.RIGHT;
      System.out.println("Button can move to the right");
    }

    // check if button can move to the left

    if (0 < x && canMoveToLeft(x, y)) {
      direction = Dir.LEFT;
      System.out.println("Button can move to the left");
    }

    // check if button can move down

    if (y < HEIGHT * 3 && canMoveDown(x, y)) {
      direction = Dir.DOWN;
      System.out.println("Button can move down");
    }

    if (0 < y && canMoveUp(x, y)) {
      direction = Dir.UP;
      System.out.println("Button can move up");
    }

    moveButton(direction, button);
  }

  private boolean canMoveToRight(int currentX, int currentY) {
    boolean matches = true;
    for (int i = 0; i < buttons.length; i++) {
      int x = buttons[i].getLocation().x;
      int y = buttons[i].getLocation().y;

      if (currentX + WIDTH == x && currentY == y) {
        matches = false;
      }
    }
    return matches;
  }

  private boolean canMoveToLeft(int currentX, int currentY) {
    boolean matches = true;

    for (int i = 0; i < buttons.length; i++) {
      int x = buttons[i].getLocation().x;
      int y = buttons[i].getLocation().y;

      if (currentX - WIDTH == x && currentY == y) {
        matches = false;
      }

    }

    return matches;
  }

  private boolean canMoveDown(int currentX, int currentY) {
    boolean matches = true;

    for (int i = 0; i < buttons.length; i++) {
      int x = buttons[i].getLocation().x;
      int y = buttons[i].getLocation().y;

      if (currentX == x && currentY + HEIGHT == y) {
        matches = false;
      }
    }

    return matches;
  }

  private boolean canMoveUp (int currentX, int currentY) {
    boolean matches = true;

    for (int i = 0; i < buttons.length; i++) {
      int x = buttons[i].getLocation().x;
      int y = buttons[i].getLocation().y;

      if (currentX == x && currentY - HEIGHT == y) {
        matches = false;
      }
    }

    return matches;
  }

  // move button

  private void moveButton(Dir direction, JButton button) {
    // original clicked button x y coordinates

    int x = button.getLocation().x;
    int y = button.getLocation().y;

    // verify direction

    if (direction == Dir.RIGHT) {

      button.setLocation(x + WIDTH, y);
    }

    if (direction == Dir.LEFT) {
      button.setLocation(x - WIDTH, y);
    }

    if (direction == Dir.UP) {
      button.setLocation(x, y - HEIGHT);
    }

    if (direction == Dir.DOWN) {
      button.setLocation(x, y + HEIGHT);
    }

  }

}