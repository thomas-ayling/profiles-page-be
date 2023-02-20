package com.profilespage.profilespagebe.files.persistence.impl;

import com.profilespage.profilespagebe.files.model.File;
import com.profilespage.profilespagebe.files.persistence.FileDao;
import com.profilespage.profilespagebe.utils.Assert;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FileDaoImpl implements FileDao {
    private final FileMapper fileMapper;

    public FileDaoImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public void persistMetadata(File file, UUID fileId, UUID contentId) {
        Assert.notNull(file, "File cannot be null.");
        Assert.notNull(fileId, "File ID cannot be null.");
        Assert.notNull(contentId, "Content ID cannot be null.");
        file.setId(fileId);
        file.setContentId(contentId);
        //TODO: Logs
        fileMapper.persistMetadata(file);
    }

    @Override
    public void createContentField(UUID contentId) {
        Assert.notNull(contentId, "Content ID cannot be null.");
        fileMapper.createContentField(contentId);
    }

    @Override
    public void uploadContent(byte[] content, UUID contentId) {
        Assert.notNull(contentId, "Content ID cannot be null.");
        //TODO: Logs
        fileMapper.uploadContent(content, contentId);
    }

    @Override
    public long getCrc(UUID fileId) {
        Assert.notNull(fileId, "File ID cannot be null.");
        //TODO: Logs
        return fileMapper.getCrc(fileId);
    }

    @Override
    public UUID getContentId(UUID fileId) {
        //TODO: Logs
        return fileMapper.getContentId(fileId).get("content_id");
    }

    @Override
    public byte[] getContent(UUID fileId) {
        return fileMapper.getContent(fileId).get("content");
    }
}
