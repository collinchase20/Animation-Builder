package cs3500.animator.controller;


import cs3500.animator.model.motion.AnimatorModel;

/**
 * Our controller is based around Java Swing's buttons, text fields, and action listeners. If a
 * button is pressed, we either perform the action instantly, or grab the text from it's
 * corresponding text field and use that to perform the action.
 */
public interface IController {


  /**
   * Starts up the controller, which handles all of the user input through java swing. More
   * details above.
   *
   * @throws IllegalArgumentException for when the textual input arguments in the editor view
   *                                   are invalid.
   */
  void beginController() throws IllegalArgumentException;

  AnimatorModel getModel();

  void editKeyframe();

  void addShape(String name, String type);
}
