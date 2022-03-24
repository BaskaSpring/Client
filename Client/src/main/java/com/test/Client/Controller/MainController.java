package com.test.Client.Controller;

import com.google.gson.Gson;
import com.google.protobuf.Timestamp;
import com.id.grpc.Test;
import com.id.grpc.TestMessageServiceGrpc;
import com.test.Client.Payloads.Dates;
import com.test.Client.Payloads.ListMessages;
import com.test.Client.Payloads.Message;
import com.test.Client.Payloads.Messages;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MainController {


    @PostMapping("/add")
    public ResponseEntity<?> addMessage(@Validated @RequestBody Message message) throws ParseException {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:7000").usePlaintext().build();
        TestMessageServiceGrpc.TestMessageServiceBlockingStub stub = TestMessageServiceGrpc.newBlockingStub(channel);
        Test.SendMessageRequest request = Test.SendMessageRequest.newBuilder()
                .setMessage(message.getMessage())
                .build();
        Test.SendMessageResponse response = stub.sendMessage(request);
        channel.shutdownNow();
        return ResponseEntity.ok().body(response.getAnswer());
    }

    @GetMapping("/return")
    public ResponseEntity<?> returnMessages(@Validated @RequestBody Dates dates) throws ParseException {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:7000").usePlaintext().build();
        TestMessageServiceGrpc.TestMessageServiceBlockingStub stub = TestMessageServiceGrpc.newBlockingStub(channel);
        Test.ReturnMessageRequest request = Test.ReturnMessageRequest.newBuilder()
                .setStartDate(Timestamp.newBuilder().setSeconds(dates.getStartDate().getEpochSecond()).build())
                .setEndDate(Timestamp.newBuilder().setSeconds(dates.getEndDate().getEpochSecond()).build())
                .build();
        Test.ReturnMessageResponse response = stub.returnMessage(request);
        channel.shutdownNow();
        ArrayList<Messages> messagesList = new ArrayList<>();
        for (int i = 0; i <response.getMesssageCount(); i++) {
            Messages messages = new Messages();
            messages.setId(response.getMesssage(i).getId());
            messages.setMessage(response.getMesssage(i).getMessage());
            messages.setTimestamp(Instant.ofEpochSecond(response.getMesssage(i).getDate().getSeconds()));
            messagesList.add(messages);
        }
        ListMessages listMessages = new ListMessages(messagesList);
        Gson gson = new Gson();
        return ResponseEntity.ok().body(gson.toJson(listMessages));
    }


}
