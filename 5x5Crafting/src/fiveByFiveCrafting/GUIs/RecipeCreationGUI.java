package fiveByFiveCrafting.GUIs;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class RecipeCreationGUI implements InventoryHolder {
	private Inventory inv;

	public RecipeCreationGUI() {
		inv = Bukkit.createInventory(this, 54, "New Recipe");
		
	}

	public void display(Player player) {

		// Fill in background tiles
		ItemStack backgroundTile = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
		ItemMeta backgroundTileMeta = backgroundTile.getItemMeta();
		backgroundTileMeta.setDisplayName(" ");
		backgroundTile.setItemMeta(backgroundTileMeta);

		for (int i = 5; i < 54; i++) {
			inv.setItem(i, backgroundTile);
		}

		// Set output tile
		ItemStack outputTile = new ItemStack(Material.AIR, 1);
		inv.setItem(26, outputTile);
		
		// Set ShapedRecipeToggle tile
		ItemStack shapedRecipeToggle = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
		ItemMeta shapedRecipeToggleMeta = shapedRecipeToggle.getItemMeta();
		shapedRecipeToggleMeta.setDisplayName("Shaped Recipe");
		shapedRecipeToggle.setItemMeta(shapedRecipeToggleMeta);

		inv.setItem(16, shapedRecipeToggle);
		// Set rightArrowSkull
		ItemStack rightArrowSkull = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		SkullMeta rightArrowSkullMeta = (SkullMeta) rightArrowSkull.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), "");
		profile.getProperties().put("textures", new Property("textures",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJiMGMwN2ZhMGU4OTIzN2Q2NzllMTMxMTZiNWFhNzVhZWJiMzRlOWM5NjhjNmJhZGIyNTFlMTI3YmRkNWIxIn19fQ=="));
		Field profileField = null;
		try {
			profileField = rightArrowSkullMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(rightArrowSkullMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		rightArrowSkullMeta.setDisplayName(" ");
		rightArrowSkull.setItemMeta(rightArrowSkullMeta);
		inv.setItem(24, rightArrowSkull);
		
		//Create "create button"
		ItemStack createButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
		ItemMeta createButtonMeta = backgroundTile.getItemMeta();
		createButtonMeta.setDisplayName("Create Recipe");
		createButton.setItemMeta(createButtonMeta);
		inv.setItem(42, createButton);

		// Fill in crafting tiles
		ItemStack craftingTiles = new ItemStack(Material.AIR, 1);

		for (int i = 0; i < 41; i++) {
			inv.setItem(i, craftingTiles);

			if (i % 9 == 4)
				i += 4;
		}

		player.openInventory(inv);
	}

	public Inventory getInventory() {
		return inv;
	}
}