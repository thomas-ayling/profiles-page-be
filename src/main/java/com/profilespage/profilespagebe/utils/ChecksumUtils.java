package com.profilespage.profilespagebe.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32C;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

public class ChecksumUtils {
    /**
     * Generates a CRC32C checksum to be checked against the CRC sent from the client
     *
     * @param bytes The content to be checked
     * @return The value of the checksum
     */
    public static long getCRC32CChecksum(byte[] bytes) {
        Checksum crc32 = new CRC32C();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }

    public static long getCRC32CChecksum(InputStream stream, int bufferSize) throws IOException {
        CheckedInputStream checkedInputStream = new CheckedInputStream(stream, new CRC32C());
        byte[] buffer = new byte[bufferSize];
        while (true) {
            if (checkedInputStream.read(buffer, 0, buffer.length) <= 0) break;
        }
        return checkedInputStream.getChecksum().getValue();
    }
}
