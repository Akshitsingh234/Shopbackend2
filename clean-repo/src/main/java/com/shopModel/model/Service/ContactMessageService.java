package com.shopModel.model.Service;

import com.shopModel.model.Model.ContactMessage;
import com.shopModel.model.Repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ContactMessageService {
    @Autowired
    private ContactMessageRepository repository;
    public ContactMessage saveMessage(ContactMessage message) {
        return repository.save(message);
    }

    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }
    public void deleteQuery(Long id) {
        repository.deleteById(id);
    }
}
