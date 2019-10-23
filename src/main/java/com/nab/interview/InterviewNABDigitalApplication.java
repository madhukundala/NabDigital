package com.nab.interview;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.nab.interview.repository")
public class InterviewNABDigitalApplication {


    static Logger logger = Logger.getLogger(InterviewNABDigitalApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(InterviewNABDigitalApplication.class, args);
    }


}
