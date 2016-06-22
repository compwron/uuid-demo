package com.example.resource.assembler;

import com.example.model.Foo;
import com.example.resource.FooResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FooAssembler extends ResourceAssemblerSupport<Foo, FooResource> {
    public FooAssembler() {
        super(Foo.class, FooResource.class);
    }

    @Override
    public FooResource toResource(Foo foo) {
        FooResource resource = instantiateResource(foo);
        resource.setUuid(foo.getUuid().toString());
        resource.setBar(foo.getBar() + " CALCULATED");
        resource.setCalculatedBar(foo.getBar() + " ALSO CALCULATED");
        return resource;
    }
}


