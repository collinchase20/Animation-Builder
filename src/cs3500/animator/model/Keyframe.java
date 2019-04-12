package cs3500.animator.model;

import cs3500.animator.model.motion.Motion;

public class Keyframe {
  Motion m;
  String name;

  //Keyframe(Motion m) {
  //  this.m = m;
  //}

  Keyframe(Motion m, String name) {
    this.m = m;
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

}
