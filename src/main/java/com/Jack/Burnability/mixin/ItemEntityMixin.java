package com.Jack.Burnability.mixin;


import com.Jack.Burnability.ShulkerBoxItemHelper;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ItemEntity.class)
public class ItemEntityMixin {
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;remove()V"), method = "damage", cancellable = true)
	private void BurnDrop(CallbackInfoReturnable<Boolean> info) {
		ShulkerBoxItemHelper.EmptyShulkerBox(((ItemEntity) (Object) this));
		((ItemEntity) (Object) this).remove();
		info.setReturnValue(true);
	}
}
