package com.example.android.submenuapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowFragment;

/**
 * Created by Elorri on 13/10/2016.
 */
public class FragmentC extends AppWindowFragment {



    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_c, null);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"view "+view);
        view.findViewById(R.id.backToB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.weAreOnFragmentC();
                mAppWindowManager.openPopup(v, FragmentBViewInfo.class.getName(), null, null);
            }
        });
        return view;
    }

}
