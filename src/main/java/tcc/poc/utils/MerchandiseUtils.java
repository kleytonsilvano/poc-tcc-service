package tcc.poc.utils;

public class MerchandiseUtils {

    public static String createUniqueCode() {
        return new StringBuilder()
                .append((long) (1000000000000L + Math.random() * 8999999999999l))
                .append("AB").toString();
    }

}
