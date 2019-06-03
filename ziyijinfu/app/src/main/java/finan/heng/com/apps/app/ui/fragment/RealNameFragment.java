package finan.heng.com.apps.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import finan.heng.com.apps.base.*;
import finan.zhimabao.com.apps.R;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class RealNameFragment extends finan.heng.com.apps.base.BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_realname, null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
