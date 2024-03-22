package world.avionik.space.spigot.api.editor

import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.entity.Display.Billboard
import org.bukkit.entity.TextDisplay
import org.bukkit.util.Transformation
import world.avionik.kotlin.paper.spawn
import world.avionik.minecraft.common.extension.text

/**
 * @author Niklas Nieberler
 */

class HologramEditor(
    private val location: Location,
) {

    private var yCloneLocation = 0.3
    private var nextLineHologram: HologramEditor? = null

    private val textDisplay = createTextDisplay()

    /**
     * Sets a [Transformation] to the text display
     * @param transformation the display transformation
     * @return this editor instance
     */
    fun withTransformation(transformation: Transformation): HologramEditor {
        this.textDisplay.transformation = transformation
        return this
    }

    /**
     * Sets a [Billboard] to the text display
     * @param billboard the text billboard
     * @return this editor instance
     */
    fun withBillboard(billboard: Billboard): HologramEditor {
        this.textDisplay.billboard = billboard
        return this
    }

    /**
     * Sets a view range to the text display
     * @param range the view range
     * @return this editor instance
     */
    fun withViewRange(range: Float): HologramEditor {
        this.textDisplay.viewRange = range
        return this
    }

    /**
     * Sets a shadow range to the text display
     * @param radius the shadow range
     * @return this editor instance
     */
    fun withShadowRadius(radius: Float): HologramEditor {
        this.textDisplay.shadowRadius = radius
        return this
    }

    /**
     * Sets the height of this text display.
     * @param height new height
     * @return this editor instance
     */
    fun withDisplayHeight(height: Float): HologramEditor {
        this.textDisplay.displayHeight = height
        return this
    }

    /**
     * Sets the width of this display.
     * @param width new width
     * @return this editor instance
     */
    fun withDisplayWidth(width: Float): HologramEditor {
        this.textDisplay.displayWidth = width
        return this
    }

    /**
     * Sets a [TextDisplay.TextAlignment] to the text display
     * @param alignment the display alignment
     * @return this editor instance
     */
    fun withAlignment(alignment: TextDisplay.TextAlignment): HologramEditor {
        this.textDisplay.alignment = alignment
        return this
    }

    /**
     * Sets a line width to the text display
     * @param width the line width
     * @return this editor instance
     */
    fun withLineWidth(width: Int): HologramEditor {
        this.textDisplay.lineWidth = width
        return this
    }

    /**
     * Sets a shadow to the text display
     * @param shadow the line shadow
     * @return this editor instance
     */
    fun withShadow(shadow: Boolean): HologramEditor {
        this.textDisplay.isShadowed = shadow
        return this
    }

    /**
     * Sets a custom name to the text display
     * @param name the custom name
     * @return this editor instance
     */
    fun withCustomName(name: Component?): HologramEditor {
        this.textDisplay.text(name)
        return this
    }

    /**
     * Sets a custom name to the text display
     * @param name the custom name
     * @return this editor instance
     */
    fun withCustomName(name: String): HologramEditor {
        return withCustomName(text(name))
    }

    /**
     * Sets a custom y clone location to the text display
     * @param yClone the clone double
     * @return this editor instance
     */
    fun withYCloneLocation(yClone: Double): HologramEditor {
        this.yCloneLocation = yClone
        return this
    }

    /**
     * Creates a new hologram line
     * @return new hologram instance
     */
    fun withNextLine(): HologramEditor {
        if (this.nextLineHologram == null)
            return HologramEditor(this.location.clone().add(0.0, this.yCloneLocation, 0.0))
        return this.nextLineHologram!!
    }

    /**
     * Destroys the text displays
     */
    fun destroy() {
        this.textDisplay.remove()
        this.nextLineHologram?.destroy()
    }

    /**
     * Removes all next lines
     */
    fun removeNextLines() {
        this.nextLineHologram?.removeNextLines()
        this.nextLineHologram?.destroy()
        this.nextLineHologram = null
    }

    private fun createTextDisplay(): TextDisplay {
        val textDisplay = this.location.world.spawn<TextDisplay>(this.location)
        textDisplay.billboard = Billboard.CENTER
        return textDisplay
    }

}