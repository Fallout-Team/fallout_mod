package net.Monsterwaill.falloutmod.mixin;

import net.Monsterwaill.falloutmod.item.custom.PipBoyItem;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {

    //    @Inject(at = @At("TAIL"),method = "Lnet/minecraft/client/renderer/entity/player/PlayerRenderer;<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;Z)V",cancellable = false)
//    private void addLayers(EntityRendererProvider.Context context, boolean slim, CallbackInfo ci) {
//
//    }
    @Inject(at = @At("HEAD"),method = "getTextureLocation(Lnet/minecraft/client/player/AbstractClientPlayer;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
    private void setSkin(AbstractClientPlayer player, CallbackInfoReturnable<ResourceLocation> cir) {
        ItemStack offHand = player.getItemBySlot(EquipmentSlot.OFFHAND);
        if(offHand.getItem() instanceof PipBoyItem item) {

        }
    }
}