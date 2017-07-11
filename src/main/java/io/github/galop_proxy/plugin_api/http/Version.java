package io.github.galop_proxy.plugin_api.http;

public interface Version extends Comparable<Version> {

    int getMajor();

    int getMinor();

    boolean isGreaterThan(Version other);

    boolean isLowerThan(Version other);

}
