import java.util.HashMap;
import java.util.Map;

public class Codec {

    private Map<String, String> map = new HashMap<>();
    private int id = 0;
    private static final String PREFIX = "http://tinyurl.com/";

    public String encode(String longUrl) {
        String shortUrl = PREFIX + id++;
        map.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}
