package finan.heng.com.apps.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import finan.heng.com.apps.helper.URLHelper;
import finan.zhimabao.com.apps.R;

/**
 * Created by ttt on 2016/7/5.
 */
public class DialogUtil {

    private static DialogUtil instance;
    private DialogUtil(){

    }
    public static DialogUtil getInstance(){

            if (instance == null){
                synchronized (DialogUtil.class) {
                    if (instance == null) {
                        instance = new DialogUtil();
                    }
                }
            }
        return instance;
    }
    public AlertDialog showLoading(Context context){
        View view = View.inflate(context, R.layout.dialog_loading,null);
        ImageView loading = (ImageView) view.findViewById(R.id.loading_image);
        final AnimationDrawable drawable = (AnimationDrawable) loading.getDrawable();
        drawable.start();
        AlertDialog dialog = new AlertDialog.Builder(context,R.style.dialog).setView(view).setCancelable(false).create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (drawable != null){
                    drawable.stop();
//                    for (int i = 0; i < drawable.getNumberOfFrames(); i++) {
//                        Drawable frame = drawable.getFrame(i);
//                        if (frame instanceof BitmapDrawable) {
//                            ((BitmapDrawable) frame).getBitmap().recycle();
//                        }
//                        frame.setCallback(null);
//                    }
//                    drawable.setCallback(null);
                }
            }
        });
        dialog.show();

        return dialog;
    }

    public static AlertDialog showDialog(Context context){
        View view = View.inflate(context, R.layout.layout_url_select,null);
        final EditText password = view.findViewById(R.id.password);
        final Button verify = view.findViewById(R.id.verify);
        final ListView listView = view.findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,URLHelper.baseUrls);
        listView.setAdapter(adapter);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals("123456")) {
                    password.setVisibility(View.GONE);
                    verify.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                } else {
                    password.setText("密码错误");
                }
            }
        });
        final AlertDialog dialog = new AlertDialog.Builder(context,R.style.dialog).setView(view).setCancelable(true).create();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedpreferenceUtil.put(URLHelper.BASE_URL,URLHelper.baseUrls.get(i));
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }

}