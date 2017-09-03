package com.example.jpademo;

import com.example.jpademo.entities.Member;
import com.example.jpademo.repositories.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class JpaDemoApplication {
    private final MemberRepo memberRepo;

    @Autowired
    public JpaDemoApplication(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            memberRepo.save(getMemberList());
            memberRepo.flush();
        };
    }

    private List<Member> getMemberList() {
        Member member1 = new Member();
        member1.setFirstName("Cherprang");
        member1.setLastName("Areekul");
        member1.setNickName("Cherprang");
        Member member2 = new Member();
        member2.setFirstName("Jennis");
        member2.setLastName("Oprasert");
        member2.setNickName("Jennis");
        Member member3 = new Member();
        member3.setFirstName("Praewa");
        member3.setLastName("Suthamphong");
        member3.setNickName("Music");
        return Arrays.asList(member1, member2, member3);
    }
}
