package jaipurbus.jaipur.tourism.jaipurbus.services;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;

import com.codersworld.jplibs.storage.AppHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.ui.SplashActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessageService";
    String CHANNEL_ID;
    Bitmap bitmap;
    Bitmap bitmap1;
    Context mcontext;
    String notification_type = "";
    PendingIntent pendingIntent;
    /* access modifiers changed from: private */
    public TextToSpeech tts;
    String image = "";

    String noti_id = "";

    public void onMessageReceived(RemoteMessage remoteMessage) {
        //this.prefsOld = new UserSessions(getApplicationContext().getSharedPreferences(Constants.SESSION_USER_DB_NAME, Context.MODE_PRIVATE));
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().toString());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        String message = "";
        String message1 = "";
        String strInfo = "";
        try {
            image = remoteMessage.getData().get("image");
        } catch (Exception e) {
            e.printStackTrace();
            image = "";
        }

        String type = remoteMessage.getData().get("type");
        message = remoteMessage.getData().get("title");
        message1 = remoteMessage.getData().get("message");

        String TrueOrFlase = "";//remoteMessage.getData().get("AnotherActivity");
        try {
            this.bitmap1 = getBitmapfromUrl("https://farmkey.in/assets/images/favicon.png");
            if (image != null && !image.isEmpty()) {
                this.bitmap = getBitmapfromUrl1(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       // Log.e("message : " + type, message + "\n" + message1);
        //this.bitmap1 = getBitmapfromUrl(AppUrls.API_BASE_URL + "assets/images/favicon.png");
        if (type == null || type.isEmpty()) {
            type = "100";
        }
/*        if (type.equalsIgnoreCase("2") || type.equalsIgnoreCase("3") || type.equalsIgnoreCase("4")) {
            if (type.equalsIgnoreCase("2") && LocalStorage.getIsLogin(this).equalsIgnoreCase("true")) {
                try {
                    LocalStorage.saveAppStatus(getApplicationContext(), type);
                    CommonMethods.checkStatusService(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }  else {
                try {
                    LocalStorage.saveAppStatus(getApplicationContext(), type);
                    CommonMethods.checkStatusService(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (!type.equalsIgnoreCase("1")) {
            if (LocalStorage.getIsLogin(this).equalsIgnoreCase("true") && String.valueOf(LocalStorage.getIsLogin(this).equalsIgnoreCase("true")).equalsIgnoreCase(userid)) {
                try {
                    sendNotification(message, message1, this.bitmap, this.bitmap1, TrueOrFlase, strInfo, type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {*/
        if (type.equalsIgnoreCase("200")) {
           new AppHelper().clearDBData(this);
        } else {
            try {
                sendNotification(message, message1, this.bitmap, this.bitmap1, TrueOrFlase, strInfo, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onNewToken(String token) {
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        //Log.e("tokentoken", token);
        SharedPreferences.Editor mEditorprefsToken = getSharedPreferences("JB_DEVICE_ID", 0).edit();
        mEditorprefsToken.putString("device_id", token);
        mEditorprefsToken.commit();
    }

    @SuppressLint("RestrictedApi")
    private void sendNotification(String messageBody, String mMessage, Bitmap image11, Bitmap imageicon,
                                  String TrueOrFalse, String strInfo, String type) {
        String str = messageBody;
        String str2 = mMessage;
        this.mcontext = this.mcontext;
        Context baseContext = getBaseContext();
        this.mcontext = baseContext;
        NotificationManager notificationManagero = (NotificationManager) baseContext.getSystemService(NOTIFICATION_SERVICE);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(2);
        if (type.equalsIgnoreCase("10")) {
        } else {
            String str5 = strInfo;
            Intent intent3 = null;
            intent3 = new Intent(this, SplashActivity.class);
            intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                pendingIntent = PendingIntent.getActivity
                        (this, 0, intent3, PendingIntent.FLAG_MUTABLE);
            } else {
                pendingIntent = PendingIntent.getActivity
                        (this, 0, intent3, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
            }
            // this.pendingIntent = PendingIntent.getActivity(this, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();
            Random r = new Random();
            int i3 = r.nextInt(15) + 65;
            this.CHANNEL_ID = "my_channel_01";
            NotificationChannel mChannel = new NotificationChannel(this.CHANNEL_ID, "my_channel",
                    NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription("This is my channel");
            mChannel.enableLights(true);
            mChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            mChannel.enableVibration(true);
            if (!type.equalsIgnoreCase("1001")) {
                mChannel.setSound(defaultSoundUri, audioAttributes);
            }
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(true);
            new NotificationCompat.BigTextStyle();
            notificationManagero.createNotificationChannel(mChannel);
            Random random = r;
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this.mcontext, this.CHANNEL_ID);
            try {
                if (image != null && !image.isEmpty()) {
                    this.bitmap = getBitmapfromUrl(image);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.bitmap != null) {
                Object obj = "my_channel";
                builder.setSmallIcon(R.mipmap.ic_launcher_round).setContentTitle(str)
                        .setContentText(str2)
                        .setContentIntent(this.pendingIntent)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(this.bitmap)
                                .bigLargeIcon((Bitmap) null))
                        .setAutoCancel(true)
                        .setLargeIcon(this.bitmap);
                notificationManagero.notify(i3, builder.build());
                return;
            }
            CharSequence name = "my_channel";
            builder.setContentTitle(str).setSmallIcon(Build.VERSION.SDK_INT >= 21 ? R.mipmap.ic_launcher_round : R.mipmap.ic_launcher_round).setContentIntent(this.pendingIntent).setStyle(new NotificationCompat.BigTextStyle().bigText(Html.fromHtml(mMessage))).setContentText(str2);
            notificationManagero.notify(i3, builder.build());
            return;
        }
        int icon = Build.VERSION.SDK_INT >= 21 ? R.mipmap.ic_launcher_round : R.mipmap.ic_launcher_round;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        if (this.bitmap != null) {
            notificationBuilder.setSmallIcon(icon).setContentTitle(str).setContentIntent(this.pendingIntent)
                    .setContentText(str2).setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(Html.fromHtml(messageBody))).setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(this.bitmap).bigLargeIcon((Bitmap) null))
                    .setLargeIcon(this.bitmap);
            if (!type.equalsIgnoreCase("1")) {
                notificationBuilder.setSound(defaultSoundUri);
            }
            notificationBuilder.setAutoCancel(true);
            notificationBuilder.setLargeIcon(bitmap);
            Context baseContext2 = getBaseContext();
            this.mcontext = baseContext2;
            ((NotificationManager) baseContext2.getSystemService(NOTIFICATION_SERVICE)).notify(4546, notificationBuilder.build());
            showsound(messageBody);
            return;
        }
        notificationBuilder.setSmallIcon(icon)
                .setAutoCancel(true).setLargeIcon(bitmap).setSmallIcon(icon)
                .setContentIntent(this.pendingIntent).setContentTitle(str2)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Html.fromHtml(messageBody))).setAutoCancel(true)
                .setContentText(str);
        if (!type.equalsIgnoreCase("1001")) {
            notificationBuilder.setSound(defaultSoundUri);
        }
        Context baseContext3 = getBaseContext();
        this.mcontext = baseContext3;
        ((NotificationManager) baseContext3.getSystemService(NOTIFICATION_SERVICE)).notify(4546, notificationBuilder.build());
        showsound(messageBody);
    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
            connection.setDoInput(true);
            connection.connect();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void showsound(String messageBody) {
        TextToSpeech textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status != -1) {
                    MyFirebaseMessagingService.this.tts.setLanguage(Locale.UK);
                }
            }
        });
        this.tts = textToSpeech;
        textToSpeech.speak(messageBody, 0, (HashMap) null);
        TextToSpeech textToSpeech2 = this.tts;
        if (textToSpeech2 != null) {
            textToSpeech2.stop();
            this.tts.shutdown();
        }
    }

    public Bitmap getBitmapfromUrl1(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }

}
