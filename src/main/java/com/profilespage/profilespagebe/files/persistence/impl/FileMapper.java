package com.profilespage.profilespagebe.files.persistence.impl;

import com.profilespage.profilespagebe.files.model.File;
import org.apache.ibatis.annotations.*;

import java.util.Map;
import java.util.UUID;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO files (id, name, type, size, crc, content_id) VALUES (#{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UuidTypeHandler}, #{name}, #{type}, #{size}, #{crc}, #{contentId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UuidTypeHandler})")
    void persistMetadata(File file);

    @Insert("INSERT INTO content (id) VALUES (#{contentId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UuidTypeHandler})")
    void createContentField(@Param("contentId") UUID contentId);

    @Update("UPDATE content SET content = #{content} WHERE id = #{contentId, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UuidTypeHandler}")
    void uploadContent(byte[] content, @Param("contentId") UUID contentId);

    @Results({@Result(column = "crc", javaType = long.class)})
    @Select("SELECT crc FROM files WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UuidTypeHandler}")
    long getCrc(@Param("id") UUID fileId);

    @Select("SELECT content_id FROM files WHERE id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UuidTypeHandler}")
    Map<String, UUID> getContentId(@Param("id") UUID fileId);

    @Select("SELECT content FROM content LEFT OUTER JOIN files ON content.id = files.content_id WHERE files.id = #{id, javaType=java.util.UUID, jdbcType=OTHER, typeHandler=UuidTypeHandler}")
    Map<String, byte[]> getContent(@Param("id") UUID fileId);
}