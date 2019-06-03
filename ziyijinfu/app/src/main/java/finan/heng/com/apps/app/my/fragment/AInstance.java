package finan.heng.com.apps.app.my.fragment;

public class AInstance {
    private static AInstance a;

    static AInstance getInstance() {
        if (a == null) {
            synchronized(AInstance.class){
                if (a == null) {
                    a = new AInstance();
                }
            }
        }
        return a;
    }
}
