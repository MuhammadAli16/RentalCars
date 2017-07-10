package com.ma.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchOuter {

    @JsonProperty
    private SearchObject Search;

	public SearchObject getSearch() {
		return Search;
	}
	
}


