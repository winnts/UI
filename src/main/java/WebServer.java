import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adyachenko on 12.11.15.
 */

@WebServlet(name = "Start Page", value = "/json")
public class WebServer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    List<EsIndex> esIndexes = new LinkedList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        response.setHeader("Access-Control-Max-Age", "86400");

        // 1. get received JSON data from request

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        EsIndex esIndex = mapper.readValue(json, EsIndex.class);
        response.setContentType("application/json");
        esIndexes.add(esIndex);
        mapper.writeValue(response.getOutputStream(), esIndexes);
    }
}

