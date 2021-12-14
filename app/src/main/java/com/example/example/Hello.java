package com.example.example;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import java.util.Random;

public class Hello {
    private static final int STORAGE_IO_PERMISSION_REQUEST_CODE = 101;

    public static boolean checkPermissionForIO(Context context) {
        String[] permissionList = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        for (String permission : permissionList)
            if (!checkPermission(context, permission))
                return false;

        return true;
    }

    public static boolean checkPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = context.checkSelfPermission(permission);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public static void requestPermission(Activity activity) {
        String[] per = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ActivityCompat.requestPermissions(activity, per, STORAGE_IO_PERMISSION_REQUEST_CODE);
    }

    public static int randNum() {
        return new Random().nextInt(100);
    }
}
