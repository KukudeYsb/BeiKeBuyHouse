package buymyhome.com.util;

/**
 * @author 杨世博
 */
public class SplitString {

    /**
     * 获取第一个数字
     * @param str
     * @return
     */
    public static Double splitString1(String str){
        str = str.trim();
        Integer ans = 0;
        String a = "";
        if(str != null && !"".equals(str)){
            int flag = 0;
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) >= 48 && str.charAt(i) <= 57){
                    a += str.charAt(i);
                    flag=1;
                }else if(flag==1) {
                    break;
                }
            }
        }

        if(a!=""){
            return Double.valueOf(a);
        }else {
            return 0.00;
        }
    }

    /**
     * 获取第二个数字
     * @param str
     * @return
     */
    public static Double splitString2(String str){
        str = str.trim();
        Integer ans = 0;
        String a = "";
        if(str != null && !"".equals(str)){
            int flag = 0;
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) >= 48 && str.charAt(i) <= 57){
                    if(flag==2){
                        a += str.charAt(i);
                    }else {
                        flag=1;
                    }
                }else if(flag==1) {
                    flag=2;
                }else if(flag==3){
                    break;
                }
            }
        }

        if(a!=""){
            return Double.valueOf(a);
        }else {
            return 99999.99;
        }
    }
}
