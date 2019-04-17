package cs3500.animator.model.motion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import cs3500.animator.model.shape.Shape;
import cs3500.animator.util.AnimationBuilder;

/**
 * Implementation class for our model. We store the animation in a HashMap, and the canvas in an
 * array of integers. We represent an animation as a HashMap of shape names, and the motions
 * associated with that shape in an ArrayList associated with the name of the shape.
 */
public class IAnimatorImpl implements IAnimator {

  private Map<Shape, ArrayList<Motion>> animation;
  // an array of integers to represent the canvas
  // index key: 0=x, 1=y, 2=width, 3=height
  private int[] canvas = new int[4];


  public IAnimatorImpl() {
    this.animation = new LinkedHashMap<>();
  }

  @Override
  public void addMotion(Motion m, String shapeName) {
    boolean checker = true;
    for (Map.Entry<Shape, ArrayList<Motion>> entry : animation.entrySet()) {
      Shape key = entry.getKey();
      ArrayList<Motion> value = entry.getValue();
      if (key.getName().equals(shapeName)) {
        if (value.size() > 0 && value.get(value.size() - 1).t2 > m.t1) {
          throw new IllegalArgumentException("Starting time has to be greater "
                  + "than the previous motions end time");
        }
        value.add(m);
        checker = false;
      }
    }
    if (checker) {
      throw new IllegalArgumentException("The shape has not yet been created");
    }
  }

  @Override
  public Map<Shape, ArrayList<Motion>> sendAnimation() {
    return this.animation;
  }

  @Override
  public void addShape(String name, String type) {
    if (!(type.equalsIgnoreCase("rectangle") || type.equalsIgnoreCase("ellipse"))) {
      throw new IllegalArgumentException("That type of shape does not exist, please" +
              " use rectangle or ellipse");
    } else {
      for (Shape s : animation.keySet()) {
        if (s.getName().equals(name)) {
          throw new IllegalArgumentException("That shape has already been added!");
        }
      }
      Shape r = new Shape(name, type);
      ArrayList<Motion> m = new ArrayList<>();
      animation.put(r, m);
    }
  }

  @Override
  public void removeShape(String name) {
    boolean modification = false;
    Shape remove = null;
    for (Shape key : animation.keySet()) {
      if (key.getName().equals(name)) {
        modification = true;
        remove = key;
      }
    }
    if (modification) {
      animation.remove(remove);
    } else {
      throw new IllegalArgumentException("That shape does not exist yet");
    }
  }

  @Override
  public void removeMotion(int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                           int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    boolean mod = false;
    Motion remove = null;
    for (Map.Entry<Shape, ArrayList<Motion>> entry : animation.entrySet()) {
      ArrayList<Motion> value = entry.getValue();
      for (Motion m : value) {
        if (m.t1 == t1 && m.t2 == t2 && m.x1 == x1 && m.x2 == x2 && m.y1 == y1 && m.y2 == y2 &&
                m.w1 == w1 && m.w2 == w2 && m.h1 == h1 && m.h2 == h2 && m.r1 == r1 && m.r2 == r2 &&
                m.g1 == g1 && m.g2 == g2 && m.b1 == b1 && m.b2 == b2) {
          mod = true;
          remove = m;
        }
      }
    }
    if (mod) {
      for (ArrayList<Motion> m : animation.values()) {
        m.remove(remove);
      }
    } else {
      throw new IllegalArgumentException("That motion does not exist yet.");
    }
  }

  @Override
  public void removeKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    boolean check = false;
    boolean tcheck = false;
    for (Map.Entry<Shape, ArrayList<Motion>> entry : animation.entrySet()) {
      Shape key = entry.getKey();
      ArrayList<Motion> value = entry.getValue();
      if (key.getName().equals(name)) {
        check = true;
        for (int i = 0; i < value.size(); i++) {
          if (value.get(0).t1 == t && value.get(0).x1 == x && value.get(0).y1 == y &&
                  value.get(0).w1 == w &&
                  value.get(0).h1 == h && value.get(0).r1 == r && value.get(0).g1 == g &&
                  value.get(0).b1 == b) {
            value.remove(0);
            tcheck = true;
          }
          if (value.get(i).t2 == t && value.get(i).x2 == x && value.get(i).y2 == y &&
                  value.get(i).w2 == w &&
                  value.get(i).h2 == h && value.get(i).r2 == r && value.get(i).g2 == g &&
                  value.get(i).b2 == b && i == value.size() - 1) {
            value.remove(i);
            tcheck = true;
          }
          if (i <= value.size() - 2) {
            if (value.get(i).t2 == t && value.get(i).x2 == x && value.get(i).y2 == y &&
                    value.get(i).w2 == w &&
                    value.get(i).h2 == h && value.get(i).r2 == r && value.get(i).g2 == g &&
                    value.get(i).b2 == b) {
              if (value.get(i + 1).t1 == t && value.get(i + 1).x1 == x &&
                      value.get(i + 1).y1 == y && value.get(i + 1).w1 == w &&
                      value.get(i + 1).h1 == h && value.get(i + 1).r1 == r &&
                      value.get(i + 1).g1 == g && value.get(i + 1).b1 == b) {
                value.get(i).t2 = value.get(i + 1).t2;
                value.get(i).x2 = value.get(i + 1).x2;
                value.get(i).y2 = value.get(i + 1).y2;
                value.get(i).w2 = value.get(i + 1).w2;
                value.get(i).h2 = value.get(i + 1).h2;
                value.get(i).r2 = value.get(i + 1).r2;
                value.get(i).g2 = value.get(i + 1).g2;
                value.get(i).b2 = value.get(i + 1).b2;
                value.remove(i + 1);
                tcheck = true;
              }
            }
          }
        }
      }
    }
    if (!check) {
      throw new IllegalArgumentException("The name of the shape to which you are trying to" +
              "remove a keyframe from does not exist.");
    }
    if (!tcheck) {
      throw new IllegalArgumentException("The time of the keyframe you are trying to remove" +
              "does not exist.");
    }
  }

  @Override
  public void addKeyFrame(String name, int time) {
    boolean check = false;
    for (Map.Entry<Shape, ArrayList<Motion>> entry : animation.entrySet()) {
      Shape key = entry.getKey();
      ArrayList<Motion> value = entry.getValue();
      if (key.getName().equalsIgnoreCase(name)) {
        check = true;
        if (value.size() == 0) {
          value.add(new Motion(time, 0, 0, 0, 0, 0, 0,
                  0, 0, 0, 0, 0, 0, 0, 0, 0));
          return;
        }
        for (int i = 0; i < value.size(); i++) {
          Motion m = value.get(i);

          if (m.getT1() == time || m.getT2() == time) {
            throw new IllegalArgumentException("There is already a keyframe here");
          }

          if (m.getT1() > time && i == 0) {
            Motion temp = new Motion(time, 0, 0, 0,
                    0, 0, 0, 0, m.getT1(), m.getX1(), m.getY2(), m.getW2(),
                    m.getH2(),
                    m.getR2(), m.getG2(), m.getB2());
            value.add(i, temp);
          }
          if (m.getT1() < time && m.getT2() > time) {
            int x2 = this.tween(time, m.getX1(), m.getX2(), m.getT1(), m.getT2());
            int y2 = this.tween(time, m.getY1(), m.getY2(), m.getT1(), m.getT2());
            int w2 = this.tween(time, m.getH1(), m.getH2(), m.getT1(), m.getT2());
            int h2 = this.tween(time, m.getW1(), m.getW2(), m.getT1(), m.getT2());
            int r2 = this.tween(time, m.getR1(), m.getR2(), m.getT1(), m.getT2());
            int g2 = this.tween(time, m.getG1(), m.getG2(), m.getT1(), m.getT2());
            int b2 = this.tween(time, m.getB1(), m.getB2(), m.getT1(), m.getT2());

            Motion frame = new Motion(time, x2, y2, w2, h2, r2, g2, b2, m.t2, m.x2, m.y2, m.w2,
                    m.h2, m.r2, m.g2, m.b2);
            m.t2 = time;
            m.x2 = x2;
            m.y2 = y2;
            m.w2 = w2;
            m.h2 = h2;
            m.r2 = r2;
            m.g2 = g2;
            m.b2 = b2;
            value.add(i + 1, frame);
            break;
          }
          if (time > m.getT2() && i == value.size() - 1) {
            value.add(new Motion(m.getT2(), m.getX2(), m.getY2(), m.getW2(), m.getH2(),
                    m.getR2(), m.getG2(),
                    m.getB2(), time, 0, 0, 0, 0, 0, 0, 0));
            return;
          }
        }
      }
    }
    if (!check) {
      throw new IllegalArgumentException("The name of the shape to which you are trying to add" +
              "a keyframe to does not exist.");
    }
  }

  @Override
  public void modifyKeyFrame(String name, int time, int x, int y, int h, int w, int r, int g,
                             int b) {
    boolean check = false;
    boolean tcheck = false;
    for (Map.Entry<Shape, ArrayList<Motion>> entry : animation.entrySet()) {
      Shape key = entry.getKey();
      ArrayList<Motion> value = entry.getValue();
      if (key.getName().equalsIgnoreCase(name)) {
        check = true;
        for (int i = 0; i < value.size(); i++) {
          Motion m = value.get(i);
          if (i < value.size() - 1 && m.t2 == time && value.get(i + 1).t1 == time) {
            m.x2 = x;
            m.y2 = y;
            m.w2 = w;
            m.h2 = h;
            m.r2 = r;
            m.g2 = g;
            m.b2 = b;
            value.get(i + 1).x1 = x;
            value.get(i + 1).y1 = y;
            value.get(i + 1).w1 = w;
            value.get(i + 1).h1 = h;
            value.get(i + 1).r1 = r;
            value.get(i + 1).g1 = g;
            value.get(i + 1).b1 = b;
            tcheck = true;
          }

          if (i == 0 && m.t1 == time) {
            m.x1 = x;
            m.y1 = y;
            m.w1 = w;
            m.h1 = h;
            m.r1 = r;
            m.g1 = g;
            m.b1 = b;
            tcheck = true;
          }

          if (i == value.size() - 1 && m.t2 == time) {
            m.x2 = x;
            m.y2 = y;
            m.w2 = w;
            m.h2 = h;
            m.r2 = r;
            m.g2 = g;
            m.b2 = b;
            tcheck = true;
          }

          if (i > 0 && m.t1 == time && value.get(i - 1).t2 == time) {
            m.x1 = x;
            m.y1 = y;
            m.w1 = w;
            m.h1 = h;
            m.r1 = r;
            m.g1 = g;
            m.b1 = b;
            value.get(i - 1).x2 = x;
            value.get(i - 1).y2 = y;
            value.get(i - 1).w2 = w;
            value.get(i - 1).h2 = h;
            value.get(i - 1).r2 = r;
            value.get(i - 1).g2 = g;
            value.get(i - 1).b2 = b;
            tcheck = true;
          }
        }
      }
    }
    if (!check) {
      throw new IllegalArgumentException("The shape whose keyframe you are trying to modify" +
              " does not exist yet.");
    }
    if (!tcheck) {
      throw new IllegalArgumentException("The time of the keyframe in which you are trying to " +
              "modify does not exist.");
    }
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    canvas[0] = x;
    canvas[1] = y;
    canvas[2] = width;
    canvas[3] = height;
  }


  @Override
  public String sendCanvas() {
    StringBuilder sb = new StringBuilder();
    sb.append("canvas ");
    for (int i : canvas) {
      sb.append(i).append(" ");
    }
    return String.valueOf(sb);
  }

  @Override
  public int[] returnCanvas() {
    return this.canvas.clone();
  }

  @Override
  public Integer tween(double time, int a, int b, int time1, int time2) {
    return (int) Math.round(a * ((time2 - time) / (time2 - time1)) +
            b * ((time - time1) / (time2 - time1)));
  }

  @Override
  public Integer returnHighestTick() {
    int accum = 0;
    for (Map.Entry<Shape, ArrayList<Motion>> entry : animation.entrySet()) {
      ArrayList<Motion> value = entry.getValue();
      for (Motion m : value) {
        if (m.getT2() > accum) {
          accum = m.getT2();
        }
      }
    }
    return accum;
  }

  /**
   * Our builder class for an IAnimation. We use this in conjunction with the AnimationBuilder to
   * parse text files in order to build our model.
   */
  public final static class Builder implements AnimationBuilder<IAnimator> {

    // required parameters
    IAnimator a = new IAnimatorImpl();

    @Override
    public IAnimator build() {
      return a;
    }

    @Override
    public AnimationBuilder<IAnimator> setBounds(int x, int y, int width, int height) {
      a.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimator> declareShape(String name, String type) {
      a.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimator> addMotion(String name, int t1, int x1, int y1, int w1,
                                                 int h1, int r1, int g1, int b1, int t2, int x2,
                                                 int y2, int w2, int h2, int r2, int g2, int b2) {

      Motion m = new Motion(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
      a.addMotion(m, name);
      return this;
    }

    // We were told on Piazza that we did not have to implement keyFrame functionality (post @487)
    @Override
    public AnimationBuilder<IAnimator> addKeyframe(String name, int t, int x, int y, int w, int h,
                                                   int r, int g, int b) {
      return null;
    }
  }

}
