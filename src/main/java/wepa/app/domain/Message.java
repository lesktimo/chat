package wepa.app.domain;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Message extends AbstractPersistable<Long> {

    private Timestamp timestamp;
    
    private String content;

    @ManyToOne
    @JoinColumn
    private Account account;

    @ManyToOne
    private ChatGroup group;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    public ChatGroup getGroup() {
        return this.group;
    }
    
    public void setGroup(ChatGroup group) {
        this.group = group;
    }

}
