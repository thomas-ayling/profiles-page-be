package com.profilespage.profilespagebe.files.persistence;

import com.profilespage.profilespagebe.files.model.File;

import java.util.UUID;

public interface FileDao {
    void persistMetadata(File file, UUID fileId, UUID contentId);
    void createContentField(UUID contentId);
    void uploadContent(byte[] content, UUID contentId);
    long getCrc(UUID fileId);
    UUID getContentId(UUID fileId);
    byte[] getContent(UUID fileId);
}
