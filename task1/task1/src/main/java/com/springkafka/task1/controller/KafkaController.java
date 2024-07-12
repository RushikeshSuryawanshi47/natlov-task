package com.springkafka.task1.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springkafka.task1.entity.User;
import com.springkafka.task1.service.KafkaProducer;


@RestController
@RequestMapping("v1")
public class KafkaController {
	
	@Autowired
    KafkaProducer kafkaProducer;
	
	

    @GetMapping("/publish")
    public String postMessage(@RequestParam("name") final String name){
       // int no=Integer.valueOf(name);
    	Long id=new Long(10);
        User user= new User();
        
        user.setId(id);
        user.setName(name);
        user.setEmail("Rushi");
        kafkaProducer.sendMessageToTopic(user);
        System.out.println("message sent");

        return "Message Published Successfully";
    }
    
    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
    	System.out.println("===============================================================");
    	System.out.println(id );
    	 return  kafkaProducer.getUserById(id);
    	 
    }
    
    @GetMapping("/allUser")
    public String getAllUser() {
    	System.out.println("==============sending all users=========");
    	return kafkaProducer.getAllUser();
    }
    
       
}
