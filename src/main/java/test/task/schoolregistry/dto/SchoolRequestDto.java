package test.task.schoolregistry.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolRequestDto {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "EDRPOU must not be blank")
    @Size(min = 8, max = 8, message = "EDRPOU must be exactly 8 characters")
    private String edrpou;

    @NotBlank(message = "Region must not be blank")
    private String region;

    @NotBlank(message = "Type must not be blank")
    private String type;

}
