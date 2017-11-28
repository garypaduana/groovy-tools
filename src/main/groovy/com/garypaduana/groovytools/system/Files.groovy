package com.garypaduana.groovytools.system

import javax.imageio.ImageIO
import java.security.MessageDigest

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

    static def generateDigest(File file, String digest, long maxLength, int paddedLength){
        MessageDigest md = MessageDigest.getInstance(digest)
        md.reset()
        def files = []
        def directories = []

        if(file.isDirectory()){
            file.eachFileRecurse(){sf ->
                if(sf.isFile()){
                    files.add(sf)
                }
                else{
                    directories.add(file.toURI().relativize(sf.toURI()).toString())
                }
            }
        }
        else if(file.isFile()){
            files.add(file)
        }

        files.sort({a, b -> return a.getAbsolutePath() <=> b.getAbsolutePath()})
        directories.sort()

        files.each(){f ->
            println file.toURI().relativize(f.toURI()).toString()
            f.withInputStream(){is ->
                byte[] buffer = new byte[8192]
                int read = 0
                long totalRead = 0
                while((read = is.read(buffer)) > 0 && totalRead <= maxLength){
                    md.update(buffer, 0, read)
                    totalRead += read
                }
            }
        }

        directories.each(){d ->
            println d
            md.update(d.getBytes())
        }

        byte[] digestBytes = md.digest()
        BigInteger bigInt = new BigInteger(1, digestBytes)
        return bigInt.toString(16).padLeft(paddedLength, '0')
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
