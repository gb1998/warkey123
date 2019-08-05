package cn.ac.iscas.gz.util;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 模拟登录接口
 */
public class CookieUtil {
    public static void main(String args[]){
        String loginUrl = "http://localhost:8080/xxx/login.do";

        String dataUrl = "http://localhost:8080/xxx/getSysMenus.do";
       JSONObject json = new JSONObject();
        json.put("username", "sys");
        json.put("password", "123456");
     //   HttpClientLogin(json.toString(), loginUrl);

    }
    String urlString = "登录页面的url";



    // 提交一次用户请求
    private boolean doPost(String user, String password) {
        boolean sucess = false;
        try {
            URL realUrl = null;
            try {
                realUrl = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setInstanceFollowRedirects(false);

            // 提交表单,发送的数据是直接用Firebug截取的然后把用户名，密码部分换成参数
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print("要提交的表单信息");
            out.flush();

            // 如果登录不成功，报头中没有Location字段，getHeaderField("Location") 返回null
            // 登录成功，返回一个随机的Location字段
            // System.out.println(conn.getHeaderFields());
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
                      //  + " : " + ps + "\n");
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
}
