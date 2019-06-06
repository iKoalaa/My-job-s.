import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileSearch {

    private static String examplText;
    private static ExecutorService executor;


    static void list(String s){
        File file = new File(s);
        String[] strings = file.list();

        if ( file.list() != null ) {
            for( int i = 0; i < strings.length; i++ )
            {
               final File f1 = new File( s + File.separator + strings[i] );

                if(f1.isFile()) {
                    executor.submit( new FileFinder( examplText, s + File.separator + strings[i] ) );
                }
                else {
                    list(s + File.separator + strings[i]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ведите текст:  ");
        examplText = reader.readLine();
        reader.close();
        executor = Executors.newFixedThreadPool(8);

        for (File file : File.listRoots()){
            System.out.println(file);
            list(file.getPath());
        }
    }

}

