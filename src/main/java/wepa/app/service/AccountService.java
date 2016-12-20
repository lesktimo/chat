package wepa.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wepa.app.domain.Account;
import wepa.app.domain.Role;
import wepa.app.repo.AccountRepo;
import wepa.app.repo.RoleRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accRepo;

    @Autowired
    private RoleRepo roleRepo;

    @PostConstruct
    public void init() {
        if (accRepo.findByUsername("testi") == null) {
            HashSet<Role> roles = new HashSet<>();
            Role user = new Role();
            user.setRoleName("USER");
            roles.add(user);
            Account account = new Account();
            account.setUsername("testi");
            account.setPassword(passwordEncoder().encode("testi"));
            account.setRoles(roles);
            account = accRepo.save(account);
            if (user.getAccounts() == null) {
                List<Account> accounts = new ArrayList<>();
                accounts.add(account);
                user.setAccounts(accounts);
            } else {
                user.getAccounts().add(account);
            }
            roleRepo.save(user);
        }
    }

    public void save(Account acc) {
        HashSet<Role> roles = new HashSet<>();
        Role user = new Role();
        if (roleRepo.findByRoleName("USER") != null) {
            user = roleRepo.findByRoleName("USER");
        } else {
            user.setRoleName("USER");
        }
        roles.add(user);
        acc.setPassword(passwordEncoder().encode(acc.getPassword()));
        acc.setRoles(roles);
        acc = accRepo.save(acc);
        if (user.getAccounts() == null) {
            List<Account> accounts = new ArrayList<>();
            accounts.add(acc);
            user.setAccounts(accounts);
        } else {
            user.getAccounts().add(acc);
        }
        roleRepo.save(user);
    }

    public Account findByUsername(String username) {
        return accRepo.findByUsername(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
