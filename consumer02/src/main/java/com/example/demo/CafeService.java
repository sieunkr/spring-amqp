package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class CafeService {

    void accept(CustomMessage c){
        System.out.println("accept");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
