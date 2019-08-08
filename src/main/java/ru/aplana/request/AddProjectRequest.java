package ru.aplana.request;

import lombok.*;

@Builder
public class AddProjectRequest {
    @Getter @Setter
    private String projectName;
}
