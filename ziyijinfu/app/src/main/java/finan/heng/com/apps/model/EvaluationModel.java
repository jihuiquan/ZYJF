package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/18 23:16
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class EvaluationModel implements Serializable{
    public String questionContent;
    public String scoreA;
    public String id;
    public String scoreF;
    public String scoreE;
    public String scoreD;
    public String answerF;
    public String scoreC;
    public String scoreB;
    public String answerD;
    public String answerE;
    public String answerB;
    public String answerC;
    public String answerA;

    public String selectedScore;

    @Override
    public String toString() {
        return "EvaluationModel{" +
                "questionContent='" + questionContent + '\'' +
                ", scoreA='" + scoreA + '\'' +
                ", id='" + id + '\'' +
                ", scoreF='" + scoreF + '\'' +
                ", scoreE='" + scoreE + '\'' +
                ", scoreD='" + scoreD + '\'' +
                ", answerF='" + answerF + '\'' +
                ", scoreC='" + scoreC + '\'' +
                ", scoreB='" + scoreB + '\'' +
                ", answerD='" + answerD + '\'' +
                ", answerE='" + answerE + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerA='" + answerA + '\'' +
                ", selectedScore='" + selectedScore + '\'' +
                '}';
    }
}
