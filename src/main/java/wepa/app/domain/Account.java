package wepa.app.domain;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "account")
public class Account extends AbstractPersistable<Long> {

    @ManyToMany
    private List<ChatGroup> chatgroups;

    @OneToMany(mappedBy = "account")
    private List<Message> messages;

    @NotNull
    @Length(min = 4, max = 25)
    private String username;

    @NotNull
    @Length(min = 4, max = 25)
    private String password;
    
    @ManyToMany
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public List<ChatGroup> getChatGroups() {
        return chatgroups;
    }

    public void setChatGroups(List<ChatGroup> chatGroups) {
        this.chatgroups = chatGroups;
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

    public List<ChatGroup> getChatgroups() {
        return chatgroups;
    }

    public void setChatgroups(List<ChatGroup> chatgroups) {
        this.chatgroups = chatgroups;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
