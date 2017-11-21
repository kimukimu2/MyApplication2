package com.example.kimuratomo2.myapplication2;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Created by kimuratomo2 on 2017/10/18.
 */

public class filetest {
    private PrintWriter writer;
    private File file;
    private SimpleDateFormat sdf;
    private  Calendar c;
    filetest(){
        c = Calendar.getInstance();
        sdf=new SimpleDateFormat("yyyy年MM月dd日HH時間mm分ss秒");
        String filePath =
                Environment.getExternalStorageDirectory().getPath()
                        + "/"+sdf.format(c.getTime());
        String tansi = new String(".txt");
        file=new File(filePath+tansi);

        try{
            FileOutputStream out = new FileOutputStream(file,true);
            writer =
                    new PrintWriter(new OutputStreamWriter(out,"UTF-8"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void writeFile(int number){
            if (checkBeforeWritefile(file)) {
                c = Calendar.getInstance();
                sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss.SSS");
                String re=new String("{'properties'" +
                        "'time':"+"'"+sdf.format(c.getTime())+"',"+
                        "'data':"+number+"}}");
                writer.append(re+"\n");
                writer.flush();
            }else{

            }
    }
    public void fileclose() {
            writer.close();
    }

    private static boolean checkBeforeWritefile(File file){
        if (file.exists()){
            if (file.isFile() && file.canWrite()){
                return true;
            }
        }

        return false;
    }

}
