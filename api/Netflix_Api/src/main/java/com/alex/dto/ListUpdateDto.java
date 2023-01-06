package com.alex.dto;

import lombok.Data;

@Data
public class ListUpdateDto {
    private String title;
    private String type;
    private String genre;
    private java.util.List<String> contentIds;
}
