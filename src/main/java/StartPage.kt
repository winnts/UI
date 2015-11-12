/**
 * Created by adyachenko on 11.11.15.
 */
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by adyachenko on 11.11.15.
 */
@WebServlet(name = "Start Page", value = "/startpage")
class Ui : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val client = ConnectAsTransport.connectToEs("main-cluster", "10.32.18.31", 9303);
        res.writer.write(EsSearch.searchByTerm(client));
            }
}