package com.sonatype.nexus;

import lombok.Data;

@Data
public class AssetInformation {

    private String downloadUrl;
    private String path;
    private String id;
    private String repository;
    private String format;
    private Checksums checksum = new Checksums();

    @Data
    public static class Checksums {
        private String md5;
        private String sha1;
        private String sha256;
        private String sha512;
    }

}
