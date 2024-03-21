package world.avionik.space.spigot.api.task

/**
 * @author Niklas Nieberler
 */

fun interface Task {

    /**
     * This method is executed when the scheduler is used
     * @param taskEdit to edit the task
     */
    fun run(taskEdit: TaskEdit)

}