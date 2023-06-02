package net.Monsterwaill.falloutmod.network.packets;

import net.Monsterwaill.falloutmod.animation.ExteriorAnimation;
import net.Monsterwaill.falloutmod.animation.ExteriorClassicAnimation;
import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateExteriorAnimationS2CPacket {
    public boolean messageIsValid;

    private float alpha;
    private BlockPos pos;
    private boolean firstRun,started;

    public UpdateExteriorAnimationS2CPacket(BlockPos pos, float alpha, boolean firstRun) {
        this.pos = pos;
        this.alpha = alpha;
        this.firstRun = firstRun;
        this.messageIsValid = true;
    }
    public UpdateExteriorAnimationS2CPacket(BlockPos pos, float alpha,boolean started, boolean firstRun) {
        this.pos = pos;
        this.alpha = alpha;
        this.firstRun = firstRun;
        this.started = started;
        this.messageIsValid = true;
    }
    public UpdateExteriorAnimationS2CPacket(BlockPos pos, float alpha) {
        this.pos = pos;
        this.alpha = alpha;
        this.firstRun = false;
        this.started = true;
        this.messageIsValid = true;
    }
    public UpdateExteriorAnimationS2CPacket() {
        this.messageIsValid = false;
    }

    public static UpdateExteriorAnimationS2CPacket decode(FriendlyByteBuf buf) {
        UpdateExteriorAnimationS2CPacket packet = new UpdateExteriorAnimationS2CPacket();

        try {
            BlockPos pos = buf.readBlockPos();

            packet.alpha = buf.readFloat();
            packet.pos = pos;
            packet.firstRun = buf.readBoolean();
            packet.started = buf.readBoolean();
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("Exception while reading Packet: " + e);
            return packet;
        }

        packet.messageIsValid = true;
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        if (!this.messageIsValid) return;

        buf.writeBlockPos(this.pos);
        buf.writeFloat(this.alpha);
        buf.writeBoolean(this.firstRun);
        buf.writeBoolean(this.started);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Make sure it's only executed on the physical client
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                Level level = Minecraft.getInstance().level;

                if (level == null) {return;}

                TARDISBlockEntity entity = (TARDISBlockEntity) level.getBlockEntity(this.pos);
                if (entity == null) {return;}

                ExteriorAnimation animation = entity.getAnimation();
                animation.setAlpha(this.alpha);
                animation.setStarted(this.started);

                if (animation instanceof ExteriorClassicAnimation classic) {
                    classic.setFirstRun(this.firstRun);
                }
            });
        });
        return true;
    }
}
