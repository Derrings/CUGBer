package edu.cugb.jts.cugber.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data stored in redis, which shows users' information in token.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenInf {
    private boolean identity;
    private int id;
    private int authority;
}
