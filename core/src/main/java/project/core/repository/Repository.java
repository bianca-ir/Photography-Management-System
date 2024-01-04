package project.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import project.core.model.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface Repository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
