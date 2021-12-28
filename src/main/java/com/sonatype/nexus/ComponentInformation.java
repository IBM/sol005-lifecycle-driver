package com.sonatype.nexus;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ComponentInformation {

    private String id;
    private String repository;
    private String format;
    private String group;
    private String name;
    private String version;
    private final List<AssetInformation> assets = new ArrayList<>();

}
