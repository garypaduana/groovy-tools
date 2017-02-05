package com.garypaduana.groovytools.system

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Files{

    static def copy(File source, File dest){
        source.withInputStream(){ bis ->
            dest.withOutputStream(){ bos ->
                byte[] buffer = new byte[8192]
                int bytesRead = 0
                while((bytesRead = (bis.read(buffer, 0, buffer.length))) != -1){
                    bos.write(buffer, 0, bytesRead)
                }
            }
        }
    }

    static def findImagesLargerThan(File rootDir, int width, int height){
        def matchedFiles = []
        rootDir.eachFileRecurse(){ sf ->
            if(sf.getName() ==~ /.+(JPG|jpg|PNG|png)/ && sf.isFile()){
                def img = ImageIO.read(sf)
                if(img != null && img.getWidth() >= Integer.valueOf(width) &&
                        img.getHeight() >= Integer.valueOf(height)){

                    matchedFiles.add(sf.getAbsolutePath())
                }
            }
        }

        return matchedFiles
    }
}
