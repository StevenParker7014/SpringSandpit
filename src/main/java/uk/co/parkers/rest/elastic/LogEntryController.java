package uk.co.parkers.rest.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uk.co.parkers.domain.web.Criteria;
import uk.co.parkers.repository.elastic.LogEntryRepository;

@RestController
@RequestMapping("/rest/logs")
public class LogEntryController {

	@Autowired
	LogEntryRepository logRepository;

	@RequestMapping(path = { "/list/", "/list" }, method = RequestMethod.POST)
	public Criteria list(@RequestBody Criteria criteria) {

		SearchQuery searchQuery = null; 
		NativeSearchQueryBuilder dBuilder = new NativeSearchQueryBuilder();
		
		return null;
	}

}
