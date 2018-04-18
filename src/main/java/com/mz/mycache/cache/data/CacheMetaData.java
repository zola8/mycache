package com.mz.mycache.cache.data;

import java.time.LocalTime;

public class CacheMetaData<T>
{

	private int expiration;
	private LocalTime time;
	private T data;

	public CacheMetaData(int expiration, LocalTime time, T data)
	{
		this.expiration = expiration;
		this.time = time;
		this.data = data;
	}

	public int getExpiration()
	{
		return expiration;
	}

	public void setExpiration(int expiration)
	{
		this.expiration = expiration;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public LocalTime getTime()
	{
		return time;
	}

	public void setTime(LocalTime time)
	{
		this.time = time;
	}

}
