package com.github.albatross256.petball.lorewriter.factory;

import com.github.albatross256.petball.lorewriter.*;
import org.bukkit.entity.EntityType;

public class LoreWriterFactory {

  public LoreWriter newLoreWriter(EntityType entityType) {
    return switch (entityType) {
      case VILLAGER -> new VillagerLoreWriter();
      case PARROT -> new ParrotLoreWriter();
      case CAT -> new CatLoreWriter();
      case OCELOT -> new OcelotLoreWriter();
      case WOLF -> new WolfLoreWriter();
      case COW -> new CowLoreWriter();
      case MOOSHROOM -> new MushroomLoreWriter();
      case PIG -> new PigLoreWriter();
      case SHEEP -> new SheepLoreWriter();
      case CHICKEN -> new ChickenLoreWriter();
      case RABBIT -> new RabbitLoreWriter();
      case POLAR_BEAR -> new PolarBearLoreWriter();
      case TURTLE -> new TurtleLoreWriter();
      case DOLPHIN -> new DolphinLoreWriter();
      case SQUID -> new SquidLoreWriter();
      case IRON_GOLEM -> new IronGolemLoreWriter();
      case SNOW_GOLEM -> new SnowmanLoreWriter();
      case BAT -> new BatLoreWriter();
      case WANDERING_TRADER -> new WanderingTraderLoreWriter();
      case PANDA -> new PandaLoreWriter();
      case FOX -> new FoxLoreWriter();
      case LLAMA -> new LlamaLoreWriter();
      case MULE -> new MuleLoreWriter();
      case DONKEY -> new DonkeyLoreWriter();
      case COD -> new CodLoreWriter();
      case SALMON -> new SalmonLoreWriter();
      case TROPICAL_FISH -> new TropicalFishLoreWriter();
      case PUFFERFISH -> new PufferFishLoreWriter();
      case BEE -> new BeeLoreWriter();
      case HORSE -> new HorseLoreWriter();
      case ZOMBIE_HORSE -> new ZombieHorseLoreWriter();
      case SKELETON_HORSE -> new SkeletonHorseLoreWriter();
      case TRADER_LLAMA -> new TraderLlamaLoreWriter();
      case PIGLIN -> new PiglinLoreWriter();
      case STRIDER -> new StriderLoreWriter();
      case AXOLOTL -> new AxolotlLoreWriter();
      case GOAT -> new GoatLoreWriter();
      case GLOW_SQUID -> new GlowSquidLoreWriter();
      case ALLAY -> new AllayLoreWriter();
      case FROG -> new FrogLoreWriter();
      case TADPOLE -> new TadpoleLoreWriter();
      case CAMEL -> new CamelLoreWriter();
      case SNIFFER -> new SnifferLoreWriter();
      case ARMADILLO -> new ArmadilloLoreWriter();
      case COPPER_GOLEM -> new CopperGolemLoreWriter();
      case HAPPY_GHAST -> new HappyGhastLoreWriter();
      case NAUTILUS -> new NautilusLoreWriter();
      default -> null;
    };
  }
}
