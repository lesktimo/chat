
package wepa.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.app.domain.ChatGroup;

public interface GroupRepo extends JpaRepository<ChatGroup, Long> {

}
