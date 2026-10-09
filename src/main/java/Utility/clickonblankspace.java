package Utility;

import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.AppiumDriver;

public class clickonblankspace {

    public static void clickAtPosition(AppiumDriver driver, int x, int y) {

        Map<String, Object> args = new HashMap<>();

        args.put("x", x);
        args.put("y", y);

        driver.executeScript("mobile: clickGesture", args);
    }
}
