package idv.leo.t5_1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView tvResult;

    private boolean isBound;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        tvResult = (TextView) findViewById(R.id.tvResult);

    }

    public void onConnectClick(View view) {
        doBindService();
        new CountDownTimer(3000,1000){

            @Override
            public void onFinish() {
                tvResult.setText("這是過3秒會出現的");
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }

        }.start();

    }

    public void onDisconnectClick(View view) {
        doUnbindService();
    }



    void doBindService() {
        if (!isBound) {
            Intent intent = new Intent(this, Service.class);

            bindService(intent, serviceCon, Context.BIND_AUTO_CREATE);
            isBound = true;
        }
    }

    void doUnbindService() {

        if (isBound) {
            unbindService(serviceCon);
            isBound = false;

            tvResult.setText(R.string.msg_serviceDisconnected);
        }
    }

    private ServiceConnection serviceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = ((Service.ServiceBinder) iBinder).getService();
            tvResult.setText(R.string.msg_serviceConnected);

        }

        @Override

        public void onServiceDisconnected(ComponentName className) {
            service = null;
            tvResult.setText(R.string.msg_serviceLostConnection);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
