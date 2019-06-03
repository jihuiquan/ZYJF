package finan.heng.com.apps.app.view;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public interface IMainView extends IBaseView{
    void viewUpdateView(boolean forceUpdate, String link, String description, String version);

    void setUpdateValue();
}
