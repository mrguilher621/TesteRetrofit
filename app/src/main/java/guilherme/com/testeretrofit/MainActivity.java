package guilherme.com.testeretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<List<Produtos>> call = api.getProdutos();

        call.enqueue(new Callback<List<Produtos>>() {
            @Override
            public void onResponse(Call<List<Produtos>> call, Response<List<Produtos>> response) {

                List<Produtos> produtos = response.body();

                String[] produtosLista = new String[produtos.size()];

                for (int i = 0; i < produtos.size(); i++){

                    produtosLista[i] = produtos.get(i).getName();
                }

                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,produtosLista));

            }

            @Override
            public void onFailure(Call<List<Produtos>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
