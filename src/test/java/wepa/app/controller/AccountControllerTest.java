//package wepa.app.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import wepa.app.domain.Account;
//import wepa.app.repo.AccountRepo;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("production")
//public class AccountControllerTest {
//
//    @Autowired
//    private WebApplicationContext webAppContext;
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private AccountRepo accountRepo;
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
//        accountRepo.deleteAll();
//    }
//
////    @Test
////    public void testGetAccount() throws Exception {
////        Account a = new Account();
////        a.setUsername("test");
////        a.setPassword("test");
////        a = accountRepo.save(a);
////        
////        mockMvc.perform(get("/account/" + a.getId())).andExpect(status().isOk());
////
////    }
//    
//    @Test
//    public void testRegistrationGet() throws Exception {
//        mockMvc.perform(get("/reg")).andExpect(status().isOk());
//    }
//}
