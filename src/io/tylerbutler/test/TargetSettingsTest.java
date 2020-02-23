package io.tylerbutler.test;

public class TargetSettingsTest {

    public static String targetSettingsTest(String texFieldInput) {

        // TODO add a more robust error handling method
        if (texFieldInput.contains(".")) {
            String out = "{!} Target "+texFieldInput +" added to scope" ;
            return out;
        } else {
            return "{x} Error, no target specefied";
        }
    }
}
