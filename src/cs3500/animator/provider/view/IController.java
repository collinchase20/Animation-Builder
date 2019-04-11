package controller;

import model.AnimatorModel;

/**
 * Interface for the controller.
 */
public interface IController extends ICallbacks {

  /**
   * Runs the animation using the selected view.
   */
  void runAll();

  /**
   * Gets the model that the controller is using.
   * @return the model
   */
  AnimatorModel getModel();
}
