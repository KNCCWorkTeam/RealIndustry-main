package com.beswk.realindustry.util.Class;

import net.minecraft.world.inventory.SimpleContainerData;

public class ObjectData extends SimpleContainerData {
    Object[] obj;
    public ObjectData(int p_40210_,int size) {
        super(p_40210_);
        obj = new Object[size];
    }

    public Object getObject(int index) {
        return obj;
    }

    public void set(int index,Object obj) {
        this.obj[index] = obj;
    }
}
