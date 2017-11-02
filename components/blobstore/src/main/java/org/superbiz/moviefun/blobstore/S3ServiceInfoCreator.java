package org.superbiz.moviefun.blobstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import java.util.Map;

public class S3ServiceInfoCreator extends CloudFoundryServiceInfoCreator<S3ServiceInfo> {

    private Logger LOG = LoggerFactory.getLogger(S3ServiceInfoCreator.class);

    public S3ServiceInfoCreator() {
        super(new Tags("s3", "aws-s3"), "s3");
        LOG.info("S3ServiceInfoCreator created.");
    }


    @Override
    public S3ServiceInfo createServiceInfo(Map<String, Object> serviceData) {


        Map<String, Object> credentials = getCredentials(serviceData);
        String accessKey = (String) credentials.get("access_key_id");
        String secretKey = (String) credentials.get("secret_access_key");
        String bucket = (String) credentials.get("bucket");
        return new S3ServiceInfo((String)serviceData.get("name"), accessKey, secretKey, bucket);

    }
}
