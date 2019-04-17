package cs3500.animator.model;

import cs3500.animator.model.motion.Motion;

public class Keyframe {
  Motion m;
  String name;


  /**
   * Constructor for a motion represented as a keyframe.
   *
   * @param t1 starting time
   * @param x1 starting x coord
   * @param y1 starting y coord
   * @param w1 starting width
   * @param h1 starting height
   * @param r1 starting red value
   * @param g1 starting green value
   * @param b1 starting blue value
   * @param t2 ending time
   * @param x2 ending x coord
   * @param y2 ending y coord
   * @param w2 ending width
   * @param h2 ending height
   * @param r2 ending red value
   * @param g2 ending green value
   * @param b2 ending blue value
   */

  public Keyframe(String name, double t1, double x1, double y1, double w1,
                double h1, double r1, double g1, double b1, double t2, double x2,
                double y2, double w2, double h2, double r2, double g2, double b2) {
    if (t2 < t1) {
      throw new IllegalArgumentException("End time must be later than or equal to the starting " +
              "time");
    }
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("Times cannot be negative");
    }
    Motion temp = new Motion((int) t1, (int) x1, (int) y1, (int) w1, (int) h1, (int) r1, (int) g1,
            (int) b1, (int) t2, (int) x2, (int) y2, (int) w2, (int) h2, (int) r2, (int) g2,
            (int) b2);
    this.m = temp;
    this.name = name;
  }

  public Keyframe(Motion m, String name) {
    this.m = m;
    this.name = name;
  }

  public double getT1() {
    return m.getT1();
  }

  public double getT2() {
    return m.getT2();
  }

  public double getX1() {
    return m.getX1();
  }

  public double getX2() {
    return m.getX2();
  }

  public double getY1() {
    return m.getY1();
  }

  public double getY2() {
    return m.getY2();
  }

  public double getW1() {
    return m.getW1();
  }

  public double getW2() {
    return m.getW2();
  }

  public double getH1() {
    return m.getH1();
  }

  public double getH2() {
    return m.getH2();
  }

  public double getR1() {
    return m.getR1();
  }

  public double getR2() {
    return m.getR2();
  }

  public double getG1() {
    return m.getG1();
  }

  public double getG2() {
    return m.getG2();
  }

  public double getB1() {
    return m.getB1();
  }

  public double getB2() {
    return m.getB2();
  }

  public String getName() {return name;}

}
