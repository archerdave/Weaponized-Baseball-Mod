package blizzardfenix.webasemod.init;

import blizzardfenix.webasemod.BaseballMod;
import blizzardfenix.webasemod.init.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BaseballMod.MODID);
	
	public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = 
			CREATIVE_MODE_TABS.register("webasemod_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BASIC_BASEBALL.get()))
			.title(Component.translatable("creativetab.tutorial_tab"))
			.displayItems((pParameters, pOutput) -> {
				pOutput.accept(ModItems.BASIC_BASEBALL.get());
				pOutput.accept(ModItems.CORK.get());
				pOutput.accept(ModItems.BASEBALL_CORE.get());
				pOutput.accept(ModItems.BASEBALL_HALF.get());
				
				pOutput.accept(ModItems.DIRTBALL.get());
				pOutput.accept(ModItems.STONEBALL.get());
				pOutput.accept(ModItems.GOLFBALL.get());
				pOutput.accept(ModItems.SUPER_SLIMEBALL.get());
				
				pOutput.accept(ModItems.CATCHER_GLOVE.get());
				
				pOutput.accept(ModItems.WOODEN_BASEBALL_BAT.get());
				pOutput.accept(ModItems.STONE_BASEBALL_BAT.get());
				pOutput.accept(ModItems.GOLDEN_BASEBALL_BAT.get());
				pOutput.accept(ModItems.IRON_BASEBALL_BAT.get());
				pOutput.accept(ModItems.DIAMOND_BASEBALL_BAT.get());
				pOutput.accept(ModItems.NETHERITE_BASEBALL_BAT.get());

			})
			.build());
	
	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TABS.register(eventBus);
	}
}
