package cz.zcu.kiv.jop.annotation.class_provider;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cz.zcu.kiv.jop.annotation.generator.ValueGeneratorAnnotation;

/**
 * Class provider annotation which will select random class which may be used for new instance
 * creation. For selection of random class will be used <em>Categorical distribution</em> - it will
 * be used one of {@link #value() values} based on specified {@link #probabilities() probabilities}.
 * <p>
 * This annotation can be also used as generator annotation which marks property for which will be
 * generated of class (<code>Class&lt;?&gt;</code>) value.
 *
 * @author Mr.FrAnTA
 * @since 1.0.0
 *
 * @see <a href="https://en.wikipedia.org/wiki/Categorical_distribution"> Categorical
 *      distribution</a>
 */
@ClassProviderAnnotation
@ValueGeneratorAnnotation
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomClass {

  /**
   * Required parameter for outcome values of generator where each value has a probability of
   * occurrence in parameter {@link #probabilities() probabilities} on same index like value.
   */
  public Class<?>[] value();

  /**
   * Optional parameter for probabilities of outcome values. If the probabilities won't be set the
   * uniform distribution will be used (all values has same probability).
   * <p>
   * The number of probabilities should be lesser that or equals to number of values. In case of
   * lesser number of probabilities, the value 0.0 will be used for rest of values.
   * <p>
   * Values of probabilities should be greater or equal to 0 and lesser or equal to 1.0. To ensure
   * that the summary of probabilities will be always 1.0, the values are automatically normalized.
   */
  public double[] probabilities() default {};

}
