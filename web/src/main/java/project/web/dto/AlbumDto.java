package project.web.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class AlbumDto extends BaseDto {
    private String photoSessionName;
    private Long clientId;
    private Long photographerId;
}
