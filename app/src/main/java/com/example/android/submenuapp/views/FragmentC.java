package com.example.android.submenuapp.views;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowFragment;

/**
 * Created by Elorri on 13/10/2016.
 */
public class FragmentC extends AppWindowFragment {


    private LinearLayout view;
    private Context mContext;
    private Resources mResource;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mResource = mContext.getResources();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (LinearLayout) inflater.inflate(R.layout.fragment_c_layout1, null);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "view " + view);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "tag " + this.getTag());
        setUpView(view);
        return view;
    }

    private View setUpView(final View view) {
        view.findViewById(R.id.backToB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "view " + view);
                mCallback.weAreOnFragmentC();
                mAppWindowManager.openPopup(v, FragmentBViewInfo.class.getName(), null, null);
            }
        });
        return view;
    }

    @Override
    public void setData(Bundle data) {
        int layoutRes = data.getInt(mResource.getString(R.string.fragment_c_layout));
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "" + layoutRes + " " + R.layout.fragment_c_layout1 + " " + R.layout.fragment_c_layout2);
        if (layoutRes != R.layout.fragment_c_layout1) {
            view.removeAllViews();
            view.addView(setUpView(View.inflate(getContext(), layoutRes, null)));
        }
    }
}
