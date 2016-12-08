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
import org.springframework.web.bind.annotation.ResponseBody;
import wepa.app.domain.Group;
import wepa.app.domain.Message;
import wepa.app.domain.Tag;
import wepa.app.repo.GroupRepo;
import wepa.app.repo.MessageRepo;
import wepa.app.repo.TagRepo;
import wepa.app.service.AccountService;

@Controller
@RequestMapping(value = "/groups")
public class ChatController {

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private TagRepo tagRepo;
    
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String listGroups(Model model) {
        model.addAttribute("groups", groupRepo.findAll());
        model.addAttribute("tags", tagRepo.findAll());
        return "groups";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addGroup(@RequestParam String topic, @RequestParam List<Tag> tagList) {
        Group group = new Group();
        group.setTopic(topic);
        group.setTags(tagList);
        group.getParticipants().add(accountService.getAccount());
        return "redirect:/groups";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Group deleteGroup(@PathVariable Long id) {
        Group g = groupRepo.getOne(id);
        groupRepo.delete(g);
        return g;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> listMessages(@PathVariable Long id) {
        return groupRepo.findOne(id).getMessages();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Message addMessage(@RequestBody Message message, @PathVariable Long id) {
        groupRepo.findOne(id).getMessages().add(message);
        messageRepo.save(message);
        return message;
    }

}
