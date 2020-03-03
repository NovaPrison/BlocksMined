package com.novaprison.blocksmined;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface BMAPI {
    int getMined(Player p);

    int getMined(UUID uuid);

    void setMined(Player p, int amount);

    void setMined(UUID uuid, int amount);

    void mine(Player p, int count);

    void mine(UUID uuid, int count);
}
