package com.example.pr26winter;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "Cat channel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        Button btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        Button btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {
            showToast();
        } else if (view.getId() == R.id.btn2){
            showToast2();
        } else if (view.getId() == R.id.btn3){
            showToast3();
        } else if (view.getId() == R.id.btn4){
            showToast4();
        } else if (view.getId() == R.id.btn5){
            showToast5();
        }else if (view.getId() == R.id.btn6){
            showToast6();
        }else if (view.getId() == R.id.btn7){
            showToast7();
        }
    }
    public void showToast() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Покормите собаку!",
                Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showToast2() {
        /*Toast toast1 = Toast.makeText(getApplicationContext(),
                R.string.toast, Toast.LENGTH_LONG);
        //toast2.setGravity(Gravity.CENTER, 0, 0);
        //LinearLayout toastContainer = (LinearLayout) toast.getView();
        ImageView image = new ImageView(getApplicationContext());
        toast1.getView().setBackgroundColor(Color.BLACK); // Изменение фонового цвета Toast
        image.setImageResource(R.drawable.ic_launcher_background);
       // toastContainer.setBackgroundColor(Color.TRANSPARENT);
       // toastContainer.addView(image, 0);
        toast1.setView(image); // Установка изображения в качестве контента Toast
        toast1.show();
        */
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom, findViewById(R.id.custom_container));

        Toast toast = new Toast(getApplicationContext());
        // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    public void showToast3() {
        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //.setSmallIcon(android.R.drawable.stat_sys_upload)
                .setContentTitle("Уведомление 3")
                .setContentText("Пора покормить собаку!")
                .setDefaults(Notification.DEFAULT_ALL)
                .setVibrate(new long[]{0, 100, 200, 300})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setColor(Color.YELLOW)
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        //явная про  ерка и обработка для доступа
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        notificationManagerCompat.notify(NOTIFY_ID, builder.build());
    }


    public void showToast4() {
        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_MUTABLE);

        //String bigText = "Это я, почтальон Печкин. Принёс для вас посылку. "
        //    + "Только я вам её не отдам. Потому что у вас документов нету. ";

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Привет от собаки")
                        .setContentText("ВИд уведомлений 4")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.drawable.dog)) // большая картинка
                        //.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                        .addAction(R.drawable.ic_launcher_foreground, "Открыть", pendingIntent)
                        .addAction(R.drawable.ic_launcher_background, "Отказаться", pendingIntent)
                        .addAction(R.drawable.ic_launcher_foreground, "Другой вариант", pendingIntent)
                        .setAutoCancel(true); // автоматически закрыть уведомление после нажатия
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    public void showToast5() {
        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Посылка")
                        .setContentText("Это я, почтальон Печкин. Принёс для вас посылку")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.drawable.dog)) // большая картинка
                        .addAction(R.drawable.ic_launcher_foreground, "Запустить активность",
                                pendingIntent)
                        // большая картинка из ресурсов
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(BitmapFactory.decodeResource(getResources(),
                                        R.drawable.dog)))
                        .setAutoCancel(true); // автоматически закрыть уведомление после нажатия

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activit Compat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    public void showToast6() {
        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Посылка")
                        .setContentText("Это я, почтальон Печкин. Принёс для вас посылку")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.drawable.dog)) // большая картинка
                        .addAction(R.drawable.ic_launcher_foreground, "Запустить активность",
                                pendingIntent)
                        // большая картинка из ресурсов
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("Первое сообщение").addLine("Второе сообщение")
                                .addLine("Третье сообщение").addLine("Четвертое сообщение")
                                .setSummaryText("+2 more"))
                        .setAutoCancel(true); // автоматически закрыть уведомление после нажатия

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFY_ID, builder.build());

    }

    public void showToast7() {
        Intent notificationIntent = new Intent(MainActivity.this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Person murzik = new Person.Builder().setName("Мурзик").build();
        Person vaska = new Person.Builder().setName("Васька").build();

        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle
                (murzik)
                .setConversationTitle("Android chat")
                .addMessage("Привет котаны!", System.currentTimeMillis(), murzik)
                .addMessage("А вы знали, что chat по-французски кошка?", System
                                .currentTimeMillis(),
                        murzik)
                .addMessage("Круто!", System.currentTimeMillis(),
                        vaska)
                .addMessage("Ми-ми-ми", System.currentTimeMillis(), vaska)
                .addMessage("Мурзик, откуда ты знаешь французский?", System.currentTimeMillis(),
                        vaska)
                .addMessage("Шерше ля фам, т.е. ищите кошечку!", System.currentTimeMillis(),
                        murzik);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.ic_launcher_foreground, "Запустить активность",
                                pendingIntent)
                        .setStyle(messagingStyle)
                        .setAutoCancel(true); // автоматически закрыть уведомление после нажатия

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFY_ID, builder.build());

    }









}