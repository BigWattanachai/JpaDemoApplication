package com.example.jpademo.controllers;

import com.example.jpademo.entities.Member;
import com.example.jpademo.models.MembersSearchCriteria;
import com.example.jpademo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public Page<Member> findAllMembers(
            @RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", value = "per_page") Integer perPage,
            @RequestParam(required = false, defaultValue = "ASC", value = "order") Sort.Direction direction,
            @RequestParam(required = false, defaultValue = "id", value = "sort") String sort,
            @RequestParam(required = false, value = "id") Long searchID,
            @RequestParam(required = false, value = "first_name") String firstName,
            @RequestParam(required = false, value = "last_name") String lastName,
            @RequestParam(required = false, value = "nickname") String nickname) {
        MembersSearchCriteria searchCriteria = MembersSearchCriteria.builder().page(page).perPage(perPage)
                .direction(direction).sort(sort).id(searchID).firstName(firstName).lastName(lastName)
                .nikename(nickname).build();
        return memberService.findAllMembers(searchCriteria);
    }
}
