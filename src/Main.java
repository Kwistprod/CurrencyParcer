import org.jsoup.*;

import java.lang.*;
import java.io.IOException;
import java.util.Locale;

public class Main {

    public static void main(String[] args)throws Exception{
        Locale locale = new Locale("en");
        try {
            initFrame b = new initFrame();
            //b.pack();
            //b.setDefaultCloseOperation(b.EXIT_ON_CLOSE);

        }catch(IOException e){
            System.out.println(e.toString());
        }
    }
}
