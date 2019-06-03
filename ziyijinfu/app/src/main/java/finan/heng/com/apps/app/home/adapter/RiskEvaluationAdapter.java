package finan.heng.com.apps.app.home.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;

import java.util.ArrayList;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.model.EvaluationModel;

/**
 * Created by Administrator on 2017/9/20.
 */

public class RiskEvaluationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    ArrayList<EvaluationModel> models;
    Context context;
    public RiskEvaluationAdapter(ArrayList<EvaluationModel> evaluationModels,Context context){
        models = evaluationModels;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.activity_risk_evaluation_item,parent,false);
            return new MyHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_risk_evaluation_item_tips,parent,false);
            return new TextHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, final int position) {
        if (vHolder instanceof MyHolder){
            MyHolder holder = (MyHolder) vHolder;
            final EvaluationModel model = models.get(position-1);
            holder.textView.setText(model.questionContent);
            RadioButton button1 = (RadioButton) holder.radioGroup.getChildAt(0);
            button1.setText(model.answerA);
            if (TextUtils.isEmpty(model.answerA)){
                button1.setVisibility(View.GONE);
            }else {
                button1.setVisibility(View.VISIBLE);
            }
            RadioButton button2 = (RadioButton) holder.radioGroup.getChildAt(1);
            button2.setText(model.answerB);
            if (TextUtils.isEmpty(model.answerB)){
                button2.setVisibility(View.GONE);
            }else {
                button2.setVisibility(View.VISIBLE);
            }
            RadioButton button3 = (RadioButton) holder.radioGroup.getChildAt(2);
            button3.setText(model.answerC);
            if (TextUtils.isEmpty(model.answerC)){
                button3.setVisibility(View.GONE);
            }else {
                button3.setVisibility(View.VISIBLE);
            }
            RadioButton button4 = (RadioButton) holder.radioGroup.getChildAt(3);
            button4.setText(model.answerD);
            if (TextUtils.isEmpty(model.answerD)){
                button4.setVisibility(View.GONE);
            }else {
                button4.setVisibility(View.VISIBLE);
            }
            RadioButton button5 = (RadioButton) holder.radioGroup.getChildAt(4);
            button5.setText(model.answerE);
            if (TextUtils.isEmpty(model.answerE)){
                button5.setVisibility(View.GONE);
            }else {
                button5.setVisibility(View.VISIBLE);
            }
            RadioButton button6 = (RadioButton) holder.radioGroup.getChildAt(5);
            button6.setText(model.answerF);
            if (TextUtils.isEmpty(model.answerF)){
                button6.setVisibility(View.GONE);
            }else {
                button6.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(model.selectedScore)){
                holder.radioGroup.clearCheck();
            }else {
                if (model.selectedScore.equals(model.scoreA)){
                    setChecked(holder.radioGroup,0);
                }else if (model.selectedScore.equals(model.scoreB)){
                    setChecked(holder.radioGroup,1);
                }else if (model.selectedScore.equals(model.scoreC)){
                    setChecked(holder.radioGroup,2);
                }else if (model.selectedScore.equals(model.scoreD)){
                    setChecked(holder.radioGroup,3);
                }else if (model.selectedScore.equals(model.scoreE)){
                    setChecked(holder.radioGroup,4);
                }else if (model.selectedScore.equals(model.scoreF)){
                    setChecked(holder.radioGroup,5);
                }
            }
            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                    switch (checkedId){
                        case R.id.radio1:
                            models.get(position-1).selectedScore = model.scoreA;
                            break;
                        case R.id.radio2:
                            models.get(position-1).selectedScore = model.scoreB;
                            break;
                        case R.id.radio3:
                            models.get(position-1).selectedScore = model.scoreC;
                            break;
                        case R.id.radio4:
                            models.get(position-1).selectedScore = model.scoreD;
                            break;
                        case R.id.radio5:
                            models.get(position-1).selectedScore = model.scoreE;
                            break;
                        case R.id.radio6:
                            models.get(position-1).selectedScore = model.scoreF;
                            break;
                    }
                    Bus.getDefault().post("check");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return models.size()+1;
    }

    public ArrayList<EvaluationModel> getModels(){
        return models;
    }
    private void setChecked(RadioGroup group,int select){
        for (int i=0;i<group.getChildCount();i++){
            RadioButton button = (RadioButton) group.getChildAt(i);
            if (i == select){
                button.setChecked(true);
            }
        }


    }
    class MyHolder extends RecyclerView.ViewHolder{

        TextView textView;
        RadioGroup radioGroup;
        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.question);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radio_group);
        }
    }
    class TextHolder extends RecyclerView.ViewHolder{

        TextView tips;
        public TextHolder(View itemView) {
            super(itemView);
            tips = (TextView) itemView.findViewById(R.id.tips);
        }
    }
}
