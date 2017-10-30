package org.superbiz.moviefun.albumsapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private AlbumsRepository repo;

    public AlbumsController(AlbumsRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Album> findAll() {
        return repo.getAlbums();
    }

    @PostMapping
    public void add(@RequestBody Album album) {
        repo.addAlbum(album);
    }

    @GetMapping("{id}")
    public Album findOne(@PathVariable("id") long id) {
        return repo.find(id);
    }




}
