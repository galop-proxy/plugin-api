package io.github.galop_proxy.plugin_api.http;

import java.io.Serializable;

/**
 * Represents an HTTP protocol version.
 * HTTP uses a "&lt;major&gt;.&lt;minor&gt;" numbering scheme to indicate versions of the protocol.
 * The major version number and the minor version number each consist of exactly one digit.
 */
public final class Version implements Comparable<Version>, Serializable {

    /**
     * HTTP protocol version 0.9
     */
    public static final Version HTTP_0_9 = new Version(0, 9);

    /**
     * HTTP protocol version 1.0
     */
    public static final Version HTTP_1_0 = new Version(1, 0);

    /**
     * HTTP protocol version 1.1
     */
    public static final Version HTTP_1_1 = new Version(1, 1);

    /**
     * HTTP protocol version 2.0
     */
    public static final Version HTTP_2_0 = new Version(2, 0);

    /**
     * The serial version id of this class according to {@link Serializable}.
     * This version is not related to the HTTP protocol version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The major version number of this HTTP protocol version.
     */
    private final int major;

    /**
     * The minor version number of this HTTP protocol version.
     */
    private final int minor;

    /**
     * Creates an HTTP protocol version consisting of a major and a minor version number.
     * Both numbers must be greater than or equal to 0 and less than or equal to 9.
     *
     * @param major The major version number of the HTTP protocol version.
     * @param minor The minor version number of the HTTP protocol version.
     */
    public Version(final int major, final int minor) {
        this.major = checkVersionNumber(major, "major");
        this.minor = checkVersionNumber(minor, "minor");
    }

    private int checkVersionNumber(final int value, final String name) {

        if (0 <= value && value <= 9) {
            return value;
        } else {
            throw new IllegalArgumentException(name + " version must be in range [0..9].");
        }

    }

    /**
     * Returns the major version number of this HTTP protocol version.
     *
     * @return The major version number of this HTTP protocol version.
     */
    public int getMajor() {
        return major;
    }

    /**
     * Returns the minor version number of this HTTP protocol version.
     *
     * @return The minor version number of this HTTP protocol version.
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Checks if this version is greater than another version.
     *
     * <p>A version that has a higher major version number than another is greater.
     * If two versions have the same major version number,
     * the version with a higher minor version number is greater.
     * If both versions have the same major and minor version numbers,
     * neither version is greater than the other version.
     *
     * @param other The version to compare.
     * @return True, if this version is greater than the passed version, otherwise false.
     */
    public boolean isGreaterThan(final Version other) {

        checkOtherVersion(other);

        if (major == other.major) {
            return minor > other.minor;
        } else {
            return major > other.major;
        }

    }

    /**
     * Checks if this version is lower than another version.
     *
     * <p>A version that has a lower major version number than another is lower.
     * If two versions have the same major version number,
     * the version with a lower minor version number is lower.
     * If both versions have the same major and minor version numbers,
     * neither version is lower than the other version.
     *
     * @param other The version to compare.
     * @return True, if this version is lower than the passed version, otherwise false.
     */
    public boolean isLowerThan(final Version other) {

        checkOtherVersion(other);

        if (major == other.major) {
            return minor < other.minor;
        } else {
            return major < other.major;
        }

    }

    private void checkOtherVersion(final Version other) {

        if (other == null) {
            throw new NullPointerException("other must not be null.");
        }

    }

    @Override
    public int compareTo(final Version other) {

        if (isGreaterThan(other)) {
            return 1;
        } else if (isLowerThan(other)) {
            return -1;
        } else {
            return 0;
        }

    }

    @Override
    public boolean equals(final Object other) {

        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        final Version that = (Version) other;
        return major == that.major && minor == that.minor;

    }

    @Override
    public int hashCode() {
        final int hashMultiplier = 59;
        int result = 17;
        result = result * hashMultiplier + major;
        result = result * hashMultiplier + minor;
        return result;
    }

    @Override
    public String toString() {
        return major + "." + minor;
    }

}
