package com.github.albatross256.petball.lorewriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.SulfurCube;
import org.bukkit.entity.Tameable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class SulfurCubeLoreWriter extends LoreWriter {

  /**
   * Loreに表示するMobの日本語名
   */
  private static String loreMobName = "サルファーキューブ";

  /**
   * コンストラクタ
   */
  public SulfurCubeLoreWriter() {
    super(loreMobName);
  }

  /**
   * Lore情報の作成
   *
   * @param entity Loreを作成するエンティティ情報
   */
  @Override
  public List<String> generateLore(Entity entity) {
    List<String> lore = new ArrayList<>();
    lore.add(loreMobName);
    if (entity instanceof Damageable d && entity instanceof Attributable a) {
      lore.add(getHealthMeter(d.getHealth(), Objects.requireNonNull(
          a.getAttribute(Attribute.MAX_HEALTH), "MAX HEALTH is null").getValue()));
    }

    if (entity instanceof Ageable a) {
      String age = a.isAdult() ? "大" : "小";
      lore.add(age);
    }

    SulfurCube cube = (SulfurCube) entity;
    // TODO Paperのメソッド解放後、サルファーキューブのアイテム表示を実装
    ItemStack item = cube.getEquipment().getItem(EquipmentSlot.BODY);
    if(item instanceof ItemStack && !item.getType().equals(Material.AIR)) {
      lore.add("中身：" + item.getType().name());
    }

    return lore;
  }
}
