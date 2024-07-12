package com.springkafka.task1.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.springkafka.task1.entity.User;
import com.springkafka.task1.repo.UserRepository;
//import com.springkafka.task1.repo.UserRepo;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	
    private static final String TOPIC = "kafka-spring-producer";


//	public void sendMessageToTopic(Long message) {
//		User user=repo.findById(message).orElse(null);
//		kafkaTemplate.send(TOPIC, user);
//		System.out.println("send topic "+user.getName());
//	}
    
    public void sendMessageToTopic(User message) {
    
    kafkaTemplate.send(TOPIC,message);
    System.out.println("send topic "+message.getName());
    }
    
    public String getUserById( Long id) {
        Optional<User> user = userRepository.findById(id);
        User us= user.get();
        kafkaTemplate.send(TOPIC,us);
        System.out.println(" in producer class================= "+us.getName());
       return "Send User in kafka producer ";
    }
    
    
    public String getAllUser() {
    	List<User> list =new ArrayList<>();
    	list=userRepository.findAll();
    	for(User ur:list) {
    		System.out.println("========== "+ur.getName()+"====================");
    		kafkaTemplate.send(TOPIC, ur);
    	}
    	return "all users are send into kafka ";
    }
    
    
    
    
}
