package com.nikitin.vaadinproject.service;

import java.util.List;
import java.util.Map;

import com.nikitin.vaadinproject.entity.Person;

public interface SignUpService {
    void savePerson(Map<String, String> dataPerson);
    boolean checkPerson(Map<String, String> dataPerson);
}
