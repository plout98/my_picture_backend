package com.plout.mypicture.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cos.client")
@Data
public class CosClientConfig {

    /**
     * 腾讯云COS的域名
     */
    private String host;

    /**
     * 腾讯云COS的SecretId
     */
    private String secretId;

    /**
     * 腾讯云COS的SecretKey(密钥)
     */
    private String secretKey;

    /* *
     * 桶名
     **/
    private String bucket;

    /* *
     * 区域
     **/
    private String region;

    @Bean
    public COSClient cosClient(){
        // 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig();
        // 设置 bucket 的地域
        clientConfig.setRegion(new Region(region));
        // 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }
}
