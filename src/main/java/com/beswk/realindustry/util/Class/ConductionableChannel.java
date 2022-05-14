package com.beswk.realindustry.util.Class;

import com.beswk.realindustry.BlockEntities.Conductionable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class ConductionableChannel extends ArrayList<Conductionable> {
    private Level level;
    private BlockPos pos;
    public ConductionableChannel(Level level, BlockPos pos) {
        this.level = level;
        this.pos = pos;
        construct();
    }

    public ConductionableChannel(ArrayList<Conductionable> arrayList) {
        if (arrayList.size()!=0&&arrayList.get(0) instanceof BlockEntity entity) {
            for (Conductionable c:arrayList) {
                this.add(c);
            }
            this.level = entity.getLevel();
            this.pos = entity.getBlockPos();
        }
    }

    void construct() {
        this.combine(getConnectedConductionables(new ArrayList<>()));
    }

    ConductionableChannel getConnectedConductionables(ArrayList<Conductionable> hasConnected) {
        ConductionableChannel channel = new ConductionableChannel(hasConnected);
        if (level.getBlockEntity(pos) instanceof Conductionable conductionable) {
            channel.add(conductionable);
        }
        for (int i = 0; i < 7; i++) {
            BlockEntity entityThis = null;
            if (level!=null&&pos!=null) {
                switch (i) {
                    case 0 -> entityThis = level.getBlockEntity(pos.above());
                    case 1 -> entityThis = level.getBlockEntity(pos.below());
                    case 2 -> entityThis = level.getBlockEntity(pos.south());
                    case 3 -> entityThis = level.getBlockEntity(pos.north());
                    case 4 -> entityThis = level.getBlockEntity(pos.west());
                    case 5 -> entityThis = level.getBlockEntity(pos.east());
                }
            }
            if (entityThis instanceof Conductionable conductionable&&!channel.contains(conductionable)&&conductionable.getConductionableChannel()!=null) {
                /*if (hasConnected.size()>=1) {
                    System.out.println(hasConnected.get(hasConnected.size() - 1) + "->" + "{" + level.getBlockEntity(pos) + "}:" + channel + "->" + entityThis);
                }

                 */
                channel.add(conductionable.getConductionableChannel().getConnectedConductionables(channel).toArray(new Conductionable[0]));
            }
        }
        return channel;
    }

    void combine(ConductionableChannel conductionableChannel) {
        if (conductionableChannel!=null) {
            this.add(conductionableChannel.toArray(new Conductionable[0]));
        }
    }

    @Override
    public boolean add(Conductionable conductionable) {
        if (!this.contains(conductionable)) {
            return super.add(conductionable);
        }
        return false;
    }

    public void add(Conductionable... conductionables) {
        for (Conductionable conductionable:conductionables) {
            this.add(conductionable);
        }
    }

    public void remove(Conductionable conductionable) {
        /*
        for (Conductionable c:conductionable.getConductionableChannel()) {
            if (c!=null) {
                c.getConductionableChannel().remove((Object)conductionable);
            }
        }
        if (this.contains(conductionable)) {
            for (int i = 0; i < 7; i++) {
                BlockEntity entityThis = null;
                switch (i) {
                    case 0 -> entityThis = level.getBlockEntity(pos.above());
                    case 1 -> entityThis = level.getBlockEntity(pos.below());
                    case 2 -> entityThis = level.getBlockEntity(pos.south());
                    case 3 -> entityThis = level.getBlockEntity(pos.north());
                    case 4 -> entityThis = level.getBlockEntity(pos.west());
                    case 5 -> entityThis = level.getBlockEntity(pos.east());
                    case 6 -> entityThis = level.getBlockEntity(pos);
                }
                if (entityThis instanceof Conductionable c) {
                    try {
                        this.remove((Object)c);
                    } catch (Exception e) {
                        //
                    }
                }
            }
        }

         */
    }

    public ArrayList<Conductionable> getConductionable(Conductionable.EnergyExportType type) {
        ArrayList<Conductionable> arrayList = new ArrayList<>();
        for (Conductionable c:this) {
            if (c.getEnergyExportType()==type) {
                arrayList.add(c);
            }
        }
        return arrayList;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }
}
