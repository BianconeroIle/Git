package com.example.ilijaangeleski.gitrepos.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilijaangeleski.gitrepos.CircleTransform;
import com.example.ilijaangeleski.gitrepos.R;
import com.example.ilijaangeleski.gitrepos.model.GitRepo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public class RepositoriesRecyclerViewAdapter extends RecyclerView.Adapter<RepositoriesRecyclerViewAdapter.MyViewHolder> {
    private List<GitRepo> repos;
    private int layoutResourceId;
    private OnUserItemClick listener;
    public RepositoriesRecyclerViewAdapter(List<GitRepo> repos, int layoutResourceId) {
        this.repos = repos;
        this.layoutResourceId = layoutResourceId;
    }

    public interface OnUserItemClick {
        void onRepositoryClick(GitRepo user, ImageView profileImage);
    }

    @Override
    public RepositoriesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RepositoriesRecyclerViewAdapter.MyViewHolder holder, int position) {
        final GitRepo current = repos.get(position);
        holder.repositoryName.setText(current.getName());
        holder.description.setText(current.getDescription());
        holder.numberForks.setText(current.getForks());
        Picasso.with(holder.avatar.getContext()).load(current.getOwner().getAvatarUrl())
                .transform(new CircleTransform()).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round)
                .into(holder.avatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onRepositoryClick(current, holder.avatar);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.repositoryName)
        TextView repositoryName;
        @BindView(R.id.numberForks)
        TextView numberForks;
        @BindView(R.id.description)
        TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public void setOnUserItemClick(OnUserItemClick listener) {
        this.listener = listener;
    }
}
