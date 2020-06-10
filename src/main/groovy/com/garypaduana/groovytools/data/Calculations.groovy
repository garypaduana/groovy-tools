package com.garypaduana.groovytools.data

import com.garypaduana.groovytools.formatting.StringCleaner

import java.security.MessageDigest

class Calculations {

    static def calculateParity(String hex, int parityBitLength){

        hex = StringCleaner.stripNonHexCharacters(hex)

        if(parityBitLength > 64){
            throw new IllegalArgumentException("parityBitLength must be less than or equal to 64")
        }

        if((hex.length() * 4) % parityBitLength != 0) {
            throw new IllegalArgumentException("invalid word length found")
        }

        def pieces = []
        StringBuilder sb = new StringBuilder(hex)

        while(sb.length() >= (parityBitLength / 4)){
            def end = (int) (parityBitLength / 4)
            pieces.add(Long.decode("0x" + sb.substring(0, end)))
            sb.delete(0, end)
        }

        long parity = 0
        for(long piece : pieces){
            parity = parity ^ piece
        }

        return Long.toHexString(parity).toLowerCase()
    }

    /**
     * A generic method to generate a hex string representation of a message digest.
     *
     * @param file - the file to be analyzed.
     * @param digest - the digest to generate, e.g. "MD5", "SHA-1"
     * @param maxLength - the number of bytes to read from the file. if no bytes are desired,
     *                       you must supply -1 or the first buffered chunk will be read.
     * @param paddedLength - the total length of the string representation of the message digest.
     *                          e.g. MD5 = 32, SHA-1 = 40
     * @return
     */
    static String generateDigest(File file, String digest, long maxLength, int paddedLength) {
        MessageDigest md = MessageDigest.getInstance(digest)
        md.reset()
        if (file.canRead()) {
            file.withInputStream() { is ->
                byte[] buffer = new byte[8192]
                int read = 0
                int totalRead = 0
                while ((read = is.read(buffer)) > 0 && totalRead <= maxLength) {
                    totalRead += read
                    md.update(buffer, 0, read)
                }
            }
        }

        byte[] digestBytes = md.digest()
        BigInteger bigInt = new BigInteger(1, digestBytes)
        return bigInt.toString(16).padLeft(paddedLength, '0')
    }

    /**
     * Generates an MD5 signature using all bytes in the file.
     * @param file
     * @return
     */
    static String generateMD5(File file) {
        return generateDigest(file, "MD5", Long.MAX_VALUE, 32)
    }

    /**
     * Generates an MD5 signature using up to 1MB (2^20) bytes from the
     * beginning of the file.  Useful to quickly determine if two large files
     * are different without having to read every byte in each.  If there is
     * equality after this method is evaluated for each file then a full
     * processing should occur for each.
     * @param file
     * @return
     */
    static String generateShortMD5(File file) {
        return generateDigest(file, "MD5", 1048576, 32)
    }
}
