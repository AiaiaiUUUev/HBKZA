package com.example.d.healthbook.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Appi.App;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by D on 26.05.2017.
 */

public class MainController {
    public static void showToast(final Context _context, final String message_text) {
        ((Activity) _context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(_context, message_text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static long getCurrent_UNIXTIME(){
        return System.currentTimeMillis() / 1000L;
    }

    public static void showPreparedToast(final Context _context, final String message_type) {
        switch (message_type) {
            case "Success": {
                showToast(_context, "Успешно");
                break;
            }
            case "Failed": {
                showToast(_context, "Ошибка");
                break;
            }
            default: {
                showToast(_context, message_type);
                break;
            }
        }

    }



    static HashMap<String, String> loadedImagePath;

    static void saveToKnownPath(String image_id, String image_path) {
        if (loadedImagePath == null) {
            loadedImagePath = new HashMap<String, String>();
        }
        if (!loadedImagePath.containsKey(image_id))
            loadedImagePath.put(image_id, image_path);
    }

    static String checkSavedPath(String image_id) {
        if (loadedImagePath != null) {
//            String image_url = loadedImagePath.get(image_id);
//            if(image_url.isEmpty())
//                return null;
//            return image_url;
            if (loadedImagePath.containsKey(image_id))
                return loadedImagePath.get(image_id);
            return null;
        }
        return null;
    }

    public static void setImageToViewById(final Context _context, final String image_id, final ImageView image_view) {
        String save_img_path;
        if ((save_img_path = checkSavedPath(image_id)) != null) {
            loadImageToView(_context, save_img_path, image_view);
            return;
        }
        OkHttpClient client = new OkHttpClient();
        String url_to_image_path = "https://healthbook.kz/api/files/image/path/" + image_id;
        Request request = new Request.Builder()
                .url(url_to_image_path)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String raw_response = response.body().string();
                raw_response = raw_response.replace("\\", "");
                raw_response = raw_response.replace("\"", "");
                saveToKnownPath(image_id, raw_response);
                loadImageToView(_context, raw_response, image_view);
            }

        });
    }
    public static Calendar getCalendarByDate(String date , String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        boolean isSuccessfull = false;
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(format1.parse(date));
            isSuccessfull = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (isSuccessfull)
            return cal;
        return null;
    }

    public static void loadImageToView(final Context _ctx, final String image_url, final ImageView image_view) {
        ((AppCompatActivity) _ctx).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(_ctx).load(image_url).into(image_view);
            }
        });
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public static void birthdayTextChangeListener(final EditText editText) {
        TextWatcher tw = new TextWatcher() {
            String current = "";
            String ddmmyyyy = "ДДMMГГГГ";
            Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
//                    String clean = s.toString().replaceAll("/", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        if (mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2013
                        //would be automatically corrected to 28/02/2013

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    editText.setText(current);
                    editText.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editText.addTextChangedListener(tw);


    }
}
