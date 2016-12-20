package wepa.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.app.domain.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
