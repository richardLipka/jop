package cz.zcu.kiv.jop.populator;

import java.lang.annotation.Annotation;

import cz.zcu.kiv.jop.property.Property;

/**
 * The common interface for all populators of properties which can be used for populating of values
 * into properties. They can be used in combination with another populator,
 * {@link cz.zcu.kiv.jop.generator.ValueGenerator ValueGenerator} or they can contains some custom
 * populating strategy.
 *
 * @author Mr.FrAnTA
 * @since 1.0.0
 *
 * @param <P> Annotation type of parameters for populator of property.
 */
public interface PropertyPopulator<P extends Annotation> {

  /**
   * Returns information whether property populator can {@link #populate} given property.
   *
   * @param property the property to check.
   * @return <code>true</code> if property populator can {@link #populate} given property;
   *         <code>false</code> otherwise.
   */
  public boolean supports(Property<?> property);

  /**
   * Populates given property according to given parameters. Populator can use additional
   * annotations which can be obtained from given property.
   *
   * @param property the property which will be populated.
   * @param owner the instance of property owner.
   * @param params the parameters for populating of given property.
   * @throws PropertyPopulatorException if some error occurs during populating of given property or
   *           if given parameters are not valid.
   */
  public void populate(Property<?> property, Object owner, P params) throws PropertyPopulatorException;

}
