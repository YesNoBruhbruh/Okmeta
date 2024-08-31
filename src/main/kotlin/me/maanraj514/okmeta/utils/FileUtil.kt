package me.maanraj514.okmeta.utils

import java.io.*

object FileUtil {

    @Throws(IOException::class)
    fun copy(source: File, destination: File) {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                destination.mkdir()
            }

            val files: Array<String> = source.list() ?: return
            for (file in files) {
                val newSource = File(source, file)
                val newDestination = File(destination, file)
                copy(newSource, newDestination)
            }
        } else {
            val `in`: InputStream = FileInputStream(source)
            val out: OutputStream = FileOutputStream(destination)

            val buffer = ByteArray(1024)

            var length: Int
            while ((`in`.read(buffer).also { length = it }) > 0) {
                out.write(buffer, 0, length)
            }
            `in`.close()
            out.close()
        }
    }

    fun delete(file: File) {
        if (file.isDirectory()) {
            val files: Array<File> = file.listFiles() ?: return
            for (child in files) {
                delete(child)
            }
        }

        file.delete()
    }
}