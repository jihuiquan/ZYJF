package finan.heng.com.apps.app.my.fragment;

public class BInstance {
    public BInstance() {
    }

    private static class Singleton{
        private static final BInstance bInstance = new BInstance();
    }

    public static BInstance getInstance(){
        return Singleton.bInstance;
    }
}
