package com.profilespage.profilespagebe.files.service;

import com.profilespage.profilespagebe.files.model.File;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

public interface FileService {
    UUID uploadMetadata(File file);
    void uploadContent(byte[] content, UUID fileId);
    byte[] getContent(UUID fileId);
}
