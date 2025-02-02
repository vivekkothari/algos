package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class TinyUrlCodec {

  private final Map<String, Long> urlMapping = new HashMap<>();
  private final Map<Long, String> idMapping = new HashMap<>();
  private final Random random = new Random();

  private static final String BASE62 =
      "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  static long base62Decode(String url) {
    long decode = 0;
    for (var i = 0; i < url.length(); i++) {
      decode = decode * 62 + BASE62.indexOf(String.valueOf(url.charAt(i)));
    }
    return decode;
  }

  static String base62Encode(long value) {
    var sb = new StringBuilder();
    while (value != 0) {
      sb.append(BASE62.charAt((int) (value % 62)));
      value /= 62;
    }
    while (sb.length() < 6) {
      sb.append(0);
    }
    return sb.reverse().toString();
  }

  // Encodes a URL to a shortened URL.
  public String encode(String longUrl) {
    var l = urlMapping.computeIfAbsent(longUrl, u -> random.nextLong(Long.MAX_VALUE));
    idMapping.put(l, longUrl);
    return "https://tinyurl.com/" + base62Encode(l);
  }

  // Decodes a shortened URL to its original URL.
  public String decode(String shortUrl) {
    var encode = shortUrl.substring(shortUrl.lastIndexOf('/') + 1);
    var l = base62Decode(encode);
    return idMapping.get(l);
  }

  public static void main(String[] args) {
    var codec = new TinyUrlCodec();
    var shortUrl = codec.encode("https://leetcode.com/problems/design-tinyurl");
    System.out.println(shortUrl);
    var decode = codec.decode(shortUrl);
    System.out.println(decode);
  }
}
