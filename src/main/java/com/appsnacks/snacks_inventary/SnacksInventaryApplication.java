package com.appsnacks.snacks_inventary;

import com.appsnacks.snacks_inventary.models.Snack;
import com.appsnacks.snacks_inventary.repository.iSnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnacksInventaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnacksInventaryApplication.class, args);
	}
/* 
      @Autowired
      private iSnackRepository snackrepo;
    @Override
    public void run(String... args) throws Exception {
       Snack s1 = new Snack(423, "Dorito", 3, "7-2030");
       Snack s2 = new Snack(89, "Pringless", 5,"3-2027");
       Snack s3 = new Snack(32,"Lays",2,"1-2022");
        snackrepo.save(s1);
        snackrepo.save(s2);
        snackrepo.save(s3);
    }*/
    
}
