package cs3500.animator.controller;


import cs3500.animator.model.Keyframe;
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

  /**
   * Returns the model that is held in the controller.
   */

  AnimatorModel getModel();

  /**
   * Uses the addShape method from our Model interface to add a shape
   * to the model in the controller.
   */

  void addShape(String name, String type);

  /**
   * Uses the removeShape method from our Model interface to remove a shape
   * from the model in the controller.
   */
  
  void removeShape(String name);

  /**
   * Uses our modifyKeyframe method from our Model interface to modify a
   * keyframe from the model in the controller.
   */

  void editKeyframe(Keyframe k, Double t1, Double x1, Double y1, Double w1, Double h1, Double r1, Double g1, Double b1, Double t2, Double x2, Double y2, Double w2, Double h2, Double r2, Double g2, Double b2);

  /**
   * Uses the addKeyframe method from our Model interface to add
   * a keyframe to the model help in the controller.
   *
   */

  void addKeyframe(Keyframe kf);

  /**
   * Uses the removeKeyframe method from our Model interface to remove
   * a keyframe from the model held in the controller.
   */

  void deleteKeyframe(Keyframe k);
}
