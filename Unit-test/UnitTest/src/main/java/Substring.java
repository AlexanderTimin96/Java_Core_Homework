
import java.util.ArrayList;
import java.util.List;

public class Substring {
    public static List<Integer> searchSubstringIndex(String source, String pattern) {
        if (pattern.length() > source.length()) {
            return null;
        }
        List<Integer> found = new ArrayList<>();

        int patternHash = sumHashPattern(pattern);
        int asterikPosition = positionAsterikInPattern(pattern);
        int windowHash = 0;

        for (int start = 0; start < source.length() - pattern.length() + 1; start++) {
            if (start == 0) {
                windowHash = partOfSumHashInSource(source, pattern.length());
                windowHash -= source.charAt(asterikPosition);
            } else {
                windowHash -= source.charAt(start - 1);
                windowHash += source.charAt(start + pattern.length() - 1);
                windowHash -= source.charAt(start + asterikPosition);
            }
            if (windowHash == patternHash) {
                for (int i = 0; i < pattern.length(); i++) {
                    if (pattern.charAt(i) != '*' && source.charAt(start + i) != pattern.charAt(i)) {
                        continue;
                    }
                    found.add(start);
                    break;
                }
            }
            windowHash += source.charAt(start + asterikPosition);
        }
        return found;
    }

    private static int sumHashPattern(String pattern) {
        int sum = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '*') {
                sum += pattern.charAt(i);
            }
        }
        return sum;
    }

   public static int positionAsterikInPattern(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '*') {
                return i;
            }
        }
        return 0;
    }

    public static int partOfSumHashInSource(String source, int lenght) {
        int sum = 0;
        for (int i = 0; i < lenght; i++) {
            sum += source.charAt(i);
        }
        return sum;
    }

    public static List<String> printSubstring(List<Integer> found, String sourse, String pattern) {
        List<String> listSubstring = new ArrayList<>();
        for (int index : found) {
            String result = sourse.substring(index, index + pattern.length());
            listSubstring.add(result);
        }
        return listSubstring;
    }
}

