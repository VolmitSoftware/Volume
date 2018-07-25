package com.volmit.volume.bukkit.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Assistive command router
 *
 * @author cyberpwn
 *
 */
public class RouterCommand extends org.bukkit.command.Command
{
	private CommandExecutor ex;

	/**
	 * The router command routes commands to bukkit executors
	 *
	 * @param realCommand
	 *            the real command
	 * @param ex
	 *            the executor
	 */
	public RouterCommand(ICommand realCommand, CommandExecutor ex)
	{
		super(realCommand.getNode().toLowerCase());
		setAliases(realCommand.getNodes());

		this.ex = ex;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args)
	{
		return ex.onCommand(sender, this, commandLabel, args);
	}
}
