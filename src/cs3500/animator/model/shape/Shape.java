package cs3500.animator.model.shape;

/**
 * Class to represent a shape. A Shape is created with 2 string a name and type and the physical
 * shape will be created graphically through swings implementation of a valid type. For example if
 * the type is rectangle, in the graphic view the program will construct a rect.
 */
public class Shape implements IShape {

  private String name;
  private String type;

  /**
   * Copy Constructor for a shape.
   *
   * @param s a shape to copy
   */
  public Shape(IShape s) {
    this.name = s.getName();
    this.type = s.getType();
  }

  /**
   * Constructor for a shape.
   *
   * @param name the name of a shape
   * @param type the name of a shape
   */

  public Shape(String name, String type) {
    this.name = name;
    this.type = type;
  }


  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getType() {
    return this.type;
  }
}
