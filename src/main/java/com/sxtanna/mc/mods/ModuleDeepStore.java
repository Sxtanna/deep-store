package com.sxtanna.mc.mods;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.sxtanna.mc.PluginDeepStore;
import com.sxtanna.mc.data.DeepStore;
import com.sxtanna.mc.data.DeepStoreType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Map;
import java.util.Optional;

public final class ModuleDeepStore implements Listener
{

	@NotNull
	private final NamespacedKey         spaced;
	@NotNull
	private final PluginDeepStore       plugin;
	@NotNull
	private final Map<Block, DeepStore> stores = Maps.newHashMap();


	public ModuleDeepStore(@NotNull final PluginDeepStore plugin)
	{
		this.plugin = plugin;
		this.spaced = new NamespacedKey(plugin, "storage");
	}


	public void load()
	{

	}

	public void kill()
	{
		stores.forEach(this::save);
		stores.clear();
	}


	@NotNull
	@Unmodifiable
	public Map<Block, DeepStore> getStores()
	{
		return ImmutableMap.copyOf(stores);
	}


	@NotNull
	public Optional<DeepStore> findDeepStore(@NotNull final Block block)
	{
		if (block.getType() != Material.BARREL)
		{
			return Optional.empty();
		}

		final DeepStore stored = stores.get(block);
		if (stored != null)
		{
			return Optional.of(stored);
		}

		final DeepStore loaded = ((PersistentDataHolder) block.getState()).getPersistentDataContainer().get(spaced, DeepStoreType.INSTANCE);
		if (loaded == null)
		{
			return Optional.empty();
		}

		load(block, loaded); // cache value

		return Optional.of(loaded);
	}


	private void load(@NotNull final Block block, @NotNull final DeepStore store)
	{
		stores.put(block, store);

		save(block, store);
	}

	private void save(@NotNull final Block block, @NotNull final DeepStore store)
	{
		final BlockState state = block.getState();

		final PersistentDataContainer container = ((PersistentDataHolder) state).getPersistentDataContainer();
		container.set(spaced, DeepStoreType.INSTANCE, store);

		final Inventory inventory = ((Barrel) state).getInventory();
		inventory.clear();

		state.update(true);
	}

}
