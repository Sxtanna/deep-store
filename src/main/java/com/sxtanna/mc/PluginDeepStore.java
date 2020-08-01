package com.sxtanna.mc;

import com.sxtanna.mc.mods.ModuleDeepStore;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class PluginDeepStore extends JavaPlugin
{

	@NotNull
	private final ModuleDeepStore module = new ModuleDeepStore(this);


	@Override
	public void onLoad()
	{

	}

	@Override
	public void onEnable()
	{
		module.load();
	}

	@Override
	public void onDisable()
	{
		module.kill();
	}


	@NotNull
	public ModuleDeepStore getModule()
	{
		return module;
	}

}
