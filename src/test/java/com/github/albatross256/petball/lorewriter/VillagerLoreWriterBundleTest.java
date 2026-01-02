package com.github.albatross256.petball.lorewriter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.bukkit.Registry;
import org.bukkit.entity.Villager;
import org.junit.jupiter.api.Test;

public class VillagerLoreWriterBundleTest {

  private static String BASE_NAME = "com.github.albatross256.petball.lorewriter.VillagerLoreWriter";
  private ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, Locale.JAPANESE);

  @Test
  void noneTest() {
    assertEquals(bundle.getString("NONE"), "ニート");
  }

  @Test
  void armorerTest() {
    assertEquals(bundle.getString("ARMORER"), "防具鍛冶");
  }

  @Test
  void butcherTest() {
    assertEquals(bundle.getString("BUTCHER"), "肉屋");
  }

  @Test
  void cartographerTest() {
    assertEquals(bundle.getString("CARTOGRAPHER"), "製図家");
  }

  @Test
  void clericTest() {
    assertEquals(bundle.getString("CLERIC"), "聖職者");
  }

  @Test
  void farmerTest() {
    assertEquals(bundle.getString("FARMER"), "農民");
  }

  @Test
  void fishermanTest() {
    assertEquals(bundle.getString("FISHERMAN"), "釣り人");
  }

  @Test
  void fletcherTest() {
    assertEquals(bundle.getString("FLETCHER"), "矢師");
  }

  @Test
  void leatherworkerTest() {
    assertEquals(bundle.getString("LEATHERWORKER"), "革細工師");
  }

  @Test
  void librarianTest() {
    assertEquals(bundle.getString("LIBRARIAN"), "司書");
  }

  @Test
  void masonTest() {
    assertEquals(bundle.getString("MASON"), "石工職人");
  }

  @Test
  void nitwitTest() {
    assertEquals(bundle.getString("NITWIT"), "ニート");
  }

  @Test
  void shepherdTest() {
    assertEquals(bundle.getString("SHEPHERD"), "羊飼い");
  }

  @Test
  void weaponsmithTest() {
    assertEquals(bundle.getString("WEAPONSMITH"), "武器鍛冶");
  }

  @Test
  void toolsmithTest() {
    assertEquals(bundle.getString("TOOLSMITH"), "道具鍛冶");
  }
}
