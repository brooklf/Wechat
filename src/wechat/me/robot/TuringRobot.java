package wechat.me.robot;

/**
 * Created by LemonTree on 2016/8/30.
 */
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class TuringRobot {
    public static String getAnswer(String keyWord) {
        String Url = null;
        Url="http://www.tuling123.com/openapi/api";
        InputStream ins=null;
        String result="";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            URL realUrl=new URL(Url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setDoOutput(true);    // 可以发送数据
            connection.setDoInput(true);    // 可以接收数据
            connection.setRequestMethod("POST");    // POST方法
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect(); // 建立实际的连接
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // 获取所有响应头字段
            osw.write("key=eb39ca1f639cca86999eae1859198cd2&info="+keyWord+"&ioc=en&userid=123456");
            osw.flush();
            osw.close();
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
 //               System.out.println(key + "--->" + map.get(key));
            }
            ins= connection.getInputStream();
            byte[] buffer= new byte[1024];
            int len =-1;
            while((len = ins.read(buffer)) != -1){
                outputStream.write(buffer, 0, len);
            }
            result=new String(outputStream.toByteArray(),"utf-8");
            JSONObject json = JSONObject.fromObject(result);
            result=json.getString("text");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }
    public static void main(String args[]){
        System.out.print(TuringRobot.getAnswer("你好..."));
    }

}

