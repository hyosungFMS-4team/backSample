package com.example.TravelPlanner.dto.User;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    @NotNull
    private String memberId;
}

