package cs3500.animator.view;

import cs3500.animator.model.motion.AnimatorModel;
import cs3500.animator.model.motion.Motion;
import cs3500.animator.model.shape.Shape;

/**
 * This class represents the textual view of our animation model which is essentially a string
 * respresentation of the Hashmap from the model contains shapes mapped to an array list of
 * motions.
 */
public class TView implements IView {

  private AnimatorModel model;

  /**
   * Constructor for a textual view of an animation.
   *
   * @param model an instance of our model representing an animation.
   */
  public TView(AnimatorModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
  }

  @Override
  public String displayView() {
    StringBuilder sb = new StringBuilder();
    if (model.returnCanvas()[0] == 0 && model.returnCanvas()[1] == 0 &&
            model.returnCanvas()[2] == 0 && model.returnCanvas()[3] == 0) {
      throw new IllegalArgumentException("Must initialize the canvas before displaying a view");
    }

    sb.append(model.sendCanvas()).append('\n');

    for (Shape key : model.sendAnimation().keySet()) {
      sb.append("shape ").append(key.getName()).append(" ").append(key.getType()).append('\n');
      for (Motion m : model.sendAnimation().get(key)) {
        sb.append("motion ").append(key.getName()).append(" ");
        sb.append(m.printMotion()).append('\n');
      }
    }

    return sb.toString();
  }


  @Override
  public void startAnimation() {
    throw new UnsupportedOperationException("Cannot start a text view");
  }


  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("No speed for text view");
  }


  @Override
  public void startVisualView() {
    throw new UnsupportedOperationException("Can't start a text view");
  }

}
