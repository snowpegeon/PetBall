package com.github.albatross256.petball.balldata;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class CamelBallData extends BallData {

  @Override
  public EntityType getEntityType() {
    return EntityType.CAMEL;
  }

  @Override
  public Material getFilledBallMaterial() {
    return Material.CAMEL_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledBallEntityType() {
    return EntityType.CAMEL;
  }
}