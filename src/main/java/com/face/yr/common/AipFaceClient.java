package com.face.yr.common;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/6 12:55
 */
public class AipFaceClient {


    private static final String APP_ID = "15066202";
    private static final String API_KEY = "18mzC1Bf5ieqtisG7IWAhbDl";
    private static final String SECRET_KEY = "4GioZSsQ3GDpEkkG1UfrVTLfVPyVgGKI";


    private AipFaceClient() {}

    private static class SingletonHolder {
        private static final AipFace INSTANCE = getClient();
    }

    public static final AipFace getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private static AipFace getClient() {
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
//        String path = "test.jpg";
//        String imageType ="BASE64";
//        JSONObject res = client.detect(path, imageType, new HashMap<String, String>());
//        System.out.println(res.toString(2));

        return client;
    }
}
