package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LocalIpTool {
    public static void main(String[] args) {
        LocalIpTool
    }
    public String getLocalPublicIp(){
        String ipApiUrl = "https://www.ip138.com/";
        String html = post(ipApiUrl, null);
        System.out.println(html);
        return "";
    }
    public String post(String strUrl, String params){
        BufferedReader reader = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json"); //设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            if(null != params)
                out.append(params);
            out.flush();
            out.close();
            //读取响应
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) System.out.println("response code="+responseCode);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
            return res;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
