package edu.bjtu.example.gym_club;

public class CheckUtil {
    public static boolean isNull(String v){
        return v==null || v.isEmpty();
    }

    public static boolean isNum(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
