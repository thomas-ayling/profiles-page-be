package com.profilespage.profilespagebe.files.model;

import java.util.UUID;

import com.profilespage.profilespagebe.utils.Assert;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class File {
    @Nullable
    private UUID id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    private long size;
    @NotNull
    private long crc;
    @Nullable
    private UUID contentId;

    /**
     * @param id      The ID of the file
     * @param name    The name of the file
     * @param type    The datatype of the file
     * @param size    The size of the file in bytes
     * @param crc     The CRC of the file
     * @param contentId The ID of the file content
     * */
    public File(UUID id, String name, String type, long size, long crc, UUID contentId) {
        setId(id);
        setName(name);
        setType(type);
        setSize(size);
        setCrc(crc);
        setContentId(contentId);
    }

    public File() {
        name = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.notNull(id, "ID cannot be null");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Assert.notBlank(Assert.notNull(name, "Name cannot be null"), "Name cannot be a blank string");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = Assert.notBlank(Assert.notNull(type, "Type cannot be null"), "Type cannot be a blank string");
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCrc() {
        return crc;
    }

    public void setCrc(long crc) {
        this.crc = crc;
    }

    public UUID getContentId() {
        return contentId;
    }

    public void setContentId(UUID contentId) {
        this.contentId = Assert.notNull(contentId, "Content ID must not be null");
    }
}
