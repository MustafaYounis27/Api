package com.example.myapi.Ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapi.Data.RetrofitClient;
import com.example.myapi.Models.NewsModel;
import com.example.myapi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment
{
    private View NewsFragment;

    private String category, country;

    private newsAdapter adapter;
    RecyclerView recyclerView;

    public NewsFragment(String category, String country)
    {
        this.category = category;
        this.country = country;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        NewsFragment=inflater.inflate ( R.layout.fragment_news,null );
        return NewsFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated ( savedInstanceState );
        initRecycler();
        getData(category,country);
    }

    private void getData(String category, String country)
    {
        RetrofitClient.getInstance ().getNews ( country,category,"b37eced752654858b1084bce0583a432" ).enqueue ( new Callback<NewsModel> () {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if(response.isSuccessful () && response.code () == 200)
                {
                    NewsModel newsModel = response.body ();
                    if(newsModel != null)
                    {
                        List<NewsModel.Items> items = newsModel.getArticles ();

                        adapter = new newsAdapter ( items );
                        recyclerView.setAdapter ( adapter );
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) 
            {
                Toast.makeText ( getContext (), t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );
    }

    private void initRecycler()
    {
        recyclerView = NewsFragment.findViewById ( R.id.recycler );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager ( getContext (),RecyclerView.VERTICAL,false );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration ( getContext (),DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager ( layoutManager );
        recyclerView.addItemDecoration ( dividerItemDecoration );
    }

    class newsAdapter extends RecyclerView.Adapter<newsAdapter.newsVH>
    {
        List<NewsModel.Items> newsModels;

        public newsAdapter(List<NewsModel.Items> newsModels)
        {
            this.newsModels = newsModels;
        }

        @NonNull
        @Override
        public newsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from ( getContext () ).inflate (R.layout.news_item,parent,false);
            return new newsVH ( view );
        }

        @Override
        public void onBindViewHolder(@NonNull newsVH holder, int position)
        {
            NewsModel.Items items = newsModels.get ( position );

            String image = items.getImage ();
            String title = items.getTitle ();
            String desc = items.getDesc ();
            final String url = items.getUrl ();

            holder.newsTitle.setText ( title );
            holder.newsDesc.setText ( desc );
            Picasso.get ()
                    .load ( image )
                    .error ( R.drawable.error )
                    .placeholder ( R.drawable.error )
                    .into ( holder.newsImage );
            holder.openPage.setOnClickListener ( new View.OnClickListener ()
            {
                @Override
                public void onClick(View v)
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                }
            } );
        }

        @Override
        public int getItemCount() {
            return newsModels.size();
        }

        class newsVH extends RecyclerView.ViewHolder
        {
            ImageView newsImage;
            TextView newsTitle,newsDesc;
            LinearLayout openPage;

            public newsVH(@NonNull View itemView)
            {
                super ( itemView );

                newsImage =itemView.findViewById ( R.id.news_image );
                newsTitle=itemView.findViewById ( R.id.news_title );
                newsDesc=itemView.findViewById ( R.id.news_desc );
                openPage=itemView.findViewById ( R.id.open_page );
            }
        }
    }
}
