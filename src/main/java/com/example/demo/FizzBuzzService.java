package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@AllArgsConstructor
@Service
public class FizzBuzzService {
    private final FizzBuzzRepository repository;
    public List<FizzBuzz> getMostRequest(){
        return repository.findAll();
    }
}
