//package wepa.app.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//import wepa.app.domain.Message;
//
//@Service
//public class MessageService {
//
//    @Autowired
//    private SimpMessagingTemplate template;
//
//    public void addMessage(Message m) {
//        this.template.convertAndSend("/channel/" + m.getGroupId(), m);
//    }
//}
