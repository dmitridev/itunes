package org.astelit.itunes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    int page = 0;
    int size = 10;
}
