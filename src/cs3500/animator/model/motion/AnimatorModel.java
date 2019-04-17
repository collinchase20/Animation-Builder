package cs3500.animator.model.motion;

import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.model.Keyframe;

/**
 * This is our implementation of the adapter pattern. It is named as such so that it is compatible
 * with our provider's code, both the interface and the concrete implementation. We give our
 * providers code an instance of this whenever they ask for it. Our concrete instance holds our
 * model, and mutates our data structure on instantion to one that is compatible with our provider's
 * code.
 */
public interface AnimatorModel {

  /**
   * On construction, our AnimatorModelImpl uses a private void method to transform our
   * representation of an animation into a representation that is compatible with the code given by
   * our partners. This method delivers that ArrayList of Keyframes, which is how our customer
   * defined their animation.
   *
   * @return an ArrayList representing an animation
   */
  ArrayList<Keyframe> getKeyframes();

  /**
   * This method delivers a hashmap of all of our shapes names and types. The name is the first key
   * and the type is the value.
   *
   * @return a Hashmap of shape names and types
   */
  HashMap<String, String> getAllShapes();

  /**
   * Returns the width of the canvas.
   *
   * @return an Integer representing the width of the canvas
   */
  Integer getWidth();

  /**
   * Returns the height of the canvas.
   *
   * @return an Integer representing the height of the canvas
   */
  Integer getHeight();

  /**
   * Returns the leftmost X value of canvas.
   *
   * @return an Integer representing the leftmost x value of the canvas
   */
  Integer getLeftX();

  /**
   * Returns the topmost y value of canvas.
   *
   * @return an Integer representing the topmost y value of the canvas
   */
  Integer getTopY();

  /**
   * This method delivers our entire animation with a new interpolated keyframe added at the given
   * tick. It seems inefficient to send the whole animation but this is what we needed to do in
   * order to make our code compatible with our partners.
   *
   * @param tick the tick to tween at
   * @return an arraylist of Keyframes with a new interpolated keyframe added at the given tick.
   */
  ArrayList<Keyframe> interpolate(double tick);

  /**
   * Searches and returns the highest tick in our model.
   *
   * @return the highest time in our animation.
   */
  double getLargestTick();
}

