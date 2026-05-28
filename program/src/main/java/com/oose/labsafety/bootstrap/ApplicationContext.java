package com.oose.labsafety.bootstrap;

import com.oose.labsafety.common.ConsoleIO;
import com.oose.labsafety.common.FeatureModule;

import java.util.List;

public record ApplicationContext(ConsoleIO consoleIO, List<FeatureModule> modules) {

    public ApplicationContext {
        modules = List.copyOf(modules);
    }
}