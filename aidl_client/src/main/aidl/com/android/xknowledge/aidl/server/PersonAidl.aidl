// PersonAidl.aidl
package com.android.xknowledge.aidl.server;

// Declare any non-default types here with import statements
import com.android.xknowledge.aidl.server.Person;

interface PersonAidl {
    void addPerson(in Person person);

}
