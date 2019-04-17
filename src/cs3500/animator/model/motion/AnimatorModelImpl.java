package cs3500.animator.model.motion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.model.Keyframe;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.util.AnimationBuilder;

/**
 * Implementation class for our model. We store the animation in a HashMap, and the canvas in an
 * array of integers. We represent an animation as a HashMap of shape names, and the motions
 * associated with that shape in an ArrayList associated with the name of the shape.
 */
public class AnimatorModelImpl implements AnimatorModel {

  private Map<Shape, ArrayList<Motion>> animation;
  private ArrayList<Keyframe> arrKeys = new ArrayList<>();
  private IAnimator model;


  /**
   * Constructor for our providers model.
   * @param m an instance of the model.
   */
  public AnimatorModelImpl(IAnimator m) {
    this.model = m;
    this.animation = model.sendAnimation();
    arrKeys = this.createKeyframes();
  }

  @Override
  public Integer getWidth() {
    return model.returnCanvas()[2];
  }

  @Override
  public Integer getHeight() {
    return model.returnCanvas()[3];
  }

  @Override
  public Integer getLeftX() {
    return model.returnCanvas()[0];
  }

  @Override
  public Integer getTopY() {
    return model.returnCanvas()[1];
  }

  @Override
  public ArrayList<Keyframe> getKeyframes() {
    return arrKeys;
  }

  private ArrayList<Keyframe> createKeyframes() {

    for (Map.Entry<Shape, ArrayList<Motion>> entry : animation.entrySet()) {
      Shape key = entry.getKey();
      ArrayList<Motion> value = entry.getValue();
      for (int i = 0; i < value.size(); i++) {
        Motion m = value.get(i);
        arrKeys.add(new Keyframe(m, key.getName()));
      }
    }
    return arrKeys;
  }


  @Override
  public HashMap<String, String> getAllShapes() {
    HashMap<String, String> temp = new HashMap<>();

    for (Map.Entry<Shape, ArrayList<Motion>> entry : animation.entrySet()) {
      Shape key = entry.getKey();
      temp.put(key.getName(), key.getType());
    }
    return temp;
  }

  @Override
  public ArrayList<Keyframe> interpolate(double tick) {

    ArrayList<Keyframe> newKeys = new ArrayList<>();

    for (Keyframe k : this.getKeyframes()) {
      if (tick >= k.getT1() && tick <= k.getT2()) {
        int t1 = (int) k.getT1();
        int t2 = (int) k.getT2();
        int x = model.tween((int) tick, (int) k.getX1(), (int) k.getX2(), t1, t2);
        int y = model.tween((int) tick, (int) k.getY1(), (int) k.getY2(), t1, t2);
        int h = model.tween((int) tick, (int) k.getH1(), (int) k.getH2(), t1, t2);
        int w = model.tween((int) tick, (int) k.getW1(), (int) k.getW2(), t1, t2);
        int r = model.tween((int) tick, (int) k.getR1(), (int) k.getR2(), t1, t2);
        int g = model.tween((int) tick, (int) k.getG1(), (int) k.getG2(), t1, t2);
        int b = model.tween((int) tick, (int) k.getB1(), (int) k.getB2(), t1, t2);

        Keyframe newFrame = new Keyframe(new Motion((int) tick, x, y, h, w, r, g, b, (int) tick,
                x, y, h, w, r,
                g, b), k.getName());
        newKeys.add(newFrame);
      }
    }
    return newKeys;
  }

  @Override
  public double getLargestTick() {
    return model.returnHighestTick();
  }

  // Needed to compile in order to work with partners code, since they implemented the extra credit
  /**
   * A Builder class needed here in order for the code to compile.
   */
  public final static class Builder implements AnimationBuilder<AnimatorModelImpl> {
    @Override
    public AnimatorModelImpl build() {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModelImpl> setBounds(int x, int y, int width, int height) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModelImpl> declareShape(String name, String type) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModelImpl> addMotion(String name, int t1, int x1, int y1,
                                                         int w1, int h1, int r1, int g1, int b1,
                                                         int t2, int x2, int y2, int w2, int h2,
                                                         int r2, int g2, int b2) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModelImpl> addKeyframe(String name, int t, int x, int y,
                                                           int w, int h, int r, int g, int b) {
      return null;
    }
  }
}
