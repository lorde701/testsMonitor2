package ru.aplana.request;

import lombok.*;
import org.jetbrains.annotations.NotNull;

@Builder
public class UserProjectRequest {
    @NotNull
    @Getter @Setter
    Long userId;

    @NotNull
    @Getter @Setter
    Long projectId;
}
