/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import wepa.app.domain.Message;

@Service
public class MessageService {
    
    
    @Autowired
    private SimpMessagingTemplate template;
    
    public void addMessage(Message m) {
        this.template.convertAndSend("/groups/" + m.getGroup().getId(), m);
    }
}
