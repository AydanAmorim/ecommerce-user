package fiap.Challenge.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
@EnableDiscoveryClient
public class EcommerceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceUserApplication.class, args);
	}

}
