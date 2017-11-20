package guilherme.com.testeretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by guilh on 19/11/2017.
 */

public interface Api {

    String BASE_URL = "http://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Produtos>> getProdutos();
}
