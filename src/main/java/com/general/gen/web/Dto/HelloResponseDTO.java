package com.general.gen.web.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResponseDTO {

    private final String name;
    private final int amount;
}
