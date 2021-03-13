package com.soroush.protrial;

import android.app.Application;

public class App extends Application {

    public static final String SAMSUNG_TABLE = "tblsamsung";
    public static final String APPLE_TABLE = "tblapple";
    public static final String LG_TABLE = "tbllg";
    public static final String IMAGE_TABLE = "tblimage";
    public static final String ENTRY_ID = "id";
    public static final String ENTRY_TITLE = "title";
    public static final String ENTRY_DESC = "desc";
    public static final String ENTRY_LOCK = "lock";
    public static final String ENTRY_IMAGE = "imgResource";

    public static final int[] imageList = {R.drawable.first,
            R.drawable.second, R.drawable.third, R.drawable.fourth};

    @Override
    public void onCreate() {
        super.onCreate();

        /*AppDatabase db = new AppDatabase(this);

        db.insertImage(R.drawable.fi);
        db.insertImage(R.drawable.se);
        db.insertImage(R.drawable.th);
        db.insertImage(R.drawable.ph_first);
        db.insertImage(R.drawable.ph_second);

        db.insertItem(App.SAMSUNG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.SAMSUNG_TABLE, "NOTE-L623", "215", 0);
        db.insertItem(App.SAMSUNG_TABLE, "SRT-602L", "128", 0);
        db.insertItem(App.SAMSUNG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.SAMSUNG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.SAMSUNG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.SAMSUNG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.SAMSUNG_TABLE, "GALAXY-S500", "326", 0);

        db.insertItem(App.APPLE_TABLE, "S326-LLA", "752", 0);
        db.insertItem(App.APPLE_TABLE, "IPHONE-SE", "529", 0);
        db.insertItem(App.APPLE_TABLE, "BRR-KJ20", "466", 0);
        db.insertItem(App.APPLE_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.APPLE_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.APPLE_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.APPLE_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.APPLE_TABLE, "GALAXY-S500", "326", 0);

        db.insertItem(App.LG_TABLE, "GGA-S500", "326", 0);
        db.insertItem(App.LG_TABLE, "NOTE-L623", "215", 0);
        db.insertItem(App.LG_TABLE, "SRT-602L", "128", 0);
        db.insertItem(App.LG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.LG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.LG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.LG_TABLE, "GALAXY-S500", "326", 0);
        db.insertItem(App.LG_TABLE, "GALAXY-S500", "326", 0);*/
    }

}
