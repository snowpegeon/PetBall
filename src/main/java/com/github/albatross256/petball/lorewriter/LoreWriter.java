package com.github.albatross256.petball.lorewriter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Tameable;

/**
 * <p>
 * {@link org.bukkit.entity.Entity} が入ってるPetBall の
 * ItemStack {@link org.bukkit.inventory.ItemStack} のLore へ書き込む文字列を構築する<br>
 * 機能の抽象クラス.
 * </p>
 */
public abstract class LoreWriter {

  /**
   * <p>
   * Loreに表示するMOBのHP表示のフォーマット.<br>
   * {@link DecimalFormat}
   * </p>
   */
  private static final DecimalFormat hpFormat = new DecimalFormat("0.0#");

  /**
   * <p>
   * Loreに表示するMOBが大人の場合の表記.<br>
   * {@link String}
   * </p>
   */
  private static final String adultDisplayStr = "大人";

  /**
   * <p>
   * Loreに表示するMOBが子供の場合の表記.<br>
   * {@link String}
   * </p>
   */
  private static final String childDisplayStr = "子供";

  /**
   * <p>
   * Loreに表示するMOBのオーナー表記が無いの場合の表記.<br>
   * {@link String}
   * </p>
   */
  private static final String noOwnerDisplayStr = "なし";

  /**
   * <p>
   * Loreに表示するMOBのオーナー表記の前につける飼い主表記.<br>
   * {@link String}
   * </p>
   */
  private static final String loreOwnerDisplayStr = "飼い主: ";

  /**
   * <p>
   * Loreに表示するMOBの名前表記.<br>
   * {@link String}
   * </p>
   */
  private final String mobName;

  /**
   * <p>
   * コンストラクタ.
   * </p>
   ** */
  public LoreWriter(String mobName) {
    this.mobName = mobName;
  }

  /**
   * <p>
   * Lore表示を作る実装クラス.<br>
   * MOB固有の表示は、必ず{@link #generateCommonLore}を呼んだ後にここで実装する.
   * </p>
   *
   * @param entity         {@link Entity} 生成対象のエンティティ情報.
   * @return {@link List} Loreを作成するエンティティ情報.
   */
  public abstract List<String> generateLore(Entity entity);

  /**
   * <p>
   * MOBに共通するLore情報の作成.<br>
   * </p>
   *
   * @param entity         {@link Entity} 生成対象のエンティティ情報.
   * @return {@link List} 作成したエンティティに関するLore情報.
   */
  public List<String> generateCommonLore(Entity entity) {
    List<String> lore = new ArrayList<>();
    lore.add(this.mobName);
    if (entity instanceof Damageable d && entity instanceof Attributable a) {
      lore.add(getHealthMeter(d.getHealth(), Objects.requireNonNull(
          a.getAttribute(Attribute.MAX_HEALTH), "MAX HEALTH is null").getValue()));
    }
    if (entity instanceof Ageable a) {
      String age = a.isAdult() ? adultDisplayStr : childDisplayStr;
      lore.add(age);
    }

    if (entity instanceof Tameable tameable) {
      String owner =
          Objects.isNull(tameable.getOwner()) ? noOwnerDisplayStr : tameable.getOwner().getName();
      lore.add(loreOwnerDisplayStr + owner);
    }
    return lore;
  }

  /**
   * <p>
   * MOBに共通するLore情報の作成.<br>
   * </p>
   *
   * @param currentHealth         {@link double} 現在のHP.
   * @param maxHealth         {@link double} MOBの最大HP.
   * @return {@link String} HPヘルスメータ表示.
   */
  protected String getHealthMeter(double currentHealth, double maxHealth) {
    int rate = (int) (currentHealth * 20 / maxHealth);

    return "体力 : [" + ChatColor.GREEN
        + ":".repeat(rate) + ChatColor.GRAY + ":".repeat(20 - rate)
        + ChatColor.DARK_PURPLE + "]  (" + hpFormat.format(currentHealth) + " / "
        + hpFormat.format(maxHealth) + ")";
  }
}
