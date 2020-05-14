package com.laocat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Author: Lao Cat
 * @Date: 2020/5/13 15:20
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable {

    @Id
    Long id;

    String roleName;

    String[] permissions;
}
