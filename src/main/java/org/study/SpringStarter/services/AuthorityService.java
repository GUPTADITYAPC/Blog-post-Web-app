package org.study.SpringStarter.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.SpringStarter.models.Authority;
import org.study.SpringStarter.repositories.AuthorityRepository;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    public Optional<Authority> findById(Long id) {
        return authorityRepository.findById(id);
    }
}
