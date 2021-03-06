package cz.zcu.kiv.jop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation may be used case that a generated <em>Object</em> has multiple constructors to
 * prevent ambiguity which constructor will be used for <em>Object</em> construction. Also allows to
 * annotate static method which may be used for construction of some <em>Object</em>.
 *
 * @author Mr.FrAnTA
 * @since 1.0.0
 */
@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Constructor {

}
