package com.example.resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class BazResource extends ResourceSupport {
    private String asdf;
    private String calculatedAsdf;
}
