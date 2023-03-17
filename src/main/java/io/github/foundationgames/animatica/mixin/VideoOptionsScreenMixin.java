package io.github.foundationgames.animatica.mixin;

import io.github.foundationgames.animatica.Animatica;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(VideoOptionsScreen.class)
public abstract class VideoOptionsScreenMixin extends Screen {
    protected VideoOptionsScreenMixin(Text title) {
        super(title);
    }

    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/OptionListWidget;addAll([Lnet/minecraft/client/option/SimpleOption;)V"
            ),
            index = 0
    )
    private SimpleOption<?>[] animatica$addTextureAnimationOptionButton(SimpleOption<?>[] old) {
        var options = new SimpleOption<?>[old.length + 1];
        System.arraycopy(old, 0, options, 0, old.length);
        options[options.length - 1] = Animatica.CONFIG.getOption();
        return options;
    }
}
