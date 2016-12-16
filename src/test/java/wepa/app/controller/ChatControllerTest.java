package wepa.app.controller;

import java.sql.Timestamp;
import java.util.List;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import wepa.app.domain.ChatGroup;
import wepa.app.domain.Message;
import wepa.app.repo.GroupRepo;
import wepa.app.repo.MessageRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
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
    public void postAddsGroupToDatabase() throws Exception {

//        mockMvc.perform(post("/groups").param("topic", chatTopic));
//
//        List<ChatGroup> groups = groupRepo.findAll();
//        assertEquals(1, groups.size());
//        assertEquals(chatTopic, groups.get(0).getTopic());
//        
//        mockMvc.perform(get("/groups/" + groups.get(0).getId())).andExpect(status().isOk());

    }

//    @Test
//    public void postAddsMessageToGroup() throws Exception {
//        ChatGroup group = groupRepo.save(testGroup);
//
//        String jsonMessage = new JSONObject()
//                .put("content", "Hello World!")
//                .put("timestamp", new Timestamp(System.currentTimeMillis()))
//                .toString();
//        
//        mockMvc.perform(post("/groups/" + group.getId())
//                .contentType("application/json").content(jsonMessage));
//        
//        List<Message> messages = messageRepo.findAll();
//
//        assertEquals(1, messages.size());
//        assertEquals("Hello World!", messages.get(0).getContent());
//        assertEquals(chatTopic, messages.get(0).getGroup());
//    }

    @Test
    public void testDeleteGroup() throws Exception {
        mockMvc.perform(post("/groups").param("topic", chatTopic));

        assertEquals(1, groupRepo.findAll().size());

        ChatGroup group = groupRepo.findAll().get(0);
        mockMvc.perform(delete("/groups/" + group.getId()));

        assertEquals(0, groupRepo.findAll().size());
    }

}
