package ru.aplana.request;

import lombok.*;

@Builder
public class AddUserRequest {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;
}
