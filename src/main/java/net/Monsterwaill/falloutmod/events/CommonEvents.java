package net.Monsterwaill.falloutmod.events;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.TARDISBlock;
import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;
import net.Monsterwaill.falloutmod.entities.FalloutEntities;
import net.Monsterwaill.falloutmod.entities.PowerArmor;
import net.Monsterwaill.falloutmod.entities.PowerArmourEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FalloutMod.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public void onBlockPlace(BlockEvent.EntityPlaceEvent event) {

        BlockState blockState = event.getPlacedBlock();
        Block block = blockState.getBlock();

        if (block instanceof TARDISBlock tardisBlock) {
            if (event.getLevel().getBlockEntity(event.getPos()) instanceof TARDISBlockEntity tardisBlockEntity) {
                tardisBlockEntity.getAnimation().setupAnimation();
            }
        }
//        if (block instanceof BlastDoorBlock blast) {
//            createAdjacentBarriers(event.getLevel(),event.getPos(),event.getState(), (Player) event.getEntity());
//        }
    }
}
