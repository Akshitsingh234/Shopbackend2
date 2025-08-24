package com.shopModel.model.Repository;

import com.shopModel.model.Model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {

}
