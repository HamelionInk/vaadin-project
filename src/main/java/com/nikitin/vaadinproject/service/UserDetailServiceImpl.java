package com.nikitin.vaadinproject.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.nikitin.vaadinproject.entity.Person;
import com.nikitin.vaadinproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

    PersonRepository personRepository;

    public UserDetailServiceImpl(PersonRepository personRepository) {
	this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Person person = personRepository.findByUsername(username);
	if(person != null) {
	    return new User(person.getUsername(), person.getPassword(), getGrantedAuthority(person));
	} else {
	    return null;
	}
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthority(Person person) {
	return Collections.<GrantedAuthority>singletonList(
			new SimpleGrantedAuthority(person.getRole().getEnumName()));
    }
}
