package com.sonatype.nexus;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PaginatedResults<T> {

    private final List<T> items = new ArrayList<>();
    private String continuationToken;

}
