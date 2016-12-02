package wepa.app.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wepa.app.domain.Group;
import wepa.app.domain.Message;

@Controller
@RequestMapping(value = "/groups")
public class ChatController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Group> listGroups() {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Group addGroup(@RequestBody Group group) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Group deleteGroup(@PathVariable Long id) {
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Message> listMessages(@PathVariable Long id) {
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Message addMessage(@RequestBody Message message, @PathVariable Long id) {      
        return null;
    }

}
