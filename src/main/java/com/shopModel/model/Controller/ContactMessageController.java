package com.shopModel.model.Controller;

import com.shopModel.model.Model.ContactMessage;
import com.shopModel.model.Service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactMessageController {
    @Autowired
    private ContactMessageService service;

    @PostMapping("/submit")
    public ContactMessage submitMessage(@RequestBody ContactMessage message) {
        return service.saveMessage(message);
    }
    @GetMapping("/all")
    public List<ContactMessage> getMessages() {
        return service.getAllMessages();
    }
    @DeleteMapping("/{id}")
    public void deleteQuery(@PathVariable Long id) {
        service.deleteQuery(id);
    }

}
