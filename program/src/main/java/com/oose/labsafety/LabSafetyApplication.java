package com.oose.labsafety;

import com.oose.labsafety.bootstrap.ApplicationBootstrap;

public final class LabSafetyApplication {

    private LabSafetyApplication() {
    }

    public static void main(String[] args) {
        new ApplicationBootstrap().run();
    }
}