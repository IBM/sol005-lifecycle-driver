package org.etsi.sol005.packagemanagement;

import java.time.OffsetDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents an artifact contained in a VNF package which represents a software image.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents an artifact contained in a VNF package which represents a software image.")
public class NsPackageSoftwareImageInfo {

    @Schema(name = "Identifier", required = true, description = "Identifier of the software image.")
    private String id;
    @Schema(name = "Name", required = true, description = "Name of the software image.")
    private String name;
    @Schema(name = "Provider", required = true, description = "Provider of the software image.")
    private String provider;
    @Schema(name = "Version", required = true, description = "Version of the software image.")
    private String version;
    @Schema(name = "Checksum", required = true, description = "Checksum of the software image file.")
    private Checksum checksum;
    @Schema(name = "Container Format", required = true, description = "Container format indicates whether the software image is in a file format that also contains metadata about the actual software.")
    private ContainerFormat containerFormat;
    @Schema(name = "Disk Format", required = true, description = "Disk format of a software image is the format of the underlying disk image.")
    private DiskFormat diskFormat;
    @Schema(name = "Created At", required = true, description = "Time when this software image was created.")
    private OffsetDateTime createdAt;
    @Schema(name = "Minimal Disk", required = true, description = "The minimal disk for this software image in bytes.")
    private int minDisk;
    @Schema(name = "Minimal RAM", required = true, description = "The minimal RAM for this software image in bytes.")
    private int minRam;
    @Schema(name = "Size", required = true, description = "Size of this software image in bytes.")
    private int size;
    @Schema(name = "User Metadata", description = "User-defined data.")
    private Map<String, String> userMetadata;
    @Schema(name = "Image Path", required = true, description = "Path in the VNF package, which identifies the image artifact and also allows to access a copy of the image artifact.")
    private String imagePath;

    /**
     * Disk format of a software image is the format of the underlying disk image.
     */
    public enum ContainerFormat {
        /**
         * a kernel image format
         */
        AKI,
        /**
         * a machine image format
         */
        AMI,
        /**
         * a ramdisk image format
         */
        ARI,
        /**
         * the image does not have a container or metadata envelope
         */
        BARE,
        /**
         * docker container format
         */
        DOCKER,
        /**
         * OVF package in a tarfile
         */
        OVA,
        /**
         * OVF container format
         */
        OVF;
    }

    public enum DiskFormat {
        /**
         * a kernel image format
         */
        AKI,
        /**
         * a machine image format
         */
        AMI,
        /**
         * a ramdisk image format
         */
        ARI,
        /**
         * an archive format for the data contents of an optical disc, such as CD-ROM
         */
        ISO,
        /**
         * a common disk image format, which can expand dynamically and supports copy on write
         */
        QCOW2,
        /**
         * an unstructured disk image format
         */
        RAW,
        /**
         * a common disk image format
         */
        VDI,
        /**
         * a common disk image format
         */
        VHD,
        /**
         * enhanced version of VHD format
         */
        VHDX,
        /**
         * a common disk image format
         */
        VMDK;
    }
}
