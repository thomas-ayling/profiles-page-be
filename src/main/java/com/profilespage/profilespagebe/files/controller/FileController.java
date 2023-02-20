package com.profilespage.profilespagebe.files.controller;

import com.profilespage.profilespagebe.exception.UnprocessableEntityException;
import com.profilespage.profilespagebe.files.model.File;
import com.profilespage.profilespagebe.files.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @CrossOrigin(exposedHeaders = "Location")
    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> createFile(@Validated @RequestBody File file, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new UnprocessableEntityException(errors.toString());
        }
        UUID id = fileService.uploadMetadata(file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        System.out.println(id);
        System.out.println(uri);
        return ResponseEntity.created(uri).build();
    }

    //    @CrossOrigin(origins = "*")
    @PutMapping(value = "/{id}", consumes = "*/*")
    public ResponseEntity<?> uploadContent(@PathVariable UUID id, @RequestBody byte[] content) {
        fileService.uploadContent(content, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value="/{fileId}", produces="application/octet-stream")
    public ResponseEntity<byte[]> getContent(@PathVariable UUID fileId) {
        byte[] content = fileService.getContent(fileId);
        if (content == null) {
            return ResponseEntity.notFound().build();
        }
        if (content.length == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(content);
    }
}