package wepa.app.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wepa.app.domain.Group;
import wepa.app.domain.Message;
import wepa.app.repo.GroupRepo;
import wepa.app.service.AccountService;

@Controller
@RequestMapping(value = "/groups")
public class ChatController {

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String listGroups(Model model) {
        model.addAttribute("groups", groupRepo.findAll());
        return "groups";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addGroup(@RequestParam String topic) {
        Group group = new Group();
        group.setTopic(topic);
        group.getParticipants().add(accountService.getAccount());
        return "redirect:/groups";
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
