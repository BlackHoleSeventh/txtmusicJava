import java.io.File;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        //play();
        playM1();
    }


    public static void play(){
       JFrameWindow.createWindow();
    }

    public static void playM1(){

        Main m = new Main();
        URL url = m.getClass().getClassLoader().getResource("text/m1.txt");
        String ur1Str = url.toString().replace("file:/", "");

        String urlStr2 = new File("").getAbsolutePath();
        urlStr2 = urlStr2 + "/tm.txt";

        ReadUtil.sleepTime = 100;

        //ReadUtil.readTxt(ur1Str);
        ReadUtil.readTxt(urlStr2);

    }
}
