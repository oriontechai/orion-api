package com.orion.orionapi.controller;

import com.orion.orionapi.dto.MessageDto;
import com.orion.orionapi.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @CrossOrigin()
    @PostMapping("/get-completion")
    public ResponseEntity<MessageDto> getCompletion(@RequestBody List<MessageDto> messages){
        return ResponseEntity.ok(chatService.getCompletion(messages));
    }

    @CrossOrigin()
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hola este es un test");
    }
}
