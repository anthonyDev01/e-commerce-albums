package com.api.ecomerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUrlsDto {
    private Map<String, String> externalUrls;
}
