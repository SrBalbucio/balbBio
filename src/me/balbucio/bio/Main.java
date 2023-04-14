package me.balbucio.bio;

import java.io.File;
import java.io.IOException;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin{
	private static Main instance;
	public String msg_1;
	public String msg_2;
	public String msg_3;
	public String msg_4;
	public String msg_5;
	public String msg_6;
	public String msg_7;
	public String msg_8;
	public String msg_9;
	public String msg_10;
	public String msg_11;
	public File bio_file = new File(getDataFolder().getPath(), "bio.yml");
	
	
	public void onEnable() {
		setInstance(this);
		File file = new File(getDataFolder().getPath(), "messages.yml");
		try {
			if(!getDataFolder().exists() || !file.exists()) {
				getDataFolder().mkdir();
				file.createNewFile();
				file.mkdir();
				Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
				config.set("atual", "§aSua bio atual é:");
				config.set("null", "§cVocê ainda não tem uma bio!");
				config.set("ver", "§aVocê está vendo a bio do");
				config.set("nullother", "§cEsse membro não tem uma bio!");
				config.set("apagar", "§aA bio foi deletada com sucesso!");
				config.set("setado", "§aA sua bio foi atualizada com sucesso!");
				config.set("follows", "§aEnviando a atualização para seus seguidores!");
				ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
			} 
			Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
			this.msg_1 = (String) config.get("atual");
			this.msg_2 = (String) config.get("null");
			this.msg_3 = (String) config.get("ver");
			this.msg_4 = (String) config.get("nullother");
			this.msg_5 = (String) config.get("apagar");
			this.msg_6 = (String) config.get("setado");
			this.msg_7 = (String) config.get("follows");
		} catch(IOException e) {
			BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§c[BalbucioBio] Ocorreu um erro grave ao criar os arquivos!"));
			e.printStackTrace();
		}
		File file2 = new File(getDataFolder().getPath(), "bio.yml");
		try {
			if(!getDataFolder().exists() || !file2.exists()) {
				getDataFolder().mkdir();
				file2.createNewFile();
				file2.mkdir();
			} 
			
		} catch(IOException e) {
			BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§c[BalbucioBio] Ocorreu um erro grave ao criar os arquivos!"));
			e.printStackTrace();
		}
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Bio());
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("[BalbucioBio] §2Ativado com sucesso!"));
	}
	   public static Main getInstance() {
	        return instance;
	    }

	    private static void setInstance(final Main instance) {
	        Main.instance = instance;
	    }

}
