package org.superbiz.moviefun.blobstore;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Collection;

@Configuration()
public class S3BlobStoreConfiguration {

    @Configuration
    @Profile("!cloud")
    public static class DevelopmentConfiguration {
        @Value("${s3.accessKey}")
        String s3AccessKey;
        @Value("${s3.secretKey}")
        String s3SecretKey;
        @Value("${s3.bucketName}")
        String s3BucketName;
        @Value("${s3.endpointurl}")
        String url;

        @Bean
        public BlobStore blobStore() {
            AWSCredentials credentials = new BasicAWSCredentials(s3AccessKey, s3SecretKey);
            AmazonS3Client s3Client = new AmazonS3Client(credentials);
            s3Client.setEndpoint(url);
            return new S3Store(s3Client, s3BucketName);
        }
    }


    @Configuration
    @Profile("cloud")
    public static class VcapConfiguration {

        CloudFactory cf = new CloudFactory();


        @Bean
        public BlobStore blobStore() {
            Cloud c = cf.getCloud();

            S3ServiceInfo info = c.getServiceInfos().stream().filter(it -> it instanceof S3ServiceInfo).map(it ->
            {return (S3ServiceInfo) it;}).findFirst().get();


            AWSCredentials credentials = new BasicAWSCredentials(info.accessKey, info.secretKey);
            AmazonS3Client s3Client = new AmazonS3Client(credentials);
            s3Client.setEndpoint("http://s3.amazonaws.com");
            return new S3Store(s3Client, info.bucket);
        }
    }


}
