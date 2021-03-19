package com.java.oop.copy;

import java.io.Serializable;

class DeepCloneObject implements Cloneable, Serializable {
    private String cloneName;

    public DeepCloneObject(String cloneName) {
        this.cloneName = cloneName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
