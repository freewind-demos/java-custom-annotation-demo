package demo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class CachedResult {
    public long timestamp;
    public String result;

    public CachedResult(long timestamp, String result) {
        this.timestamp = timestamp;
        this.result = result;
    }
}


public class Hello {

    private static Map<String, CachedResult> cache = new HashMap<>();

    private static String callCalculateSlowValue(String name) throws Exception {
        Calculator calculator = new Calculator();
        Method calculateSlowValueMethod = Calculator.class.getDeclaredMethod("calculateSlowValue", String.class);
        Cache annotation = calculateSlowValueMethod.getDeclaredAnnotation(Cache.class);
        if (annotation != null) {
            long now = System.currentTimeMillis();
            {
                // clear expired value
                CachedResult cachedValue = cache.get(name);
                if (cachedValue != null) {
                    if (now - cachedValue.timestamp > annotation.expiryTime()) {
                        cache.put(name, null);
                    }
                }
            }

            {
                // calculate if no cache
                CachedResult cachedValue = cache.get(name);
                if (cachedValue == null) {
                    String result = (String) calculateSlowValueMethod.invoke(calculator, name);
                    cache.put(name, new CachedResult(System.currentTimeMillis(), result));
                }
            }

            return cache.get(name).result;
        } else {
            return (String) calculateSlowValueMethod.invoke(calculator, name);
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println(callCalculateSlowValue("Freewind"));
        System.out.println(callCalculateSlowValue("Freewind"));
        System.out.println("Sleep 2s");
        Thread.sleep(2000);
        System.out.println(callCalculateSlowValue("Freewind"));
    }

}
