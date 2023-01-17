package com.general.gen.Dto;

import com.general.gen.web.Dto.HelloResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloResponseDTOTest {
    @Test
    @DisplayName("롬복 기능 테스트")
    public void f() throws Exception{
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }


}