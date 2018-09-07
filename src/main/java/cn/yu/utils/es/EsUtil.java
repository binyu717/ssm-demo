package cn.yu.utils.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * @author bin.yu
 * @create 2018-09-07 17:57
 **/
public class EsUtil {

    public final static String HOST = "172.25.50.69";

    public final static int PORT = 9300;

    public static TransportClient getConnection() throws Exception {
        // 设置集群名称
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticSearch")
                .put("client.transport.sniff", false)
                .put("thread_pool.search.size", 5)
                .build();        // 创建client
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(HOST), PORT));

        client.close();
        return client;
    }

    public static void main(String[] args) {
        try {
            TransportClient client = getConnection();
            System.out.println("====:"+client.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
