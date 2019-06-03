package finan.heng.com.apps.app.ui.fragment;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.avast.android.dialogs.fragment.ProgressDialogFragment;
import com.avast.android.dialogs.fragment.SimpleDialogFragment;
import com.github.johnpersano.supertoasts.SuperToast;

import butterknife.ButterKnife;
import finan.zhimabao.com.apps.R;


public abstract class BaseFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private boolean isActived = false;
    ProgressDialog progressDialog;
    protected boolean isNeedSysBar = false;


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        if (view == null) {
            return inflater.inflate(R.layout.fragment_base, container, false);
        }
        ButterKnife.bind(this, view);
        setupProgressDialog();
        setupViews(view);
        setTranslucentStatus(isNeedSysBar);
        initSystemBarTintManager();
        return view;
    }

    protected void setIsNeedSysBar(boolean isNeedSysBar){
        this.isNeedSysBar = isNeedSysBar;
    }
    public int getLayout() {
        return 0;
    }

    public void setupViews(View root) {
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void showMsgDialog(String msg, boolean Cancelable) {
        if(!isActived){
            return;
        }
        //Extend BaseDialogFragment to create custom DialogFragments if you want.
        SimpleDialogFragment.createBuilder(getContext(), getFragmentManager())
                .setMessage(msg)
                .setCancelable(Cancelable)
                .show();
    }

    public DialogFragment showProgressDialog(String msg, boolean Cancelable) {
        return ProgressDialogFragment.createBuilder(getContext(), getFragmentManager())
                .setMessage(msg)
                .setCancelable(Cancelable)
                .show();
    }

    public void toast(String s) {
        if(getActivity()==null){
            return;
        }
        SuperToast superToast = new SuperToast(getActivity());
        superToast.setDuration(SuperToast.Duration.VERY_SHORT);
//        superToast.setGravity(Gravity.CENTER, 0, 0);
        superToast.setText(s);
        superToast.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        isActived=true;
    }
    @Override
    public void onStop() {
        super.onStop();
        isActived=false;
    }
    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    /**
     * 初始化顶部状态栏
     */
    private void initSystemBarTintManager() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
//        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.transparent);//通知栏所需颜色
//// set a custom tint color for all system bars
////        tintManager.setTintColor(Color.parseColor("#99000FF"));
//// set a custom navigation bar resource
//        tintManager.setNavigationBarTintResource(R.mipmap.ic_launcher);
    }




}
