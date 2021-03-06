package wepa.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wepa.app.domain.Account;
import wepa.app.domain.Role;
import wepa.app.repo.AccountRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepo accRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accRepo.findByUsername(username);

        if (account == null) {

            throw new UsernameNotFoundException("No such user: " + username);

        }

        List<GrantedAuthority> list = buildUserAuthority(account.getRoles());

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                list);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        userRoles.stream().forEach((userRole) -> {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        });
        List<GrantedAuthority> Result = new ArrayList<>(setAuths);
        return Result;
    }
}
