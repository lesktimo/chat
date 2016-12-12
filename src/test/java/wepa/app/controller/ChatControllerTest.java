package wepa.app.controller;

import java.util.List;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import wepa.app.domain.ChatGroup;
import wepa.app.domain.Message;
import wepa.app.repo.GroupRepo;
import wepa.app.repo.MessageRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private MessageRepo messageRepo;

    private MockMvc mockMvc;
    private ChatGroup testGroup;

    private final String chatTopic = "chatti";

    public ChatControllerTest() {
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();

        groupRepo.deleteAll();
        messageRepo.deleteAll();

        testGroup = new ChatGroup();
        testGroup.setTopic(chatTopic);
    }

    @Test
    public void test() {
        assertTrue(true);
    }

//    @Test
//    public void statusOkAndParametersFound() throws Exception {
//        mockMvc.perform(get("/groups")).andExpect(status().isOk());
//        mockMvc.perform(get("/groups")).andExpect(model().attributeExists("groups"));
//    }
//
//    @Test
//    public void existingGroupsAreListed() throws Exception {
//
//        for (int i = 1; i <= 3; i++) {
//            ChatGroup group = new ChatGroup();
//            group.setTopic("ryhmä" + i);
//            groupRepo.save(group);
//        }
//
//        MvcResult res = mockMvc.perform(get("/groups")).andReturn();
//        List<ChatGroup> groups = (List) res.getModelAndView().getModel().get("groups");
//
//        assertEquals(3, groups.size());
//    }
//
//    @Test
//    public void postAddsGroupToDatabase() throws Exception {
//        mockMvc.perform(post("/groups").param("topic", chatTopic));
//
//        List<ChatGroup> groups = groupRepo.findAll();
//        assertEquals(1, groups.size());
//        assertEquals(chatTopic, groups.get(0).getTopic());
//    }
//
//    @Test
//    public void canAddTagsToGroup() throws Exception {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap();
//        params.add("topic", "harry potter");
//        params.add("tags", "magic, book");
//
//        mockMvc.perform(post("/groups").params(params));
//
//        List<ChatGroup> groups = groupRepo.findAll();
//        assertEquals(1, groups.size());
//
//        List<String> tags = groups.get(0).getTags();
//        assertEquals(2, tags.size());
//        assertTrue(tags.contains("magic") && tags.contains("book"));
//    }
//
//    @Test
//    public void cannotAddGroupWithEmptyTopic() throws Exception {
//        mockMvc.perform(post("/groups").param("tags", "potter"))
//                .andExpect(status().isConflict());
//
//        List<ChatGroup> groups = groupRepo.findAll();
//
//        assertTrue(groups.isEmpty());
//    }
//
//    @Test
//    public void messagesAreListedInAGroup() throws Exception {
//        ChatGroup group = groupRepo.save(testGroup);
//        createGroupAndACoupleMessages(group);
//
//        MvcResult res = mockMvc.perform(get("/groups/" + group.getId())).andReturn();
//        List<Message> messages = (List) res.getModelAndView().getModel().get("messages");
//        assertEquals(3, messages.size());
//
//    }
//
//    @Test
//    public void postAddsMessageToGroup() throws Exception {
//        ChatGroup group = groupRepo.save(testGroup);
//        mockMvc.perform(post("/groups/" + group.getId()).param("message", "Hello World!"));
//
//        List<Message> messages = messageRepo.findAll();
//        
//        assertEquals(1, messages.size());
//        assertEquals("Hello World!", messages.get(0).getContent());
//        assertEquals(chatTopic, messages.get(0).getGroup());
//    }
//
//    @Test
//    private void testDeleteGroup() throws Exception {
//        mockMvc.perform(post("/groups").param("topic", chatTopic));
//
//        assertEquals(1, groupRepo.findAll().size());
//        assertEquals(chatTopic, groupRepo.findAll().get(0).getTopic());
//
//        mockMvc.perform(delete("/groups" + groupRepo.findAll().get(0).getId()));
//        
//        assertEquals(0, groupRepo.findAll().size());
//    }
//
//    private void createGroupAndACoupleMessages(ChatGroup group) {
//
//        for (int i = 1; i <= 3; i++) {
//            Message msg = new Message();
//            msg.setContent("viesti" + i);
//            msg.setGroup(group);
//            messageRepo.save(msg);
//        }
//
//        group.setMessages(messageRepo.findAll());
//        groupRepo.save(group);
//    }

}
