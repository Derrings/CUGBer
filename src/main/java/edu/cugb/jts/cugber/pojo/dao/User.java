package edu.cugb.jts.cugber.pojo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String name;
    private String sex;
    private Boolean handsome;
}
