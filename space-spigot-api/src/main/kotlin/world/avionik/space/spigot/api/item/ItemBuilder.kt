package world.avionik.space.spigot.api.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.block.banner.Pattern
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta
import org.bukkit.inventory.meta.BookMeta
import org.bukkit.inventory.meta.BookMeta.Generation
import org.bukkit.inventory.meta.FireworkEffectMeta
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionType
import world.avionik.kotlin.paper.editMeta
import world.avionik.minecraft.common.extension.text

/**
 * @author Niklas Nieberler
 */

class ItemBuilder(
    private val material: Material,
    private val itemStack: ItemStack = ItemStack(material)
) {

    private val lores = arrayListOf<Component>()

    /**
     * Sets the displayName to the itemMeta
     * @param component the displayName
     * @return this builder instance
     */
    fun withDisplayName(component: Component?): ItemBuilder {
        this.itemStack.editMeta { it.displayName(decodeComponent(component)) }
        return this
    }

    /**
     * Sets the displayName to the itemMeta
     * @param name of the displayName
     * @return this builder instance
     */
    fun withDisplayName(name: String): ItemBuilder {
        return withDisplayName(text(name))
    }

    /**
     * Sets an amount to the itemStack
     * @param amount the item amount
     * @return this builder instance
     */
    fun withAmount(amount: Int): ItemBuilder {
        this.itemStack.amount = amount
        return this
    }

    /**
     * Updates the material of this itemStack
     * @param material the new material
     * @return this builder instance
     */
    fun withMaterial(material: Material): ItemBuilder {
        this.itemStack.type = material
        return this
    }

    /**
     * Sets a custom model data to this itemMeta
     * @param data the custom model data
     * @return this builder instance
     */
    fun withCustomModelData(data: Int?): ItemBuilder {
        this.itemStack.editMeta { it.setCustomModelData(data) }
        return this
    }

    /**
     * Sets a new lore line
     * @param component the lore line
     * @return this builder instance
     */
    fun withLoreLine(component: Component?): ItemBuilder {
        if (component == null)
            return this
        this.lores.add(component)
        return this
    }

    /**
     * Sets a new lore line
     * @param component the lore line
     * @return this builder instance
     */
    fun withLoreLines(vararg component: Component?): ItemBuilder {
        this.lores.addAll(component.filterNotNull())
        return this
    }

    /**
     * Sets a new lore line
     * @param name of the lore line
     * @return this builder instance
     */
    fun withLoreLine(name: String): ItemBuilder {
        return withLoreLine(decodeComponent(name))
    }

    /**
     * Clears the list of lore lines
     * @return this builder instance
     */
    fun clearLores(): ItemBuilder {
        this.lores.clear()
        return this
    }

    /**
     * Sets a glowing effect to this itemMeta
     * @param glow sets a glowing or not
     * @return this builder instance
     */
    fun withGlowing(glow: Boolean = true): ItemBuilder {
        if (!glow)
            return this
        this.itemStack.editMeta {
            it.addEnchant(Enchantment.UNBREAKING, 1, true)
            it.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        }
        return this
    }

    /**
     * Sets an unbreakable value to the metaData
     * @param unbreakable the boolean value
     * @return this builder instance
     */
    fun withUnbreakable(unbreakable: Boolean = true): ItemBuilder {
        this.itemStack.editMeta { it.isUnbreakable = unbreakable }
        return this
    }

    /**
     * Sets an enchantment to the itemMeta
     * @param enchantment the new enchantment
     * @param level the enchantment level
     * @param ignoreLevelRestriction this indicates the enchantment should be applied, ignoring the level limit
     * @return this builder instance
     */
    fun withEnchantment(enchantment: Enchantment, level: Int, ignoreLevelRestriction: Boolean): ItemBuilder {
        this.itemStack.editMeta { it.addEnchant(enchantment, level, ignoreLevelRestriction) }
        return this
    }

    /**
     * Sets a [PersistentDataContainer] to this itemMeta
     * @param key the key this value will be stored under
     * @param type the type this tag uses
     * @param value the value to store in the tag
     * @param <P> the generic java type of the tag value
     * @param <C> the generic type of the object to store
     */
    fun <P, C : Any> withPersistentDataContainer(key: NamespacedKey, type: PersistentDataType<P, C>, value: C): ItemBuilder {
        this.itemStack.editMeta { it.persistentDataContainer.set(key, type, value) }
        return this
    }

    /**
     * Sets item flags to the itemMeta
     * @param itemFlag to set
     * @return this builder instance
     */
    fun withItemFlags(vararg itemFlag: ItemFlag): ItemBuilder {
        this.itemStack.editMeta { it.addItemFlags(*itemFlag) }
        return this
    }

    /**
     * Sets a color to a [LeatherArmorMeta]
     * @param color the leather color
     * @return this builder instance
     */
    fun withArmorColor(color: Color): ItemBuilder {
        this.itemStack.editMeta<LeatherArmorMeta> { it.setColor(color) }
        return this
    }

    /**
     * Sets a [PotionType] to a [PotionMeta]
     * @param potionType of the potion effect
     * @return this builder instance
     */
    @Suppress("DEPRECATION", "removal")
    fun withPotionType(potionType: PotionType): ItemBuilder {
        this.itemStack.editMeta<PotionMeta> { it.basePotionData = PotionData(potionType) }
        return this
    }

    /**
     * Sets a [Pattern] to a [BannerMeta]
     * @param pattern of the banner
     * @return this builder instance
     */
    fun withBannerPattern(pattern: Pattern): ItemBuilder {
        this.itemStack.editMeta<BannerMeta> { it.addPattern(pattern) }
        return this
    }

    /**
     * Sets a [FireworkEffect] to a [FireworkEffectMeta]
     * @param fireworkEffect of a firework
     * @return this builder instance
     */
    fun withFireworkEffect(fireworkEffect: FireworkEffect): ItemBuilder {
        this.itemStack.editMeta<FireworkEffectMeta> { it.effect = fireworkEffect }
        return this
    }

    /**
     * Sets a [FireworkEffect] to a [FireworkEffectMeta]
     * @param color of a firework
     * @return this builder instance
     */
    fun withFireworkColor(color: Color): ItemBuilder {
        return withFireworkEffect(FireworkEffect.builder().withColor(color).build())
    }

    /**
     * Sets a book page to a [BookMeta]
     * @param component of a page
     * @return this builder instance
     */
    fun withBookPage(vararg component: Component): ItemBuilder {
        this.itemStack.editMeta<BookMeta> { it.addPages(*component) }
        return this
    }

    /**
     * Sets a book page to a [BookMeta]
     * @param name of a page
     * @return this builder instance
     */
    fun withBookPage(vararg name: String): ItemBuilder {
        return withBookPage(*name.map { text(it) }.toTypedArray())
    }

    /**
     * Sets a book page to a [BookMeta]
     * @param id the page id
     * @param component of a page
     * @return this builder instance
     */
    fun withBookPage(id: Int, component: Component): ItemBuilder {
        this.itemStack.editMeta<BookMeta> { it.page(id, component) }
        return this
    }

    /**
     * Sets a book page to a [BookMeta]
     * @param id the page id
     * @param name of a page
     * @return this builder instance
     */
    fun withBookPage(id: Int, name: String): ItemBuilder {
        return withBookPage(id, text(name))
    }

    /**
     * Sets a book author to a [BookMeta]
     * @param author of this book
     * @return this builder instance
     */
    fun withBookAuthor(author: String): ItemBuilder {
        this.itemStack.editMeta<BookMeta> { it.author = author }
        return this
    }

    /**
     * Sets a book generation to a [BookMeta]
     * @param generation of this book
     * @return this builder instance
     */
    fun withBookGeneration(generation: Generation): ItemBuilder {
        this.itemStack.editMeta<BookMeta> { it.generation = generation }
        return this
    }

    /**
     * Sets a book title to a [BookMeta]
     * @param component the title
     * @return this builder instance
     */
    fun withBookTitle(component: Component): ItemBuilder {
        this.itemStack.editMeta<BookMeta> { it.title(component) }
        return this
    }

    /**
     * Sets a book title to a [BookMeta]
     * @param name of the title
     * @return this builder instance
     */
    fun withBookTitle(name: String): ItemBuilder {
        return withBookTitle(text(name))
    }

    /**
     * Creates a new [ItemStack] instance
     * @return new item stack instance
     */
    fun build(): ItemStack {
        if (this.lores.isNotEmpty()) {
            this.itemStack.editMeta { it.lore(this.lores.map { decodeComponent(it) }) }
        }
        return this.itemStack
    }

    /**
     * Serializes this item builder to raw bytes in NBT. NBT is safer for data migrations as it will
     * use the built-in data converter instead of bukkits dangerous serialization system.
     * @return bytes representing this item in NBT.
     */
    fun serializeAsBytes(): ByteArray {
        return build().serializeAsBytes()
    }

    private fun decodeComponent(component: Component?): Component? {
        return component?.decoration(TextDecoration.ITALIC, false)
    }

    private fun decodeComponent(string: String): Component? {
        return decodeComponent(text(string))
    }

    companion object {
        /**
         * Creates a new item builder with [ItemStack]
         * @param itemStack to create this builder
         * @return new [ItemBuilder] instance
         */
        fun of(itemStack: ItemStack): ItemBuilder {
            return ItemBuilder(itemStack.type, itemStack)
        }

        /**
         * Creates a new item builder with [ByteArray]
         * @param bytes of the item stack
         * @return new [ItemBuilder] instance
         */
        fun deserializeBytes(bytes: ByteArray): ItemBuilder {
            return of(ItemStack.deserializeBytes(bytes))
        }
    }

}