import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 * Created by adyachenko on 11.11.15.
 */
public class EsSearch {
    static Client client;

    public static SearchResponse scrollES (QueryBuilder fqb, String index, Integer size) {
        SearchResponse scrollResp = client.prepareSearch(index)
                .setSearchType(SearchType.SCAN)
                .setScroll(new TimeValue(60000))
                .setQuery(fqb)
                .setSize(size)
                .execute().actionGet();
        return scrollResp;
    }

    public static String searchES (QueryBuilder fqb, String index, Integer searchSize) {
        String output = "";
        SearchResponse scrollResp = scrollES(fqb, index, searchSize);
        while (true) {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                //output = output + hit.getId() + " ";
            }
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
            if (scrollResp.getHits().getHits().length == 0) {
                break;
            }
        }
        return output;
     }




}
