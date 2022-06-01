package com.example.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("FizzBuzz")
public class FizzBuzz {
    @Id
    private String id;
    private int int1;
    private int int2;
    private int limit;
    private String str1;
    private String str2;
    private int count;

    public int getCount() {
        return count;
    }

    public FizzBuzz(int int1, int int2, int limit, String str1, String str2) {
        this.int1 = int1;
        this.int2 = int2;
        this.limit = limit;
        this.str1 = str1;
        this.str2 = str2;
        this.count = 1;

    }
    public int getInt1(){
        return this.int1;
    }
    public void setInt1(int int1){
        this.int1 = int1;
    }
    public int getInt2(){
        return this.int2;
    }
    public void setInt2(int int2){
        this.int2 = int2;
    }
    public int getLimit(){
        return this.limit;
    }
    public void setLimit(int limit){
        this.limit = limit;
    }
    public String getStr1(){
        return this.str1;
    }
    public String getStr2(){
        return this.str2;
    }
    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }
}
