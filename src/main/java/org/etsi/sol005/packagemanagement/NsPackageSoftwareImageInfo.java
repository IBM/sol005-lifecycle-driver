package org.etsi.sol005.packagemanagement;

import java.time.OffsetDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents an artifact contained in a VNF package which represents a software image.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents an artifact contained in a VNF package which represents a software image.")
public class NsPackageSoftwareImageInfo {

    @ApiModelProperty(name = "Identifier", required = true, notes = "Identifier of the software image.")
    private String id;
    @ApiModelProperty(name = "Name", required = true, notes = "Name of the software image.")
    private String name;
    @ApiModelProperty(name = "Provider", required = true, notes = "Provider of the software image.")
    private String provider;
    @ApiModelProperty(name = "Version", required = true, notes = "Version of the software image.")
    private String version;
    @ApiModelProperty(name = "Checksum", required = true, notes = "Checksum of the software image file.")
    private Checksum checksum;
    @ApiModelProperty(name = "Container Format", required = true, notes = "Container format indicates whether the software image is in a file format that also contains metadata about the actual software.")
    private ContainerFormat containerFormat;
    @ApiModelProperty(name = "Disk Format", required = true, notes = "Disk format of a software image is the format of the underlying disk image.")
    private DiskFormat diskFormat;
    @ApiModelProperty(name = "Created At", required = true, notes = "Time when this software image was created.")
    private OffsetDateTime createdAt;
    @ApiModelProperty(name = "Minimal Disk", required = true, notes = "The minimal disk for this software image in bytes.")
    private int minDisk;
    @ApiModelProperty(name = "Minimal RAM", required = true, notes = "The minimal RAM for this software image in bytes.")
    private int minRam;
    @ApiModelProperty(name = "Size", required = true, notes = "Size of this software image in bytes.")
    private int size;
    @ApiModelProperty(name = "User Metadata", notes = "User-defined data.")
    private Map<String, String> userMetadata;
    @ApiModelProperty(name = "Image Path", required = true, notes = "Path in the VNF package, which identifies the image artifact and also allows to access a copy of the image artifact.")
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
