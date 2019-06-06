import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;

public class FileFinder implements Runnable{
    String name;
    String find;

    public FileFinder(String find, String name){
        this.find = find;
        this.name = name;
    }

    @Override
    public void run() {
        String string;
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new BufferedReader(new FileReader(name)));
            while (true){
                string = lineNumberReader.readLine();
                if (string == null) break;
                if ((string.indexOf(find)) != -1){
                    System.out.println(name);
                    break;
                }
            }
            lineNumberReader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
