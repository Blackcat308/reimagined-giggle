package com.laocat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Lao Cat
 * @Date: 2020/5/13 15:20
 */
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {

    @Id
    Long id;

    String userName;

    String passWord;

    List<SysRole> roles;

}
