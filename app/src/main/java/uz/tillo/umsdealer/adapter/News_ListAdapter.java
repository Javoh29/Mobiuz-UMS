package uz.tillo.umsdealer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import uz.tillo.umsdealer.Activities.models.NewsData;
import uz.tillo.umsdealer.R;


public class News_ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private ArrayList<NewsData> data;
    private static OnItemClickListener onItemClickListener;
    private Context context;

    public News_ListAdapter(Context context, ArrayList<NewsData> data) {
        this.data = data;
        this.context = context;
    }

    public static void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        News_ListAdapter.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(data.get(position));
        viewHolder.detailed.setTag(data.get(position).getUrl());
        viewHolder.detailed.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        TextView news_title;
        TextView news_text;
        Button detailed;

        NewsData newsData;
        private Object NewsData;

        public ViewHolder(View itemView) {
            super(itemView);
            news_text = itemView.findViewById(R.id.news_text);
            news_title = itemView.findViewById(R.id.news_title);
            detailed=itemView.findViewById(R.id.detailed);

            itemView.setOnClickListener(this);
        }

        public void setData(NewsData data) {
            newsData = data;
            news_title.setText(newsData.getNews_title());
            news_text.setText(newsData.getNews_text());


        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick((uz.tillo.umsdealer.Activities.models.NewsData) NewsData);
            }
        }
    }

    @Override
    public void onClick(View v) {
        //clicked button
       String mURL = (String) v.getTag();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mURL));
        context.startActivity(i);
    }

    public interface OnItemClickListener {
        void onItemClick(NewsData data);
    }
}
