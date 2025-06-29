package com.github.albatross256.petball;

import com.github.albatross256.petball.balldata.AllayBallData;
import com.github.albatross256.petball.balldata.ArmadilloBallData;
import com.github.albatross256.petball.balldata.AxolotlBallData;
import com.github.albatross256.petball.balldata.BallData;
import com.github.albatross256.petball.balldata.BatBallData;
import com.github.albatross256.petball.balldata.BeeBallData;
import com.github.albatross256.petball.balldata.CamelBallData;
import com.github.albatross256.petball.balldata.CatBallData;
import com.github.albatross256.petball.balldata.ChickenBallData;
import com.github.albatross256.petball.balldata.CodBallData;
import com.github.albatross256.petball.balldata.CowBallData;
import com.github.albatross256.petball.balldata.DolphinBallData;
import com.github.albatross256.petball.balldata.DonkeyBallData;
import com.github.albatross256.petball.balldata.FoxBallData;
import com.github.albatross256.petball.balldata.FrogBallData;
import com.github.albatross256.petball.balldata.GlowSquidBallData;
import com.github.albatross256.petball.balldata.GoatBallData;
import com.github.albatross256.petball.balldata.HappyGhastBallData;
import com.github.albatross256.petball.balldata.HorseBallData;
import com.github.albatross256.petball.balldata.IronGolemBallData;
import com.github.albatross256.petball.balldata.LlamaBallData;
import com.github.albatross256.petball.balldata.MuleBallData;
import com.github.albatross256.petball.balldata.MushroomBallData;
import com.github.albatross256.petball.balldata.OcelotBallData;
import com.github.albatross256.petball.balldata.PandaBallData;
import com.github.albatross256.petball.balldata.ParrotBallData;
import com.github.albatross256.petball.balldata.PigBallData;
import com.github.albatross256.petball.balldata.PiglinBallData;
import com.github.albatross256.petball.balldata.PolarBearBallData;
import com.github.albatross256.petball.balldata.PufferFishBallData;
import com.github.albatross256.petball.balldata.RabbitBallData;
import com.github.albatross256.petball.balldata.SalmonBallData;
import com.github.albatross256.petball.balldata.SheepBallData;
import com.github.albatross256.petball.balldata.SkeletonHorseBallData;
import com.github.albatross256.petball.balldata.SnifferBallData;
import com.github.albatross256.petball.balldata.SnowGolemBallData;
import com.github.albatross256.petball.balldata.SquidBallData;
import com.github.albatross256.petball.balldata.StriderBallData;
import com.github.albatross256.petball.balldata.TadpoleBallData;
import com.github.albatross256.petball.balldata.TraderLlamaBallData;
import com.github.albatross256.petball.balldata.TropicalFishData;
import com.github.albatross256.petball.balldata.TurtleBallData;
import com.github.albatross256.petball.balldata.VillagerBallData;
import com.github.albatross256.petball.balldata.WanderingTraderBallData;
import com.github.albatross256.petball.balldata.WolfBallData;
import com.github.albatross256.petball.balldata.ZombieHorseBallData;
import com.github.teruteru128.logger.Logger;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import org.bukkit.entity.EntityType;

/**
 * <p>
 * ボールの情報を管理するマネージャー.
 * </p>
 */
public class BallManager {

  protected Map<EntityType, BallData> ballData;
  private final Logger logger;

  /**
   * <p>
   * コンストラクタ.
   * </p>
   *
   * @param logger  {@link Logger} ログ出力用のロガー本体.
   */
  public BallManager(Logger logger) {
    logger.debug("BallManager:Start");
    this.logger = logger;
    init();
    logger.debug("BallManager:End");
  }

  /**
   * <p>
   * 初期化処理.<br>
   * PetBallのボール本体の一覧を作成.
   * </p>
   */
  private void init() {
    logger.debug("BallManager.init:Start");

    var map = new EnumMap<EntityType, BallData>(EntityType.class);
    BallData[] ballDataArray = {
        new VillagerBallData(),
        new CatBallData(),
        new OcelotBallData(),
        new ParrotBallData(),
        new WolfBallData(),
        new CowBallData(),
        new MushroomBallData(),
        new PigBallData(),
        new SheepBallData(),
        new ChickenBallData(),
        new RabbitBallData(),
        new PolarBearBallData(),
        new TurtleBallData(),
        new DolphinBallData(),
        new SquidBallData(),
        new IronGolemBallData(),
        new SnowGolemBallData(),
        new BatBallData(),
        new WanderingTraderBallData(),
        new PandaBallData(),
        new FoxBallData(),
        new LlamaBallData(),
        new MuleBallData(),
        new DonkeyBallData(),
        new CodBallData(),
        new SalmonBallData(),
        new TropicalFishData(),
        new PufferFishBallData(),
        new BeeBallData(),
        new HorseBallData(),
        new ZombieHorseBallData(),
        new SkeletonHorseBallData(),
        new TraderLlamaBallData(),
        new PiglinBallData(),
        new StriderBallData(),
        new AxolotlBallData(),
        new GoatBallData(),
        new GlowSquidBallData(),
        new AllayBallData(),
        new FrogBallData(),
        new TadpoleBallData(),
        new CamelBallData(),
        new SnifferBallData(),
        new ArmadilloBallData(),
        new HappyGhastBallData(),
    };

    for (BallData ballData : ballDataArray) {
      logger.trace("ballData:" + ballData);
      registerBall(map, ballData);
    }
    this.ballData = Collections.unmodifiableMap(map);
    logger.debug("BallManager.init:End");
  }

  /**
   * <p>
   * PetBallデータの登録.
   * </p>
   *
   * @param map  {@link EnumMap} 登録先のMAP.
   * @param ballData  {@link BallData} 登録したいボールデータ.
   */
  private static void registerBall(EnumMap<EntityType, BallData> map, BallData ballData) {
    map.put(ballData.getEntityType(), ballData);
  }

  /**
   * <p>
   * すべてのPetBallデータの取得.
   * </p>
   *
   * @return {@link Map} 全ボールデータ一覧.
   */
  public Map<EntityType, BallData> getAllBallDatas() {
    logger.debug("BallManager.getAllBallDatas");
    return this.ballData;
  }

  /**
   * <p>
   *   エンティティタイプに対応するPetBallデータが存在するかを返す.
   * </p>
   *
   * @param e エンティティタイプ
   * @return 対応するボールデータの存在可否
   */
  public boolean containsBall(EntityType e) {
    logger.debug("BallManager.containsBall");
    return this.ballData.containsKey(e);
  }

  /**
   * <p>
   * 渡されたEntityTypeに該当するPetBallのボールデータ取得.
   * </p>
   *
   * @param entityType  {@link EntityType} 取得したいエンティティタイプ.
   * @return {@link BallData} 該当するボールデータ.
   */
  public BallData getBallData(EntityType entityType) {
    logger.debug("BallManager.getBallData");
    return this.ballData.get(entityType);
  }


}
