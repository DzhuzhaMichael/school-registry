package test.task.schoolregistry.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SchoolFilterDto {

    private String region;

    private String type;

    private Boolean active;
}
