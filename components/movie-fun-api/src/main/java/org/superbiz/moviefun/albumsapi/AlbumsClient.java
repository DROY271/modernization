package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    private String albumsUrl;
    private RestOperations restOperations;

    public AlbumsClient(String moviesUrl, RestOperations restOperations) {
        this.albumsUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.exchange(albumsUrl, GET, null, albumListType).getBody();
    }

    public AlbumInfo find(long albumId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(albumsUrl).pathSegment(String.valueOf(albumId));
        return restOperations.getForObject(builder.toUriString(), AlbumInfo.class);
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForLocation(albumsUrl, album);
    }
}
