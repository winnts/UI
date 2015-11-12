import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 * Created by adyachenko on 11.11.15.
 */
public class EsSearch {

    public static String searchByTerm(Client client) {
        String output = "";
        FilteredQueryBuilder fqb = QueryBuilders.filteredQuery(QueryBuilders.matchAllQuery(), FilterBuilders.termFilter("host", "windowparts.com"));

        SearchResponse scrollResp = client.prepareSearch("marketing*")
                .setSearchType(SearchType.SCAN)
                .setScroll(new TimeValue(60000))
                .setQuery(fqb)
                .setSize(3)
                .execute().actionGet();
        System.out.println(scrollResp.getHits().getTotalHits());
        while (true) {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                output = output + hit.getId() + " ";
            }
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
            if (scrollResp.getHits().getHits().length == 0) {
                break;
            }
        }
        System.out.println();
        return output;
     }


}
