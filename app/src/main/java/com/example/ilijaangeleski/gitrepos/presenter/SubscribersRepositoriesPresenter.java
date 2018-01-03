package com.example.ilijaangeleski.gitrepos.presenter;

import com.example.ilijaangeleski.gitrepos.callback.SubscribersCallback;
import com.example.ilijaangeleski.gitrepos.manager.SubscribersRepositoriesManager;
import com.example.ilijaangeleski.gitrepos.model.GitSubscribers;
import com.example.ilijaangeleski.gitrepos.view.SubscribersView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilija Angeleski on 12/28/2017.
 */

public class SubscribersRepositoriesPresenter {
    private SubscribersRepositoriesManager subscribersRepositoriesManager;
    private WeakReference<SubscribersView> subscribersViewWeakReference;
    private List<GitSubscribers> subscribers = new ArrayList<>();

    public SubscribersRepositoriesPresenter(WeakReference<SubscribersView> view, SubscribersRepositoriesManager subscribersRepositoriesManager) {
        this.subscribersViewWeakReference = view;
        this.subscribersRepositoriesManager = subscribersRepositoriesManager;
    }

    public void fetchSubscribers(final String url) {
        subscribersRepositoriesManager.fetchSubscribers(url, new SubscribersCallback() {
            @Override
            public void onSuccess(List<GitSubscribers> response) {
                SubscribersView view = subscribersViewWeakReference.get();
                if (view != null) {
                    if (response != null) {
                        subscribers.addAll(response);
                        view.updateView(subscribers.size());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                SubscribersView view = subscribersViewWeakReference.get();
                if (view != null) {
                    view.showErrorGettingSubscribers();
                }
            }
        });
    }

    public List<GitSubscribers> getSubscribers() {
        return subscribers;
    }
}
