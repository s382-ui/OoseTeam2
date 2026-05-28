package com.oose.labsafety.common;

public interface FeatureModule {

    String code();

    String title();

    String description();

    void open(ConsoleIO io);
}