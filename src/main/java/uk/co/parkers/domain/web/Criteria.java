package uk.co.parkers.domain.web;

import java.util.ArrayList;
import java.util.List;

public abstract class Criteria<T> {
	
	private long totalResults;
	private int page, totalPages;
	private long offset;
	
	private T domainObject;

	private List<Row> results = new ArrayList<>();
	
	public Criteria(){}

	public Criteria(long totalResults, int page, int totalPages, long offset) {
		super();
		this.totalResults = totalResults;
		this.page = page;
		this.totalPages = totalPages;
		this.offset = offset;
	}

	public Criteria(long totalResults, int page, int totalPages, long offset, List<Row> results) {
		super();
		this.totalResults = totalResults;
		this.page = page;
		this.totalPages = totalPages;
		this.offset = offset;
		this.results = results;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public List<Row> getResults() {
		return results;
	}

	public void setResults(List<Row> results) {
		this.results = results;
	}

	public T getDomainObject() {
		return domainObject;
	}

	public void setDomainObject(T domainObject) {
		this.domainObject = domainObject;
	}
		
}
