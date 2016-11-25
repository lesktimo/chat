package wepa.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.app.domain.Group;

public interface GroupRepo extends JpaRepository<Group, Long> {

}
