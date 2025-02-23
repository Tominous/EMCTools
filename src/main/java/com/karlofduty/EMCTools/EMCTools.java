package com.karlofduty.EMCTools;

import com.karlofduty.EMCTools.commands.DiscordCommand;
import com.karlofduty.EMCTools.commands.JoinQueueCommand;
import com.karlofduty.EMCTools.commands.SafeRestartCommand;
import com.karlofduty.EMCTools.commands.VoteCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class EMCTools extends JavaPlugin
{
    public static EMCTools instance;

    @Override
    public void onEnable()
    {
        instance = this;
        EMCTools.executeCommand("whitelist on");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "queue:join");
        this.getCommand("joinqueue").setExecutor(new JoinQueueCommand());
        this.getCommand("saferestart").setExecutor(new SafeRestartCommand());
        this.getCommand("vote").setExecutor(new VoteCommand());
        this.getCommand("discord").setExecutor(new DiscordCommand());

        getServer().getScheduler().scheduleSyncDelayedTask(this, () ->
        {
            EMCTools.executeCommand("whitelist off");
        }, 200);
    }

    public static boolean executeCommand(String command)
    {
        return instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), command);
    }

    public static void log(String message)
    {
        instance.getServer().getLogger().info(message);
    }
}