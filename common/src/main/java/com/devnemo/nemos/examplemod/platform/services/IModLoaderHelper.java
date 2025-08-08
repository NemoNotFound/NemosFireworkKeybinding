package com.devnemo.nemos.examplemod.platform.services;

public interface IModLoaderHelper {

    String getModLoaderName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();
}