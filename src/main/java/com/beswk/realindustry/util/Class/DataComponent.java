package com.beswk.realindustry.util.Class;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.inventory.ContainerData;

public class DataComponent extends TextComponent {
    ContainerData data;
    public DataComponent(String p_131286_, ContainerData data) {
        super(p_131286_);
        this.data = data;
    }

    public ContainerData getData() {
        return data;
    }
}
