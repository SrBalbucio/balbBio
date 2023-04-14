package me.balbucio.bio;

import java.io.IOException;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Bio extends Command{
	public Bio() {
		super("bio");
	}
	public void execute(CommandSender sender, String[] args) {
		if(sender.hasPermission("bbio.use")) {
		try {
			Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(Main.getInstance().bio_file);
			String bio_atual = config.getString(sender.getName());
		if(args.length == 0) {
			if(!(bio_atual.length() == 0)) {
			sender.sendMessage(new TextComponent(""));
			sender.sendMessage(new TextComponent(Main.getInstance().msg_1));
			sender.sendMessage(new TextComponent(bio_atual));
			sender.sendMessage(new TextComponent(""));
			} else {
				sender.sendMessage(new TextComponent(Main.getInstance().msg_2));
			}
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("apagar")) {
			config.set(sender.getName(), "");;
			sender.sendMessage(new TextComponent(Main.getInstance().msg_5));
		}
		if(args.length > 1 && args[0].equalsIgnoreCase("add")) {
			String message =  "";
			for(int i= 1; 
					i < args.length; 
					i++) {
				message = message +" "+ args[i];
			}
			config.set(sender.getName(), message);
			sender.sendMessage(new TextComponent(Main.getInstance().msg_6));
		}
		if(args.length > 1 && args[0].equalsIgnoreCase("ver")) {
			String message =  "";
				message = args[1];
				if(config.contains(message)) {
					String bio = config.getString(message);
				if(!(bio.length() == 0)) {
				sender.sendMessage(new TextComponent(""));
				sender.sendMessage(new TextComponent(Main.getInstance().msg_3+" "+message));
				sender.sendMessage(new TextComponent(bio));
				sender.sendMessage(new TextComponent(""));
				} else {
					sender.sendMessage(new TextComponent(Main.getInstance().msg_4));
				}
				} else {
					sender.sendMessage(new TextComponent(Main.getInstance().msg_4));
				}
		}
		if(args.length > 1 && args[0].equalsIgnoreCase("delete")) {
			if(sender.hasPermission("bbio.staff")) {
			String message =  "";
				message = args[1];
				config.set(message, "");;
				sender.sendMessage(new TextComponent(Main.getInstance().msg_5));
			}
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(new TextComponent("§aHelp > §f/bio"));
			sender.sendMessage(new TextComponent("§aHelp > §f/bio apagar"));
			sender.sendMessage(new TextComponent("§aHelp > §f/bio ver <player>"));
			sender.sendMessage(new TextComponent("§aHelp > §f/bio add <bio>"));
		}
		ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, Main.getInstance().bio_file);
		}catch(IOException e) {
			BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§c[BalbucioBio] Ocorreu um erro grave ao carregar as Bios!"));
			e.printStackTrace();
		}
		}
	}

}
