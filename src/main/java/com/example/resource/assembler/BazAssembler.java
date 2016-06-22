package com.example.resource.assembler;

import com.example.model.Baz;
import com.example.resource.BazResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class BazAssembler extends ResourceAssemblerSupport<Baz, BazResource> {
    public BazAssembler() {
        super(Baz.class, BazResource.class);
    }

    @Override
    public BazResource toResource(Baz baz) {
        BazResource resource = instantiateResource(baz);
        resource.setAsdf(baz.getAsdf());
        resource.setCalculatedAsdf(translateAsdf(baz));
        return resource;
    }

    private String translateAsdf(Baz baz) {
        return (Integer.parseInt(baz.getAsdf()) * 2) + " CALCULATED";
    }
}

