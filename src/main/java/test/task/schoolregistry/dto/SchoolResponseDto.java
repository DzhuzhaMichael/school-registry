package test.task.schoolregistry.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolResponseDto {

    private Long id;

    private String name;

    private String edrpou;

    private String region;

    private String type;

    private boolean isActive;
}
