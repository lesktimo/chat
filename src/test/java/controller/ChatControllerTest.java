package controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import wepa.app.controller.ChatController;
import wepa.app.domain.ChatGroup;
import wepa.app.domain.Message;
import wepa.app.repo.GroupRepo;
import wepa.app.repo.MessageRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChatController.class)
public class ChatControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private MessageRepo messageRepo;

    private MockMvc mockMvc;
    private ChatGroup testGroup;

    public ChatControllerTest() {
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        groupRepo.deleteAll();
        
        testGroup = new ChatGroup();
        testGroup.setTopic("chatti");
    }

    @Test
    public void statusOkAndParametersFound() throws Exception {
        mockMvc.perform(get("/groups")).andExpect(status().isOk());
        mockMvc.perform(get("/groups")).andExpect(model().attributeExists("groups", "tags"));
    }

    @Test
    public void existingGroupsAreListed() throws Exception {

        for (int i = 0; i <= 3; i++) {
            ChatGroup group = new ChatGroup();
            group.setTopic("ryhmä" + i);
            groupRepo.save(group);
        }

        MvcResult res = mockMvc.perform(get("/groups")).andReturn();
        List<ChatGroup> groups = (List) res.getModelAndView().getModel().get("groups");

        assertEquals(3, groups.size());
    }

    @Test
    public void postAddsGroupToDatabase() throws Exception {
        mockMvc.perform(post("/groups").param("group", "chatti"))
                .andExpect(redirectedUrl("/groups"));

        List<ChatGroup> groups = groupRepo.findAll();
        assertEquals("chatti", groups.get(0).getTopic());
    }

    @Test
    public void messagesAreListedInAGroup() throws Exception {
        ChatGroup group = groupRepo.save(testGroup);
        createGroupAndACoupleMessages(group);
        
        MvcResult res = mockMvc.perform(get("/groups/" + group.getId())).andReturn();
        List<Message> messages = (List) res.getModelAndView().getModel().get("messages");
        assertEquals(3, messages.size());

    }
    
    @Test
    public void postAddsMessageToGroup() throws Exception {
        ChatGroup group = groupRepo.save(testGroup);
        mockMvc.perform(post("/groups/" + group.getId()).param("message", "viesti"));
        
        List<Message> messages = messageRepo.findAll();
        assertEquals("viesti", messages.get(0).getContent());
        assertEquals("chatti", messages.get(0).getGroup());
        
    }

    private void createGroupAndACoupleMessages(ChatGroup group) {
        
        for (int i = 0; i <= 3; i++) {
            Message msg = new Message();
            msg.setContent("viesti" + i);
            msg.setGroup(group);
            messageRepo.save(msg);
        }
        
        group.setMessages(messageRepo.findAll());
        groupRepo.save(group);
    }
}
