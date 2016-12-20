package wepa.app.domain;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @ManyToMany
    private List<ChatGroup> chatGroups;

    @NotNull
    @Length(min = 4, max = 25)
    private String username;

    @NotNull
    @Length(min = 4)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;

    public List<ChatGroup> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(List<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
