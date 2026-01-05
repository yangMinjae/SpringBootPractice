package com.jay.shop.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberJoinDTO {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min=4, max=20, message = "아이디는 4~20자 사이여야 합니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 모두 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
}
