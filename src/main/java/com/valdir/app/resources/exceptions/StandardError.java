package com.valdir.app.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
    private static final Long SerialVersionUID = 1L;

    private Integer status;
    private Long timestamp;
    private String msg;
}
