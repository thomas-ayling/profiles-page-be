package com.profilespage.profilespagebe.files.service.impl;

import com.profilespage.profilespagebe.exception.UnmatchedCrcException;
import com.profilespage.profilespagebe.files.model.File;
import com.profilespage.profilespagebe.files.persistence.FileDao;
import com.profilespage.profilespagebe.files.service.FileService;
import com.profilespage.profilespagebe.utils.Assert;
import com.profilespage.profilespagebe.utils.ChecksumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final FileDao fileDao;
    private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public FileServiceImpl(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Transactional
    @Override
    public UUID uploadMetadata(File file) {
        UUID fileId = UUID.randomUUID();
        UUID contentId = UUID.randomUUID();
        logger.debug("Creating content field with ID {}", contentId);
        System.out.println(contentId);
        fileDao.createContentField(contentId);
        logger.debug("Persisting metadata {} with ID {} and content ID {}", file, fileId, contentId);
        fileDao.persistMetadata(file, fileId, contentId);
        return fileId;
    }

    @Transactional
    @Override
    public void uploadContent(byte[] content, UUID fileId) {
        // Find CRC from bytes
        logger.debug("Calculating crc from current request");
        long currentCrc = ChecksumUtils.getCRC32CChecksum(content);
        // Request the persisted CRC from the database
        logger.debug("Requesting persisted crc value");
        long persistedCrc = fileDao.getCrc(fileId);
        // Compare CRCs and throw an error if they don't match
        if (currentCrc != persistedCrc) {
            throw new UnmatchedCrcException("CRCs don't match in file upload.");
        }
        // Request the content ID from the database
        logger.debug("Requesting content ID from files table");
        UUID contentId = fileDao.getContentId(fileId);
        logger.debug("Uploading content, ID: {}", contentId);
        fileDao.uploadContent(content, contentId);
    }

    @Transactional
    @Override
    public byte[] getContent(UUID fileId) {
        Assert.notNull(fileId, "File ID cannot be null");
        logger.debug("Service requesting content with ID {}", fileId);
        return fileDao.getContent(fileId);
    }
}
