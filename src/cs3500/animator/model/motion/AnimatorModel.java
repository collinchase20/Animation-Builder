package cs3500.animator.model.motion;

import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.model.Keyframe;

/**
 * This is our adapter
 * //TODO expand
 */
public interface AnimatorModel {

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

  double getLargestTick();
}

