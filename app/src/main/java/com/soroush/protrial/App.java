package com.soroush.protrial;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class App extends Application {

    public static final String PRODUCT_TABLE = "tblProduct";
    public static final String ENTRY_ID = "id";
    public static final String ENTRY_NAME = "name";
    public static final String ENTRY_TITLE = "title";
    public static final String ENTRY_DESC = "desc";
    public static final String ENTRY_LOCK = "lock";
    public static final String ENTRY_IMAGE = "imgRes";
    public static final String ENTRY_AMOUNT = "amount";
    public static final String ENTRY_PRICE = "price";

    public static final String SAMSUNG = "SAMSUNG";
    public static final String APPLE = "APPLE";
    public static final String LG = "LG";

    private static final String PLAIN_TEXT = "Lorem ipsum dolor sit amet, consectetur " +
            "adipiscing elit. Duis vitae ultrices sapien, ut suscipit risus. Curabitur" +
            " sem nisl, congue nec dui vitae, pharetra pellentesque ligula. Praesent " +
            "accumsan lectus maximus arcu lacinia, id semper orci pretium. Phasellus in tortor" +
            " ut nisl hendrerit sodales. Donec ornare arcu a est porttitor, nec tempus ligula" +
            " efficitur. In vel placerat mi, eu egestas risus. Vestibulum tincidunt felis ut " +
            "augue consectetur, eu tincidunt risus viverra. Phasellus blandit est in lacinia elementum.";

    public static final int[] imageList = {R.drawable.first,
            R.drawable.second, R.drawable.third, R.drawable.fourth};

    @Override
    public void onCreate() {
        super.onCreate();

        /*AppDatabase db = new AppDatabase(this);

        db.insertItem(R.drawable.fi, SAMSUNG, "GALAXY-S500", "326", 326,0, 0);
        db.insertItem(R.drawable.th, SAMSUNG, "NOTE-L623", "215", 215,0, 0);
        db.insertItem(R.drawable.se, SAMSUNG, "SRT-602L", "128", 128,0, 0);
        db.insertItem(R.drawable.ph_first,  SAMSUNG, "GALAXY-S500", "326", 650,0, 0);
        db.insertItem(R.drawable.ph_second,  SAMSUNG, "GALAXY-S500", "326", 441,0, 0);
        db.insertItem(R.drawable.fi,  SAMSUNG, "GALAXY-S500", "326", 212,0, 0);
        db.insertItem(R.drawable.ph_first,  SAMSUNG, "GALAXY-S500", "326", 235,0, 0);
        db.insertItem(R.drawable.se, SAMSUNG, "GALAXY-S500", "326", 112,0, 0);

        db.insertItem(R.drawable.fi, APPLE, "GALAXY-S500", "326", 326,0, 0);
        db.insertItem(R.drawable.th, APPLE, "NOTE-L623", "215", 215,0, 0);
        db.insertItem(R.drawable.se, APPLE, "SRT-602L", "128", 128,0, 0);
        db.insertItem(R.drawable.ph_first,  APPLE, "GALAXY-S500", "326", 650,0, 0);
        db.insertItem(R.drawable.ph_second,  APPLE, "GALAXY-S500", "326", 441,0, 0);
        db.insertItem(R.drawable.fi,  APPLE, "GALAXY-S500", "326", 212,0, 0);
        db.insertItem(R.drawable.ph_first,  APPLE, "GALAXY-S500", "326", 235,0, 0);
        db.insertItem(R.drawable.se, APPLE, "GALAXY-S500", "326", 112,0, 0);

        db.insertItem(R.drawable.fi, LG, "GALAXY-S500", "326", 326,0, 0);
        db.insertItem(R.drawable.th, LG, "NOTE-L623", "215", 215,0, 0);
        db.insertItem(R.drawable.se, LG, "SRT-602L", "128", 128,0, 0);
        db.insertItem(R.drawable.ph_first,  LG, "GALAXY-S500", "326", 650,0, 0);
        db.insertItem(R.drawable.ph_second,  LG, "GALAXY-S500", "326", 441,0, 0);
        db.insertItem(R.drawable.fi,  LG, "GALAXY-S500", "326", 212,0, 0);
        db.insertItem(R.drawable.ph_first,  LG, "GALAXY-S500", "326", 235,0, 0);
        db.insertItem(R.drawable.se, LG, "GALAXY-S500", "326", 112,0, 0);

        List<BrandModel> list = db.getAllData();
        for (BrandModel model : list){
            if (model.getId() == 1 || model.getId() == 2)
                Log.i("TAG", "onCreate: " + model.getAmount());
        }
*/

        /*AppDatabase db = new AppDatabase(this);

        db.insertItem(getByteImage(R.drawable.fi), SAMSUNG, "GALAXY-S500", "326", 326,0, 0);
        db.insertItem(getByteImage(R.drawable.th), SAMSUNG, "NOTE-L623", "215", 215,0, 0);
        db.insertItem(getByteImage(R.drawable.se), SAMSUNG, "SRT-602L", "128", 128,0, 0);
        db.insertItem(getByteImage(R.drawable.ph_first),  SAMSUNG, "GALAXY-S500", "326", 650,0, 0);
        db.insertItem(getByteImage(R.drawable.ph_second),  SAMSUNG, "GALAXY-S500", "326", 441,0, 0);
        db.insertItem(getByteImage(R.drawable.se),  SAMSUNG, "GALAXY-S500", "326", 212,0, 0);
        db.insertItem(getByteImage(R.drawable.th),  SAMSUNG, "GALAXY-S500", "326", 235,0, 0);
        db.insertItem(getByteImage(R.drawable.fi), SAMSUNG, "GALAXY-S500", "326", 112,0, 0);

        db.insertItem(getByteImage(R.drawable.fi), APPLE, "GALAXY-S500", "326", 326,0, 0);
        db.insertItem(getByteImage(R.drawable.th), APPLE, "NOTE-L623", "215", 215,0, 0);
        db.insertItem(getByteImage(R.drawable.se), APPLE, "SRT-602L", "128", 128,0, 0);
        db.insertItem(getByteImage(R.drawable.ph_first),  APPLE, "GALAXY-S500", "326", 650,0, 0);
        db.insertItem(getByteImage(R.drawable.ph_second),  APPLE, "GALAXY-S500", "326", 441,0, 0);
        db.insertItem(getByteImage(R.drawable.se),  APPLE, "GALAXY-S500", "326", 212,0, 0);
        db.insertItem(getByteImage(R.drawable.th),  APPLE, "GALAXY-S500", "326", 235,0, 0);
        db.insertItem(getByteImage(R.drawable.fi), APPLE, "GALAXY-S500", "326", 112,0, 0);

        db.insertItem(getByteImage(R.drawable.fi), LG, "GALAXY-S500", "326", 326,0, 0);
        db.insertItem(getByteImage(R.drawable.th), LG, "NOTE-L623", "215", 215,0, 0);
        db.insertItem(getByteImage(R.drawable.se), LG, "SRT-602L", "128", 128,0, 0);
        db.insertItem(getByteImage(R.drawable.ph_first),  LG, "GALAXY-S500", "326", 650,0, 0);
        db.insertItem(getByteImage(R.drawable.ph_second),  LG, "GALAXY-S500", "326", 441,0, 0);
        db.insertItem(getByteImage(R.drawable.se),  LG, "GALAXY-S500", "326", 212,0, 0);
        db.insertItem(getByteImage(R.drawable.th),  LG, "GALAXY-S500", "326", 235,0, 0);
        db.insertItem(getByteImage(R.drawable.fi), LG, "GALAXY-S500", "326", 112,0, 0);*/


    }

    private byte[] getByteImage(int resId) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

}
