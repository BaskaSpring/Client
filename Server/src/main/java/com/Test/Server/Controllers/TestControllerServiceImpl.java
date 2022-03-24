package com.Test.Server.Controllers;

import com.Test.Server.Models.Message;
import com.Test.Server.Service.TestService;
import com.google.protobuf.Timestamp;
import com.id.grpc.Test;
import com.id.grpc.TestMessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.List;

@Component
public class TestControllerServiceImpl extends TestMessageServiceGrpc.TestMessageServiceImplBase {

    @Autowired
    TestService testService;


    @Override
    public void sendMessage(Test.SendMessageRequest request, StreamObserver<Test.SendMessageResponse> responseObserver) {
        String ans = testService.addMessage(request.getMessage());
        Test.SendMessageResponse response = Test.SendMessageResponse
                .newBuilder()
                .setAnswer(ans)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



    @Override
    public void returnMessage(Test.ReturnMessageRequest request, StreamObserver<Test.ReturnMessageResponse> responseObserver) {
        Test.ReturnMessageResponse.Builder productResponse = Test.ReturnMessageResponse.newBuilder();
        List<Message> messageList = testService.returnMessages(Instant.ofEpochSecond(request.getStartDate().getSeconds()),Instant.ofEpochSecond(request.getEndDate().getSeconds()));
        for (Message message: messageList){
            productResponse.addMesssage(Test.Messages.newBuilder().setId(message.getId()).setMessage(message.getMessage()).setDate(Timestamp.newBuilder().setSeconds((message.getTimestamp().getEpochSecond())).build()).build());
        }
        responseObserver.onNext(productResponse.build());
        responseObserver.onCompleted();
    }
}
