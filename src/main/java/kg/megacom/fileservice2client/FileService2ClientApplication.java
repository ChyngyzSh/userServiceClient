package kg.megacom.fileservice2client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("kg.megacom.fileservice2client.microservices")
public class FileService2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileService2ClientApplication.class, args);
    }

}
