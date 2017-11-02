package org.superbiz.moviefun.blobstore;

import org.springframework.cloud.service.ServiceInfo;

public class S3ServiceInfo implements ServiceInfo {

    public final String id;
    public final String accessKey;
    public final String secretKey;
    public final String bucket;


    public S3ServiceInfo(String id, String accessKey, String secretKey, String bucket) {
        this.id = id;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "S3ServiceInfo{" +
                "id='" + id + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }
}
