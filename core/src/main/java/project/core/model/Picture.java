package project.core.model;


import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Picture extends BaseEntity<Long>{
    private String title;
    private String description;
    private int width;
    private int height;
}
