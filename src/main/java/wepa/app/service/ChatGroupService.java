/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChatGroupService {
    
    
    public ArrayList<String> createTags(String tags){
        String[] tagsArray = tags.split(",");
        ArrayList<String> tagList = new ArrayList();
        
        for (String tag : tagsArray) {
            tag = tag.trim();
            tagList.add(tag);
        }
        return tagList;
    }
}
