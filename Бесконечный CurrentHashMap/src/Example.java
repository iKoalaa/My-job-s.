import java.util.concurrent.ConcurrentHashMap;

public class Example {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        new Write(map, "Writer-1", 1).start();
        new Write(map, "Writer-2", 2).start();

        for (int i = 1; i <= 5; i++) {
            new Read(map, "Reader-" + i).start();
        }
    }
}

