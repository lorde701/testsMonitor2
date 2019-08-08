package ru.aplana.request;

import lombok.*;

@Builder
public class AddUserToProject {
    @Getter @Setter
    Long userId;

    @Getter @Setter
    Long projectId;
}
