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
public class Photographer extends BaseEntity<Long> {
    private String name;
    private int age;
    private String cameraBrand;
    private int rating;
}
