package cz.zcu.kiv.jop.example.bmi;

/**
 * The exception which may be thrown in case that calculation of BMI fails.
 *
 * @author Mr.FrAnTA
 */
public class BmiException extends Exception {

  /**
   * Determines if a de-serialized file is compatible with this class.
   * <p>
   * Maintainers must change this value if and only if the new version of this class is not
   * compatible with old versions. See Oracle docs for <a
   * href="http://docs.oracle.com/javase/1.5.0/docs/guide/serialization/">details</a>.
   * <p>
   * Not necessary to include in first version of the class, but included here as a reminder of its
   * importance.
   */
  private static final long serialVersionUID = 20160511;

  /**
   * Constructs a new BMI calculation exception with <code>null</code> as its detail message. The
   * cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
   */
  public BmiException() {
    super();
  }

  /**
   * Constructs a new BMI calculation exception with the specified detail message. The cause is not
   * initialized, and may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *          {@link #getMessage()} method.
   */
  public BmiException(String message) {
    super(message);
  }

  /**
   * Constructs a new BMI calculation exception with the specified cause and <code>null</code> as
   * its detail message.
   *
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *          (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *          unknown.)
   */
  public BmiException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new BMI calculation exception with the specified detail message and cause.
   * <p>
   * Note that the detail message associated with <code>cause</code> is <i>not</i> automatically
   * incorporated in this exception's detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the
   *          {@link #getMessage()} method).
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *          (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *          unknown.)
   */
  public BmiException(String message, Throwable cause) {
    super(message, cause);
  }

}
