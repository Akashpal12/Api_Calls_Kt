package com.school.apicallskt.firebase_java.real_time_database;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.school.apicallskt.R;
import com.school.apicallskt.databinding.ActivityRealTimeDbJavaBinding;
import com.school.apicallskt.databinding.FirebaseDialogueBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RealTimeDbJava extends AppCompatActivity {
    ActivityRealTimeDbJavaBinding binding;
    DatabaseReference databaseReference;
    private FirebaseDialogueBinding dialogBinding;
    int OPEN_CAM_REQUEST = 101;
    int CHECK_CAM_PRMS = 100;
    String OPEN_PICTURE_PATH;
    Bitmap CAP_BMP;
    private boolean permissionRequested = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRealTimeDbJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.addBtn.setOnClickListener(v -> {
            Dialog dialog = new Dialog(RealTimeDbJava.this);

            dialogBinding = FirebaseDialogueBinding.inflate(getLayoutInflater());
            dialog.setContentView(dialogBinding.getRoot());

            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();

            dialogBinding.openCam.setOnClickListener(v1 -> {
                /*if (ContextCompat.checkSelfPermission(RealTimeDbJava.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RealTimeDbJava.this, new String[]{Manifest.permission.CAMERA}, CHECK_CAM_PRMS);
                } else {
                    captureImage();
                }*/
                if (ContextCompat.checkSelfPermission(RealTimeDbJava.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("PermissionCheck", "Camera permission not granted. Requesting permission...");
                    if (!permissionRequested) {
                        ActivityCompat.requestPermissions(RealTimeDbJava.this, new String[]{Manifest.permission.CAMERA}, CHECK_CAM_PRMS);
                        permissionRequested = true;
                    } else {
                        showSettingsOrRationale();
                    }
                } else {
                    Log.d("PermissionCheck", "Camera permission granted. Launching camera...");
                    captureImage();
                }

            });
            dialogBinding.openGallery.setOnClickListener(v1 -> {

            });
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("message").setValue("Hello, Firebase!");

        // Example: Reading data from the database
        databaseReference.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                Toast.makeText(RealTimeDbJava.this, "" + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    void captureImage() {
        try {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            PackageManager packageManager = RealTimeDbJava.this.getPackageManager();
            if (takePictureIntent.resolveActivity(RealTimeDbJava.this.getPackageManager()) != null) {

                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {

                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(RealTimeDbJava.this,
                            "com.school.apicallskt.provider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                }
            }
            startActivityForResult(takePictureIntent, OPEN_CAM_REQUEST);
        } catch (Exception e) {

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        OPEN_PICTURE_PATH = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_CAM_REQUEST) {
            try {
                File checkImg = new File(OPEN_PICTURE_PATH);
                if (checkImg.exists()) {
                    Bitmap imageBitmap = ShrinkBitmapByPath(OPEN_PICTURE_PATH, 800, 800);
                    imageBitmap = adjustImageOrientation(imageBitmap, OPEN_PICTURE_PATH);
                    dialogBinding.imageView.setImageBitmap(imageBitmap);
                } else {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    CAP_BMP = imageBitmap;
                    createImageFile();
                    File file = new File(OPEN_PICTURE_PATH);
                    if (file != null && file.exists()) {
                        file.delete();
                    }
                    if (CAP_BMP != null) {
                        file.createNewFile();
                        Bitmap bitmap = CAP_BMP;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        byte[] bitmapdata = bos.toByteArray();
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                    }
                    dialogBinding.imageView.setImageBitmap(imageBitmap);

                }
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public Bitmap ShrinkBitmapByPath(String file, int width, int height) throws Exception {
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);

        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) height);
        int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth / (float) width);

        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }

        bmpFactoryOptions.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
        return bitmap;
    }

    Bitmap adjustImageOrientation(Bitmap bitmap, String imagePath) {
        try {
            ExifInterface exifInterface = new ExifInterface(imagePath);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED
            );

            Matrix matrix = new Matrix();
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.setRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.setRotate(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.setRotate(270);
                    break;
                case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    matrix.setScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    matrix.setScale(1, -1);
                    break;
                default:
                    return bitmap;
            }
            // Apply matrix transformation to the bitmap
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
            return bitmap; // Return the original bitmap if an error occurs
        }
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CHECK_CAM_PRMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("PermissionCheck", "Camera permission granted in onRequestPermissionsResult.");
                captureImage();
            } else {
                Log.d("PermissionCheck", "Camera permission denied in onRequestPermissionsResult.");
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CHECK_CAM_PRMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("PermissionCheck", "Camera permission granted.");
                captureImage();
            } else {
                Log.d("PermissionCheck", "Camera permission denied.");
                boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);

                if (showRationale) {
                    // Show rationale and re-request permission
                    Toast.makeText(this, "Camera permission is needed to proceed.", Toast.LENGTH_LONG).show();
                } else {
                    // Permission denied with "Don't ask again", show settings or inform user
                    showSettingsOrRationale();
                }
            }
        }
    }

    // Show app settings or inform user
    private void showSettingsOrRationale() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Required")
                .setMessage("You have denied camera permission. Please enable it in app settings to proceed.")
                .setPositiveButton("Go to Settings", (dialog, which) -> {
                    dialog.cancel();
                    openAppSettings();
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    // Open the app's settings page
    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, OPEN_CAM_REQUEST);
    }
}