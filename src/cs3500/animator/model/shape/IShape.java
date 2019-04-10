package cs3500.animator.model.shape;


/**
 * Interface to hold methods operated on shapes. A Shape is created with 2 string a name and type
 * and the physical shape will be created graphically through swings implementation of a valid type.
 * For example if the type is rectangle, in the graphic view the program will construct a rect.
 */
public interface IShape {

  /**
   * Getter method for name of a shape.
   */
  String getName();

  /**
   * Getter method for type of a shape.
   */
  String getType();

}
