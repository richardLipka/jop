package cz.zcu.kiv.jop.cf.interval;

import java.security.InvalidParameterException;

public class Interval {
    private double min;
    private double max;



    public Interval() {
        min = Long.MIN_VALUE;
        max = Long.MAX_VALUE;
    }

    /**
     * Initializes a closed interval [min, max].
     *
     * @param  min the smaller endpoint
     * @param  max the larger endpoint
     * @throws IllegalArgumentException if the min endpoint is greater than the max endpoint
     * @throws IllegalArgumentException if either {@code min} or {@code max}
     *         is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY} or
     *         {@code Double.NEGATIVE_INFINITY}

     */
    public Interval(double min, double max) {
        if (Double.isInfinite(min) || Double.isInfinite(max))
            throw new IllegalArgumentException("Endpoints must be finite");
        if (Double.isNaN(min) || Double.isNaN(max))
            throw new IllegalArgumentException("Endpoints cannot be NaN");

        // convert -0.0 to +0.0
        if (min == 0.0) min = 0.0;
        if (max == 0.0) max = 0.0;

        if (min <= max) {
            this.min = min;
            this.max = max;
        }
        else throw new IllegalArgumentException("Illegal interval");
    }

    /**
     * Returns the min endpoint of this interval.
     *
     * @return the min endpoint of this interval
     */
    public double getMin() {
        return min;
    }

    /**
     * Returns the max endpoint of this interval.
     *
     * @return the max endpoint of this interval
     */
    public double getMax() {
        return max;
    }


    public void setMin(double min){
        if(min <= max){
            this.min = min;
        }else{
            throw new InvalidParameterException("Max ("+max+") must be greater than the min ("+min+")");
        }
    }

    public void setMax(double max){
        if(min <= max){
            this.max = max;
        }else{
            throw new InvalidParameterException("Max ("+max+") must be greater than the min ("+min+")");
        }
    }

    /**
     * Returns true if this interval intersects the specified interval.
     *
     * @param  that the other interval
     * @return {@code true} if this interval intersects the argument interval;
     *         {@code false} otherwise
     */
    public boolean intersects(Interval that) {
        if (this.max < that.min) return false;
        if (that.max < this.min) return false;
        return true;
    }

    /**
     * Returns true if this interval contains the specified value.
     *
     * @param x the value
     * @return {@code true} if this interval contains the value {@code x};
     *         {@code false} otherwise
     */
    public boolean contains(double x) {
        return (min <= x) && (x <= max);
    }

    /**
     * Returns the length of this interval.
     *
     * @return the length of this interval (max - min)
     */
    public double length() {
        return max - min;
    }

    /**
     * Returns a string representation of this interval.
     *
     * @return a string representation of this interval in the form [min, max]
     */
    public String toString() {
        return "[" + min + ", " + max + "]";
    }

    /**
     * Compares this transaction to the specified object.
     *
     * @param  other the other interval
     * @return {@code true} if this interval equals the other interval;
     *         {@code false} otherwise
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Interval that = (Interval) other;
        return this.min == that.min && this.max == that.max;
    }

    /**
     * Returns an integer hash code for this interval.
     *
     * @return an integer hash code for this interval
     */
    public int hashCode() {
        int hash1 = ((Double) min).hashCode();
        int hash2 = ((Double) max).hashCode();
        return 31*hash1 + hash2;
    }
}
