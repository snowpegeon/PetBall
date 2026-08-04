package com.github.albatross256.petball.balldata;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * <p>
 * PetBall の {@link BallData} .<br>
 * {@link org.bukkit.entity.SulfurCube} を捕獲するボールを構築する実装クラス.
 * </p>
 * */
public class SulferCubeBallData extends BallData {

  /**
   * <p>
   * このボールで召喚可能なエンティティタイプ.
   * </p>
   *
   * @return {@link EntityType}
   * */
  @Override
  public EntityType getEntityType() {
    return EntityType.SULFUR_CUBE;
  }

  /**
   * <p>
   * ボールデータに利用されるスポーンエッグ表示.
   * </p>
   *
   * @return {@link Material}
   * */
  @Override
  public Material getFilledBallMaterial() {
    return Material.SULFUR_CUBE_SPAWN_EGG;
  }

  /**
   * </p>
   * このボールに格納可能なエンティティタイプ.
   * </p>
   *
   * @return {@link EntityType}
   * */
  @Override
  public EntityType getFilledBallEntityType() {
    return EntityType.SULFUR_CUBE;
  }
}
