/**
 * This class was created by <Vazkii>. It's distributed as part of the ThaumicTinkerer Mod.
 *
 * ThaumicTinkerer is Open Source and distributed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0
 * License (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * ThaumicTinkerer is a Derivative Work on Thaumcraft 4. Thaumcraft 4 (c) Azanor 2012
 * (http://www.minecraftforum.net/topic/1585216-)
 *
 * File Created @ [Dec 8, 2013, 6:26:09 PM (GMT)]
 */
package thaumic.tinkerer.common.item.foci;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

import cpw.mods.fml.common.Loader;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.compat.SpecialMobs;
import thaumic.tinkerer.common.lib.LibItemNames;
import thaumic.tinkerer.common.lib.LibResearch;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemFocusDeflect extends ItemModFocus {

    public static Set<Class<?>> DeflectWhitelist = new HashSet<Class<?>>();
    private static final AspectList visUsage = new AspectList().add(Aspect.ORDER, 8).add(Aspect.AIR, 4);

    public static void setupWhiteList() {
        DeflectWhitelist.add(EntityArrow.class);
        DeflectWhitelist.add(EntityPotion.class);
        DeflectWhitelist.add(EntitySnowball.class);
        DeflectWhitelist.add(EntityEgg.class);

        if (Loader.isModLoaded("Special Mobs")) {
            SpecialMobs.setupClass();
        }
    }

    public static void protectFromProjectiles(EntityPlayer player, ItemStack stack) {
        int range = 0;
        if (stack != null) {
            ItemWandCasting wand = (ItemWandCasting) stack.getItem();
            range = wand.getFocusEnlarge(stack);
        }

        List<Entity> projectiles = player.worldObj.getEntitiesWithinAABB(
                IProjectile.class,
                AxisAlignedBB.getBoundingBox(
                        player.posX - (4 + range),
                        player.posY - (4 + range),
                        player.posZ - (4 + range),
                        player.posX + (3 + range),
                        player.posY + (3 + range),
                        player.posZ + (3 + range)));

        for (Entity projectile : projectiles) {
            if (!isValidProjectile(projectile)) continue;
            Vector3 motionVec = new Vector3(projectile.motionX, projectile.motionY, projectile.motionZ).normalize()
                    .multiply(
                            Math.sqrt(
                                    (projectile.posX - player.posX) * (projectile.posX - player.posX)
                                            + (projectile.posY - player.posY) * (projectile.posY - player.posY)
                                            + (projectile.posZ - player.posZ) * (projectile.posZ - player.posZ))
                                    * 2);

            for (int i = 0; i < 6; i++) ThaumicTinkerer.tcProxy
                    .sparkle((float) projectile.posX, (float) projectile.posY, (float) projectile.posZ, 6);

            projectile.posX += motionVec.x;
            projectile.posY += motionVec.y;
            projectile.posZ += motionVec.z;
        }
    }

    private static boolean isValidProjectile(Entity entity) {
        Class<? extends Entity> aClass = entity.getClass();
        if (DeflectWhitelist.contains(aClass)) return true;

        for (Class<?> testClass : DeflectWhitelist) {
            if (testClass.isInterface() && testClass.isAssignableFrom(aClass)) return true;
        }
        return false;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1:
            case 3:
            case 5:
                return new FocusUpgradeType[] { FocusUpgradeType.frugal };
            case 2:
            case 4:
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.enlarge };
        }
        return null;
    }

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int ticks) {
        ItemWandCasting wand = (ItemWandCasting) stack.getItem();

        if (wand.consumeAllVis(stack, p, getVisCost(stack), true, false)) protectFromProjectiles(p, stack);
    }

    public String getSortingHelper(ItemStack itemstack) {
        return "TTDF" + super.getSortingHelper(itemstack);
    }

    @Override
    public boolean isVisCostPerTick(ItemStack stack) {
        return true;
    }

    @Override
    public int getFocusColor(ItemStack stack) {
        return 0xFFFFFF;
    }

    @Override
    public AspectList getVisCost(ItemStack stack) {
        return visUsage;
    }

    @Override
    public String getItemName() {
        return LibItemNames.FOCUS_DEFLECT;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return (TTResearchItem) new TTResearchItem(
                LibResearch.KEY_FOCUS_DEFLECT,
                new AspectList().add(Aspect.MOTION, 2).add(Aspect.AIR, 1).add(Aspect.ORDER, 1).add(Aspect.DEATH, 1),
                -4,
                -3,
                3,
                new ItemStack(this)).setConcealed().setParents(LibResearch.KEY_FOCUS_SMELT)
                        .setPages(new ResearchPage("0"), ResearchHelper.infusionPage(LibResearch.KEY_FOCUS_DEFLECT))
                        .setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe(
                LibResearch.KEY_FOCUS_DEFLECT,
                new ItemStack(this),
                5,
                new AspectList().add(Aspect.AIR, 15).add(Aspect.ARMOR, 5).add(Aspect.ORDER, 20),
                new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusFlight.class)),
                new ItemStack(ConfigItems.itemResource, 1, 10),
                new ItemStack(ConfigItems.itemResource, 1, 10),
                new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 3),
                new ItemStack(ConfigItems.itemShard, 1, 4));
    }
}
