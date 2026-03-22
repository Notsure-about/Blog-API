package com.example.Blog_API.Exception;

import lombok.Getter;
import lombok.Setter;


public class ResourceNotFoundException extends  RuntimeException{
    String resourceName;
    String fieldName;
    Long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName ,Long fieldValue ){
        super(resourceName + " not found with" + fieldName + ":" + fieldValue);

    }
}
