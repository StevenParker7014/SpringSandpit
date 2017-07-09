package uk.co.parkers.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import uk.co.parkers.model.elastic.LogEntry;

public interface LogEntryRepository extends ElasticsearchRepository<LogEntry, String> {

}
