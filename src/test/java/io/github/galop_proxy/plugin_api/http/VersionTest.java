package io.github.galop_proxy.plugin_api.http;

import org.junit.Test;

import static io.github.galop_proxy.plugin_api.http.Version.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the class {@link Version}.
 */
public class VersionTest {

    private final Version other = new Version(1, 1);

    // Getter:

    @Test
    public void getMajor_returnsGivenMajorVersionNumber() {
        assertEquals(1, HTTP_1_0.getMajor());
    }

    @Test
    public void getMinor_returnsGivenMinorVersionNumber() {
        assertEquals(9, HTTP_0_9.getMinor());
    }

    // isGreaterThan:

    @Test
    public void isGreaterThan_withLowerMajorNumber_returnsTrue() {
        assertTrue(HTTP_2_0.isGreaterThan(HTTP_1_0));
    }

    @Test
    public void isGreaterThan_withLowerMajorAndHigherMinorNumber_returnsTrue() {
        assertTrue(HTTP_2_0.isGreaterThan(HTTP_1_1));
    }

    @Test
    public void isGreater_withSameMajorAndLowerMinorNumber_returnsTrue() {
        assertTrue(HTTP_1_1.isGreaterThan(HTTP_1_0));
    }

    @Test
    public void isGreaterThan_withHigherMajorNumber_returnsFalse() {
        assertFalse(HTTP_1_0.isGreaterThan(HTTP_2_0));
    }

    @Test
    public void isGreaterThan_withSameMajorAndHigherMinorNumber_returnsFalse() {
        assertFalse(HTTP_1_0.isGreaterThan(HTTP_1_1));
    }

    @Test
    public void isGreaterThan_withSameMajorAndSameMinorNumber_returnsFalse() {
        assertFalse(HTTP_1_1.isGreaterThan(HTTP_1_1));
    }

    // isLowerThan:

    @Test
    public void isLowerThan_withHigherMajorNumber_returnsTrue() {
        assertTrue(HTTP_1_0.isLowerThan(HTTP_2_0));
    }

    @Test
    public void isLowerThan_withSameMajorAndHigherMinorNumber_returnsTrue() {
        assertTrue(HTTP_1_0.isLowerThan(HTTP_1_1));
    }

    @Test
    public void isLowerThan_withHigherMajorNumberAndLowerMinorNumber_returnsTrue() {
        assertTrue(HTTP_1_1.isLowerThan(HTTP_2_0));
    }

    @Test
    public void isLowerThan_withLowerMajorNumber_returnsFalse() {
        assertFalse(HTTP_2_0.isLowerThan(HTTP_1_0));
    }

    @Test
    public void isLowerThan_withLowerMajorNumberAndHigherMinorNumber_returnsFalse() {
        assertFalse(HTTP_2_0.isLowerThan(HTTP_1_1));
    }

    @Test
    public void isLowerThan_withSameMajorAndLowerMinorNumber_returnsFalse() {
        assertFalse(HTTP_1_1.isLowerThan(HTTP_1_0));
    }

    @Test
    public void isLowerThan_withSameMajorAndSameMinorNumber_returnsFalse() {
        assertFalse(HTTP_1_1.isLowerThan(HTTP_1_1));
    }

    // compareTo:

    @Test
    public void compareTo_withSameMajorAndMinorNumbers_returnsZero() {
        assertEquals(0, HTTP_1_1.compareTo(other));
        assertTrue(HTTP_1_1.equals(other));
    }

    @Test
    public void compareTo_withLowerVersionNumber_returnsPositiveValue() {
        assertTrue(HTTP_1_1.compareTo(HTTP_1_0) > 0);
    }

    @Test
    public void compareTo_withHigherVersionNumber_returnsNegativeValue() {
        assertTrue(HTTP_1_1.compareTo(HTTP_2_0) < 0);
    }

    // equals:

    @Test
    public void equals_withSameMajorAndMinorNumbers_returnsTrue() {
        assertTrue(HTTP_1_1.equals(other));
    }

    @Test
    public void equals_withDifferentMajorNumbers_returnsFalse() {
        assertFalse(HTTP_1_0.equals(HTTP_2_0));
    }

    @Test
    public void equals_withDifferentMinorNumbers_returnsFalse() {
        assertFalse(HTTP_1_1.equals(HTTP_1_0));
    }

    @Test
    @SuppressWarnings("EqualsWithItself")
    public void equals_withSameObject_returnsTrue() {
        assertTrue(HTTP_1_1.equals(HTTP_1_1));
    }

    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void equals_withNull_returnsFalse() {
        assertFalse(HTTP_1_1.equals(null));
    }

    @Test
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    public void equals_withDifferentClass_returnsFalse() {
        assertFalse(HTTP_1_1.equals("1.1"));
    }

    // hashCode:

    @Test
    public void hashCode_formEqualObjects_returnsSameValue() {
        assertEquals(HTTP_1_1.hashCode(), other.hashCode());
    }

    // toString:

    @Test
    public void toString_returnConcatenatedMajorAndMinorNumbers() {
        assertEquals("0.9", HTTP_0_9.toString());
    }

    // Wrong use of API:

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withNegativeMajorVersionNumber_throwsIllegalArgumentException() {
        new Version(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withNegativeMinorVersionNumber_throwsIllegalArgumentException() {
        new Version(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withTooHighMajorVersionNumber_throwsIllegalArgumentException() {
        new Version(10, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withTooHighMinorVersionNumber_throwsIllegalArgumentException() {
        new Version(0, 10);
    }

    @Test(expected = NullPointerException.class)
    public void isGreaterThan_withoutVersion_throwsNullPointerException() {
        HTTP_1_1.isGreaterThan(null);
    }

    @Test(expected = NullPointerException.class)
    public void isLowerThan_withoutVersion_throwsNullPointerException() {
        HTTP_1_1.isLowerThan(null);
    }

}
