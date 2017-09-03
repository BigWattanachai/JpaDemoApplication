package com.example.jpademo.services;

import com.example.jpademo.entities.Member;
import com.example.jpademo.models.MembersSearchCriteria;
import com.example.jpademo.repositories.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private MemberRepo memberRepo;

    @Autowired
    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }


    public Page<Member> findAllMembers(MembersSearchCriteria searchCriteria) {
        PageRequest pageRequest = genPageRequest(searchCriteria.getPage(), searchCriteria.getPerPage(),
                searchCriteria.getDirection(), searchCriteria.getSort());
        return memberRepo.findAll(getMemberSpecification(searchCriteria), pageRequest);
    }

    private PageRequest genPageRequest(int page, int limit, Sort.Direction direction, String sort) {
        return new PageRequest(page - 1, limit, new Sort(direction, sort));
    }

    private Specification<Member> getMemberSpecification(MembersSearchCriteria filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filter.getId()));
            }
            if (!StringUtils.isEmpty(filter.getFirstName())) {
                predicates.add(cb.like(cb.lower(root.get("firstName")),
                        "%" + filter.getFirstName().toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(filter.getLastName())) {
                predicates.add(cb.like(cb.lower(root.get("lastName")),
                        "%" + filter.getLastName().toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(filter.getNikename())) {
                predicates.add(cb.like(cb.lower(root.get("nickName")),
                        "%" + filter.getNikename().toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
