package com.example.employee.exception.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String field;
    private String error;
    private String message;
}
