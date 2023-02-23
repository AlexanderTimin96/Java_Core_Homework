import java.util.List;

public class Main {
    public static void main(String[] args) {
        String source = "Alibaba or Alibubab? I do not know!";
        String pattern = "b*b";
        System.out.println("Ищем подстроку: ~" + pattern + "~ в строке: ~" + source + "~");

        List<Integer> listOfIndexSubstring = Substring.searchSubstringIndex(source, pattern);
        if (listOfIndexSubstring == null || listOfIndexSubstring.isEmpty()) {
            System.out.println("Такой подстроки нет!");
        } else {
            System.out.println("Подстроки начинаются с индексов: " + listOfIndexSubstring);
            System.out.println("Найденные подстроки: " + Substring.printSubstring(listOfIndexSubstring, source, pattern));
        }
    }
}