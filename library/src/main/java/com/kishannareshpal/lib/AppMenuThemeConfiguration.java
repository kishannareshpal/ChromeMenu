package com.kishannareshpal.lib;

public class AppMenuThemeConfiguration {
    public enum ThemeModes {
        NIGHT,
        LIGHT
    }

    static ThemeModes themeMode;

    public static void setTheme(ThemeModes themeMode){
        AppMenuThemeConfiguration.themeMode = themeMode;
    }

    static ThemeModes getTheme(){
        return themeMode;
    }
}
