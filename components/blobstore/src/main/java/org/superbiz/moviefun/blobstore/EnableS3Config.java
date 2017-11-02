package org.superbiz.moviefun.blobstore;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import({S3BlobStoreConfiguration.DevelopmentConfiguration.class, S3BlobStoreConfiguration.VcapConfiguration.class})
public @interface EnableS3Config {
}
