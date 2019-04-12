package cs3500.animator.provider.view;


import java.util.function.Consumer;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.motion.AnimatorModel;


/**
 * Interface for views.
 */
public interface IView {

  // display cs3500.animator.view: displays anything in the window/browser
  // (text, image, animation, etc)

  // convertTicks: convert the animation from unitless “ticks” to actual time durations
  // This can be controlled by specifying a tempo for the animation, in ticks per second.
  // Thus the same  animation can be produced at different speeds, by altering the tempo.
  // This tempo will be supplied as a command-line parameter.


  /**
   * Make the cs3500.animator.view visible.
   */
  void makeVisible();

  /**
   * Provide the cs3500.animator.view with a callback option to process a command
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Transmit an error message to the cs3500.animator.view, in case the command could not be
   * processed correctly
   */
  void showErrorMessage(String error);

  /**
   * Signal the cs3500.animator.view to draw itself
   */
  void refresh();

  /**
   * Returns the model.
   *
   * @return the model
   */
  AnimatorModel getAnimatorModel();

  /**
   * Tells the view which controller is being used.
   */
  void addController(Controller c);
}
