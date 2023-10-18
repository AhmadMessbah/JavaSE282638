package mft.model.bl;

import mft.model.da.LogDa;
import mft.model.entity.Log;

import java.time.LocalDateTime;

public class Logger {
    public static void info(String action, String data,int userId) throws Exception {
//        try(LogDa logDa = new LogDa()){
//            logDa.save(log);
            System.out.printf("%s [%s]:[%s] - By userId %s %n", LocalDateTime.now(),action,data,userId);
//        }
    }
    public static void error(String action, String data,int userId)throws Exception{
//        try(LogDa logDa = new LogDa()){
//            logDa.save(log);
            System.err.printf("%s [%s]:[%s] - By userId %s %n", LocalDateTime.now(),action,data,userId);
//        }
    }
}
