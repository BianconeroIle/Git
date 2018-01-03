package com.example.ilijaangeleski.gitrepos.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ilijaangeleski.gitrepos.MyApp;
import com.example.ilijaangeleski.gitrepos.R;
import com.example.ilijaangeleski.gitrepos.adapter.RepositoriesRecyclerViewAdapter;
import com.example.ilijaangeleski.gitrepos.di.components.DaggerRepositoriesActivityComponent;
import com.example.ilijaangeleski.gitrepos.di.modules.RepositoriesActivityModule;
import com.example.ilijaangeleski.gitrepos.model.GitRepo;
import com.example.ilijaangeleski.gitrepos.presenter.RepositoriesPresenter;
import com.example.ilijaangeleski.gitrepos.view.RepositoriesView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesView {
    @BindView(R.id.searchRepositories)
    EditText searchRepositories;
    @BindView(R.id.gitRepoRecyclerView)
    RecyclerView gitRepoRecyclerView;

    @Inject
    RepositoriesPresenter presenter;
    private RepositoriesRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_list_activity);
        ButterKnife.bind(this);
        createDependencies();
        initVariables();
        initListeners();
    }

    public void createDependencies() {
        DaggerRepositoriesActivityComponent
                .builder()
                .repositoriesActivityModule(new RepositoriesActivityModule(this))
                .baseComponent(MyApp.getApp().getBaseComponent())
                .build()
                .inject(this);
    }

    public void initVariables() {
        adapter = new RepositoriesRecyclerViewAdapter(presenter.getRepositories(), R.layout.item_repository);
        gitRepoRecyclerView.setAdapter(adapter);
    }

    public void initListeners() {
        adapter.setOnUserItemClick(new RepositoriesRecyclerViewAdapter.OnUserItemClick() {
            @Override
            public void onRepositoryClick(GitRepo gitRepo, ImageView avatar) {
                openDetailsActivity(gitRepo,avatar);
            }
        });

        searchRepositories.addTextChangedListener(new TextWatcher() {
            CountDownTimer timer = null;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (timer != null) {
                    timer.cancel();
                }
                timer = new CountDownTimer(500, 250) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        presenter.onTextChange(searchRepositories.getText().toString());

                    }
                }.start();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void openDetailsActivity(GitRepo gitRepo, ImageView avatar){
        Intent intent = new Intent(RepositoriesActivity.this, RepositoriesDetailsActivity.class);
        intent.putExtra(RepositoriesDetailsActivity.REPOSITORY_EXTRA, gitRepo);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(RepositoriesActivity.this, avatar, "profile");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void updateView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorGettingRepositories() {
        Toast.makeText(this, R.string.error_getting_repositories, Toast.LENGTH_LONG).show();
    }
}
