package com.beswk.realindustry.util.Class;

import net.minecraft.world.level.block.entity.BlockEntity;

public class MachineComponent extends ObjectComponent{
    public MachineComponent(String p_131286_, BlockEntity entity) {
        super(p_131286_, entity);
    }

    public BlockEntity getEntity() {
        return (BlockEntity)objs[0];
    }
}
