package com.demo.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.Data;

/**
 * Created by KimYJ on 2017-08-10.
 */
@Data
@Component
@ConfigurationProperties("fruit")
public class FruitProperty {
    private List<Fruit> list;
}
