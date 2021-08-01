package com.manhcode.rest.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
//    Basic: use difference URL
    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return  new PersonV1("Nguyen Manh");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return  new PersonV2(new Name("Nguyen","Manh"));
    }

//C2    Advance: use same URL with another verson: http://localhost:8080/person/param?version=2

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return  new PersonV1("Nguyen Manh");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return  new PersonV2(new Name("Nguyen","Manh"));
    }

//    C3 su dung header. Need add X-API-VERSION into header of request
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return  new PersonV1("Nguyen Manh");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return  new PersonV2(new Name("Nguyen","Manh"));
    }

//  C4: Use produce. Need add Accept is "application/vnd.company.app-v1+json" in header request

    @GetMapping(value = "/person/produce", produces = "application/vnd.company.app-v1+json")
    public PersonV1 produceV1() {
        return  new PersonV1("Nguyen Manh");
    }

    @GetMapping(value = "/person/produce", produces = "application/vnd.company.app-v2+json")
    public PersonV2 produceV2() {
        return  new PersonV2(new Name("Nguyen","Manh"));
    }

}
