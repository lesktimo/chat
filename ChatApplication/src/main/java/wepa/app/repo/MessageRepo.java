
package wepa.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.app.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
