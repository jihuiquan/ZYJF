package finan.heng.com.apps.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import finan.zhimabao.com.apps.R;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class RiskFragment extends BaseFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_risk, null);
        return view;
    }
}
