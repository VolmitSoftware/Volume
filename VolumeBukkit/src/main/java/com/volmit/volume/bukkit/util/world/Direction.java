package com.volmit.volume.bukkit.util.world;

import com.volmit.volume.bukkit.pawn.Documented;
import com.volmit.volume.bukkit.util.world.Cuboid.CuboidDirection;
import com.volmit.volume.lang.collections.GList;

/**
 * Directions
 *
 * @author cyberpwn
 */
@Documented
public enum Direction
{
	U(0, 1, 0, CuboidDirection.Up),
	D(0, -1, 0, CuboidDirection.Down),
	N(0, 0, -1, CuboidDirection.North),
	S(0, 0, 1, CuboidDirection.South),
	E(1, 0, 0, CuboidDirection.East),
	W(-1, 0, 0, CuboidDirection.West);

	private int x;
	private int y;
	private int z;
	private CuboidDirection f;

	private Direction(int x, int y, int z, CuboidDirection f)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.f = f;
	}

	public int x()
	{
		return x;
	}

	public int y()
	{
		return y;
	}

	public int z()
	{
		return z;
	}

	public CuboidDirection f()
	{
		return f;
	}

	public static GList<Direction> news()
	{
		return new GList<Direction>().qadd(N).qadd(E).qadd(W).qadd(S);
	}

	public static GList<Direction> udnews()
	{
		return new GList<Direction>().qadd(U).qadd(D).qadd(N).qadd(E).qadd(W).qadd(S);
	}

	/**
	 * Get the directional value from the given byte from common directional blocks
	 * (MUST BE BETWEEN 0 and 5 INCLUSIVE)
	 *
	 * @param b
	 *            the byte
	 * @return the direction or null if the byte is outside of the inclusive range
	 *         0-5
	 */
	public static Direction fromByte(byte b)
	{
		if(b > 5 || b < 0)
		{
			return null;
		}

		if(b == 0)
		{
			return D;
		}

		else if(b == 1)
		{
			return U;
		}

		else if(b == 2)
		{
			return N;
		}

		else if(b == 3)
		{
			return S;
		}

		else if(b == 4)
		{
			return W;
		}

		else
		{
			return E;
		}
	}

	/**
	 * Get the byte value represented in some directional blocks
	 *
	 * @return the byte value
	 */
	public byte byteValue()
	{
		switch(this)
		{
			case D:
				return 0;
			case E:
				return 5;
			case N:
				return 2;
			case S:
				return 3;
			case U:
				return 1;
			case W:
				return 4;
			default:
				break;
		}

		return -1;
	}
}