package cs3500.animator.model.motion;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.shape.Shape;

/**
 * Interface to hold methods used on an animation. An instance of an animation is essentially a
 * hashmap composed of shapes as the keys and an arrayList of motions as the values for each shape.
 * These methods, aside from the 3 canvas ones, work with that hashmap to create, add, remove, or
 * mutate an animation.
 */
public interface AnimatorModel {

  /**
   * This adds a motion to our {@code Map<Shape, ArrayList<Motion>>} based on the name of the shape
   * passed in. We find the correct key based on the name, and then add the motion to the
   * corresponding value.
   *
   * @param m    the motion to be added to the animation
   * @param name the name of the shape to add the motion to
   * @throws IllegalArgumentException if sTime or eTime is less than 0, if eTime is less than sTime,
   *                                  or if sTime is not equal to eTime of the previous motion from
   *                                  the arrayList of motions.
   */
  void addMotion(Motion m, String name);

  /**
   * Allows us to send our animation to the model.
   *
   * @return a map representing our animation.
   * @throws IllegalArgumentException when starting time is not greater than the previous motions
   *                                  end time, or if you try to add a motion to a shape that has
   *                                  not been created.
   */
  Map<Shape, ArrayList<Motion>> sendAnimation();

  /**
   * This method allows you to add a shape key by defining the shape's name and type. The key is
   * initialized with an empty ArrayList of motions, and then that is mutated with addMotion in
   * order to build the animation.
   *
   * @param name The name of the shape
   * @param type The type of the shape (rectangle, ellipse, etc)
   */
  void addShape(String name, String type);

  /**
   * Removes a shape and all of it's motions from our animation.
   *
   * @param name name of the shape
   * @throws IllegalArgumentException if the user specify an unsupported shape type, or if the user
   *                                  tries to add a shape that has already been added.
   */
  void removeShape(String name);

  /**
   * Removes a specific motion from the animation. We take in all of the values in order to be able
   * to find the exact motion.
   *
   * @param t1 start time
   * @param x1 start x value
   * @param y1 start y value
   * @param w1 start width
   * @param h1 start height
   * @param r1 start red value
   * @param g1 start green value
   * @param b1 start blue value
   * @param t2 end time
   * @param x2 end x value
   * @param y2 end y value
   * @param w2 end width
   * @param h2 end height
   * @param r2 end red value
   * @param g2 end green value
   * @param b2 end blue value
   * @throws IllegalArgumentException if the user tries to remove a shape that does not exist.
   */
  void removeMotion(int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                    int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * Sets the values for the canvas of the animation to be drawn. The canvas is stored as an array
   * of integers, and we can use sendCanvas() to send this to our view.
   *
   * @param x      top left x value for the canvas
   * @param y      top left y value for the canvas
   * @param width  width of the canvas
   * @param height height of the canvas
   * @throws IllegalArgumentException if the user tries to remove a motion that is not in the
   *                                  animation.
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * This makes it easy to output our canvas in the textual view.
   *
   * @return a string representing the x, y, width, and height of the canvas
   */
  String sendCanvas();

  /**
   * @return a copy of our array representing the canvas, so that our view can use it.
   */
  int[] returnCanvas();

  /**
   * Computes the interpolated value of a and b at the current time between time1 and time2.
   *
   * @param time  the current time
   * @param a     the first value to be interpolated
   * @param b     the second value to be interpolated
   * @param time1 the first time
   * @param time2 the second time
   * @return the interpolation of a and b
   */
  Integer tween(double time, int a, int b, int time1, int time2);


  /**
   * Returns the highest tick from our models hashmap of shapes and motions. Parses every arraylist
   * of motions and returns the number of the highest found tick.
   */
  Integer getLargestTick();

  /**
   * Removes the given keyframe from the arrayList of motions.
   *
   * @param name the name of the shape whose keyframe your trying to remove
   * @param t    the time of the keyframe
   * @param x    the x position of the keyframe
   * @param y    the y position of the keyframe
   * @param w    the width of the keyframe
   * @param h    the height of the keyframe
   * @param r    the red value of the color of that keyframe
   * @param g    the green value of the color of that keyframe
   * @param b    the blue value of the color of that keyframe
   * @throws IllegalArgumentException if you try to remove the keyframe of a shape that does not
   *                                  exist, or if the user passes a time that does not correspond
   *                                  to any keyframes.
   */
  void removeKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Adds a keyframe to our model.
   *
   * @param time the time at which you would like to add a keyframe.
   * @throws IllegalArgumentException if the user tries to add a keyframe where there is already a
   *                                  keyframe, or if there is no shape with the name passed.
   */
  void addKeyFrame(String name, int time);

  /**
   * Based on the given name of a shape and time of a keyframe, this method will modify that
   * keyframe with the rest of the given values.
   *
   * @param name the name of the shape whose keyframe your trying to remove
   * @param time the time of the keyframe
   * @param x    the x position of the keyframe
   * @param y    the y position of the keyframe
   * @param w    the width of the keyframe
   * @param h    the height of the keyframe
   * @param r    the red value of the color of that keyframe
   * @param g    the green value of the color of that keyframe
   * @param b    the blue value of the color of that keyframe
   * @throws IllegalArgumentException if the user tries to modify a keyframe of an invalid shape or
   *                                  time.
   */
  void modifyKeyFrame(String name, int time, int x, int y, int w, int h, int r, int g, int b);

  /**
   * This method uses a private void method to transform our representation of an animation into
   * a representation that is compatible with the code given by our partners. It returns an
   * ArrayList of Keyframes, which is how our customer defined their animation.
   * @return an ArrayList representing an animation
   */
  ArrayList<Keyframe> getKeyframes();

  /**
   * TODO
   */
  HashMap<String,String> getAllShapes();

  Integer getWidth();

  Integer getHeight();

  Integer getLeftX();

  Integer getTopY();

  ArrayList<Keyframe> interpolate(double tick);
}

