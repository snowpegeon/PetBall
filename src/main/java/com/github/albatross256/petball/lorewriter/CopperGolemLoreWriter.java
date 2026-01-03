package com.github.albatross256.petball.lorewriter;

import io.papermc.paper.world.WeatheringCopperState;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.entity.CopperGolem;
import org.bukkit.entity.CopperGolem.Oxidizing;
import org.bukkit.entity.Entity;

/**
 * <p>
 * {@link org.bukkit.entity.CopperGolem} が入ってるPetBall の ItemStack {@link org.bukkit.inventory.ItemStack} の<br>
 * Lore へ書き込む文字列を構築する機能の実装クラス.
 * </p>
 */
public class CopperGolemLoreWriter extends LoreWriter {

  /**
   * <p>
   * Loreに表示するMOBの日本語名.<br>
   * {@link String}
   * </p>
   */
  private static final String loreMobName = "カッパーゴーレム";

  private static final Map<WeatheringCopperState, String> loreOxdizingName = new HashMap<>() {{
    put(WeatheringCopperState.UNAFFECTED, "未酸化");
    put(WeatheringCopperState.EXPOSED, "風化");
    put(WeatheringCopperState.WEATHERED, "錆びた");
    put(WeatheringCopperState.OXIDIZED, "酸化");
  }};

  /**
   * <p>
   * コンストラクタ.<br>
   * 必ずsuperクラスに{@value loreMobName}を渡す.<br>
   * それ以外の固有の初期化処理をここで実施.
   * </p>
   *
   */
  public CopperGolemLoreWriter() {
    super(loreMobName);
  }

  /**
   * <p>
   * Lore情報の作成.<br>
   * 親クラスの{@link com.github.albatross256.petball.lorewriter.LoreWriter#generateCommonLore}で<br>
   * 共通の情報を先に生成後固有のLore情報追加を実施する.
   * </p>
   *
   * @param entity         {@link Entity} 生成対象のエンティティ情報.
   * @return {@link List} 作成したエンティティに関するLore情報.
   */
  @Override
  public List<String> generateLore(Entity entity) {
    CopperGolem golem = (CopperGolem)entity;
    List<String> lore = generateCommonLore(entity);
    // さび止め・酸化状態の取得
    String waxed = golem.getOxidizing() instanceof Oxidizing.Waxed ? "あり" : "なし";
    lore.add("さび止め:" + waxed + " " + loreOxdizingName.get(golem.getWeatheringState()));
    return lore;
  }
}
