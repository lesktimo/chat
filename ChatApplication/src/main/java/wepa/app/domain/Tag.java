/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa.app.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Column;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Tag extends AbstractPersistable<Long> {

    private String tag;

    
    @ManyToMany
    private List<Group> groups;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
