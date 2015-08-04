package com.insa.rocketlyonandroid.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import trikita.log.Log;

/* With this Base Class, we can access to parent activity of fragment very easily */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(mActivity.getClass().toString());
    }
}