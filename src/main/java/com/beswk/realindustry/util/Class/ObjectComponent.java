package com.beswk.realindustry.util.Class;

import net.minecraft.network.chat.TextComponent;

public class ObjectComponent extends TextComponent {
    Object[] objs;
    public ObjectComponent(String p_131286_,Object... objs) {
        super(p_131286_);
        this.objs = objs;
    }

    public Object[] getObjs() {
        return objs;
    }
}
