package wepa.app.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wepa.app.domain.Account;
import wepa.app.domain.ChatGroup;
import wepa.app.domain.Message;
import wepa.app.repo.AccountRepo;
import wepa.app.repo.GroupRepo;
import wepa.app.repo.MessageRepo;
import wepa.app.service.AccountService;
//import wepa.app.service.MessageService;

@Controller
@RequestMapping(value = "/groups")
public class ChatController {

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountService accountService;

//    @Autowired
//    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public String listGroups(Model model) {
        model.addAttribute("groups", groupRepo.findAll());
        long apu = groupRepo.count();
        model.addAttribute("apu", apu);

        return "groups";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addGroup(@RequestParam("topic") String topic) {
        ChatGroup group = new ChatGroup();
        group.setTopic(topic);
        groupRepo.save(group);
        return "redirect:/groups";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ChatGroup deleteGroup(@PathVariable Long id) {
        ChatGroup g = groupRepo.getOne(id);
        groupRepo.delete(g);
        return g;
    }

    @RequestMapping(value = "/mygroups", method = RequestMethod.GET)
    public String listMyGroups(Model model, Principal principal) {
        model.addAttribute("owngroups", accountService.findByUsername(principal.getName()).getChatGroups());
        return "owngroups";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String followGroup(Principal principal, @PathVariable Long id) {
        Account a = accountService.findByUsername(principal.getName());
        ChatGroup g = groupRepo.findOne(id);

        if (!a.getChatGroups().contains(g)) {
            a.getChatGroups().add(g);
            accountRepo.save(a);
        }

        return "redirect:/groups/mygroups";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getGroup(Model model, @PathVariable Long id, Principal principal) {
        model.addAttribute("messages", groupRepo.findOne(id).getMessages());
        model.addAttribute("groupId", id);
        model.addAttribute("username", principal.getName());
        model.addAttribute("topic", groupRepo.findOne(id).getTopic());

        return "chat";
    }

//    @MessageMapping("/messages")
//    public void handleMessage(Message message) throws Exception {
//        messageService.addMessage(message);
//        accountService.findByUsername(message.getUsername()).getMessages().add(message);
//        messageRepo.save(message);
//    }

}
