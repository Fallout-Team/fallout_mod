package net.Monsterwaill.falloutmod.block.entities;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.FalloutBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FalloutBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FalloutMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<TARDISBlockEntity>> TARDIS_BLOCK_ENTITY = BLOCK_ENTITIES.register("tardis_block_entity",
            () -> BlockEntityType.Builder.of(TARDISBlockEntity::new, FalloutBlocks.TARDIS_BLOCK.get()).build(null));
}