import org.gradle.api.*
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.*
import org.gradle.kotlin.dsl.*
import java.io.File

open class InstallShuffleboardTask : DefaultTask() {

    init {
        group = "gradlerio"
        description = "Installs Shuffleboard to ~/wpilib/tools for launching with the driver station software"
    }

    private val userHome = System.getProperty("user.home")
    private val wpiToolsDir = "$userHome/wpilib/tools"

    @TaskAction
    fun run() {
        val wpiTools = project.configurations["wpiTools"]
        val shuffleboard = wpiTools.dependencies.find {
            it.group == "edu.wpi.first.shuffleboard"
        }

        if (shuffleboard != null) {
            logger.quiet("Found Shuffleboard dependency: $shuffleboard")
            val jar = getShuffleboardJar(wpiTools, shuffleboard)

            if (jar != null) {
                val destination = getDestinationFile()
                logger.quiet("Copying ${jar.name} to ${destination.absolutePath}")
                jar?.copyTo(destination)
            } else {
                throw IllegalStateException("Unable to find Shuffleboard JAR")
            }
        } else {
            throw IllegalStateException("Unable to find Shuffleboard dependency")
        }
    }

    private fun getShuffleboardJar(wpiTools: Configuration, dependency: Dependency): File? {
        val files = wpiTools.files(dependency)
        files.forEach { logger.info("Shuffleboard dependency file: ${it.name}") }

        return files.firstOrNull { it.extension == "jar" }
    }

    private fun getDestinationFile(): File {
        val destination = File(wpiToolsDir, "Shuffleboard.jar")
        destination.mkdirs()
        if (destination.exists()) {
            destination.delete()
        }

        return destination
    }

}

/**
 * Declares an [InstallShuffleboardTask] named `installShuffleboard`.
 */
fun Project.addInstallShuffboardTask() =
        task<InstallShuffleboardTask>("installShuffleboard")