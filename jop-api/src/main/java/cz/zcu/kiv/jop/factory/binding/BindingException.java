package cz.zcu.kiv.jop.factory.binding;

import cz.zcu.kiv.jop.JopRuntimeException;

/**
 * This exception can occur during binding in case of some error - for example if binding already
 * exists.
 *
 * @author Mr.FrAnTA
 * @since 1.0.0
 */
public class BindingException extends JopRuntimeException {

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
  private static final long serialVersionUID = 20160207L;

  /**
   * Constructs a new binding exception with <code>null</code> as its detail message. The cause is
   * not initialized, and may subsequently be initialized by a call to {@link #initCause}.
   */
  public BindingException() {
    super();
  }

  /**
   * Constructs a new binding exception with the specified detail message. The cause is not
   * initialized, and may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *          {@link #getMessage()} method.
   */
  public BindingException(String message) {
    super(message);
  }

  /**
   * Constructs a new binding exception with the specified cause and <code>null</code> as its detail
   * message. This constructor is different to parent
   * {@link RuntimeException#RuntimeException(Throwable) constructor} which sets detail message as
   * message from cause. This constructor brings possibility to use {@link #getDefaultMessage}
   * instead.
   *
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *          (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *          unknown.)
   */
  public BindingException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new binding exception with the specified detail message and cause.
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
  public BindingException(String message, Throwable cause) {
    super(message, cause);
  }

}
