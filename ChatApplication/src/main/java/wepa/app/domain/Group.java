/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa.app.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Group extends AbstractPersistable<Long>{
    
    
    
    private List<Account> accounts;
    
    @OneToMany
    @JoinColumn
    private List<Message> messages;
    
    private List<Tag> tags;
    
    
    
}
