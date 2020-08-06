package pl.lacquer.lacquerapp.common;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Marcin Zając on 2020-08-04
 */
@Data
@NoArgsConstructor
public class QueryResult<T> {

    private long totalRecords;
    private int page;
    private int pageSize;
    private List<T> results;

    public QueryResult(List<T> objects, long totalRecord, int page) {
        this.page = page;
        this.results = objects;
        this.totalRecords = totalRecord;
        this.pageSize = objects.size();
    }
}
