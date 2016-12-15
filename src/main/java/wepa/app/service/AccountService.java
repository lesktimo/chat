
package wepa.app.service;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wepa.app.domain.Account;
import wepa.app.repo.AccountRepo;
import wepa.app.repo.RoleRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    public void save(Account acc) {
        acc.setPassword(bCrypt.encode(acc.getPassword()));
        acc.setRoles(new HashSet<>(roleRepo.findAll()));
        accRepo.save(acc);
    }

    public Account findByUsername(String username) {
        return accRepo.findByUsername(username);
    }

}
