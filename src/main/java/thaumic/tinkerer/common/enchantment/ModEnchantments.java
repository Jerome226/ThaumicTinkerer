/**
 * This class was created by <Vazkii>. It's distributed as part of the ThaumicTinkerer Mod.
 *
 * ThaumicTinkerer is Open Source and distributed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0
 * License (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * ThaumicTinkerer is a Derivative Work on Thaumcraft 4. Thaumcraft 4 (c) Azanor 2012
 * (http://www.minecraftforum.net/topic/1585216-)
 *
 * File Created @ [4 Sep 2013, 16:57:55 (GMT)]
 */
package thaumic.tinkerer.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigResearch;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.lib.LibEnchantIDs;
import thaumic.tinkerer.common.lib.LibEnchantNames;

public final class ModEnchantments {

    public static Enchantment ascentBoost;
    public static Enchantment slowFall;
    public static Enchantment autoSmelt;
    public static Enchantment desintegrate;
    public static Enchantment quickDraw;
    public static Enchantment vampirism;
    public static Enchantment dispersedStrikes;
    public static Enchantment filtration;
    public static Enchantment finalStrike;
    public static Enchantment focusedStrike;
    public static Enchantment imbued;
    public static Enchantment pounce;
    public static Enchantment resolute;
    public static Enchantment shatter;
    public static Enchantment shockwave;
    public static Enchantment tunnel;
    public static Enchantment valiance;

    public static void initEnchantments() {
        ascentBoost = new EnchantmentAscentBoost(LibEnchantIDs.idAscentBoost).setName(LibEnchantNames.ASCENT_BOOST);
        slowFall = new EnchantmentSlowFall(LibEnchantIDs.idSlowFall).setName(LibEnchantNames.SLOW_FALL);
        autoSmelt = new EnchantmentAutoSmelt(LibEnchantIDs.idAutoSmelt).setName(LibEnchantNames.AUTO_SMELT);
        desintegrate = new EnchantmentDesintegrate(LibEnchantIDs.idDesintegrate).setName(LibEnchantNames.DESINTEGRATE);
        quickDraw = new EnchantmentQuickDraw(LibEnchantIDs.idQuickDraw).setName(LibEnchantNames.QUICK_DRAW);
        vampirism = new EnchantmentVampirism(LibEnchantIDs.idVampirism).setName(LibEnchantNames.VAMPIRISM);

        dispersedStrikes = new EnchantmentDispersedStrikes(LibEnchantIDs.dispersedStrikes)
                .setName(LibEnchantNames.dispersedStrikes);

        finalStrike = new EnchantmentFinalStrike(LibEnchantIDs.finalStrike).setName(LibEnchantNames.finalStrike);

        focusedStrike = new EnchantmentFocusedStrikes(LibEnchantIDs.focusedStrike)
                .setName(LibEnchantNames.focusedStrike);

        pounce = new EnchantmentPounce(LibEnchantIDs.pounce).setName(LibEnchantNames.pounce);
        shatter = new EnchantmentShatter(LibEnchantIDs.shatter).setName(LibEnchantNames.shatter);

        shockwave = new EnchantmentShockwave(LibEnchantIDs.shockwave).setName(LibEnchantNames.shockwave);

        tunnel = new EnchantmentTunnel(LibEnchantIDs.tunnel).setName(LibEnchantNames.tunnel);

        valiance = new EnchantmentValiance(LibEnchantIDs.valiance).setName(LibEnchantNames.valiance);

        MinecraftForge.EVENT_BUS.register(new ModEnchantmentHandler());
    }

    public static void addInfusionEnchants() {
        ConfigResearch.recipes.put(
                "TTENCH_ASCENT_BOOST",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_ASCENT_BOOST",
                        Enchantment.enchantmentsList[LibEnchantIDs.idAscentBoost],
                        5,
                        new AspectList().add(Aspect.TRAVEL, 16).add(Aspect.ENERGY, 2),
                        new ItemStack[] { new ItemStack(Items.ender_eye), new ItemStack(Items.feather),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.feather), }));
        ConfigResearch.recipes.put(
                "TTENCH_AUTO_SMELT",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_AUTO_SMELT",
                        Enchantment.enchantmentsList[LibEnchantIDs.idAutoSmelt],
                        5,
                        new AspectList().add(Aspect.FIRE, 2).add(Aspect.METAL, 4),
                        new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 1),
                                new ItemStack(Items.magma_cream), new ItemStack(ConfigItems.itemResource, 1, 14),
                                new ItemStack(Items.magma_cream), }));
        ConfigResearch.recipes.put(
                "TTENCH_DESINTEGRATE",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_DESINTEGRATE",
                        Enchantment.enchantmentsList[LibEnchantIDs.idDesintegrate],
                        5,
                        new AspectList().add(Aspect.ENERGY, 16).add(Aspect.MINE, 8),
                        new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack(Items.redstone),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.redstone), }));
        ConfigResearch.recipes.put(
                "TTENCH_DISPERSED",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_DISPERSED",
                        Enchantment.enchantmentsList[LibEnchantIDs.dispersedStrikes],
                        6,
                        new AspectList().add(Aspect.TAINT, 16).add(Aspect.WEAPON, 4),
                        new ItemStack[] { new ItemStack(ConfigItems.itemNugget, 1, 7),
                                new ItemStack(ConfigItems.itemResource, 1, 4),
                                new ItemStack(ConfigItems.itemResource, 1, 14),
                                new ItemStack(ConfigItems.itemResource, 1, 4), }));
        ConfigResearch.recipes.put(
                "TTENCH_FOCUSED",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_FOCUSED",
                        Enchantment.enchantmentsList[LibEnchantIDs.focusedStrike],
                        6,
                        new AspectList().add(Aspect.MIND, 16).add(Aspect.ENERGY, 8),
                        new ItemStack[] { new ItemStack(Items.iron_sword), new ItemStack(Items.skull, 1, 1),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.blaze_rod) }));
        ConfigResearch.recipes.put(
                "TTENCH_FINAL",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_FINAL",
                        Enchantment.enchantmentsList[LibEnchantIDs.finalStrike],
                        8,
                        new AspectList().add(Aspect.DEATH, 16).add(Aspect.WEAPON, 8),
                        new ItemStack[] { new ItemStack(Items.diamond_sword),
                                new ItemStack(
                                        ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class),
                                        1,
                                        6),
                                new ItemStack(ConfigItems.itemResource, 1, 14),
                                new ItemStack(
                                        ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class),
                                        1,
                                        7) }));
        ConfigResearch.recipes.put(
                "TTENCH_POUNCE",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_POUNCE",
                        Enchantment.enchantmentsList[LibEnchantIDs.pounce],
                        6,
                        new AspectList().add(Aspect.BEAST, 8).add(Aspect.FLIGHT, 4),
                        new ItemStack[] { new ItemStack(Items.fireworks), new ItemStack(Items.arrow),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.arrow), }));
        ConfigResearch.recipes.put(
                "TTENCH_QUICK_DRAW",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_QUICK_DRAW",
                        Enchantment.enchantmentsList[LibEnchantIDs.idQuickDraw],
                        5,
                        new AspectList().add(Aspect.MOTION, 8).add(Aspect.AIR, 4),
                        new ItemStack[] { new ItemStack(Items.gold_nugget), new ItemStack(Items.blaze_powder),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.blaze_powder) }));
        ConfigResearch.recipes.put(
                "TTENCH_SHATTER",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_SHATTER",
                        Enchantment.enchantmentsList[LibEnchantIDs.shatter],
                        6,
                        new AspectList().add(Aspect.CRYSTAL, 8).add(Aspect.ENTROPY, 4),
                        new ItemStack[] { new ItemStack(Items.diamond), new ItemStack(Items.quartz),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.quartz) }));
        ConfigResearch.recipes.put(
                "TTENCH_SHOCKWAVE",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_SHOCKWAVE",
                        Enchantment.enchantmentsList[LibEnchantIDs.shockwave],
                        6,
                        new AspectList().add(Aspect.TRAVEL, 8).add(Aspect.ENERGY, 4),
                        new ItemStack[] { new ItemStack(Blocks.tnt), new ItemStack(ConfigItems.itemResource, 1, 0),
                                new ItemStack(ConfigItems.itemResource, 1, 14) }));
        ConfigResearch.recipes.put(
                "TTENCH_SLOW_FALL",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_SLOW_FALL",
                        Enchantment.enchantmentsList[LibEnchantIDs.idSlowFall],
                        5,
                        new AspectList().add(Aspect.TRAP, 2).add(Aspect.FLIGHT, 4),
                        new ItemStack[] { new ItemStack(Items.ender_eye), new ItemStack(Items.feather),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.feather) }));
        ConfigResearch.recipes.put(
                "TTENCH_TUNNEL",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_TUNNEL",
                        Enchantment.enchantmentsList[LibEnchantIDs.tunnel],
                        6,
                        new AspectList().add(Aspect.MINE, 4).add(Aspect.TRAVEL, 8),
                        new ItemStack[] { new ItemStack(Items.fire_charge), new ItemStack(Items.gunpowder),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.gunpowder) }));
        ConfigResearch.recipes.put(
                "TTENCH_VALIANCE",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_VALIANCE",
                        Enchantment.enchantmentsList[LibEnchantIDs.valiance],
                        6,
                        new AspectList().add(Aspect.LIFE, 4).add(Aspect.DEATH, 4),
                        new ItemStack[] { new ItemStack(Items.golden_apple), new ItemStack(Items.emerald),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.emerald), }));
        ConfigResearch.recipes.put(
                "TTENCH_VAMPIRISM",
                ThaumcraftApi.addInfusionEnchantmentRecipe(
                        "TTENCH_VAMPIRISM",
                        Enchantment.enchantmentsList[LibEnchantIDs.idVampirism],
                        5,
                        new AspectList().add(Aspect.HUNGER, 8).add(Aspect.LIFE, 4),
                        new ItemStack[] { new ItemStack(Items.beef), new ItemStack(Items.bone),
                                new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.speckled_melon) }));
    }
}
