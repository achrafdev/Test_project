package com.viking.test_project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viking.test_project.Model.News;
import com.viking.test_project.R;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<News> newsList = new ArrayList<>();
    Context context;
    OnItemClickListener onItemClickListener;


    public NewsAdapter(ArrayList<News> newsList, Context context) {
        this.newsList.addAll(newsList);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder mViewHolder = (ViewHolder) holder;
        final News news = newsList.get(position);
        mViewHolder.textviewName.setText(news.getName());
        mViewHolder.textviewSubtitle.setText(news.getSubtitle());
        Picasso.get().load(news.getThumbnail()).into(mViewHolder.imageviewThumb);
        mViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(news);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void updateItem(int i, News news) {
        newsList.remove(i);
        newsList.add(i, news);
        notifyItemChanged(i);
    }

    public void clear() {
        newsList.clear();
        notifyDataSetChanged();
    }

    public void insertItems(ArrayList<News> listNewsTemp) {
        newsList.addAll(listNewsTemp);
        notifyItemRangeInserted(newsList.size() - listNewsTemp.size(), listNewsTemp.size());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addAll(ArrayList<News> news) {
        newsList.clear();
        newsList.addAll(news);
        notifyDataSetChanged();
    }

    public void removeItem(int pos) {
        newsList.remove(pos);
        notifyItemRemoved(pos);
    }

    public void reset(ArrayList<News> news) {
        this.newsList.clear();
        this.newsList.addAll(news);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(News news);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageviewThumb;
        TextView textviewName;
        TextView textviewSubtitle;
        ConstraintLayout container;

        ViewHolder(View view) {
            super(view);
            imageviewThumb = view.findViewById(R.id.thumb);
            textviewName = view.findViewById(R.id.name);
            textviewSubtitle = view.findViewById(R.id.subtitle);
            container = view.findViewById(R.id.container);
        }
    }

}
