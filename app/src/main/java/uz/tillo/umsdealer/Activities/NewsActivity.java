package uz.tillo.umsdealer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;

import uz.tillo.umsdealer.Activities.models.NewsData;
import uz.tillo.umsdealer.R;
import uz.tillo.umsdealer.adapter.News_ListAdapter;

public class NewsActivity extends AppCompatActivity implements News_ListAdapter.OnItemClickListener{
    RecyclerView list;
    News_ListAdapter adapter;
    ArrayList<NewsData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(getResources().getString(R.string.news));

        load();

        list = findViewById(R.id.news_recyclerView);
        adapter = new News_ListAdapter(this, data);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        News_ListAdapter.setOnItemClickListener(this);
    }
    private void load() {
        data = new ArrayList<>();
        data.add(new NewsData(
                getString(R.string.tiklash_text_news),
                getString(R.string.tiklash_title_news),
                "https://mobi.uz/uz/news/2020/20865/"
        ));
        data.add(new NewsData(
                getString(R.string.tarif_text_news),
                getString(R.string.tarif_title_news),
                "https://mobi.uz/uz/news/2020/20863/"
        ));
        data.add(new NewsData(
                getString(R.string.TV_text_news),
                getString(R.string.TV_title_news),
                "https://mobi.uz/uz/news/2020/20655/"
        ));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemClick(NewsData data) {

    }
}
