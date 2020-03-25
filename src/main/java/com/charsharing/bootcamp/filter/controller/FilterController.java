package com.charsharing.bootcamp.filter.controller;

import com.charsharing.bootcamp.filter.domain.FilterRequest;
import com.charsharing.bootcamp.filter.domain.FilterResponse;
import com.charsharing.bootcamp.filter.service.FilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FilterController {

    private FilterService filterService;
    public FilterController(FilterService filterService)
    {
        this.filterService = filterService;
    }

    /**
     * Filters profanities out of a given text
     * @param request a request containing the text to filter
     * @return a http response containing a FilterResponse with the filtered text
     */
    @PostMapping(value="/filter")
    public ResponseEntity<FilterResponse> profanityFilter(@RequestBody FilterRequest request) {
        String filteredText = filterService.filterText(request.getText());
        FilterResponse filterResponse = new FilterResponse(filteredText);
        filterResponse.setValid(filterService.isValid());
        if(!filterService.isValid()) {
            filterResponse.setFilteredText(request.getText());
        }
        return ResponseEntity.ok(filterResponse);
    }

}
