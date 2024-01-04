package project.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PhotographerDto extends BaseDto {
    private String name;
    private int age;
    private String cameraBrand;
    private int rating;
}
