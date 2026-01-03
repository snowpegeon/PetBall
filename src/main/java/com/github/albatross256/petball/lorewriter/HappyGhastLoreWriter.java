package com.github.albatross256.petball.lorewriter;

import java.util.List;
import org.bukkit.entity.Entity;

/**
 * <p>
 * {@link org.bukkit.entity.HappyGhast} が入ってるPetBall の ItemStack {@link org.bukkit.inventory.ItemStack} の<br>
 * Lore へ書き込む文字列を構築する機能の実装クラス.
 * </p>
 */
public class HappyGhastLoreWriter extends LoreWriter {

  /**
   * <p>
   * Loreに表示するMOBの日本語名.<br>
   * {@link String}
   * </p>
   */
  private static final String loreMobName = "ハッピーガスト";

  /**
   * <p>
   * コンストラクタ.<br>
   * 必ずsuperクラスに{@value loreMobName}を渡す.<br>
   * それ以外の固有の初期化処理をここで実施.
   * </p>
   *
   */
  public HappyGhastLoreWriter() {
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
    return generateCommonLore(entity);
  }
}
