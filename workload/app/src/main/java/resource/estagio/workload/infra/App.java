package resource.estagio.workload.infra;

import android.app.Application;

import androidx.room.Room;

import resource.estagio.workload.domain.User;

public class App extends Application {
    private static App instance;
    private static RestClient restClient;
    private static AppDatabase db;
    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        restClient = RestClient.getInstante();
        db = Room.databaseBuilder(
                getApplicationContext(), AppDatabase.class, "app_database").build();
    }

    public static App getInstance() {
        return instance;
    }

    public static RestClient getRestClient() {
        return restClient;
    }

    public static AppDatabase getDb() {
        return db;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }
}