package Main;

import entity.Player;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class EldenSkills extends Skill {
    private int skillNumber;
    private Timer stealthTimer;
    GamePanel gp;

    public EldenSkills(int skillNumber, GamePanel gp) {
        super(getSkillName(skillNumber), getSkillDescription(skillNumber));
        this.skillNumber = skillNumber;
        this.gp = gp;
    }

    private static String getSkillName(int skillNumber) {
        switch (skillNumber) {
            case 1: return "Healing Bloom";
            case 2: return "Silent Steps";
            case 3: return "Teleportation";
            default: return "Unknown Skill";
        }
    }

    private static String getSkillDescription(int skillNumber) {
        switch (skillNumber) {
            case 1: return "Summons a healing flower that restores her health.";
            case 2: return "Moves quietly to avoid certain dangers.";
            case 3: return "Teleports to where the special books is.";
            default: return "No description available.";
        }
    }

    @Override
    public void use(Player player) {
        int manaCost = getManaCost(skillNumber);

        if (player.mana < manaCost) {
            gp.ui.addMessage("Not enough mana to use this skill!");
            return;
        }

        player.mana -= manaCost;

        switch (skillNumber) {
            case 1:
                player.life += 2;
                player.life = Math.min(player.life, player.getMaxLife());
                gp.ui.addMessage("Healing Bloom used! HP restored by 2. (Mana Cost: " + manaCost + ")");
                break;
            case 2:
                activateStealth(player, 10);
                gp.ui.addMessage("Silent Steps used! Player is now stealthy. (Mana Cost: " + manaCost + ")");
                break;
            case 3:
                gp.ui.addMessage("Teleports to where the special key is. (Mana Cost: " + manaCost + ")");
                if(gp.currentMap == 0){
                    gp.eHandler.teleport(0, 17, 23);
                } else if(gp.currentMap == 1){
                gp.eHandler.teleport(1, 44, 84);
                } else if(gp.currentMap == 2){
                gp.eHandler.teleport(2, 42, 38);
            }
                break;
            default:
                System.out.println("Unknown skill used.");
                break;
        }

        player.life = Math.max(player.life, 0);
        player.mana = Math.max(player.mana, 0);
    }

    private int getManaCost(int skillNumber) {
        switch (skillNumber) {
            case 1: return 10; // Healing Bloom
            case 2: return 10; // Silent Steps
            case 3: return 15; //  Teleportation
            default: return 0; // Unknown skill
        }
    }

    private void activateStealth(Player player, int duration) {
        player.isStealth = true;
        player.alpha = 0.5f;
        player.invincible = true;  // Prevents damage

        if (stealthTimer != null) {
            stealthTimer.cancel();
            stealthTimer = null;
        }

        stealthTimer = new Timer();
        stealthTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                EventQueue.invokeLater(() -> {
                    player.isStealth = false;
                    player.invincible = false;
                    player.alpha = 1.0f;
                    gp.ui.addMessage("Stealth ended");
                });
                stealthTimer = null;
            }
        }, duration * 1000L);
        gp.ui.addMessage("Stealth active for " + duration + "s");
    }
}
