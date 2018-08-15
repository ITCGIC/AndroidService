package gicitc.service.testservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyService myService = null;
    private ServiceConnection myConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void doStartService(View view) {
        startService(new Intent(this,MyService.class));
    }
    public void doStopService(View view) {
        stopService(new Intent(this,MyService.class));
    }
    public void doBindService(View view) {
        bindService(new Intent(this, MyService.class),
                myConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        myService = ((MyService.MyBinder)service).getService();
                        Toast.makeText(MainActivity.this, "Service bind succeed!!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        myService = null;
                        Toast.makeText(MainActivity.this, "Service disconnected!!", Toast.LENGTH_LONG).show();
                    }
                },BIND_AUTO_CREATE);
    }
    public void doCallServiceMethod(View view) {
        if(myService != null){
            myService.customFunction();
        }
    }
    public void doUnbindService(View view){
        if(myService != null){
            unbindService(myConnection);
            myService = null;
        }
    }

}
