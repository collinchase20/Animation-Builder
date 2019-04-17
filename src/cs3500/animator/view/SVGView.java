package cs3500.animator.view;

import java.util.ArrayList;
import java.util.Map;

import cs3500.animator.model.motion.AnimatorModel;
import cs3500.animator.model.motion.IAnimator;
import cs3500.animator.model.motion.Motion;
import cs3500.animator.model.shape.Shape;

/**
 * View implementation for SVG. Turns our animation model into svg format, and outputs it to either
 * system.out or a specified .svg file. This class essentially converts our models Hasmap of shapes
 * mapped to an arraylist of motions on that shape into a string of svg format.
 */
public class SVGView implements IView {

  IAnimator model;
  int speed = 20;

  /**
   * Constructor for a svg description of an animation.
   *
   * @param model an instance of our model.
   */

  public SVGView(IAnimator model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;

  }

  @Override
  public String displayView() {
    StringBuilder sb = new StringBuilder();
    if (model.returnCanvas()[0] == 0 && model.returnCanvas()[1] == 0
            && model.returnCanvas()[2] == 0 && model.returnCanvas()[3] == 0) {
      throw new IllegalArgumentException("Must initialize the canvas before displaying a view");
    }
    sb.append("<svg width=\"" + (model.returnCanvas()[2])).append("\" height=\""
            + (model.returnCanvas()[3])).append("\"")
            .append(" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");


    for (Map.Entry<Shape, ArrayList<Motion>> entry : model.sendAnimation().entrySet()) {
      Shape key = entry.getKey();
      ArrayList<Motion> value = entry.getValue();
      if (key.getType().equals("rectangle")) {
        if (value.size() == 0) {
          throw new IllegalArgumentException("This shape has no motions");
        } else {
          sb.append("<rect id=\"" + key.getName() + "\"");
          sb.append(" x=\"" + value.get(0).getX1() + "\" y=\"" + (value.get(0).getY1()) + "\" " +
                  "width=\"" + value.get(0).getW1() +
                  "\" " + "height=\"" + value.get(0).getH1() + "\" " + "fill=\"rgb" +
                  "(" + value.get(0).getR1() + "," + value.get(0).getG1() + ","
                  + value.get(0).getB1() + ")\" " + "visibility=\"visible\" >\n");
          for (int i = 0; i < value.size(); i++) {
            int begin = value.get(i).getT1() * 1000 / speed;
            int duration =
                    ((value.get(i).getT2() * 1000 / speed) - (value.get(i).getT1() * 1000 / speed));
            sb.append("<animate attributeType=\"xml\" begin=\""
                    + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"").append(" " +
                    "attributeName=\"x\" from=\"" + value.get(i).getX1() + "\" to=\""
                    + value.get(i).getX2() + "\" fill=\"freeze\" />\n");
            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"").append(" " +
                    "attributeName=\"y\" from=\"" + value.get(i).getY1()
                    + "\" to=\"" + value.get(i).getY2() + "\" fill=\"freeze\" />\n");
            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"").append(" " +
                    "attributeName=\"width\" from=\"" + value.get(i).getW1() + "\" to=\""
                    + value.get(i).getW2() + "\" fill=\"freeze\" />\n");
            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"").append(" " +
                    "attributeName=\"height\" from=\"" + value.get(i).getH1() + "\" to=\""
                    + value.get(i).getH2() + "\" fill=\"freeze\" />\n");
            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"").append(" " +
                    "attributeName=\"fill\" from=\"rgb(" + value.get(i).getR1() + "," +
                    value.get(i).getG1() + "," + value.get(i).getB1() + ")\" to=\"rgb(" +
                    value.get(i).getR2() + "," + value.get(i).getG2() + "," + value.get(i).getB2() +
                    ")\"" + " fill=\"freeze\" />\n");
          }
          sb.append("</rect>\n");
        }


      } else if (key.getType().equals("ellipse")) {
        if (value.size() == 0) {
          throw new IllegalArgumentException("This shape has no motions");
        } else {
          sb.append("<ellipse id=\"" + key.getName() + "\"");
          sb.append(" cx=\"" + value.get(0).getX1() + "\" cy=\"" + value.get(0).getY1() + "\" " +
                  "rx=\"" + value.get(0).getW1() +
                  "\" " + "ry=\"" + value.get(0).getH1() + "\" " + "fill=\"rgb" +
                  "(" + value.get(0).getR1() + "," + value.get(0).getG1() + ","
                  + value.get(0).getB1() +
                  ")\" " +
                  "visibility=\"visible\" >\n");

          for (int i = 0; i < value.size(); i++) {
            int begin = value.get(i).getT1() * 1000 / speed;
            int duration =
                    ((value.get(i).getT2() * 1000 / speed) - (value.get(i).getT1() * 1000 / speed));

            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"");
            sb.append(" attributeName=\"cx\" from=\"" + value.get(i).getX1() + "\" to=\""
                    + value.get(i).getX2() + "\" fill=\"remove\" />\n");
            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"");
            sb.append(" attributeName=\"cy\" from=\"" + value.get(i).getY1() + "\" to=\""
                    + value.get(i).getY2() + "\" fill=\"remove\" />\n");
            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"");
            sb.append(" attributeName=\"rx\" from=\"" + value.get(i).getW1() + "\" to=\""
                    + value.get(i).getW2() + "\" fill=\"remove\" />\n");
            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"");
            sb.append(" attributeName=\"ry\" from=\"" + value.get(i).getH1() + "\" to=\""
                    + value.get(i).getH2() + "\" fill=\"remove\" />\n");
            sb.append("<animate attributeType=\"xml\" begin=\"" + begin +
                    "ms" + "\" " + "dur=\"" + duration + "ms\"");
            sb.append(" attributeName=\"fill\" from=\"rgb(" + value.get(i).getR1()
                    + "," + value.get(i).getG1() + "," + value.get(i).getB1() + ")\" to=\"rgb("
                    + value.get(i).getR2() + "," + value.get(i).getG2() + ","
                    + value.get(i).getB2() + ")\"" + " fill=\"remove\" />\n");
          }
          sb.append("</ellipse>\n");
        }
      }
    }
    sb.append("</svg>");
    return sb.toString();

  }


  @Override
  public void startAnimation() {
    throw new UnsupportedOperationException("Can't start an SVG view");
  }


  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }


  @Override
  public void startVisualView() {
    throw new UnsupportedOperationException("Can't start an SVG view");
  }
}
