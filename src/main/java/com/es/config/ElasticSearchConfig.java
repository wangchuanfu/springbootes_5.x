package com.es.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
@Configuration
public class ElasticSearchConfig {
    @Value("${spring.data.elasticsearch.properties.cluster-name}")
    private String clusterName;
    @Value("${spring.data.elasticsearch.properties.cluster-nodes}")
    private String clusterNodes;
    @Value("${spring.data.elasticsearch.properties.ip}")
    private String ips;
    @Value("${spring.data.elasticsearch.properties.port}")
    private String port;

    @Bean
    public TransportClient getESClient() {
        // 设置集群名字
        Settings settings = Settings.builder().put("client.transport.ping_timeout", "30s").put("cluster.name", clusterName)
                .put("client.transport.sniff", true).put("node.name", clusterNodes).put("client.transport.sniff", false).build();
        String[] ipstr = ips.split(",");
        TransportClient transportClient = new PreBuiltTransportClient(settings);
        try {
            for (int i = 0; i < ipstr.length; i++) {
                transportClient.addTransportAddress(
                        new InetSocketTransportAddress(InetAddresses.forString(ipstr[i]), Integer.parseInt(port)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     //   log.info("ElasticSearch client get over ..config:" + "ip：" + ips + " " + "端口：" + port + " ");
        return transportClient;
    }

}
