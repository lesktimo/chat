package wepa.app.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @ManyToMany
    private List<ChatGroup> chatgroups;

    @OneToMany(mappedBy = "account")
    private List<Message> messages;

    private String username;
    private String password;

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

}
