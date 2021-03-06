package com.volmit.volume.bukkit.nms;

import java.util.UUID;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.volmit.volume.bukkit.L;
import com.volmit.volume.bukkit.U;
import com.volmit.volume.bukkit.nms.adapter.AbstractChunk;
import com.volmit.volume.bukkit.nms.adapter.ChunkSendQueue;
import com.volmit.volume.bukkit.nms.adapter.NMSA10;
import com.volmit.volume.bukkit.nms.adapter.NMSA11;
import com.volmit.volume.bukkit.nms.adapter.NMSA12;
import com.volmit.volume.bukkit.nms.adapter.NMSA13;
import com.volmit.volume.bukkit.nms.adapter.NMSA8;
import com.volmit.volume.bukkit.nms.adapter.NMSA92;
import com.volmit.volume.bukkit.nms.adapter.NMSA94;
import com.volmit.volume.bukkit.pawn.Start;
import com.volmit.volume.bukkit.pawn.Stop;
import com.volmit.volume.bukkit.service.IService;
import com.volmit.volume.bukkit.util.net.Protocol;
import com.volmit.volume.bukkit.util.net.ProtocolRange;
import com.volmit.volume.bukkit.util.text.C;
import com.volmit.volume.bukkit.util.world.MaterialBlock;
import com.volmit.volume.lang.collections.GSet;

public class NMSSVC implements IService, IAdapter
{
	private IAdapter ia;

	public IAdapter adapter()
	{
		return ia;
	}

	@Start
	public void start()
	{
		Protocol cp = Protocol.getProtocolVersion();
		ia = null;

		if(Protocol.R1_8.to(Protocol.R1_8_9).contains(cp))
		{
			ia = new NMSA8();
		}

		else if(Protocol.R1_9.to(Protocol.R1_9_2).contains(cp))
		{
			ia = new NMSA92();
		}

		else if(Protocol.R1_9_3.to(Protocol.R1_9_4).contains(cp))
		{
			ia = new NMSA94();
		}

		else if(Protocol.R1_10.to(Protocol.R1_10_2).contains(cp))
		{
			ia = new NMSA10();
		}

		else if(Protocol.R1_11.to(Protocol.R1_11_2).contains(cp))
		{
			ia = new NMSA11();
		}

		else if(Protocol.R1_12.to(Protocol.R1_12_2).contains(cp))
		{
			ia = new NMSA12();
		}

		else if(Protocol.R1_13.to(Protocol.R1_13_2).contains(cp))
		{
			ia = new NMSA13();
		}

		try
		{
			U.getPawnObject(this).attach(ia);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		L.l("NMS Adapter: " + (ia == null ? "NONE" : ia.getClass().getCanonicalName()));
	}

	public boolean hasBinding()
	{
		return ia != null;
	}

	@Stop
	public void stop()
	{

	}

	@Override
	public ProtocolRange getSupportedProtocol()
	{
		if(!hasBinding())
		{
			return null;
		}

		return ia.getSupportedProtocol();
	}

	@Override
	public void addPacketHandler(IPacketHandler h)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.addPacketHandler(h);
	}

	@Override
	public void removePacketHandler(IPacketHandler h)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.removePacketHandler(h);
	}

	@Override
	public void sendPacket(Object packet)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendPacket(packet);
	}

	@Override
	public void sendPacket(Object packet, World world)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendPacket(packet, world);
	}

	@Override
	public void sendPacket(Object packet, Player player)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendPacket(packet, player);
	}

	@Override
	public void sendPacket(Object packet, Location location)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendPacket(packet, location);
	}

	@Override
	public void sendPacket(Object packet, Chunk chunk)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendPacket(packet, chunk);
	}

	@Override
	public int getViewDistance(Player player)
	{
		if(!hasBinding())
		{
			return -1;
		}

		return ia.getViewDistance(player);
	}

	@Override
	public boolean canSee(Player player, Location location)
	{
		if(!hasBinding())
		{
			return false;
		}

		return ia.canSee(player, location);
	}

	@Override
	public boolean canSee(Player player, Chunk chunk)
	{
		if(!hasBinding())
		{
			return false;
		}

		return ia.canSee(player, chunk);
	}

	@Override
	public void sendPickup(Entity drop, Entity who)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendPickup(drop, who);
	}

	@Override
	public void pathFind(LivingEntity e, Location l, boolean sprint, double speed)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.pathFind(e, l, sprint, speed);
	}

	@Override
	public void sendChunkMap(AbstractChunk c, Player p)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendChunkMap(c, p);
	}

	@Override
	public void sendChunkMap(AbstractChunk c, Chunk area)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendChunkMap(c, area);
	}

	@Override
	public void sendChunkUnload(int x, int z, Player p)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendChunkUnload(x, z, p);
	}

	@Override
	public void sendChunkUnload(int x, int z, Chunk area)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendChunkUnload(x, z, area);
	}

	@Override
	public void generateChunk(World world, int x, int z)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.generateChunk(world, x, z);
	}

	@Override
	public void setBlock(Location l, MaterialBlock m)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.setBlock(l, m);
	}

	@Override
	public void queueChunkUpdate(Chunk c)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.queueChunkUpdate(c);
	}

	@Override
	public ChunkSendQueue getChunkQueue()
	{
		if(!hasBinding())
		{
			return null;
		}

		return ia.getChunkQueue();
	}

	@Override
	public void relight(Chunk c)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.relight(c);
	}

	@Override
	public void updateSection(Chunk c, int section)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.updateSection(c, section);
	}

	@Override
	public void updateSections(Chunk c, int from, int to)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.updateSections(c, from, to);
	}

	@Override
	public void queueSection(Chunk c, int section)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.queueSection(c, section);
	}

	@Override
	public void queueSection(Location c)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.queueSection(c);
	}

	@Override
	public void updateSections(Chunk c, GSet<Integer> v)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.updateSections(c, v);
	}

	@Override
	public void injectBlockInstance(MaterialBlock mb, Object o)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.injectBlockInstance(mb, o);
	}

	@Override
	public void scroll(Player sender, int previous)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.scroll(sender, previous);
	}

	@Override
	public int getAction(Object packetIn)
	{
		if(!hasBinding())
		{
			return -1;
		}

		return ia.getAction(packetIn);
	}

	@Override
	public Vector getDirection(Object packetIn)
	{
		if(!hasBinding())
		{
			return new Vector();
		}

		return ia.getDirection(packetIn);
	}

	@Override
	public void spawnArmorStand(int eid, UUID id, Location l, int data, Player player)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.spawnArmorStand(eid, id, l, data, player);
	}

	@Override
	public void removeEntity(int eid, Player p)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.removeEntity(eid, p);
	}

	@Override
	public void moveEntityRelative(int eid, Player p, double x, double y, double z, boolean onGround)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.moveEntityRelative(eid, p, x, y, z, onGround);
	}

	@Override
	public void teleportEntity(int eid, Player p, Location l, boolean onGround)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.teleportEntity(eid, p, l, onGround);
	}

	@Override
	public void updatePassengers(Player p, int vehicle, int... passengers)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.updatePassengers(p, vehicle, passengers);
	}

	@Override
	public void displayScoreboard(Player p, int slot, String id)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.displayScoreboard(p, slot, id);
	}

	@Override
	public void displayScoreboard(Player p, C slot, String id)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.displayScoreboard(p, slot, id);
	}

	@Override
	public void sendNewObjective(Player p, String id, String name)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendNewObjective(p, id, name);
	}

	@Override
	public void sendDeleteObjective(Player p, String id)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendDeleteObjective(p, id);
	}

	@Override
	public void sendEditObjective(Player p, String id, String name)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendEditObjective(p, id, name);
	}

	@Override
	public void sendScoreUpdate(Player p, String name, String objective, int score)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendScoreUpdate(p, name, objective, score);
	}

	@Override
	public void sendScoreRemove(Player p, String name, String objective)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendScoreRemove(p, name, objective);
	}

	@Override
	public void sendTeam(Player p, String id, String name, String prefix, String suffix, C color, int mode)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendTeam(p, id, name, prefix, suffix, color, mode);
	}

	@Override
	public void addTeam(Player p, String id, String name, String prefix, String suffix, C color)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.addTeam(p, id, name, prefix, suffix, color);
	}

	@Override
	public void updateTeam(Player p, String id, String name, String prefix, String suffix, C color)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.updateTeam(p, id, name, prefix, suffix, color);
	}

	@Override
	public void addToTeam(Player p, String id, String... entities)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.addToTeam(p, id, entities);
	}

	@Override
	public void removeTeam(Player p, String id)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.removeTeam(p, id);
	}

	@Override
	public void removeFromTeam(Player p, String id, String... entities)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.removeFromTeam(p, id, entities);
	}

	@Override
	public void sendGlowingColorMeta(Player p, Entity glowing, C color)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendGlowingColorMeta(p, glowing, color);
	}

	@Override
	public void sendEffect(Player p, Entity entity, PotionEffectType type, int duration, int amp, boolean ambient, boolean showParticles)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendEffect(p, entity, type, duration, amp, ambient, showParticles);
	}

	@Override
	public void removeEffect(Player p, Entity entity, PotionEffectType type)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.removeEffect(p, entity, type);
	}

	@Override
	public void sendRemoveGlowingColorMeta(Player p, Entity glowing)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendRemoveGlowingColorMeta(p, glowing);
	}

	@Override
	public void sendRemoveGlowingColorMetaEntity(Player p, UUID glowing)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendRemoveGlowingColorMetaEntity(p, glowing);
	}

	@Override
	public void sendRemoveGlowingColorMetaPlayer(Player p, UUID glowing, String name)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendRemoveGlowingColorMetaPlayer(p, glowing, name);
	}

	@Override
	public void sendGlowingColorMetaEntity(Player p, UUID euid, C color)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendGlowingColorMetaEntity(p, euid, color);
	}

	@Override
	public void sendGlowingColorMetaName(Player p, String euid, C color)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendGlowingColorMetaName(p, euid, color);
	}

	@Override
	public void sendBrand(Player p, String brand)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.sendBrand(p, brand);
	}

	@Override
	public void spawnFallingBlock(int eid, UUID id, Location l, Player player, MaterialBlock mb)
	{
		if(!hasBinding())
		{
			return;
		}

		ia.spawnFallingBlock(eid, id, l, player, mb);
	}
}
