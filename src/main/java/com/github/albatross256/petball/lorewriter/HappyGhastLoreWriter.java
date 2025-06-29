package com.github.albatross256.petball.lorewriter;

import java.util.List;
import org.bukkit.entity.Entity;

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

  @Override
  public List<String> generateLore(Entity entity) {
    return generateCommonLore(entity);
  }

}
