package wepa.app.domain;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "account")
public class Account extends AbstractPersistable<Long> {

    @ManyToMany
    private List<ChatGroup> chatGroups;

    @OneToMany
    private List<Message> messages;

    @NotNull
    @Length(min = 4, max = 25)
    private String username;

    @NotNull
    @Length(min = 4)
    private String password;

    private Set<SimpleGrantedAuthority> roles;

    public List<ChatGroup> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(List<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
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

    public Set<SimpleGrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<SimpleGrantedAuthority> roles) {
        this.roles = roles;
    }

}
