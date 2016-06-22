package com.example.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class FooResource extends ResourceSupport {
    private String uuid;
    private String bar;
    private String calculatedBar;
}
