import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class radomMusic {

    static ArrayList list = new ArrayList();
    static char cl[] = {'1','2','3','4','5','6','7','#',' ','(',')','[',']'};

    static int count = 1000;

    static boolean needNum = false;
    static boolean needJingAndNum = false;


    static boolean canXiaoKuoHao = true;
    static boolean canZhongKuoHao = true;

    static boolean canZuoXiaoKuoHao = true;
    static boolean canZuoZhongKuoHao = true;

    static boolean canYouXiaoKuoHao = false;
    static boolean canYouZhongKuoHao = false;


    public static void main(String argv[]){
        System.out.println("启动");
        try{
            count = Integer.parseInt(argv[0]);
        }
        catch (Exception e){
            count = 1000;
        }

        initList();
        FileWriter fw = getFileWriter();
        for(int i=0; i< count; i++) {
            radomWrite(fw);
        }
        closeFR(fw);
        System.out.println("");
        System.out.println("结束");
    }

    public static void  initList(){
        for(char c: cl){
            list.add(c);
        }
    }
    public static void  radomWrite(FileWriter fw){
        int length = list.size();
        int target = (int)(Math.random()*length);
        try {
            String str = writeRule(String.valueOf(list.get(target)));
            System.out.print(str);

            writeToFile(fw, str);

        }catch (Exception e){
            System.out.println("出现错误，可能下标越界！");
        }

    }

    public static String reRadom(int begin, int end){
        String str = null;
        int i = (int) (Math.random()*(end-begin) + begin);
        try{
            str = String.valueOf(list.get(i));
        }catch (Exception e){
            System.out.println("重随机错误，可能越界！");
        }
        return str;
    }

    /**
     * 要求：
     * #后一位只能加数字；
     * (后不能出现[和]和(
     * [后不能出现(和)和[
     * )后不能出现)和]
     * ]后不能出现]和)
     *
     * 必须先出现一次(才能出现)
     * 必须先出现一次[才能出现]
     *
     * (后需要#或数字
     * [后需要#或数字
     *
     * //list中，0-6为1-7
     * 7为#
     * 8为空格
     * 9为(
     * 10为)
     * 11为[
     * 12为]
     *
     * @param input
     * @return
     */
    public static String writeRule(String input){
        String output = input;

        if(needNum){
            needNum = false;
            return reRadom(0,6);
        }
        if(needJingAndNum){
            needJingAndNum = false;
            return reRadom(0,7);
        }

        if("#".equals(input)){
            needNum = true;
        }else if("(".equals(input)){
            if(!canZuoXiaoKuoHao){
                return " ";
            }
            if(!canXiaoKuoHao){
                return " ";
            }
            canZhongKuoHao = false;
            canYouXiaoKuoHao = true;
            needJingAndNum = true;
            canZuoXiaoKuoHao = false;

        }else if(")".equals(input)){
            if(!canXiaoKuoHao){
                return " ";
            }
            if(!canYouXiaoKuoHao){
                return " ";
            }
            canZhongKuoHao = true;
            canYouXiaoKuoHao = false;
            canZuoXiaoKuoHao = true;
            canZuoZhongKuoHao = true;

        }else if("[".equals(input)){
            if(!canZuoZhongKuoHao){
                return " ";
            }
            if(!canZhongKuoHao){
                return " ";
            }
            canXiaoKuoHao = false;
            canYouZhongKuoHao = true;
            needJingAndNum = true;
            canZuoZhongKuoHao = false;
        }else if("]".equals(input)){
            if(!canZhongKuoHao){
                return " ";
            }
            if(!canYouZhongKuoHao){
                return " ";
            }
            canXiaoKuoHao = true;
            canYouZhongKuoHao = false;
            canZuoXiaoKuoHao = true;
            canZuoZhongKuoHao = true;

        }else if(" ".equals(input)){

        }
        else{

        }

        return output;
    }

    public static FileWriter getFileWriter(){

        //URL path = radomMusic.class.getClassLoader().getResource("");
        String url = new File("").getAbsolutePath();
        url = url + "/tm.txt";
        System.out.println(url);
        File f = new File(url);

        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e.toString());

            }
        }

        try {
            FileWriter fw = new FileWriter(url);
            return fw;
        } catch (IOException e) {
            System.out.println(e.toString());

        }
        return null;
    }

    public static void writeToFile(FileWriter fw, String str){
        try {
            fw.write(str);
        } catch (IOException e) {
            System.out.println(e.toString());

        }
    }

    public static void closeFR(FileWriter fw){
        try {
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());

        }
    }
}