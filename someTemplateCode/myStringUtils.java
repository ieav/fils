public class myStringUtils{

 /**
     * 把字符串 中大写字母 替换成下划线+加小写字母
     * @param str
     * @return
     */
    public static String dealString(String str){
        if (null == str ||  str.length() ==0){
            return "";
        }


        for (int i = 0; i < str.length(); i++) {
            Character temp =str.charAt(i);
            if(Character.isUpperCase(temp)){
              String lowerStr=String.valueOf("_"+temp.toLowerCase(temp));
//                System.out.println(String.valueOf(temp));
//                System.out.println(lowerStr);

              str=str.replace(String.valueOf(temp),lowerStr);
            }

        }
        return str;

    }

}
