package com.github.albatross256.petball.eventlistner;

import static com.github.albatross256.petball.balldata.BallData.ENTITYBALL_CONTENT_EMPTY;
import static com.github.albatross256.petball.balldata.BallData.ENTITYBALL_CONTENT_KEY;
import static com.github.albatross256.petball.balldata.BallData.ENTITYBALL_ISC_KEY;
import static com.github.albatross256.petball.balldata.BallData.ENTITYBALL_NBT_KEY;
import static com.github.albatross256.petball.balldata.BallData.ENTITYBALL_TIMESTAMP_KEY;
import static java.util.Map.entry;
import static org.bukkit.Material.AIR;
import static org.bukkit.Material.ANVIL;
import static org.bukkit.Material.BEACON;
import static org.bukkit.Material.BLACK_SHULKER_BOX;
import static org.bukkit.Material.BLUE_SHULKER_BOX;
import static org.bukkit.Material.BREWING_STAND;
import static org.bukkit.Material.BROWN_SHULKER_BOX;
import static org.bukkit.Material.CAKE;
import static org.bukkit.Material.CANDLE_CAKE;
import static org.bukkit.Material.CARTOGRAPHY_TABLE;
import static org.bukkit.Material.CHEST_MINECART;
import static org.bukkit.Material.CHIPPED_ANVIL;
import static org.bukkit.Material.COMMAND_BLOCK;
import static org.bukkit.Material.CRAFTING_TABLE;
import static org.bukkit.Material.CYAN_SHULKER_BOX;
import static org.bukkit.Material.DAMAGED_ANVIL;
import static org.bukkit.Material.DAYLIGHT_DETECTOR;
import static org.bukkit.Material.DROPPER;
import static org.bukkit.Material.FURNACE_MINECART;
import static org.bukkit.Material.GRAY_SHULKER_BOX;
import static org.bukkit.Material.GREEN_SHULKER_BOX;
import static org.bukkit.Material.HOPPER_MINECART;
import static org.bukkit.Material.IRON_DOOR;
import static org.bukkit.Material.IRON_TRAPDOOR;
import static org.bukkit.Material.LIGHT_BLUE_SHULKER_BOX;
import static org.bukkit.Material.LIGHT_GRAY_SHULKER_BOX;
import static org.bukkit.Material.LIME_SHULKER_BOX;
import static org.bukkit.Material.LOOM;
import static org.bukkit.Material.MAGENTA_SHULKER_BOX;
import static org.bukkit.Material.ORANGE_SHULKER_BOX;
import static org.bukkit.Material.PINK_SHULKER_BOX;
import static org.bukkit.Material.PURPLE_SHULKER_BOX;
import static org.bukkit.Material.RED_SHULKER_BOX;
import static org.bukkit.Material.RESPAWN_ANCHOR;
import static org.bukkit.Material.SADDLE;
import static org.bukkit.Material.SHULKER_BOX;
import static org.bukkit.Material.SMITHING_TABLE;
import static org.bukkit.Material.STONECUTTER;
import static org.bukkit.Material.WHITE_SHULKER_BOX;
import static org.bukkit.Material.YELLOW_SHULKER_BOX;

import com.github.albatross256.petball.BallManager;
import com.github.albatross256.petball.Main;
import com.github.albatross256.petball.WorldManager;
import com.github.albatross256.petball.balldata.BallData;
import com.github.albatross256.petball.lorewriter.factory.LoreWriterFactory;
import com.github.teruteru128.logger.Logger;
import com.saicone.rtag.RtagEntity;
import com.saicone.rtag.RtagItem;
import com.saicone.rtag.stream.TStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.block.CommandBlock;
import org.bukkit.block.Container;
import org.bukkit.block.DaylightDetector;
import org.bukkit.block.Dispenser;
import org.bukkit.block.EnchantingTable;
import org.bukkit.block.EnderChest;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.block.Jukebox;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Gate;
import org.bukkit.block.data.type.Grindstone;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.block.data.type.Switch;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Mule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;


/**
 * <p>
 * PetBallのイベントを実行するイベントリスナー.<br> {@link org.bukkit.event.Listener} を継承する.
 * </p>
 */
public class EventListener implements Listener {

  /**
   * <p>
   * ボールを持っている状態で右クリックしたときに右クリック先のイベントを優先させるアイテム一覧.<br> {@link org.bukkit.Material}
   * </p>
   */
  private static final Set<Material> USABLE_MATERIALS = Collections.unmodifiableSet(EnumSet.of(
      // 作業台
      CRAFTING_TABLE
      // 金床系
      , ANVIL, CHIPPED_ANVIL, DAMAGED_ANVIL
      // ビーコン
      , BEACON
      // 醸造台
      , BREWING_STAND
      // トロッコ類
      , FURNACE_MINECART, HOPPER_MINECART, CHEST_MINECART
      // ケーキ類
      , CAKE, CANDLE_CAKE
      // コマンドブロック
      , COMMAND_BLOCK
      // 日照センサー
      , DAYLIGHT_DETECTOR
      // リスポーンアンカー
      , RESPAWN_ANCHOR
      // 石切台
      , STONECUTTER
      // 製図台
      , CARTOGRAPHY_TABLE
      // 鍛冶台
      , SMITHING_TABLE
      // 機織り機
      , LOOM
      // シュルカーボックス類
      , SHULKER_BOX, RED_SHULKER_BOX, ORANGE_SHULKER_BOX, YELLOW_SHULKER_BOX, LIME_SHULKER_BOX,
      GREEN_SHULKER_BOX, CYAN_SHULKER_BOX, BLUE_SHULKER_BOX, PURPLE_SHULKER_BOX,
      MAGENTA_SHULKER_BOX, LIGHT_BLUE_SHULKER_BOX, PINK_SHULKER_BOX, BROWN_SHULKER_BOX,
      WHITE_SHULKER_BOX, GRAY_SHULKER_BOX, LIGHT_GRAY_SHULKER_BOX, BLACK_SHULKER_BOX));

  /**
   * <p>
   * ボールを持っている状態で右クリックしたときに右クリックしても動作することのないアイテム一覧.<br>
   * 鉄シリーズだけ、右クリックしても通常の扉の動作をすることがないため、除外する目的で書いている.<br> {@link org.bukkit.Material}
   * </p>
   */
  private static final Set<Material> EXCLUSION_MATERIALS = Collections.unmodifiableSet(
      EnumSet.of(IRON_DOOR, IRON_TRAPDOOR));

  /**
   * <p>
   * 1.20.4以前の旧式のEntityType名と新EntityType名のMap.<br> 20.5で該当EntityType名が完全に互換性がなくなってしまったため、ここで補っている.
   * </p>
   */
  private static final Map<String, String> OLD_ENTITY_TYPES = Map.ofEntries(
      entry("MUSHROOM_COW", EntityType.MOOSHROOM.toString()),
      entry("SNOWMAN", EntityType.SNOW_GOLEM.toString()));
  /**
   *
   */
  private static final Class<?>[] STATE_CLASSES = new Class[]{
      Container.class,
      EnderChest.class,
      EnchantingTable.class,
      EnchantingTable.class,
      CommandBlock.class,
      DaylightDetector.class,
      Jukebox.class,
      ChiseledBookshelf.class
  };
  /**
   *
   */
  private static final Class<?>[] BLOCK_DATA_CLASSES = new Class[]{
      Door.class,
      TrapDoor.class,
      Bed.class,
      Gate.class,
      Cake.class,
      Switch.class,
      Repeater.class,
      Dispenser.class,
      Comparator.class,
      Hopper.class,
      NoteBlock.class,
      Chest.class,
      Grindstone.class,
      Furnace.class,
      Barrel.class
  };
  /**
   * <p>
   * PetBallに関するデータを管理するManager. <br> {@link com.github.albatross256.petball.BallManager}
   * </p>
   */
  private final BallManager ballManager;
  /**
   * <p>
   * 読み込まれたサーバーのワールドに関するデータを管理するManager. <br> {@link com.github.albatross256.petball.WorldManager}
   * </p>
   */
  private final WorldManager worldManager;
  /**
   * <p>
   * クラス内のログを出力するロガー.<br> {@link com.github.teruteru128.logger.Logger}
   * </p>
   */
  private final Logger logger;
  /**
   * <p>
   * プラグインの本体.<br> {@link com.github.albatross256.petball.Main}
   * </p>
   */
  private final Plugin plugin;

  /**
   * <p>
   * コンストラクタ.
   * </p>
   *
   * @param ballManager  {@link BallManager} PetBallのマネージャー.
   * @param worldManager {@link WorldManager} ワールドのマネージャー.
   * @param main         {@link Main} プラグイン本体.
   */
  public EventListener(BallManager ballManager, WorldManager worldManager, Main main) {
    plugin = main;
    logger = Logger.getInstance(plugin);
    this.ballManager = ballManager;
    this.worldManager = worldManager;
  }

  /**
   * <p>
   * ブロック排出イベント処理.
   * </p>
   *
   * @param event {@link BlockDispenseEvent} 受け渡されるイベントの詳細情報.
   */
  @EventHandler
  public void onBlockDispense(BlockDispenseEvent event) {
    logger.debug("onBlockDispense:Start");
    logger.trace("event.isCancelled():" + event.isCancelled());
    logger.trace("event.getBlock():" + event.getBlock());
    logger.trace("event.getBlock().getType():" + event.getBlock().getType());
    logger.trace(
        "event.isCancelled() || event.getBlock().getType() .equals(DROPPER):" + (event.isCancelled()
            || event.getBlock().getType().equals(DROPPER)));
    if (event.isCancelled() || event.getBlock().getType().equals(DROPPER)) {
      logger.debug("イベントキャンセルか、ドロッパーが実施");
      return;
    }
    if (isEntityBall(event.getItem())) {
      logger.debug("isEntityBall=true");
      event.setCancelled(true);
    }
    logger.debug("onBlockDispense:End");
  }

  /**
   * <p>
   * 捕獲可能なエンティティか.
   * </p>
   *
   * @param type {@link EntityType} エンティティのタイプ.
   * @return {@link boolean} チェック結果.
   */
  private boolean isUncapturable(EntityType type) {
    logger.debug("isUncapturableCall");
    logger.trace("type:" + type);
    return !this.ballManager.containsBall(type);
  }

  /**
   * <p>
   * プレイヤーが右クリックした時のイベント.PetBallの取り出し処理もここで実施.
   * </p>
   *
   * @param event {@link PlayerInteractEvent} 受け渡されるイベントの詳細情報.
   */
  @EventHandler
  public void onTap(PlayerInteractEvent event) {
    logger.debug("onTap:Start");
    logger.trace("event:" + event);
    logger.trace("event.getAction():" + event.getAction());
    logger.trace("event.getAction().equals(Action.RIGHT_CLICK_AIR):" + event.getAction()
        .equals(Action.RIGHT_CLICK_AIR));

    if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
      logger.debug("right click AIR");
      logger.trace("this.isEntityBall(event.getItem()):" + this.isEntityBall(event.getItem()));
      if (this.isEntityBall(event.getItem())) {
        event.setCancelled(true);
        logger.trace("isEntityBall:True");
      } else {
        logger.trace("isEntityBall:False");
      }
      return;
    } else if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
      logger.debug("is not action RIGHT_CLICK_BLOCK");
      return;
    }

    Location location = Objects.requireNonNull(event.getClickedBlock()).getLocation();
    logger.trace("event.getClickedBlock():" + event.getClickedBlock());
    logger.trace("location:" + location);

    Location newLocation = new Location(
        location.getWorld(),
        location.getX() + event.getBlockFace().getModX() + 0.5,
        location.getY() + event.getBlockFace().getModY(),
        location.getZ() + event.getBlockFace().getModZ() + 0.5
    );
    logger.trace("location.getWorld():" + location.getWorld());
    logger.trace("location.getX():" + location.getX());
    logger.trace("event.getBlockFace().getModX() + 0.5:" + event.getBlockFace().getModX() + 0.5);
    logger.trace("location.getY():" + location.getY());
    logger.trace("event.getBlockFace().getModY():" + event.getBlockFace().getModY());
    logger.trace("location.getZ():" + location.getZ());
    logger.trace("event.getBlockFace().getModZ() + 0.5:" + event.getBlockFace().getModZ() + 0.5);
    logger.trace("newLocation:" + newLocation);

    logger.trace("!event.getPlayer().isSneaking():" + !event.getPlayer().isSneaking());
    logger.trace("isTouchable(event.getClickedBlock()):" + isTouchable(event.getClickedBlock()));
    if (!event.getPlayer().isSneaking() && isTouchable(event.getClickedBlock())) {
      logger.debug("is not Sneaking or is Touchable");
      return;
    }

    ItemStack mainItem = event.getPlayer().getInventory().getItemInMainHand();
    ItemStack offItem = event.getPlayer().getInventory().getItemInOffHand();
    logger.trace("mainItem:" + mainItem);
    logger.trace("offItem:" + offItem);

    ItemStack entityBall = null;
    boolean isEntityBallMain = isEntityBall(mainItem);
    boolean isEntityBallOff = isEntityBall(offItem);
    logger.trace("isEntityBallMain:" + isEntityBallMain);
    logger.trace("isEntityBallOff:" + isEntityBallOff);
    if (isEntityBallMain) {
      logger.debug("mainItem is EntityBall");
      event.setCancelled(true);

      logger.trace("isEntityEmptyBall(mainItem):" + isEntityEmptyBall(mainItem));
      if (isEntityEmptyBall(mainItem)) {
        logger.debug("mainItem is EntityEmptyBall");
        return;
      } else {
        logger.debug("entityBall is MainItem");
        entityBall = mainItem;
      }
    } else if (isEntityBallOff) {
      logger.trace("OffItem is EntityBall");
      event.setCancelled(true);

      logger.trace("isEntityEmptyBall(offItem):" + isEntityEmptyBall(offItem));
      logger.trace("mainItem.getType().equals(AIR):" + mainItem.getType().equals(AIR));
      if (isEntityEmptyBall(offItem)) {
        logger.debug("offItem is EntityEmptyBall");
        return;
      } else if (mainItem.getType().equals(AIR)) {
        logger.debug("mainItem.getType().equals(AIR) True");
        entityBall = offItem;
      }
    }

    logger.trace("entityBall:" + entityBall);
    if (entityBall == null) {
      logger.debug("entity ball is null");
      return;
    }

    logger.trace("this.worldManager.isUnusableWorld(event.getPlayer().getWorld().getName()):"
        + this.worldManager.isUnusableWorld(event.getPlayer().getWorld().getName()));
    if (this.worldManager.isUnusableWorld(event.getPlayer().getWorld().getName())) {
      logger.debug("is not Usable World");
      return;
    }

    RtagItem tag = new RtagItem(entityBall);
    var allBallData = this.ballManager.getAllBallDatas();
    String entityBallContentKey = tag.get(ENTITYBALL_CONTENT_KEY);
    logger.trace("this.ballManager:" + this.ballManager);
    logger.trace("allBallData:" + allBallData);
    logger.trace("entityBallContentKey:" + entityBallContentKey);

    // 1.20.5で内部エンティティデータキーが変更になったので、それを新しいキーに変換する
    logger.trace(
        "OLD_ENTITY_TYPES.containsKey(entityBallContentKey): " + OLD_ENTITY_TYPES.containsKey(
            entityBallContentKey));
    if (OLD_ENTITY_TYPES.containsKey(entityBallContentKey)) {
      entityBallContentKey = OLD_ENTITY_TYPES.get(entityBallContentKey);
    }

    Entity entity = null;
    for (EntityType key : allBallData.keySet()) {
      BallData eachBallData = allBallData.get(key);
      logger.trace("key:" + key);
      logger.trace("eachBallData:" + eachBallData);
      logger.trace("eachBallData.getEntityType().toString().equals(entityBallContentKey):"
          + eachBallData.getEntityType().toString().equals(entityBallContentKey));
      if (eachBallData.getEntityType().toString().equals(entityBallContentKey)) {
        logger.debug("eachBallData entityBallContentKey is Found. key:" + key);
        event.setCancelled(true);

        //同時出し入れ対策2
        long currentTime = System.currentTimeMillis();
        var entityBallTimestamp = tag.getOptional(ENTITYBALL_TIMESTAMP_KEY).asLong();
        var abs = Math.abs(currentTime - entityBallTimestamp);
        logger.trace(
            "currentTime = " + currentTime + ", entityBallTimestamp = " + entityBallTimestamp
                + ", diff = " + abs);
        // 差が50ミリ秒未満だった場合拒否する
        if (abs < 50) {
          logger.debug("abs < 50");
          return;
        }

        entity = Objects.requireNonNull(location.getWorld())
            .spawnEntity(newLocation, eachBallData.getEntityType(), false);
        logger.trace("entity:" + entity);
        break;
      }
    }

    if (entity == null) {
      logger.debug("entity is null");
      return;
    }


    /* 以下 NBTの解析及び埋め込み */
    this.logger.trace("[TRACE] EventListener.EventListener.onTap Parse NBT START");
    RtagEntity entityTag = new RtagEntity(entity);
    this.logger.trace("[TRACE] RtagEntity entityTag <- new RtagEntity(entity)");

    RtagItem itemTag = new RtagItem(mainItem);

    this.logger.trace("[TRACE]entityTag:" + entityTag.get());
    // 1.20.5以降のタグ保存用のデータ
    ItemStack equippedHorseArmor = null;

    // 1.21以降のタグ保存用のデータ
    Float maxHealth = null;
    Float movementSpeed = null;
    Float jumpStrength = null;

    if (tag.hasTag(ENTITYBALL_ISC_KEY)) {
      Map<?, ?> tags = tag.get(ENTITYBALL_ISC_KEY);
      tags.forEach((k, v) -> entityTag.set(v, k));
    } else {
      // 1.20.4以前のNBTデータから馬鎧を取り出す
      var nbtTag = itemTag.get().get(ENTITYBALL_NBT_KEY);
      boolean isInstanceOfBytes = nbtTag instanceof byte[];

      if (isInstanceOfBytes) {
        byte[] tagCompoundBytes = (byte[]) nbtTag;
        var tagCompound = TStream.COMPOUND.fromBytes(tagCompoundBytes);
        Map<String, Object> tags = TStream.COMPOUND.toMap(tagCompound);
        if (!tags.keySet().isEmpty()) {
          for (String k : tags.keySet()) {
            Object v = tags.get(k);
            this.logger.trace("[TRACE] key=" + k);
            this.logger.trace("[TRACE] value=" + v.toString());

            // Attributes を取得
            boolean isAttributes = "Attributes".equals(k);
            this.logger.trace("[TRACE] isAttributes ? " + isAttributes);
            if (isAttributes && v instanceof List<?> l) {
              boolean isNotEmptyAttributesList = !l.isEmpty();

              if (isNotEmptyAttributesList) {
                for (var attr : l) {
                  logger.trace("[TRACE] attr=" + attr.toString());
                  if (attr instanceof Map<?, ?> attrMap) {
                    boolean isNotEmptyAttrMap = !attrMap.keySet().isEmpty();
                    this.logger.trace("[TRACE] isNotEmptyAttrMap ? " + isNotEmptyAttrMap);
                    if (isNotEmptyAttrMap) {
                      Float baseValue = null;
                      Attribute attribute = null;

                      for (var key : attrMap.keySet()) {
                        if (key instanceof String attrKey) {
                          boolean isAttrKeyBase = "Base".equals(attrKey);
                          boolean isAttrKeyName = "Name".equals(attrKey);

                          if (isAttrKeyBase) {
                            baseValue = Float.valueOf(attrMap.get(attrKey).toString());                            
                          
                          } else if (isAttrKeyName) {
                            String attrName = (String) attrMap.get(attrKey);
                            // 最大HPのAttribute
                            if ("minecraft:generic.max_health".equals(attrName)) {
                              attribute = Attribute.GENERIC_MAX_HEALTH;
                            }
                            // スピードのAttribute
                            else if ("minecraft:generic.movement_speed".equals(attrName)) {
                              attribute = Attribute.GENERIC_MOVEMENT_SPEED;
                            }
                            // 跳躍力のAttribute
                            else if ("minecraft:horse.jump_strength".equals(attrName)) {
                              attribute = Attribute.GENERIC_JUMP_STRENGTH;
                            }
                          }
                        }
                        if (Objects.nonNull(baseValue) && Objects.nonNull(attribute)) {
                          if (Attribute.GENERIC_MAX_HEALTH.equals(attribute)) {
                            maxHealth = baseValue;
                          } else if (Attribute.GENERIC_MOVEMENT_SPEED.equals(attribute)) {
                            movementSpeed = baseValue;
                          } else if (Attribute.GENERIC_JUMP_STRENGTH.equals(attribute)) {
                            jumpStrength = baseValue;
                          }
                        }
                      }
                    }
                  }
                }
              }
            }

            boolean isArmorItems = k.equals("ArmorItems");
            this.logger.trace("[TRACE] boolean isArmorItems <- k.equals(\"ArmorItems\")");
            this.logger.trace("[TRACE] isArmorItems ? " + isArmorItems);

            if (isArmorItems && v instanceof List<?> l) {
              boolean isNotEmptyArmorItemList = !l.isEmpty();
              this.logger.trace(
                  "[TRACE] boolean isNotEmptyArmorItemList <- !armorItemList.isEmpty()");
              this.logger.trace("[TRACE] isNotEmptyArmorItemList ? " + isNotEmptyArmorItemList);

              if (isNotEmptyArmorItemList) {
                // Armorはプレイヤーと一緒で４部位に分かれて格納されているため、格納部分を探しに行く
                for (var item : l) {
                  if (item instanceof Map<?, ?> armorMap) {
                    boolean isNotEmptyArmorMap = !armorMap.keySet().isEmpty();
                    this.logger.trace(
                        "[TRACE] boolean isNotEmptyArmorMap <- !armorMap.keySet().isEmpty()");
                    this.logger.trace("[TRACE] isNotEmptyArmorMap ? " + isNotEmptyArmorMap);
                    if (isNotEmptyArmorMap) {
                      Integer count = null;
                      Material armorMaterial = null;
                      for (var key : armorMap.keySet()) {
                        if (key instanceof String armKey) {
                          boolean isIdCount = armKey.equals("Count");
                          this.logger.trace("[TRACE] boolean isIdCount = armKey.equals(\"Count\")");
                          this.logger.trace("[TRACE] isIdCount ? " + isIdCount);
                          if (isIdCount) {
                            count = Integer.valueOf(armorMap.get(armKey).toString());
                            this.logger.trace(
                                "[TRACE]int count <- Integer.valueOf(armorMap.get(armKey).toString())");
                            this.logger.trace("[TRACE] count=" + count);
                          }

                          boolean isIdKey = armKey.equals("id");
                          this.logger.trace("[TRACE] boolean isIdKey = armKey.equals(\"id\")");
                          this.logger.trace("[TRACE] isIdKey ? " + isIdKey);
                          if (isIdKey) {
                            String namespaceKey = armorMap.get(armKey).toString();
                            armorMaterial = Material.matchMaterial(namespaceKey);
                            this.logger.trace(
                                "[TRACE] Material armorMaterial <- Material.matchMaterial(armKey)");
                            this.logger.trace("[TRACE] armorMaterial=" + armorMaterial);
                          }

                          // アーマー名と数量が入ってたら、設定されているものと見て馬鎧を設定する
                          if (Objects.nonNull(armorMaterial) && Objects.nonNull(count)) {
                            equippedHorseArmor = new ItemStack(armorMaterial, count);
                            this.logger.trace(
                                "[TRACE] equippedHorseArmor <- new ItemStack(armorMaterial, count)");
                            this.logger.trace("[TRACE] equippedHorseArmor=" + equippedHorseArmor);
                          }
                        }
                      }
                    }
                  }
                }
              }
            } else {
              // 入っていたタグを召喚したエンティティタグに設定する
              entityTag.set(v, k);
            }
          }
        }
      }
    }
    entityTag.load();
    this.logger.trace("[TRACE] entityTag.load()");
    // 基本パラメータの設定
    // ウマ系
    if (entity instanceof AbstractHorse absHorse) {
      this.logger.trace("[TRACE] entity is AbstractHorse");
      // 最大体力
      if (Objects.nonNull(maxHealth)) {
        absHorse.setMaxHealth(Double.valueOf((double) maxHealth));
        this.logger.trace("[TRACE] absHorse.setMaxHealth(Double.valueOf(" + maxHealth + "))");
      }
      // 移動速度
      if (Objects.nonNull(movementSpeed)) {
        absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue((double) movementSpeed);
        this.logger.trace("[TRACE] absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(" + movementSpeed + ")");
      }
      // 跳躍力
      if (Objects.nonNull(jumpStrength)) {
        absHorse.setJumpStrength((double) jumpStrength);
        this.logger.trace("[TRACE] absHorse.setJumpStrength((double) " + jumpStrength + ")");
      }
    }

    // 旧式馬鎧の設定
    if (Objects.nonNull(equippedHorseArmor) && entity instanceof Horse horse) {
      HorseInventory horseInv = horse.getInventory();
      this.logger.trace("[TRACE] HorseInventory horseInv <- horse.getInventory()");
      this.logger.trace("[TRACE] horseInv=" + horseInv);
      boolean isEmptyInventory = horseInv.isEmpty();
      this.logger.trace("boolean isEmptyInventory <- horseInv.isEmpty() ? ");
      this.logger.trace("[TRACE] isEmptyInventory ? " + isEmptyInventory);
      horseInv.setArmor(equippedHorseArmor);
    }
    // タグ情報にエンティティの位置情報があるため、ここで今ボールを使った座標に移動させる
    entity.teleport(newLocation);
    this.logger.trace("[TRACE]EventListener.EventListener.onTap Parse NBT END");

    /* 以下ボールの生成及びインベントリ転送*/
    ItemStack itemStack = new ItemStack(entityBall);
    itemStack.setAmount(1);
    HashMap<Integer, ItemStack> leftItems = event.getPlayer().getInventory().removeItem(itemStack);
    logger.trace("leftItems" + leftItems);
    leftItems.keySet().forEach(ignored -> offItem.setAmount(offItem.getAmount() - 1));

    ItemStack addItem = new ItemStack(
        this.ballManager.getBallData(entity.getType()).getEmptyBallMaterial(), 1);
    logger.trace("addItem:" + addItem);
    addItem = this.getMetaItem(addItem);
    ItemMeta meta = addItem.getItemMeta();
    Objects.requireNonNull(meta)
        .setDisplayName(this.ballManager.getBallData(entity.getType()).getDisplayName());
    logger.trace("afterAddItem:" + addItem);
    logger.trace("meta:" + meta);
    logger.trace("this.ballManager.getBallData(entity.getType()).getDisplayName()"
        + this.ballManager.getBallData(entity.getType()).getDisplayName());

    // 空のボール作成
    var lore = new ArrayList<String>();
    lore.add("Empty");
    meta.setLore(lore);
    addItem.setItemMeta(meta);

    Player player = event.getPlayer();
    PlayerInventory inventory = player.getInventory();
    logger.trace("player:" + player);
    logger.trace("inventory:" + inventory);
    logger.trace("inventory.getItem(inventory.getHeldItemSlot()) == null :" + (
        inventory.getItem(inventory.getHeldItemSlot()) == null));
    if (inventory.getItem(inventory.getHeldItemSlot()) == null) {
      logger.debug("inventory is Empty");
      inventory.setItem(inventory.getHeldItemSlot(), addItem);
    } else {
      logger.debug("inventory is not Empty");
      HashMap<Integer, ItemStack> notAddedItems = inventory.addItem(addItem);
      if (!notAddedItems.isEmpty()) {
        logger.debug("notAddedItems is Empty");
        player.getWorld().dropItem(player.getLocation(), notAddedItems.get(0));
        player.sendMessage(ChatColor.GREEN + "[PetBall] " + ChatColor.RED
            + ":: 空きスロット不足 :: 空のPetBallを地面に捨てました");
      }
    }
    logger.debug("onTap:End");
  }

  /**
   * <p>
   * アイテムの作成処理.
   * </p>
   *
   * @param item {@link ItemStack} Item情報.
   * @return {@link ItemStack} 作成したアイテム情報.
   */
  private ItemStack getMetaItem(ItemStack item) {
    logger.debug("getMetaItem:Start");

    ItemStack entityBall = item.clone();
    var tag = new RtagItem(entityBall);

    logger.trace("tag:" + tag);
    tag.set(ENTITYBALL_CONTENT_EMPTY, ENTITYBALL_CONTENT_KEY);
    tag.load();
    logger.trace("tag:" + tag);
    logger.trace("entityBall:" + entityBall);

    logger.debug("getMetaItem:End");
    return entityBall;
  }

  /**
   * <p>
   * 受け渡されたアイテムがPetBallかの判定処理.
   * </p>
   *
   * @param item {@link ItemStack} Item情報.
   * @return {@link boolean} チェック結果.
   */
  private boolean isEntityBall(ItemStack item) {
    logger.debug("isEntityBall:Start");
    if (item == null) {
      logger.debug("item is NULL");
      return false;
    }
    ItemMeta itemMeta = item.getItemMeta();
    if (itemMeta == null) {
      logger.debug("itemMeta is NULL");
      return false;
    }

    ItemStack handItemCopy = item.clone();
    RtagItem tag = new RtagItem(handItemCopy);
    if (tag.hasTag(ENTITYBALL_CONTENT_KEY)) {
      logger.debug("is Entity Ball");
      return true;
    }

    logger.debug("itemMeta is not Exist");
    return false;
  }

  /**
   * <p>
   * 受け渡されたアイテムが空のPetBallかの判定処理.
   * </p>
   *
   * @param item {@link ItemStack} Item情報.
   * @return {@link boolean} チェック結果.
   */
  private boolean isEntityEmptyBall(ItemStack item) {
    logger.debug("isEntityEmptyBall:Start");
    ItemMeta itemMeta = item.getItemMeta();
    if (itemMeta == null) {
      logger.debug("itemMeta is NULL");
      return false;
    }

    ItemStack handItemCopy = item.clone();
    RtagItem tag = new RtagItem(handItemCopy);
    if (tag.hasTag(ENTITYBALL_CONTENT_KEY) && tag.get(ENTITYBALL_CONTENT_KEY)
        .toString().equals(ENTITYBALL_CONTENT_EMPTY)) {
      logger.debug("item is EntityEmptyBall");
      return true;
    }

    logger.debug("item is not empty ball.");
    return false;
  }

  /**
   * <p>
   * プレイヤーがエンティティに対して右クリックした時のイベント. PetBall収納処理を実施.
   * </p>
   *
   * @param event {@link PlayerInteractEntityEvent} 受け渡されるイベントの詳細情報.
   */
  @EventHandler
  public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
    logger.debug("onPlayerInteractEntityEvent:Start");
    ItemStack mainItem = event.getPlayer().getInventory().getItemInMainHand();
    ItemStack offItem = event.getPlayer().getInventory().getItemInOffHand();

    //子供生成対策
    var allBallData = this.ballManager.getAllBallDatas();
    logger.trace("mainItem:" + mainItem);
    logger.trace("offItem:" + offItem);
    logger.trace("allBallData:" + allBallData);
    for (EntityType key : allBallData.keySet()) {
      BallData ballData = allBallData.get(key);

      logger.trace("key:" + key);
      logger.trace("ballData:" + ballData);
      logger.trace("event.getRightClicked().getType().equals(ballData.getFilledBallEntityType()):"
          + event.getRightClicked().getType().equals(ballData.getFilledBallEntityType()));
      logger.trace("event.getRightClicked().getType().equals(ballData.getEmptyBallEntityType()):"
          + event.getRightClicked().getType().equals(ballData.getEmptyBallEntityType()));
      if (event.getRightClicked().getType().equals(ballData.getFilledBallEntityType())) {
        logger.debug("getFilledBallEntityType is found. key:" + key);
        logger.trace("isEntityBall(mainItem):" + isEntityBall(mainItem));
        logger.trace("!isEntityEmptyBall(mainItem) :" + !isEntityEmptyBall(mainItem));
        logger.trace(
            "mainItem.getType().equals(ballData.getFilledBallMaterial()):" + mainItem.getType()
                .equals(ballData.getFilledBallMaterial()));
        logger.trace("isEntityBall(offItem):" + isEntityBall(offItem));
        logger.trace("!isEntityEmptyBall(offItem):" + !isEntityEmptyBall(offItem));
        logger.trace(
            "offItem.getType().equals(ballData.getFilledBallMaterial()):" + offItem.getType()
                .equals(ballData.getFilledBallMaterial()));
        if (isEntityBall(mainItem) && !isEntityEmptyBall(mainItem) && mainItem.getType()
            .equals(ballData.getFilledBallMaterial())) {
          logger.debug("mainItem is not EmptyBall");
          event.setCancelled(true);
          return;
        } else if (isEntityBall(offItem) && !isEntityEmptyBall(offItem) && offItem.getType()
            .equals(ballData.getFilledBallMaterial())) {
          logger.debug("offItem is not EmptyBall");
          event.setCancelled(true);
          return;
        }
      } else if (event.getRightClicked().getType().equals(ballData.getEmptyBallEntityType())) {
        logger.debug("getFilledBallEntityType is not found.");
        logger.trace("isEntityEmptyBall(mainItem):" + isEntityEmptyBall(mainItem));
        logger.trace(
            "mainItem.getType().equals(ballData.getEmptyBallMaterial()):" + mainItem.getType()
                .equals(ballData.getEmptyBallMaterial()));
        logger.trace("isEntityEmptyBall(offItem) :" + offItem.getType()
            .equals(ballData.getEmptyBallMaterial()));
        if (isEntityEmptyBall(mainItem) && mainItem.getType()
            .equals(ballData.getEmptyBallMaterial())) {
          logger.debug("mainItem is EntityEmptyBall");
          event.setCancelled(true);
          return;
        } else if (isEntityEmptyBall(offItem) && offItem.getType()
            .equals(ballData.getEmptyBallMaterial())) {
          logger.debug("offItem is EntityEmptyBall");
          event.setCancelled(true);
        }
      }
    }
    if (!isEntityBall(mainItem) && !isEntityBall(offItem)) {
      logger.debug("not entity ball");
      return;
    }
    if (this.isUncapturable(event.getRightClicked().getType())) {
      logger.debug("can not catch RightClicked");
      return;
    }

    logger.trace("this.isEntityEmptyBall(mainItem):" + this.isEntityEmptyBall(mainItem));
    logger.trace("this.isEntityEmptyBall(offItem):" + this.isEntityEmptyBall(offItem));
    logger.trace("mainItem.getType().equals(AIR):" + mainItem.getType().equals(AIR));
    ItemStack entityEmptyBall = null;
    boolean isMainHand = true;
    if (this.isEntityEmptyBall(mainItem)) {
      logger.debug("mainItem is Empty Ball");
      entityEmptyBall = mainItem;
      // isMainHand = true;
    } else if (this.isEntityEmptyBall(offItem) && mainItem.getType().equals(AIR)) {
      logger.debug("offItem is Empty Ball");
      entityEmptyBall = offItem;
      isMainHand = false;
    }

    logger.trace("this.worldManager.isUnusableWorld(event.getPlayer().getWorld().getName()):"
        + this.worldManager.isUnusableWorld(event.getPlayer().getWorld().getName()));
    if (this.worldManager.isUnusableWorld(event.getPlayer().getWorld().getName())) {
      logger.debug("not usage world");
      return;
    }

    RtagItem handTag = new RtagItem(mainItem);
    if (!handTag.hasTag(ENTITYBALL_CONTENT_KEY) || !handTag.get(
        ENTITYBALL_CONTENT_KEY).toString().equals(ENTITYBALL_CONTENT_EMPTY)) {
      logger.debug("not entity ball");
      return;
    }

    Entity entity = event.getRightClicked();
    logger.trace("entity:" + entity);
    logger.trace("this.isUncapturable(entity.getType()):" + this.isUncapturable(entity.getType()));
    if (this.isUncapturable(entity.getType())) {
      logger.debug("can not catch");
      return;
    }

    /*
     * 馬 チェストアイテム ばらまき
     */

    if (entity instanceof ChestedHorse horse) {
      logger.debug("entity is ChestedHorse");

      Location entityLocation = entity.getLocation();
      Location dropItemLocation = new Location(entity.getWorld(), entityLocation.getX() + 0.5,
          entityLocation.getY() + 0.5, entityLocation.getZ() + 0.5);
      logger.trace("entity:" + entity);
      logger.trace("entityLocation:" + entityLocation);
      logger.trace("dropItemLocation:" + dropItemLocation);
      logger.trace("entity.getWorld():" + entity.getWorld());
      logger.trace("entityLocation.getX()+0.5:" + entityLocation.getX() + 0.5);
      logger.trace("entityLocation.getY()+0.5:" + entityLocation.getY() + 0.5);
      logger.trace("entityLocation.getZ()+0.5:" + entityLocation.getZ() + 0.5);

      // ロバかラバの場合、外れないように先頭にある鞍をあらかじめ除外しておく
      boolean hasSaddle = false;
      AbstractHorse abHorse = null;
      if (entity instanceof Donkey || entity instanceof Mule) {
        logger.trace("entity is Donkey or Mule.");
        abHorse = (AbstractHorse) entity;

        // サドルがついてる場合は、フラグを立てて一度外す
        logger.trace("hasSaddle: " + Objects.nonNull(abHorse.getInventory().getSaddle()));
        if (Objects.nonNull(abHorse.getInventory().getSaddle())) {
          logger.trace("this entity has Saddle.");
          hasSaddle = true;
          abHorse.getInventory().setSaddle(null);
        }
      }
      for (ItemStack storageItem : horse.getInventory().getStorageContents()) {
        if (storageItem != null) {
          logger.debug("StorageItem is not null.Item drop!");
          entity.getWorld().dropItem(dropItemLocation, storageItem);
        }
      }
      logger.debug("HorseInventory Clear.");
      horse.getInventory().clear();

      // サドルがある場合は、馬につけなおす
      logger.trace("hasSaddle: " + hasSaddle);
      if (hasSaddle) {
        logger.trace("setSaddle.");
        abHorse.getInventory().setSaddle(new ItemStack(SADDLE));
      }
    }

    RtagEntity entityTag = new RtagEntity(entity);
    logger.trace("[TRACE]BeforeEntityTag:" + entityTag.get());
    entityTag.update();
    event.setCancelled(true);

    ItemStack item = new ItemStack(
        this.ballManager.getBallData(entity.getType()).getFilledBallMaterial(), 1);
    ItemStack clonedItem = item.clone();
    RtagItem itemTag = new RtagItem(clonedItem);
    itemTag.update();
    logger.trace("item:" + item);

    //空気対策
    Entity ent = entityTag.getEntity();
    logger.trace("ent: " + ent);
    if (Objects.isNull(ent)) {
      logger.debug("tag is empty");
      return;
    }

    logger.trace("[TRACE]AfterEntityTag:" + entityTag.get());
    itemTag.set(entityTag.get(), ENTITYBALL_ISC_KEY);
    itemTag.set(entity.getType().toString(), ENTITYBALL_CONTENT_KEY);

    /*
     * 出し入れ同時対策1
     */
    long time = System.currentTimeMillis();
    itemTag.set(time, ENTITYBALL_TIMESTAMP_KEY);
    entity.getPersistentDataContainer()
        .set(new NamespacedKey(this.plugin, ENTITYBALL_TIMESTAMP_KEY),
            PersistentDataType.LONG, time);

    itemTag.load();
    logger.trace("entityBall:" + clonedItem);

    ItemMeta itemMeta2 = clonedItem.getItemMeta();
    Objects.requireNonNull(itemMeta2).setDisplayName(
        entity.getCustomName() == null ? this.ballManager.getBallData(entity.getType())
            .getDisplayName() : entity.getCustomName());
    logger.trace("itemMeta2:" + itemMeta2);

    List<String> lore = new LoreWriterFactory().newLoreWriter(entity.getType())
        .generateLore(entity);
    logger.trace("lore:" + lore);

    itemMeta2.setLore(lore);
    clonedItem.setItemMeta(itemMeta2);

    Player player = event.getPlayer();
    logger.trace(
        "entityEmptyBall.getAmount() == 1:" + (Objects.requireNonNull(entityEmptyBall).getAmount()
            == 1));
    if (entityEmptyBall.getAmount() == 1) {
      logger.debug("entityEmptyBall count is 1");
      logger.trace("isMainHand:" + isMainHand);
      if (isMainHand) {
        logger.debug("isMainHand:True");
        player.getInventory().setItem(player.getInventory().getHeldItemSlot(), clonedItem);
      } else {
        logger.debug("isMainHand:False");
        player.getInventory().remove(entityEmptyBall);
        player.getInventory().addItem(clonedItem);
      }
    } else {
      logger.debug("entityEmptyBall count is not 1");
      entityEmptyBall.setAmount(entityEmptyBall.getAmount() - 1);
      Map<Integer, ItemStack> left = player.getInventory().addItem(clonedItem);
      if (!left.isEmpty()) {
        player.getWorld().dropItem(player.getLocation(), left.get(0));
        player.sendMessage(ChatColor.GREEN + "[PetBall] " + ChatColor.RED
            + ":: 空きスロット不足:: 捕獲済みPetBallを地面に捨てました");
      }
    }

    entity.remove();
    logger.debug("onPlayerInteractEntityEvent:End");
  }

  /**
   * <p>
   * 渡されたブロックがPetBallを動かしていいブロックかの判定.<br> 主に右クリックしたときにインベントリや、動作があるブロックかをここでチェックする.
   * </p>
   *
   * @param block {@link Block} 判定対象のブロック情報.
   * @return {@link boolean} チェック結果.
   */
  private boolean isTouchable(Block block) {

    logger.trace("isTouchable:Start");
    var state = block.getState();
    logger.trace("state:" + state);
    var type = block.getType();
    logger.trace("type:" + type);
    logger.trace("EXCLUSION_MATERIALS.contains(type):" + EXCLUSION_MATERIALS.contains(type));
    if (EXCLUSION_MATERIALS.contains(type)) {
      logger.debug("type is IRON series");
      // 鉄シリーズは問答無用でfalse
      return false;
    } else if (state instanceof Sign sign) {
      // 看板は編集可・不可の状態が変化するので、動的に取得する
      logger.debug("type is Sign series");
      logger.trace("!sign.isWaxed():" + !sign.isWaxed());
      return !sign.isWaxed();
    } else {
      var blockData = block.getBlockData();
      var material = blockData.getMaterial();
      logger.trace("stateClass:" + state.getClass());
      logger.trace(
          "checkMaterial.blockData:" + blockData + ".material:" + material + "isTouchable:End");
      // それ以外
      // ベルは触った場所が本体以外だとうまく動作しないが、場所を知る手立てがないため入れてない
      // コンポスターは最大状態以外だとうまく動作しないが、知る手立てがないため入れていない

      boolean b = false;

      b |= Arrays.stream(STATE_CLASSES).anyMatch(c -> c.isInstance(state));
      b |= Arrays.stream(BLOCK_DATA_CLASSES).anyMatch(c -> c.isInstance(blockData));
      b |= USABLE_MATERIALS.contains(material);

      return b;
    }
  }
}
