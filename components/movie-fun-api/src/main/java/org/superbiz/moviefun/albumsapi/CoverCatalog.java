package org.superbiz.moviefun.albumsapi;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.superbiz.moviefun.blobstore.Blob;
import org.superbiz.moviefun.blobstore.BlobStore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static java.lang.String.format;

@Component
public class CoverCatalog {

    private final BlobStore store;

    public CoverCatalog(BlobStore store) {
        this.store = store;
    }


    public void uploadCover(long albumId, InputStream is, String contentType) throws IOException {
        Blob b = new Blob(getCoverBlobName(albumId), is, contentType);
        store.put(b);
    }


    @HystrixCommand(fallbackMethod = "getDefaultCoverBlob")
    public Blob getCover(long albumId) throws IOException {
        Optional<Blob> maybeCoverBlob = store.get(getCoverBlobName(albumId));
        Blob coverBlob = maybeCoverBlob.orElseGet(this::buildDefaultCoverBlob);
        return coverBlob;
    }

    Blob getDefaultCoverBlob(long albumId) {
        return buildDefaultCoverBlob();
    }

    private Blob buildDefaultCoverBlob() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream("default-cover.jpg");

        return new Blob("default-cover", input, MediaType.IMAGE_JPEG_VALUE);
    }

    private String getCoverBlobName(long albumId) {
        return format("covers/%d", albumId);
    }
}
