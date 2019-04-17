package cs3500.animator.provider.view;

import cs3500.animator.model.motion.AnimatorModel;

public class AddModelToView extends AbstractViewClass implements IView {

  VisualView v;
  AbstractViewClass abs;

  public AddModelToView(AnimatorModel a, int speed) {
    super.m = a;
  }

}
