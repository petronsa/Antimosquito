package petroncompany.antimosquito;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NotificationCompat;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;


public class MainActivity extends ActionBarActivity {
    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    ImageView botonimagen;
    Button diecisiete;
    Button doce;
    TextView fretext;
    String ftext;
    int icono_r;
    MediaPlayer mediaplayer;
    //NotificationManager nm;
    //Notification notif;
    Button quince;
    SharedPreferences sharedPreferences;
    int sonidoActual;
    Button veinte;
    Button veinticinco;
    private StartAppAd startAppAd = new StartAppAd(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StartAppSDK.init(this, "101423750", "206787354", true);
        this.setContentView(R.layout.activity_main);

        this.fretext = (TextView)(this.findViewById(R.id.fretext));
        this.doce = (Button)(this.findViewById(R.id.doce));

        this.doce.setOnClickListener((View.OnClickListener)(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.doce();
            }
        }));
        this.quince = (Button)(this.findViewById(R.id.quince));
        this.quince.setOnClickListener((View.OnClickListener)(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.quince();
            }
        }));
        this.diecisiete = (Button)(this.findViewById(R.id.diecisiete));
        this.diecisiete.setOnClickListener((View.OnClickListener)(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.diecisiete();
            }
        }));
        this.veinte = (Button)(this.findViewById(R.id.veinte));
        this.veinte.setOnClickListener((View.OnClickListener)(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.veinte();
            }
        }));
        this.veinticinco = (Button)(this.findViewById(R.id.veinticinco));
        this.veinticinco.setOnClickListener((View.OnClickListener)(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.veintcinco();
            }
        }));
        this.botonimagen = (ImageView)(this.findViewById(R.id.botonimagen));
        this.botonimagen.setOnClickListener((View.OnClickListener)(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.botonImagen();
            }
        }));
        this.sonidoActual = (R.raw.diecisietekhz);

        //Iniciar el notificationManager
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


    }

    private void createNotification()
    {
        Intent intent = new Intent(this,MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new  NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(getResources().getString(R.string.notificion))
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(NOTIFICATION_ID,notification);

    }

    public void destruirNotificacion()
    {
        notificationManager.cancel(NOTIFICATION_ID);
    }

  /*  public void borrarnotificacion() {
        this.nm.cancel(1);
    }*/

    public void botonImagen() {
        try {
            if ((this.mediaplayer != null) && (this.mediaplayer.isPlaying())) {
                this.pararAudio();
                this.botonimagen.setImageResource(R.drawable.mosquito);
                //this.borrarnotificacion();
                destruirNotificacion();
                return;
            }
            this.iniciarAudio();
            this.botonimagen.setImageResource(R.drawable.mosquitoprohibido);
            //this.notificar();
            //Creamos la notificacion
            createNotification();
            return;
        }
        catch (Exception var1_1) {
            this.iniciarAudio();
            return;
        }
    }

    public void iniciarAudio() {
        this.mediaplayer = MediaPlayer.create((Context)(this), (int) (this.sonidoActual));
        if (this.mediaplayer == null) {
            return;
        }
        this.mediaplayer.setLooping(true);
        this.mediaplayer.start();
    }

    public void pararAudio() {
        if (!((this.mediaplayer != null) && (this.mediaplayer.isPlaying()))) {
            return;
        }
        this.mediaplayer.stop();
    }
    public void reiniciarAudio() {
        try {
            if (this.mediaplayer == null) return;
            if (!(this.mediaplayer.isPlaying())) {
                return;
            }
            this.mediaplayer.stop();
            this.mediaplayer = MediaPlayer.create((Context)(this), (int)(this.sonidoActual));
            this.mediaplayer.setLooping(true);
            this.mediaplayer.start();
            return;
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
        }
    }



 /* public void notificacion(int n, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        long l = System.currentTimeMillis();
        Context context = this.getApplicationContext();
        PendingIntent pendingIntent = PendingIntent.getActivity((Context) this, (int)0, (Intent) new Intent((Context)this, (Class) MainActivity.class), (int)0);
        this.notif = new Notification(n, charSequence, l);
        this.notif.setLatestEventInfo(context, charSequence2, charSequence3, pendingIntent);
        this.notif.flags = 2;
    }

    public void notificar() {
        this.nm = (NotificationManager) this.getSystemService(ns);
        String string = this.ftext;
        this.notificacion(this.icono_r, (CharSequence) (string), (CharSequence) ("Antimosquito"), (CharSequence) ("Antimosquito en ejecucion"));
        this.nm.notify(1, this.notif);
    }
*/

    public void resetearBotones() {
        this.doce.setBackgroundColor(Color.LTGRAY);
        this.doce.setTextColor(Color.BLACK);
        this.quince.setBackgroundColor(Color.LTGRAY);
        this.quince.setTextColor(Color.BLACK);
        this.diecisiete.setBackgroundColor(Color.LTGRAY);
        this.diecisiete.setTextColor(Color.BLACK);
        this.veinte.setBackgroundColor(Color.LTGRAY);
        this.veinte.setTextColor(Color.BLACK);
        this.veinticinco.setBackgroundColor(Color.LTGRAY);
        this.veinticinco.setTextColor(Color.BLACK);
    }

    public void doce() {
        this.resetearBotones();
        this.sonidoActual = (R.raw.docekhz);
        this.reiniciarAudio();
        this.doce.setBackgroundColor(Color.GREEN);
        this.doce.setTextColor(-1);
        this.ftext = "Frecuencia 12 Khz";
        this.fretext.setText((CharSequence) (this.ftext));
    }

    public void quince() {
        this.resetearBotones();
        this.sonidoActual = (R.raw.quincekhz);
        this.reiniciarAudio();
        this.quince.setBackgroundColor(Color.GREEN);
        this.quince.setTextColor(-1);
        this.ftext = "Frecuencia 15 Khz";
        this.fretext.setText((CharSequence) (this.ftext));
    }

    public void diecisiete() {
        this.resetearBotones();
        this.sonidoActual = (R.raw.diecisietekhz);
        this.reiniciarAudio();
        this.diecisiete.setBackgroundColor(Color.GREEN);
        this.diecisiete.setTextColor(-1);
        this.ftext = "Frecuencia 17 Khz";
        this.fretext.setText((CharSequence) (this.ftext));
    }

    public void veinte() {
        this.resetearBotones();
        this.sonidoActual = (R.raw.veintekhz);
        this.reiniciarAudio();
        this.veinte.setBackgroundColor(Color.GREEN);
        this.veinte.setTextColor(-1);
        this.ftext = "Frecuencia 20 Khz";
        this.fretext.setText((CharSequence)(this.ftext));
    }

    public void veintcinco() {
        this.resetearBotones();
        this.sonidoActual = (R.raw.veinticincokhz);
        this.reiniciarAudio();
        this.veinticinco.setBackgroundColor(Color.GREEN);
        this.veinticinco.setTextColor(-1);
        this.ftext = "Frecuencia 25 Khz";
        this.fretext.setText((CharSequence)(this.ftext));
    }

    protected void segundoPlano() {
        this.moveTaskToBack(true);
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (n != 4) {
            return super.onKeyDown(n, keyEvent);
        }
        if (keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(n, keyEvent);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)(this));
        builder.setTitle((CharSequence)("Salir de la aplicacion"));
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage((CharSequence) ("Cerrar AntiMosquito")).setCancelable(false).setNegativeButton((CharSequence) ("Si"), (DialogInterface.OnClickListener)(new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                //MainActivity.this.borrarnotificacion();
                MainActivity.this.pararAudio();
                MainActivity.this.finish();
                destruirNotificacion();
                MainActivity.this.startAppAd.showAd();
                MainActivity.this.startAppAd.loadAd();
            }
        })).setNeutralButton((CharSequence) ("Minimizar"), (DialogInterface.OnClickListener)(new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int n) {
                MainActivity.this.segundoPlano();
            }
        })).setPositiveButton((CharSequence) ("No"), (DialogInterface.OnClickListener) (new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
            }
        }));
        builder.create().show();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return true;
            }
            case R.id.about: {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context)(this));
                builder.setMessage((CharSequence)("Anti Mosquito creada por Manuel Petron")).setIcon(R.mipmap.ic_launcher).setTitle((CharSequence)("Acerca de")).setCancelable(false).setNeutralButton((CharSequence)("Aceptar"), (DialogInterface.OnClickListener)(new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        dialogInterface.cancel();
                    }
                }));
                builder.create().show();
                return true;
            }
            case R.id.help:
        }
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)(this));
        builder.setMessage((CharSequence)("Pulse para empezar a emitir ultrasonidos, por defecto estan seleccionado 17Khz, puede quedar la aplicacion en segundo plano y utilizar el dispositivo con normalidad.")).setIcon(R.mipmap.ic_launcher).setTitle((CharSequence) ("Ayuda")).setCancelable(false).setNeutralButton((CharSequence) ("Aceptar"), (DialogInterface.OnClickListener) (new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface.cancel();
            }
        }));
        builder.create().show();
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
    }
    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }

}
