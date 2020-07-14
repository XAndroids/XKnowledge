package com.android.xknowledge.aidl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.xknowledge.aidl.server.Person;
import com.android.xknowledge.aidl.server.PersonAidl;

public class MainActivity extends AppCompatActivity {
    private PersonAidl personAidl;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            personAidl = PersonAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            personAidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.aidl_addperson_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    personAidl.addPerson(new Person("aidl test"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.xknowledge.aidl.server", "com.android.xknowledge.aidl.server.PersonAidlService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
