package life.qbic.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import com.vaadin.ui.AbstractField;

/**
 * Math utilities for portals.
 */
public class PortalUtils  {

    private static final Logger LOG = LogManager.getLogger(PortalUtils.class);

    /**
     * Computes the sum of the values contained in the passed Vaadin fields.
     * 
     * @param a the first Vaadin field.
     * @param b the second Vaadin field.
     * 
     * @return {@code a + b}.
     * 
     * @throws NullPointerException if either {@code a} or {@code b} are {@code null}.  
     * @throws IllegalArgumentException if the value contained in either field cannot be converted to a {@code double}.
     */
    public double sum(final AbstractField<String> a, final AbstractField<String> b) {
        Validate.notNull(a, "First field cannot be null");
        Validate.notNull(b, "Second field cannot be null");

        Validate.isTrue(NumberUtils.isCreatable(a.getValue()), "Invalid number in first field: {}", a.getValue());
        Validate.isTrue(NumberUtils.isCreatable(b.getValue()), "Invalid number in second field: {}", b.getValue());

        final Double firstValue = NumberUtils.createDouble(a.getValue());
        final Double secondValue = NumberUtils.createDouble(b.getValue());

        LOG.info("Computing {} + {}", a, b);
        return firstValue + secondValue;
    }
}