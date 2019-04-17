package cs3500.animator.view;

import java.awt.Component;

import javax.swing.*;

/**
 * Interface used to hold the methods used on our EditView. These
 * methods are all to be used in our controller with buttons that will call
 * these methods.
 *
 */
public interface IEditor {

  /**
   * Pause and resume function which Pauses and resumes the animation.
   * This will be used with buttons on the controller to call this method
   * to pause or resume.
   */
  void pauseAndResume();

  /**
   * Restart method.
   * This will be used with a button on our controller
   * to reset the current tick to 0 whenever its hit to send the animation
   * back to the beginning.
   */
  void restart();

  /**
   * Loop animation Method.
   * This method is to be used with a button on our controller that initilaizes
   * as the color red (meaning the animation is currently not looping) and whenever it is hit
   * the controller calls this method and turns the button green (meaning the animation will
   * loop upon hitting the end.
   */
  void loopAnimation();

  /**
   * This method is simply here as a means to call our start animation method
   * from the graphic view. It is what begins the animation for our controller.
   */
  void beginVisual();

  /**
   * This method reutrns the current frame of the animation.
   */

  Component getFrame();

  /**
   * This method increases the tick speed of the animation in graphic view.
   * It is to be used with a button
   * on the controller that
   * every time it is hit the speed of the animation increases.
   */

  void upSpeed();

  /**
   * This method decreases the tick speed of the animation in graphic view.
   * It is to be used with a button on the controller
   * that every time it is hit the speed of the animation will increase.
   */

  void downSpeed();

  /**
   * Sends the play button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendPlay();

  /**
   * Sends the slowdown button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendSlowDown();

  /**
   * Sends the speedUp button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendSpeedUp();

  /**
   * Sends the loop button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendLoop();

  /**
   * Sends the restart button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendRestart();

  /**
   * Sends the addShape button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendAddShape();

  /**
   * Sends the removeShape button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendRemoveShape();

  /**
   * Sends the Remove keyframe button button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendKeyFrameButtonR();

  /**
   * Sends the KeyBA button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendKeyBA();

  /**
   * Sends the keyBM button to our controller
   * to handle user interaction with the buttons.
   */

  JButton sendKeyBM();

  /**
   * Sends the shapeType text field to our
   * controller to handle the user input.
   */

  JTextField sendShapeType();

  /**
   * Sends the shapeName text field to our
   * controller to handle the user input.
   */

  JTextField sendShapeName();

  /**
   * Sends the shape type to remove text field to our
   * controller to handle the user input.
   */

  JTextField sendShapeTypeRemove();

  /**
   * Sends the shape frame field r text field to our
   * controller to handle the user input.
   */

  JTextField sendKeyFrameFieldR();

  /**
   * Sends the KeyTLA text field to our
   * controller to handle the user input.
   */

  JTextField sendKeyTLA();

  /**
   * Sends the KeyTLM text field to our
   * controller to handle the user input.
   */

  JTextField sendKeyTLM();

}

