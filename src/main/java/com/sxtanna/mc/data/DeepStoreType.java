package com.sxtanna.mc.data;

import com.sxtanna.mc.util.Serializer;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public final class DeepStoreType implements PersistentDataType<String, DeepStore>
{

	public static PersistentDataType<String, DeepStore> INSTANCE = new DeepStoreType();


	private DeepStoreType()
	{}


	@NotNull
	@Override
	public Class<String> getPrimitiveType()
	{
		return String.class;
	}

	@NotNull
	@Override
	public Class<DeepStore> getComplexType()
	{
		return DeepStore.class;
	}


	@NotNull
	@Override
	public String toPrimitive(@NotNull final DeepStore complex, @NotNull final PersistentDataAdapterContext context)
	{
		return Serializer.push(complex).orElseThrow(IllegalStateException::new);
	}

	@NotNull
	@Override
	public DeepStore fromPrimitive(@NotNull final String primitive, @NotNull final PersistentDataAdapterContext context)
	{
		return Serializer.pull(primitive, getComplexType()).orElseThrow(IllegalStateException::new);
	}

}
