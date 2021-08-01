package com.manhcode.rest.demo.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public SomeBean findOne() {
        return  new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-lists")
    public List<SomeBean> finAll() {
        return Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value12", "value22", "value32"),
                new SomeBean("value13", "value23", "value33"));
    }

//    Dynamic filtering

    //ignore feild1
    @GetMapping("/dyfiltering")
    public MappingJacksonValue findOneDy() {
        SomeBeanDynamic st = new SomeBeanDynamic("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        FilterProvider filterProvider = new SimpleFilterProvider()
                                            .addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(st);
        mapping.setFilters(filterProvider);


        return  mapping;
    }

    //ignore feild 2
    @GetMapping("/dyfiltering-lists")
    public MappingJacksonValue finAllDy() {
        List<SomeBeanDynamic> list = Arrays.asList(new SomeBeanDynamic("value1", "value2", "value3"),
                new SomeBeanDynamic("value12", "value22", "value32"),
                new SomeBeanDynamic("value13", "value23", "value33"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filterProvider);

        return  mapping;
    }
}
