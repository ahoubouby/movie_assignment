package com.ahoububy.movies.exceptions;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder

public class ApiError implements Serializable {
    private static long serialVersionUID = 01213L;
    private String msg;
    private int code;

}
