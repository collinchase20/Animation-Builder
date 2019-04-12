package cs3500.animator.provider.view;

import cs3500.animator.model.Keyframe;

/**
 * Interface for Callbacks. Whenever the model is modified, these methods are called by the view.
 */
public interface ICallbacks {

  /**
   * Removes a shape with the given name.
   * @param s the name of the shape to be removed
   */
  void removeShape(String s);

  /**
   * Adds a new keyframe to the model's ArrayList of keyframes.
   * @param k is the keyframe that will be added
   */
  void addKeyframe(Keyframe k);

  /**
   * Edits a specific keyframe.
   * @param k is the keyframe that will be edited
   * @param t1 is the new t1 value
   * @param x1 is the new x1 value
   * @param y1 is the new y1 value
   * @param w1 is the new w1 value
   * @param h1 is the new h1 value
   * @param r1 is the new r1value
   * @param g1 is the new g1 value
   * @param b1 is the new b1 value
   * @param t2 is the new t2 value
   * @param x2 is the new x2 value
   * @param y2 is the new y2 value
   * @param w2 is the new w2 value
   * @param h2 is the new h2 value
   * @param r2 is the new r2 value
   * @param g2 is the new g2 value
   * @param b2 is the new b2 value
   */
  void editKeyframe(Keyframe k, double t1, double x1, double y1, double w1, double h1,
      double r1, double g1, double b1, double t2, double x2, double y2, double w2, double h2,
      double r2, double g2, double b2);

  /**
   * Delete a specific keyframe from the model.
   * @param k is the keyframe that will be deleted
   */
  void deleteKeyframe(Keyframe k);

  /**
   * Adds a new shape to the model's map of shapes.
   * @param name is the name of the shape
   * @param type is the type of shape
   */
  void addShape(String name, String type);
}
