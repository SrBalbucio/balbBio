package me.balbucio.bio;

import java.io.IOException;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BioAPI {
	
	public static String getBio(String player) {
			Configuration config = null;
			try {
				config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(Main.getInstance().bio_file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return config.getString(player);
	}

}
