package project.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PictureDto extends BaseDto {
    private String title;
    private String description;
    private int width;
    private int height;
}
