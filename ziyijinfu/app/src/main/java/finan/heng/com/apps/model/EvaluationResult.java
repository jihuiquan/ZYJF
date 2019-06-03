package finan.heng.com.apps.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/18 23:16
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class EvaluationResult implements Serializable{
    public ArrayList<EvaluationModel> questionList;
    public String max_level;
    public String max_level_description;

    @Override
    public String toString() {
        return "EvaluationResult{" +
                "questionList=" + questionList +
                ", max_level='" + max_level + '\'' +
                ", max_level_description='" + max_level_description + '\'' +
                '}';
    }
}
