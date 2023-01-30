package demo;

public class Calculator {

    @Cache(expiryTime = 1000)
    public String calculateSlowValue(String key) {
        return key + "! (" + System.currentTimeMillis() + ")";
    }
}
