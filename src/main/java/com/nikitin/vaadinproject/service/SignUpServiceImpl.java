package com.nikitin.vaadinproject.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.nikitin.vaadinproject.entity.Person;
import com.nikitin.vaadinproject.repository.PersonRepository;
import com.nikitin.vaadinproject.util.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final PersonRepository personRepository;

    public SignUpServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void savePerson(Map<String, String> dataPerson) {
        if(checkPerson(dataPerson)) {
            Person person = new Person(
                            dataPerson.get("username"),
                            dataPerson.get("first_name"),
                            dataPerson.get("last_name"),
                            Role.USER,
                            dataPerson.get("age"),
                            dataPerson.get("password"),
                            dataPerson.get("email")
            );
            personRepository.save(person);
            System.out.println("save");
        }
    }

    @Override
    public boolean checkPerson(Map<String, String> dataPerson) {
        boolean flag = true;
        for (String item : dataPerson.values()) {
            if (Objects.equals(item, "")) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
