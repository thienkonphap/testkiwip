package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@AllArgsConstructor
public class FizzBuzzController {
    @Autowired
    private final FizzBuzzRepository fizzBuzzRepository;
    @Autowired
    private final MongoTemplate mongoTemplate;

    @GetMapping("/fizz")
    public List<String> fizz(@RequestParam(value = "int1",defaultValue = "world") int int1,
                             @RequestParam(value = "int2",defaultValue = "world") int int2,
                             @RequestParam(value = "limit",defaultValue = "world") int limit,
                             @RequestParam(value = "str1",defaultValue = "world") String str1,
                             @RequestParam(value = "str2",defaultValue = "world") String str2
                       ){
        List<String> res = new ArrayList<String>();
        for (int i = 1; i < limit;i++) {
            if (i % (int2 * int1) == 0) {
                res.add((str1 + str2));
            } else if (i % int2 == 0) {
                res.add(str2);
            } else if (i % int1 == 0) {
                res.add(str1);
            } else {
                res.add(String.valueOf(i));
            }
        }
        Query query = new Query().addCriteria(Criteria.where("int1").is(int1))
                .addCriteria((Criteria.where("int2").is(int2)))
                .addCriteria((Criteria.where("limit").is(limit)))
                .addCriteria((Criteria.where("str1").is(str1)))
                .addCriteria((Criteria.where("str2").is(str2)))
                ;
        FizzBuzz check = mongoTemplate.findOne(query,FizzBuzz.class);
        if (check != null){
            check.setCount(check.getCount()+1);
            mongoTemplate.save(check);
        }
        else{
            FizzBuzz fizz = new FizzBuzz(int1,int2,limit,str1,str2);
            this.fizzBuzzRepository.insert(fizz);
        }
        return res;
    }
    @GetMapping("/")
    public FizzBuzz getMostRequest(){
        return fizzBuzzRepository.findAll(Sort.by(Sort.Direction.DESC, "count")).get(0);
    }
}
