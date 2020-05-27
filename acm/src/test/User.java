package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Cloneable{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public synchronized void sayName(){
        System.out.println(this.name);
    }

    public static void main(String[] args) throws CloneNotSupportedException, UnsupportedEncodingException {
        User user = new User();
        user.setId(1);
        user.setName("zhuojh");
        User user2 = (User) user.clone();
        user2.setId(2);
        user.setId(3);
        user2.setName("zhuojh sec");
        System.out.println("user 1="+user);
        System.out.println("user 2="+user2);
        String redirectUrl = "https://www.soliao.com/quote/center?productId=3BAAB20388B44EB483E6FD01ACBBF1BF&colorNum=NC&region=上海";
        redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        System.out.println(redirectUrl);
        System.out.println("<script type=\"text/javascript\">var cnzz_protocol = ((\"https:\" == document.location.protocol) ? \"https://\" : \"http://\");document.write(unescape(\"%3Cspan id='cnzz_stat_icon_1277608402'%3E%3C/span%3E%3Cscript src='\" + cnzz_protocol + \"s96.cnzz.com/z_stat.php%3Fid%3D1277608402%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E\"));</script>".length());
        System.out.println(URLEncoder.encode("productId=3BAAB20388B44EB483E6FD01ACBBF1BF&colorNum=NC&region=台湾", "utf-8"));
        System.out.println(URLEncoder.encode("/乐天/list.html?sp=1.0.0", "UTF-8"));
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        redirectUrl = "/乐天/list.html?sp=1.0.0";
        Matcher m = p.matcher(redirectUrl);
        while(m.find()){
            System.out.println(m.group());
            String encodeStr = URLEncoder.encode(m.group(), "UTF-8");
            redirectUrl = redirectUrl.replace(m.group(), encodeStr);
        }
        System.out.println("ater matcher redirectUrl="+redirectUrl);
        System.out.println("gQG47zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyckNITjU1NTNleDExaGpCYWh0Y0IAAgQnJEpdAwQsAQAA".length());
        System.out.println(System.currentTimeMillis());
    }
}
