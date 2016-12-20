package wepa.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.app.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
