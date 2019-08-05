package cn.ac.iscas.gz.util;



import cn.ac.iscas.gz.model.Data;
import cn.ac.iscas.gz.model.PostMan;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;



public class ExportPostManApiUtil {

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }


    public  static  void main(String args[]) throws IOException {
//        readFile("C:\\Users\\Yan\\Desktop\\api\\b.json");
//        readFile("C:\\Users\\Yan\\Desktop\\api\\c.json");
//        readFile("C:\\Users\\Yan\\Desktop\\api\\d.json");
//        readFile("C:\\Users\\Yan\\Desktop\\api\\e.json");
//        readFile("C:\\Users\\Yan\\Desktop\\api\\f.json");

    readFile("C:\\Users\\user\\Desktop\\工作周报\\response.json");

    }




    private static void readFile(String path) {
        String file = readToString(path);
        JSONObject allData = JSONObject.fromObject(file);
        String title = allData.getString("name");
        String requests = allData.getString("requests");

        JSONArray reqList = JSONArray.fromObject(requests);

        List<PostMan> postManList = new ArrayList<>();

        for (Object o : reqList) {
            JSONObject jo = (JSONObject) o;
            PostMan postMan = new PostMan();
            postMan.setName(jo.getString("name"));
            postMan.setUrl(jo.getString("url"));
            postMan.setMethod(jo.getString("method"));

            if (!jo.getString("data").equals("null")) {
                JSONArray dataArr = JSONArray.fromObject(jo.getString("data"));
                List<Data> dataList = new ArrayList<>();
                if (dataArr.size() > 0) {
                    for (Object d : dataArr) {
                        JSONObject jsonData = (JSONObject) d;
                        Data data = new Data();
                        data.setKey(jsonData.getString("key"));
                        data.setType(jsonData.getString("type"));
                        data.setEnabled(jsonData.getBoolean("enabled"));
                        if (jsonData.containsKey("description")) {
                            data.setDescription(jsonData.getString("description"));
                        }
                        data.setValue(jsonData.getString("value"));
                        dataList.add(data);
                    }
                }
                postMan.setData(dataList);
            }
            postManList.add(postMan);
        }
       writeToHtml(title, postManList);


    }



    private static void  writeToHtml(String title, List<PostMan> postManList) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<h2>" + title + "</h2>");
        buffer.append("<br/>");
        for (PostMan pm : postManList) {
            buffer.append("<h3 style='margin:1px 0 1px 0'>接口名称：" + pm.getName() + "</h3>");
            buffer.append("<br/>");
            buffer.append("<h3 style='margin:1px 0 1px 0'>接口地址：" + pm.getUrl() + "</h3>");
            buffer.append("<br/>");
            buffer.append("<h3 style='margin:1px 0 1px 0'>请求方式：" + pm.getMethod() + "</h3>");
            buffer.append("<br/>");
            List<Data> dataList = pm.getData();
            if (null != dataList && dataList.size() > 0) {
                buffer.append("<h3 style='margin:1px 0 1px 0'>请求参数：</h3>");
                buffer.append("<br/>");

                buffer.append("<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
                buffer.append("<tr>");
                buffer.append("<td>参数名</td><td>参数类型</td><td>是否必填</td><td>示例值</td><td>描述</td>");
                buffer.append("</tr>");
                for (Data data : dataList) {
                    buffer.append("<tr>");
                    buffer.append("<td>" + data.getKey() + "</td><td>" + data.getType() + "</td><td>" + data.getEnabled() + "</td><td>" + data.getValue() + "</td><td>" + data.getDescription() + "</td>");
                    buffer.append("</tr>");
                }
                buffer.append("</table>");
                buffer.append("<br/>");
            }
        }
        System.out.println(buffer.toString());

    }


}
