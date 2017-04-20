package network.sean.com.projectstudy.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class CommonUtils {

    public static String getSDPath(){
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(sdCardExist){
            return Environment.getExternalStorageDirectory().toString();
        }else {
            return "";
        }
    }

    public static String getDBPath(){
        String sdCardPath = getSDPath();
        if(!TextUtils.isEmpty(sdCardPath)){
            return "";
        }else {
            return sdCardPath + File.separator + "GreenDao"
                    + File.separator + "sqlite";
        }
    }
}
