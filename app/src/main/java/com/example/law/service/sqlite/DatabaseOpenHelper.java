package com.example.law.service.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.law.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 林书浩
 * @date 2020/07/29
 * @lestDate 2020/08/07
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "law.db";
    final static int DATABASE_VERSION = 1;
    public static final String PACKAGE_NAME = "com.example.lawapp";

    private String dataBasePath;
    private final Context context;

    public static DatabaseOpenHelper DB_HELPER_INSTANCE = null;


    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dataBasePath = "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/"
                + PACKAGE_NAME;
        createDatabase();
    }

    public void createDatabase() {
        isDatabaseExist();
        this.getReadableDatabase();
    }

    /**
     * 判断手机数据库是否存在
     */
    public void isDatabaseExist() {
        String path = dataBasePath + "/" + DATABASE_NAME;

        File folder = new File(dataBasePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File databaseFile = new File(path);
        if (!databaseFile.exists()) {
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制本地数据库
     *
     * @throws IOException
     */
    public void copyDatabase() throws IOException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.law);

        String outFileName = dataBasePath + "/" + DATABASE_NAME;
        OutputStream outputStream = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];

        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    /**
     * 获取Database实例
     *
     * @param context 场景对象（Activity）
     * @return 返回注入场景对象的Database
     */
    public static DatabaseOpenHelper getInstance(Context context) {
        if (DB_HELPER_INSTANCE == null) {
            DB_HELPER_INSTANCE = new DatabaseOpenHelper(context);
        }
        return DB_HELPER_INSTANCE;
    }

    /**
     * 开启数据库
     *
     * @return 返回开启数据库方法
     */
    public SQLiteDatabase openDatabase() throws SQLException {
        String path = dataBasePath + "/" + DATABASE_NAME;
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String path = dataBasePath + "/" + DATABASE_NAME;
        File databaseFile = new File(path);
        if (databaseFile.exists()) {
            databaseFile.setExecutable(true, false);
            databaseFile.setReadable(true, false);
            databaseFile.setWritable(true, false);
            databaseFile.delete();
        }
    }
}
