package com.task.permission.service;

import com.task.permission.model.Person;
import com.task.permission.repository.PersonRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    public CustomUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person= personRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(username+" kullanıcısı bulunamamıştır"));
        return new User(person.getUsername(),person.getPassword(),getAuthorities(person));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Person person){
        List<GrantedAuthority> result=new ArrayList<>();
        result.add(new SimpleGrantedAuthority(person.getRole().name()));
        return result;
    }
}
