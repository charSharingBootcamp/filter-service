package com.charsharing.bootcamp.filter.domain;

import lombok.Data;

@Data
public final class FilterResponse {
    private boolean valid = false;
    private String filteredText;

    public FilterResponse(String filteredText) {

        this.filteredText = filteredText;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
