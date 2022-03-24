package com.Test.Server;

import com.Test.Server.Controllers.TestControllerServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ServerApplication.class, args);
		Server server = ServerBuilder.forPort(7000)
				.addService(ApplicationContextHolder.getContext().getBean(TestControllerServiceImpl.class))
				.build();
		server.start();
		server.awaitTermination();
	}

}
