package cs3500.animator.model.motion;

/**
 * Class to represent a motion. The motion holds the starting and ending values of a shape that is
 * changing in some way.
 */
public class Motion {

  int t1;
  int x1;
  int y1;
  int w1;
  int h1;
  int r1;
  int g1;
  int b1;
  int t2;
  int x2;
  int y2;
  int w2;
  int h2;
  int r2;
  int g2;
  int b2;

  /**
   * Constructor for a motion.
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

  public Motion(int t1, int x1, int y1, int w1,
                int h1, int r1, int g1, int b1, int t2, int x2,
                int y2, int w2, int h2, int r2, int g2, int b2) {
    if (t2 < t1) {
      throw new IllegalArgumentException("End time must be later than or equal to the starting " +
              "time");
    }
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("Times cannot be negative");
    }

    this.t1 = t1;
    this.x1 = x1;
    this.y1 = y1;
    this.w1 = w1;
    this.h1 = h1;
    this.r1 = r1;
    this.g1 = g1;
    this.b1 = b1;
    this.t2 = t2;
    this.x2 = x2;
    this.y2 = y2;
    this.w2 = w2;
    this.h2 = h2;
    this.r2 = r2;
    this.g2 = g2;
    this.b2 = b2;
  }

  /**
   * Returns a motion as a string in order to cut down duplicate code when outputting textual
   * views.
   *
   * @return the motion as a string
   */
  public String printMotion() {
    return t1 + " " + x1 + " " + y1 + " " + w1 + " " + h1 + " " + r1 + " " + g1 + " "
            + b1 + " " + t2 + " " + x2 + " " + y2 + " " + w2 + " " + h2 + " " + r2 + " " + g2 +
            " " + b2;
  }

  public int getT1() {
    return this.t1;
  }

  public int getT2() {
    return this.t2;
  }

  public int getX1() {
    return this.x1;
  }

  public int getX2() {
    return this.x2;
  }

  public int getY1() {
    return this.y1;
  }

  public int getY2() {
    return this.y2;
  }

  public int getW1() {
    return this.w1;
  }

  public int getW2() {
    return this.w2;
  }

  public int getH1() {
    return this.h1;
  }

  public int getH2() {
    return this.h2;
  }

  public int getR1() {
    return this.r1;
  }

  public int getR2() {
    return this.r2;
  }

  public int getG1() {
    return this.g1;
  }

  public int getG2() {
    return this.g2;
  }

  public int getB1() {
    return this.b1;
  }

  public int getB2() {
    return this.b2;
  }

}
