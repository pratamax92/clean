package com.cartenz.component_design.image;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ImageHelper {

    public static void loadImageCenterCrop(Context context, String url, ImageView imageView, int placeholder) {
        RequestOptions myOptions = new RequestOptions().centerCrop();
        loadImage(context, url, placeholder).apply(myOptions).into(imageView);
    }

    public static void loadImageFitCenter(Context context, String url, ImageView imageView, int placeholder) {
        RequestOptions myOptions = new RequestOptions().fitCenter();
        loadImage(context, url, placeholder).apply(myOptions).into(imageView);
    }

    public static RequestBuilder<Drawable> loadImage(Context context, String url, int placeholder) {

        RequestBuilder<Drawable> glide = Glide.with(context).load(url);
        if (placeholder > 0) {
            RequestOptions options = new RequestOptions().placeholder(placeholder);
            ;
            return glide.apply(options);
        }
        return glide;
    }

    public static void setImage(Context context, String url, ProgressBar progressBar, ImageView imageview, int placeholder, int error) {
        imageview.setImageResource(0);
        if (!TextUtils.isEmpty(url)) {

            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }

            RequestBuilder<Drawable> glide = Glide.with(context).load(url);
            RequestOptions myOptions = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);

            if (placeholder > 0) {
                myOptions = myOptions.placeholder(placeholder);
            }
            if (error > 0) {
                myOptions = myOptions.error(error);
            }
            glide = glide.apply(myOptions);
            glide = glide.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    return false;
                }
            });
            glide.into(imageview);
        } else {
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    public static Bitmap textToBitmap(Context context, String mText, float zoom) {
        try {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//                paint.setTextAlign(Paint.Align.RIGHT);
            paint.setColor(Color.BLACK);
            paint.setTextSize((int) 2.5 * zoom);
            paint.setShadowLayer(1f, 0f, 1f, Color.DKGRAY);
            float baseline = -paint.ascent(); // ascent() is negative
            int width = (int) (paint.measureText(mText + 0.5f)); // round
            int height = (int) (baseline + paint.descent() + 0.5f);
            Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(image);
            canvas.drawText(mText, 0, baseline, paint);
            return image;
        } catch (Exception e) {
            return null;
        }
    }

    public static void setImageCircle(Context context, String url, ProgressBar progressBar, ImageView imageview, int placeholder, int error) {
        imageview.setImageResource(0);
        if (!TextUtils.isEmpty(url)) {

            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }


            imageview.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                    placeholder));

            RequestBuilder<Bitmap> glide = Glide.with(context).asBitmap().load(url);
            RequestOptions myOptions = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);

            if (placeholder > 0) {
                myOptions = myOptions.placeholder(placeholder);
            }
            if (error > 0) {
                myOptions = myOptions.error(error);
            }
            glide = glide.apply(myOptions);
            glide = glide.listener(new RequestListener<Bitmap>() {

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    imageview.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                            placeholder));
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    imageview.setImageBitmap(resource);
                    return false;
                }
            });
            glide.into(imageview);
        } else {
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    public static void loadImageWithHeader(String header, Context context, String url, ProgressBar progressBar, ImageView imageview, int placeholder, int error) {
        if (url == null) {
            setImageCircle(context, url, progressBar, imageview, placeholder, error);
        } else {
            GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                    .addHeader("Authorization", header)
                    .build());

            RequestBuilder<Bitmap> glide = Glide.with(context).asBitmap().load(glideUrl);

            RequestOptions myOptions = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);

            if (placeholder > 0) {
                myOptions = myOptions.placeholder(placeholder);
            }
            if (error > 0) {
                myOptions = myOptions.error(error);
            }
            glide = glide.apply(myOptions);
            glide = glide.listener(new RequestListener<Bitmap>() {

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    imageview.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                            placeholder));
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    imageview.setImageBitmap(resource);
                    return false;
                }
            });
            glide.into(imageview);
        }
    }

    public static ArrayList<Object> compressResizeImage(String uri) {
        Bitmap bmp = BitmapFactory.decodeFile(uri);
        return compressResizeImage(bmp);
    }
    public static ArrayList<Object> compressResizeImage(Bitmap bmp) {
        int MAX_IMAGE_SIZE = 300 * 1024;
        int bitmapheight = bmp.getHeight();
        int bitmapwidth = bmp.getWidth();
        int streamLength = bmp.getByteCount();
        int oneMB = 1024 * 1024;
        if (streamLength > oneMB) {
            MAX_IMAGE_SIZE = 600 * 1024;
        }
        ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
        int resize = 1;
        int quality = 80;
        if (streamLength > oneMB * 100) {
            quality = 50;
        } else if (streamLength > oneMB * 50) {
            quality = 60;
        } else if (streamLength > oneMB * 20) {
            quality = 70;
        }
        while (streamLength >= MAX_IMAGE_SIZE && quality > 0) {
            try {
                bmpStream.flush();//to avoid out of memory error
                bmpStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bmp.compress(Bitmap.CompressFormat.JPEG, quality, bmpStream);
            byte[] bmpPicByteArray = bmpStream.toByteArray();
            streamLength = bmpPicByteArray.length;
            if (quality <= 5) {
                quality -= 1;
            } else {
                quality -= 5;
                resize++;
            }
        }


        bitmapwidth = bitmapwidth / resize;
        bitmapheight = bitmapheight / resize;
        ArrayList<Object> list = new ArrayList<>();
        list.add(bmpStream.toByteArray());
        list.add(bitmapwidth);
        list.add(bitmapheight);

        return list;
    }

    public static byte[] bitmapToByte(String uri) {
        Bitmap bmp = BitmapFactory.decodeFile(uri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(new File(uri).getAbsolutePath(), options);
        ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
        try {
            bmpStream.flush();//to avoid out of memory error
            bmpStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bmpStream);
        return bmpStream.toByteArray();
    }

    public static byte[] pathToByte(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = new File(path);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] compressImage(String uri) {
        ArrayList<Object> list = new ArrayList<>(compressResizeImage(uri));
        if (list.size() > 0) {
            return (byte[]) list.get(0);
        } else {
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            return bmpStream.toByteArray();
        }
    }

    public static List<Integer> resize(String uri) {
        ArrayList<Object> list = new ArrayList<>(compressResizeImage(uri));
        List<Integer> result = new ArrayList<>();
        if (list.size() > 2) {
            result.add((Integer) list.get(1));
            result.add((Integer) list.get(2));
        }
        return result;
    }


    public static String printSize(int fileSize) {
        String sz = "";
        int kb = 1024;
        int mb = 1024 * 1024;
        if (fileSize > mb) {
            sz = fileSize + "b " + (fileSize / mb) + " mb";
        } else if (fileSize > kb) {
            sz = fileSize + "b " + (fileSize / kb) + " kb";
        } else {
            sz = (fileSize) + " b";
        }
        return sz;
    }

    public static int sizeOfBitmap(String uri) {
        Bitmap bmp = BitmapFactory.decodeFile(uri);
        return sizeOfBitmap(bmp);
    }


    public static int sizeOfBitmap(Bitmap data) {
        return data.getByteCount() / 10;
    }

    public static int maxImage() {
        return 1024 * 1024 * 5;
    }

    public static boolean imageIsAvailable(Bitmap bitmap) {
        int imageSize = ImageHelper.sizeOfBitmap(bitmap);
        return imageSize <= maxImage();
    }

    public static boolean imageIsAvailable(int imageSize) {
        return imageSize <= maxImage();
    }

    public static String getFileName(Context context, String uri) {
        if (TextUtils.isEmpty(uri)) {
            return null;
        } else {
            return getFileName(context, Uri.parse(uri));
        }
    }

    public static String getFileName(Context context, Uri uri) {
        if (uri == null) {
            return null;
        } else {
            String result = null;
            if (uri.getScheme().equals("content")) {
                Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }
            if (result == null) {
                result = uri.getPath();
                int cut = result.lastIndexOf('/');
                if (cut != -1) {
                    result = result.substring(cut + 1);
                }
            }
            return result;
        }
    }

    public static boolean checkIfNotLocale(String uri){
        return !checkIfLocale(uri);
    }

    public static boolean checkIfLocale(String uri){
        if(!TextUtils.isEmpty(uri)) {
            if (uri.contains("data/") || uri.contains("storage/") || uri.contains("content:") || uri.contains("external/")) {
                return true;
            }
        }
        return false;
    }
}
