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
public class Album extends BaseEntity<Long>{
    private String photoSessionName;
    private Long clientId;
    private Long photographerId;
}
