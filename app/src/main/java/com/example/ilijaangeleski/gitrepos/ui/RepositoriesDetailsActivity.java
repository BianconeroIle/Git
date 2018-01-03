package com.example.ilijaangeleski.gitrepos.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilijaangeleski.gitrepos.CircleTransform;
import com.example.ilijaangeleski.gitrepos.MyApp;
import com.example.ilijaangeleski.gitrepos.R;
import com.example.ilijaangeleski.gitrepos.adapter.SubscribersRecyclerViewAdapter;
import com.example.ilijaangeleski.gitrepos.di.components.DaggerSubscribersActivityComponent;
import com.example.ilijaangeleski.gitrepos.di.modules.SubscribersDetailsActivityModule;
import com.example.ilijaangeleski.gitrepos.model.GitRepo;
import com.example.ilijaangeleski.gitrepos.presenter.SubscribersRepositoriesPresenter;
import com.example.ilijaangeleski.gitrepos.view.SubscribersView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public class RepositoriesDetailsActivity extends AppCompatActivity implements SubscribersView {
    public static final String REPOSITORY_EXTRA = "repository";

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.repositoryName)
    TextView repositoryName;
    @BindView(R.id.subscribers)
    TextView subscribers;
    @BindView(R.id.listOfSubscribers)
    RecyclerView recyclerListOfSubscribers;
    @Inject
    SubscribersRepositoriesPresenter presenter;
    private SubscribersRecyclerViewAdapter adapter;
    private GitRepo gitRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_details_activity);
        ButterKnife.bind(this);
        createDependencies();

        if (getIntent().getExtras() != null
                && getIntent().hasExtra(REPOSITORY_EXTRA)) {
            gitRepo = (GitRepo) getIntent().getExtras().getSerializable(REPOSITORY_EXTRA);
        }
        initView();
        presenter.fetchSubscribers(gitRepo.getSubscribersUrl());
    }

    private void createDependencies(){
        DaggerSubscribersActivityComponent
                .builder()
                .subscribersDetailsActivityModule(new SubscribersDetailsActivityModule(this))
                .baseComponent(MyApp.getApp().getBaseComponent())
                .build()
                .inject(this);
    }

    public void initView() {
        adapter = new SubscribersRecyclerViewAdapter(presenter.getSubscribers());
        recyclerListOfSubscribers.setAdapter(adapter);
        repositoryName.setText(gitRepo.getName());
        Picasso.with(this).load(gitRepo.getOwner().getAvatarUrl())
                .transform(new CircleTransform())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .fit()
                .into(avatar);

    }

    @Override
    public void updateView(int total) {
        subscribers.setText(total + "");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorGettingSubscribers() {
        Toast.makeText(this, R.string.error_getting_subscribers, Toast.LENGTH_LONG).show();
    }
}
