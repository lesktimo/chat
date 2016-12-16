
package wepa.app.domain;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Message extends AbstractPersistable<Long> {


    private String content;

    @ManyToOne
    @JoinColumn
    private Account account;

    @ManyToOne
    @NotNull
    private ChatGroup group;

 

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
