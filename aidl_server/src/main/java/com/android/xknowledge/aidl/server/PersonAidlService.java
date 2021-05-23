package com.android.xknowledge.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PersonAidlService extends Service {
    private ArrayList<Person> personList;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        personList = new ArrayList<>();
        return iBinder;
    }

    private IBinder iBinder = new PersonAidl.Stub() {

        @Override
        public void addPerson(Person person) throws RemoteException {
            personList.add(person);
            Log.i("AIDL", "PersonAidlService = " + personList.toString());
            Log.i("AIDL", "Service Thread = " + Thread.currentThread().getName());
        }
    };
}
