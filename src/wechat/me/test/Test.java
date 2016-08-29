package wechat.me.test;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JulyLe on 2016/8/23.
 */
public class Test {
    public static void main(String args[]) throws IOException {
            System.setProperty("jsse.enableSNIExtension", "false");  //因为jdk原因请求https必须加这一句
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = null;
            String time1 = String.valueOf(System.currentTimeMillis());
            String time2 = String.valueOf(System.currentTimeMillis());
            HttpGet get = new HttpGet("https://webpush.wx.qq.com/cgi-bin/mmwebwx-bin/synccheck?r="+time2+"&skey=%40crypt_6e766e18_0b2a342b3d8ac51d7ff60650709a0d52&sid=rmPwQvQrkLFSiSLL&uin=526557615&deviceid=e157686097734021&synckey=1_639092512%7C2_639092610%7C3_639092277%7C1000_1471999142&_="+time1);
            get.addHeader("Host", "webpush.wx.qq.com");
            get.addHeader("cookie","wxuin=526557615; wxsid=rmPwQvQrkLFSiSLL; webwx_data_ticket=gSe1Cv2hBbZ04Ilac6RMuNrL");
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            get.addHeader("Referer", "https://wx.qq.com/");
            response = client.execute(get);
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        HttpPost post = new HttpPost("");

        JSONObject baseRequest = new JSONObject();
        baseRequest.put("DeviceID","e345681102574697");
        baseRequest.put("Skey","@crypt_6e766e18_0b2a342b3d8ac51d7ff60650709a0d52");
        baseRequest.put("Sid","rmPwQvQrkLFSiSLL");
        baseRequest.put("Uin","526557615");







    }

}
