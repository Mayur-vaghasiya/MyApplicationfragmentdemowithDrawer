package com.example.peacock.myapplicationfragmentdemo.util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.peacock.myapplicationfragmentdemo.BuildConfig;
import com.example.peacock.myapplicationfragmentdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;


/**
 * Created by peacock on 10/7/17.
 */

public class CommonUtility {

    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
    public static String ipAddress = "";
    public static String AppsCode = "3";
    public static String PlatForm = "ANDROID";
    public static String Fill = "FILL";

    private static String operations_font = "MyriadPro-Semibold.otf";
    private static String operations_font1 = "AvenirNextLTPro-Bold_1.otf";
    private static String diamonds_shapes_font = "R-Rajesh-Exports.ttf";
    private static String diamond_shaps_font = "SRKshape.ttf";
    private static String paladiya_font = "PaladiyaApp.ttf";
    private static String diamond_search_fontbottom = "RRajeshApp.ttf";
    private static String operations_fontnaro = "Narola-App.ttf";
    private static String operations_font1naro = "Narola-App1.ttf";
    private static String diamonds_shapes_fontnaro = "Diamond-Shapes.ttf";
    private static HashMap<String, String> mySearchParams = null;
    private static String rrajeshttf = "RRajeshExports.ttf";//new mayur 21-11
    private static String uniqueID = null;

    public static void Log(String tag, String msg) {
        if (BuildConfig.ENABLE_DEBUG)
            Log.e(tag, msg);
    }


    public static void hideKeyboard(Activity activity) {

        if (activity.getCurrentFocus() != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public static void showKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

    /* new  mayur 21-11*/
    public static void setappfont(Context context, TextView textView) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), rrajeshttf);
        textView.setTypeface(font);

    }

    public static void setOperationFont(Context context, TextView textView) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), operations_font);
        textView.setTypeface(font);

    }

    public static void setOperationFontnarola(Context context, TextView textView) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), operations_fontnaro);
        textView.setTypeface(font);

    }

    public static void setDiamondsShapesFont(Context context, TextView textView) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), diamonds_shapes_font);

        textView.setTypeface(font);

    }

    public static void setDiamondsShapesFontsonly(Context context, TextView textView) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), diamond_shaps_font);
        textView.setTypeface(font);

    }

    public static void setDiamondsSearchbottomFontsonly(Context context, TextView textView) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), diamond_search_fontbottom);
        textView.setTypeface(font);

    }

    public static void setfontfrompaladiya(Context context, TextView textView) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), paladiya_font);
        textView.setTypeface(font);

    }



    public static void commomAlert(final Context context, final String buttonOk,
                                   final String errorMessage, final String isTitle,
                                   final Boolean close, final Boolean isLogOut) {

        View inf = LayoutInflater.from(context).inflate(R.layout.common_alert_dialog, null);

        AppCompatButton tv_dialog_ok = (AppCompatButton) inf.findViewById(R.id.acbtn_dialog_ok);
        tv_dialog_ok.setText(buttonOk);

        AppCompatTextView tv_error_string = (AppCompatTextView) inf.findViewById(R.id.actv_error_string);
        tv_error_string.setText(errorMessage);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        AppCompatTextView tv_thank_you = (AppCompatTextView) inf.findViewById(R.id.actv_thank_you);

        if (isTitle.equals(context.getString(R.string.no))) {

            tv_thank_you.setVisibility(View.GONE);

        } else {

            tv_thank_you.setVisibility(View.VISIBLE);

            tv_thank_you.setText(context.getString(R.string.app_name));

        }

        tv_thank_you.setLayoutParams(params);

        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(inf);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundedcorner);
        dialog.show();

        tv_dialog_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();

//                if (close) {
//
//                    if (isLogOut) {
//
//                        ApplicationLoader.getAppLoader().getPreferencesUtility().clearAll();
//
//                        Intent login_intent = new Intent(context, LoginActivity.class);
//                        login_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        context.startActivity(login_intent);
//
//                        ((Activity) context).finish();
//
//                    } else {
//
//                        ((MainActivity) context).getSupportFragmentManager().popBackStackImmediate();
//
//                    }
//                }

            }
        });
    }

    public static void pathpopup(final Context context, final String buttonOk, String cancel, final String errorMessage, final String isTitle, final File file, final String type) {

        View inf = LayoutInflater.from(context).inflate(R.layout.common_alert_dialog, null);


        AppCompatButton tv_dialog_ok = (AppCompatButton) inf.findViewById(R.id.acbtn_dialog_ok);
        AppCompatButton tv_dialog_cancel = (AppCompatButton) inf.findViewById(R.id.acbtn_delete);

        if (type.equalsIgnoreCase("Certificate")) {
            tv_dialog_ok.setText(buttonOk);
            tv_dialog_cancel.setText(cancel);
            tv_dialog_cancel.setVisibility(View.VISIBLE);
        } else {
            tv_dialog_ok.setText("OK");
            tv_dialog_cancel.setText(cancel);
            tv_dialog_cancel.setVisibility(View.GONE);
        }
        AppCompatTextView tv_error_string = (AppCompatTextView) inf.findViewById(R.id.actv_error_string);

        tv_error_string.setText(errorMessage + "\n\n" + String.valueOf(file));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        AppCompatTextView tv_thank_you = (AppCompatTextView) inf.findViewById(R.id.actv_thank_you);

        if (isTitle.equals(context.getString(R.string.no))) {

            tv_thank_you.setVisibility(View.GONE);

        } else {
            tv_thank_you.setVisibility(View.VISIBLE);
            tv_thank_you.setText(context.getString(R.string.app_name));
        }
        tv_thank_you.setLayoutParams(params);

        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(inf);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundedcorner);
        dialog.show();

        tv_dialog_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();

//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + File.separator + context.getString(R.string.app_name) + File.separator);
//                intent.setDataAndType(uri, "*/*");
//                context.startActivity(Intent.createChooser(intent, "Open folder"));

                if (type.equalsIgnoreCase("Certificate"))
                    openfile(file, (Activity) context);
            }
        });

        tv_dialog_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
    }

    public static void commonAlert(final Activity activity, String message) {

        View inf = LayoutInflater.from(activity).inflate(R.layout.common_alert_dialog, null);

        AppCompatButton tv_dialog_ok = (AppCompatButton) inf.findViewById(R.id.acbtn_dialog_ok);
        tv_dialog_ok.setText(activity.getString(R.string.ok));

        AppCompatTextView tv_error_string = (AppCompatTextView) inf.findViewById(R.id.actv_error_string);
        tv_error_string.setText(message);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        AppCompatTextView tv_thank_you = (AppCompatTextView) inf.findViewById(R.id.actv_thank_you);
        tv_thank_you.setVisibility(View.VISIBLE);
        tv_thank_you.setText(activity.getString(R.string.app_name));

        tv_thank_you.setLayoutParams(params);

        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(inf);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundedcorner);
        dialog.show();

        tv_dialog_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();

                activity.finish();

            }
        });
    }

    public static void showSingleButtonDialog(final Context context, String message, String title, String btnText) {

        View inf = LayoutInflater.from(context).inflate(R.layout.common_alert_dialog, null);

        AppCompatButton tv_dialog_ok = (AppCompatButton) inf.findViewById(R.id.acbtn_dialog_ok);
        tv_dialog_ok.setText(btnText);

        AppCompatTextView tv_error_string = (AppCompatTextView) inf.findViewById(R.id.actv_error_string);
        tv_error_string.setText(message);

        AppCompatTextView tv_thank_you = (AppCompatTextView) inf.findViewById(R.id.actv_thank_you);
        tv_thank_you.setText(title);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(inf);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundedcorner);
        dialog.show();

        tv_dialog_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

//    public static ArrayList<RegistrationFillData> parseData(JSONArray jsonArray) throws JSONException {
//
//        ArrayList<RegistrationFillData> arrayList = new ArrayList<>();
//
//        if (jsonArray != null && jsonArray.length() > 0) {
//
//            int size = jsonArray.length();
//
//            for (int i = 0; i < size; i++) {
//
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                RegistrationFillData fillData = new RegistrationFillData();
//
//                if (jsonObject.has(StaticDataUtility.CODE)) {
//
//                    fillData.setCODE(jsonObject.optString(StaticDataUtility.CODE));
//
//                }
//
//                if (jsonObject.has(StaticDataUtility.NAME)) {
//
//                    fillData.setNAME(jsonObject.optString(StaticDataUtility.NAME));
//
//                }
//
//
//                arrayList.add(fillData);
//
//            }
//        }
//
//        return arrayList;
//
//    }

    public static Bitmap getSelectedImageFromGallery(Activity activity, Intent data) {

        try {

            Bitmap selected_bimap = null;

            Uri selectedImageUri = data.getData();

            String[] projection = {MediaStore.MediaColumns.DATA};

            Cursor cursor = activity.getContentResolver().query(selectedImageUri, projection, null, null, null);

            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

            cursor.moveToFirst();

            String selectedImagePath = cursor.getString(column_index);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeFile(selectedImagePath, options);

            final int REQUIRED_SIZE = 1080;

            int scale = 1;

            while (options.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    options.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;

            selected_bimap = BitmapFactory.decodeFile(selectedImagePath, options);

            /*if (image_setup.getTag().toString().equals(getString(R.string.photo_proof))) {

                photo_image_name = new File(selectedImagePath).getName();

                stringImage = bitmapToString(selected_bimap);

                //bitmap = stringToBitmap(stringImage);

            } else {

                bussiness_image_name = new File(selectedImagePath).getName();

                stringBussinessImage = bitmapToString(selected_bimap);

                //bitmap = stringToBitmap(stringBussinessImage);

            }*/

            return selected_bimap;

        } catch (Exception e) {

            System.out.print("KitkatGalleryException ...>>>..." + e.getMessage());
            return null;
        }
    }

    public static Bitmap getSeletedImageFromKitkatGallery(Activity activity, Intent data) {

        ParcelFileDescriptor parcelFileDescriptor = null;

        try {

            parcelFileDescriptor = activity.getContentResolver().openFileDescriptor(data.getData(), "r");

            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

            final int REQUIRED_SIZE = 1080;

            int scale = 1;

            while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;

            Uri selectedImageUri = data.getData();
            String path = selectedImageUri.toString();

            Bitmap bmp = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

            /*if (image_setup.getTag().toString().equals(activity.getString(R.string.photo_proof))) {

                photo_image_name = path.substring(path.lastIndexOf("/") + 1);

                stringImage = bitmapToString(bmp);

                //bitmap = stringToBitmap(stringImage);

            } else {

                bussiness_image_name = path.substring(path.lastIndexOf("/") + 1);

                stringBussinessImage = bitmapToString(bmp);

                //bitmap = stringToBitmap(stringBussinessImage);

            }*/

            parcelFileDescriptor.close();

            return bmp;

        } catch (Exception e) {

            System.out.print("KitkatGalleryException ...>>>..." + e.getMessage());

            return null;

        }
    }

    public static String bitmapToString(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        String temp = Base64.encodeToString(b, Base64.DEFAULT);

        return temp;

    }

//    public static HashMap<String, String> getCommonParameters() {
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put(StaticDataUtility.LoginName, ApplicationLoader.getAppLoader().getPreferencesUtility().getLoginID());
//        params.put(StaticDataUtility.Password, ApplicationLoader.getAppLoader().getPreferencesUtility().getPasssword());
//
//        return params;
//
//    }

    public static String getLocalIpAddress() {

        try {

            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {

                NetworkInterface intf = en.nextElement();

                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                     enumIpAddr.hasMoreElements(); ) {

                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {

                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());

                        System.out.println("CommonUtility -- getLocalIpAddress -->" + ip);

                        return ip;

                    }
                }
            }
        } catch (SocketException ex) {

            System.out.println("CommonUtility -- getLocalIpAddress -->" + ex.getMessage());

        }

        return "";

    }

//    public static String getdevicename() {
//        return Build.MODEL.toString();
//    }
//
//
//    public static HashMap<String, String> getParams() {
//
//        return mySearchParams;
//
//    }
//
//    public static void setParams(HashMap<String, String> searchParams) {
//
//        mySearchParams = searchParams;
//
//    }
//
//    public static void shareImageOrCerti(Activity activity, String type, File file, String[] details) {
//
//        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//
//        if (type.equals(activity.getString(R.string.certificate))) {
//
//            sharingIntent.setType("application/pdf");
//            if (file.exists()) {
//
//                sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//
//            }
//
//            activity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
//
//        } else if (type.equals(activity.getString(R.string.image))) {
//
//            sharingIntent.setType("*/*");
//
//           /* sharingIntent.putExtra(Intent.EXTRA_TEXT, "Stone Id : ".concat(details[0]).
//                    concat("\n Lab : ").concat(details[1]).concat("\n Shape : ").concat(details[2]).
//                    concat("\n Carat : ").concat(details[3]).concat("\n Clarity-Color : ").
//                    concat(details[4]).concat("\n Cut-Pol-Sym-Flou : ").concat(details[5]));*/
//
//            if (file.exists()) {
//
//                try {
//                    File output = new File(Environment.getExternalStorageDirectory() + "/share.jpg");
//                    FileOutputStream out = new FileOutputStream(output);
//                    Uri uriFromPath = Uri.fromFile(file);
//
//                    Bitmap originalBitmap = null;
//                    Bitmap mutableBitmap = null;
//                    try {
//                        originalBitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uriFromPath));
//                        mutableBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//
//                    // NEWLY ADDED CODE STARTS HERE [
//                    Canvas canvas = new Canvas(mutableBitmap);
//
//                    Paint paint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
//                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
//                    paint.setColor(Color.parseColor("#000000")); // Text Color
//                    paint.setStrokeWidth(30); // Text Size
//
//                    canvas.drawBitmap(mutableBitmap, 0, 0, paint);
//
//                    String[] clarityColor = details[4].split("-");
//                    String color = "";
//                    String clarity = "";
//                    if (clarityColor.length > 0 && null != clarityColor[1])
//                        clarity = clarityColor[1] + "".trim();
//                    if (clarityColor.length > 1 && null != clarityColor[0])
//                        color = clarityColor[0] + "".trim();
//
//                    clarity = clarity.trim();
//                    color = color.trim();
//
//                    List<String> headersList = Arrays.asList("", "");
//                    List<List<String>> rowsList = null;
//
//
//                    rowsList = new ArrayList<>();
//                    rowsList.add(Arrays.asList("Stone Id:", details[0]));
//                    rowsList.add(Arrays.asList("Lab:", details[1]));
//                    rowsList.add(Arrays.asList("Shape:", details[2]));
//                    rowsList.add(Arrays.asList("Carat:", details[3]));
//                    if (color.length() > 0)
//                        rowsList.add(Arrays.asList("Color:", color));
//                    if (clarity.length() > 0)
//                        rowsList.add(Arrays.asList("Clarity:", clarity));
//                    rowsList.add(Arrays.asList(details[5].replace("--", "-"), ""));
//
//
//                    Board board = new Board(32);
//                    Table table = new Table(board, 32, headersList, rowsList);
//                    table.invalidate().setGridMode(Table.GRID_NON).setRowsList(rowsList);
//                    List<Integer> colWidthsList = Arrays.asList(14, 18);
//                    table.setColWidthsList(colWidthsList);
//                    Block tableBlock = table.tableToBlocks();
//                    board.setInitialBlock(tableBlock);
//                    board.build();
//                    String sharetext = board.getPreview();
//
//
//                    Typeface fontString = Typeface.createFromAsset(activity.getAssets(), "abc.ttf");
//
//                    paint.setTypeface(fontString);
//                    int x = 10, y = 10;
//                    for (String line : sharetext.split("\n")) {
//                        canvas.drawText(line, x, y, paint);
//                        y += paint.descent() - paint.ascent() + 4;
//                    }
//
//                    // NEWLY ADDED CODE ENDS HERE ]
//
//                    mutableBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
//                    out.flush();
//                    out.close();
//                    sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(output));
//                    activity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }
//    }
//
//    public static Bitmap combineImages(Bitmap c, Bitmap s) {
//        Bitmap cs = null;
//
//        int width, height = 0;
//
//        if (c.getWidth() > s.getWidth()) {
//            width = c.getWidth() + s.getWidth();
//            height = c.getHeight();
//        } else {
//            width = s.getWidth() + s.getWidth();
//            height = c.getHeight();
//        }
//
//        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//
//        Canvas comboImage = new Canvas(cs);
//
//        comboImage.drawBitmap(c, 0f, 0f, null);
//        comboImage.drawBitmap(s, 0f, c.getHeight(), null);
//
//        // this is an extra bit I added, just incase you want to save the new image somewhere and then return the location
//    /*String tmpImg = String.valueOf(System.currentTimeMillis()) + ".png";
//
//    OutputStream os = null;
//    try {
//      os = new FileOutputStream(loc + tmpImg);
//      cs.compress(CompressFormat.PNG, 100, os);
//    } catch(IOException e) {
//      Log.e("combineImages", "problem combining images", e);
//    }*/
//
//        return cs;
//    }
//
//    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
//        Paint paint = new Paint();
//        paint.setTextSize(textSize);
//        paint.setColor(textColor);
//        paint.setTextAlign(Paint.Align.LEFT);
//
//
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.BLUE);
//
//
//        float baseline = -paint.ascent(); // ascent() is negative
//        int width = (int) (paint.measureText(text) + 0.5f); // round
//        int height = (int) (baseline + paint.descent() + 0.5f);
//        Bitmap image = Bitmap.createBitmap(width, 500, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(image);
//
//        int x = 100, y = 100;
//        for (String line : text.split("\n")) {
//            canvas.drawText(line, x, y, paint);
//            y += paint.descent() - paint.ascent();
//        }
//        canvas.drawPaint(paint);
//        return image;
//    }
//
//    public static void checkPermission(Activity activity, int RequestCode, SharedPreferenceUtility preferenceUtility) {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            int hasWritePermission = activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            int hasReadPermission = activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
//
//            List<String> permissions = new ArrayList<String>();
//            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
//
//                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            } else {
//                preferenceUtility.setString(StaticDataUtility.STORAGE, activity.getString(R.string.true_false));
//            }
//
//            if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {
//
//                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//
//            } else {
//
//                preferenceUtility.setString(StaticDataUtility.STORAGE, activity.getString(R.string.
//                        true_false));
//
//            }
//
//            if (!permissions.isEmpty()) {
//
//                activity.requestPermissions(permissions.toArray(new String[permissions.size()]), RequestCode);
//
//            }
//        }
//    }
//
//    public static void askForPermission(Activity activity, String permission, Integer requestCode) {
//
//        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
//
//                //This is called if user has denied the permission before
//                //In this case I am just asking the permission again
//                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
//
//            } else {
//
//                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
//            }
//        } else {
//
//            Intent callIntent = new Intent(Intent.ACTION_VIEW);
//            callIntent.setData(Uri.parse("tel:+91 22 40757575"));
//            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            activity.startActivity(callIntent);
//        }
//    }
//
//
//    public static void call(final Activity activity) {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//                askForPermission(activity, Manifest.permission.CALL_PHONE, StaticDataUtility.CALL);
//
//            }
//
//        } else {
//
//            Intent callIntent = new Intent(Intent.ACTION_VIEW);
//            callIntent.setData(Uri.parse("tel:+91 22 40757575"));
//            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            activity.startActivity(callIntent);
//        }
//    }

    public static void logMeOut(final Activity activity, final String tag, String btnok, String btncancel) {

        View inf = LayoutInflater.from(activity).inflate(R.layout.common_alert_dialog, null);

        AppCompatButton tv_dialog_ok = (AppCompatButton) inf.findViewById(R.id.acbtn_dialog_ok);
        tv_dialog_ok.setText(btnok);

        AppCompatButton acbtn_delete = (AppCompatButton) inf.findViewById(R.id.acbtn_delete);
        acbtn_delete.setVisibility(View.VISIBLE);
        acbtn_delete.setText(btncancel);

        AppCompatTextView tv_error_string = (AppCompatTextView) inf.findViewById(R.id.actv_error_string);

        if (tag.equalsIgnoreCase(activity.getString(R.string.sign_out))) {
            tv_error_string.setText(activity.getString(R.string.logout_message));
        } else if (tag.equalsIgnoreCase(activity.getString(R.string.call))) {
            tv_error_string.setText(activity.getString(R.string.call_message));
        } else if (tag.equalsIgnoreCase(activity.getString(R.string.appvesion))) {
            tv_error_string.setText(activity.getString(R.string.updateapp));
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        AppCompatTextView tv_thank_you = (AppCompatTextView) inf.findViewById(R.id.actv_thank_you);
        tv_thank_you.setLayoutParams(params);
        tv_thank_you.setVisibility(View.VISIBLE);
        tv_thank_you.setText(activity.getString(R.string.app_name));

        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(inf);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundedcorner);
        dialog.show();

        tv_dialog_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();

//                if (tag.equalsIgnoreCase(activity.getString(R.string.sign_out))) {
//                    ApplicationLoader.getAppLoader().getPreferencesUtility().clearAll();
//                    activity.startActivity(new Intent(activity, LoginActivity.class));
//                    activity.finish();
//                } else if (tag.equalsIgnoreCase(activity.getString(R.string.call))) {
//                    call(activity);
//                } else if (tag.equalsIgnoreCase(activity.getString(R.string.appvesion))) {
//
//                    final String appPackageName = activity.getPackageName(); // getPackageName() from Context or Activity object
//                    Intent marketIntent = null;
//                    try {
//                        marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
//
//                    } catch (ActivityNotFoundException anfe) {
//                        marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
//                    }
//                    marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    activity.startActivity(marketIntent);
//                }
            }
        });

        acbtn_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
    }


   /* public static void downloadOrShare(Activity activity, String type, String stoneId, String operation, String url, String[] details) {

        String str = url;
        url = str.replace("http://docs.google.com/viewer?embedded=true&url=", "");

        CommonUtility.Log("COMMONUTILURL", url);

        File myDir = null;

        String fname = "";

        String root = Environment.getExternalStorageDirectory().toString();

        Calendar c = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = sdf.format(c.getTime());

        if (type.equals(activity.getString(R.string.certificate))) {

            myDir = new File(root.concat("/").concat(activity.getString(R.string.app_name)).concat("/").concat(activity.getString(R.string.certificate)));
            myDir.mkdirs();

            fname = "Certi_" + stoneId + ".pdf";
            CommonUtility.Log("CERTIURL", url);


        } else if (type.equals(activity.getString(R.string.image))) {

            myDir = new File(root.concat("/").concat(activity.getString(R.string.app_name)).concat("/").concat(activity.getString(R.string.image)));
            myDir.mkdirs();

            fname = activity.getString(R.string.image).concat("_").concat(strDate).concat("_").concat(stoneId).concat(".jpeg");

        }

        File file = new File(myDir, fname);

        if (file.exists()) {

            if (operation.equals(activity.getString(R.string.share))) {

                CommonUtility.shareImageOrCerti(activity, type, file, details);

            } else {
                CommonUtility.pathpopup(activity, activity.getString(R.string.preview), activity.getString(R.string.cancel), activity.getString(R.string.exist_file), "", file, type);
            }
        } else {

            //if (type.equals(getString(R.string.image))) {

            if (type.equalsIgnoreCase(activity.getString(R.string.image))) {

                new DownloadAndShareImageCerti(activity, type, operation, file, details).execute(url);

            } else if (type.equals(activity.getString(R.string.certificate))) {

                new Downloadpdf(activity, type, operation, file, details).execute(url);

            }

            //} else {
            //new DownloadFileFromURL(what, which, file).execute(temp);
            //}
        }
    }*/

    public static void openfile(File file, Activity activity) {
        Uri path = Uri.fromFile(file);
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //  pdfOpenintent.setDataAndType(path, "*/*");
        pdfOpenintent.setDataAndType(path, "application/pdf");
        try {
            activity.startActivity(pdfOpenintent);
        } catch (ActivityNotFoundException e) {

//            CommonUtility.commonAlert(activity,"No Pdf Viewer Found");
            Toast.makeText(activity, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }

    public static int statusBarHeight(android.content.res.Resources res) {
        return (int) (24 * res.getDisplayMetrics().density);
    }

    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);

            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }

        return uniqueID;
    }

    /*public static int createPdffile(Activity activity, CustomProgressDialog pDialog, ArrayList<DataListParamsGsonModel.Result> selectedStoneslist) throws Exception {

        String FILE;

        String folder_main = "/R.Rajesh Exports/".concat("Documents");
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {

            f.mkdirs();
        }

        FILE = Environment.getExternalStorageDirectory().toString().concat(folder_main).concat("/").
                concat(String.valueOf(System.currentTimeMillis())).concat(".pdf");

        Document document = new Document(B4.rotate());
        document.setMargins(5, 5, 7, 7);

        try {

            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();

            addMetaData(document);

            PdfPTable table = new PdfPTable(22);
            table.setTotalWidth(Utilities.millimetersToPoints(100));
            table.setWidths(new int[]{1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 2});

            table.setWidthPercentage(100);


            PdfPCell c1 = new PdfPCell(new Phrase("SR no", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Stone No", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1.setFixedHeight(20);
            c1 = new PdfPCell(new Phrase("Lab", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("CertNo", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Shape", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("CRT", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("COL", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("CL", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("CT", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("PL", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("SY", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("FL", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("RAP", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("DIS", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("$/CT", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Total", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("D/R", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Measure", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Depth", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Table", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Eye Clean", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Shade", FontFactory.getFont(FontFactory.HELVETICA, 9, com.itextpdf.text.Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setFixedHeight(20);
            table.addCell(c1);


            table.setHeaderRows(1);

            for (int i = 0; i < selectedStoneslist.size(); i++) {

                c1 = new PdfPCell(new Phrase(String.valueOf(i + 1), FontFactory.getFont(FontFactory.HELVETICA, 7, com.itextpdf.text.Font.BOLD)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getPId(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getCRName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getCertNo(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getSName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getCarat(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getGCName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getGQName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getGCTName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getGPOName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getGSYName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getGFLName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getGRap(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getDiscNew(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getGRateNew(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getTotalNew(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getDiameter(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getMeas(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getTotDepth(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getTable1(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getECName(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(selectedStoneslist.get(i).getBSName1(), FontFactory.getFont(FontFactory.HELVETICA, 7)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);


            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = "";
        try {


            result = "PDF Location : " + FILE;

            pDialog.dismiss();

            openfile(new File(FILE), activity);


        } catch (Exception e) {

            pDialog.dismiss();

            if (e instanceof ActivityNotFoundException) {

                String message = activity.getString(R.string.no_open_pdf_app_available);

                if (result.length() > 0) {

                    message = result.concat("\n\n").concat(message);

                }

                CommonUtility.commomAlert(activity, activity.getString(R.string.ok), message, "", false, false);

            }

        }

        return 0;
    }

    private static void addMetaData(com.itextpdf.text.Document document) {
        document.addTitle("RRajeshExports.co.peacocktech");
        document.addSubject("RRajeshExports.co.peacocktech");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("RRajeshExports.co.peacocktech");
        document.addCreator("RRajeshExports.co.peacocktech");
    }*/
}
