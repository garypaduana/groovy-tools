package com.garypaduana.groovytools.formatting

import java.text.DecimalFormat

class StringCleaner {

    static def stripNonHexCharacters(String text){
        return text.replaceAll("[^0-9ABCDEFabcdef]", '')
    }

    /**
     *
     * @param charsPerWord
     * @param wordsPerLine
     * @param text
     * @return map with keys ['parityText', 'label', 'parityRowCounter', 'ascii']
     */
    static def formatParityText(int charsPerWord, int wordsPerLine, String text){
        StringBuilder sb = new StringBuilder()
        StringBuilder counter = new StringBuilder()
        counter.append("00000000 ")
        StringBuilder ascii = new StringBuilder()

        text = stripNonHexCharacters(text)

        int bits = text.length() * 4
        int bytes = bits % 8 == 0 ? (bits / 8) : (bits / 8 + 1)
        String label = "Length: ${customFormat('###,###,###', text.length())} hex chars; " +
                "${customFormat('###,###,###', bits)} bits; " +
                "${customFormat('###,###,###', bytes)} bytes"

        // We want the output to look like this:
        // 1234 5233 5923 4234 ab32 def9 acd3 92ff [...]
        for(int i = 0; i < text.length(); i += charsPerWord){
            if(i % (charsPerWord * wordsPerLine) == 0 && i > 0){
                // remove trailing space
                sb.setLength(sb.length() - 1)
                sb.append("\n")
                ascii.append("\n")
                counter.append("\n" + Integer.toString((i / 2).intValue(), 8).padLeft(8, '0') + " ")
            }

            List<String> asciiBytes = new ArrayList<String>()

            for(int j = 0; j < charsPerWord; j += 2){
                asciiBytes.add(safeSubstring(text, i + j, i + j + 2))
            }

            for(String asciiByte : asciiBytes){
                if(asciiByte.length() > 0){
                    String toAppend = String.valueOf((char) (Integer.decode("0x" + asciiByte) & 0xFF))
                    toAppend = toAppend.replaceAll(/[^\p{Print}]/, String.valueOf((char) Integer.decode("0x00")))
                    ascii.append(toAppend)
                }
            }

            int end = (i + charsPerWord > text.length()) ? (text.length()) : (i + charsPerWord)
            sb.append(text.substring(i, end))
            sb.append(" ")
        }
        sb.setLength(sb.toString().trim().length())
        return ['parityText': sb.toString(), 'label': label, 'parityRowCounter': counter.toString(), 'ascii': ascii.toString()]
    }

    /**
     * Convenience method for obtaining a substring without having the client perform any size checks.
     * @param str - the source string
     * @param start - the start position
     * @param end - the end position
     * @return the desired substring or less if the request couldn't be fully satisfied
     */
    static def safeSubstring(String str, int start, int end){
        if(start < 0){
            start = 0
        }

        if(end > str.length()){
            end = str.length()
        }

        if(end < start || start >= str.length()){
            return ""
        }

        return str.substring(start, end)
    }

    static def customFormat(String pattern, int value){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(value);
    }

    static def customFormat(String pattern, double value){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(value);
    }

    static byte[] decodeMessyHexToByteArray(String text){
        text = stripNonHexCharacters(text)

        int bitCount = text.length() * 4
        int byteCount = bitCount % 8 == 0 ? (bitCount / 8) : (bitCount / 8 + 1)

        byte[] bytes = new byte[byteCount]

        int j = 0
        for(int i = 0; i < text.length(); i += 2){
            int end = (i + 2) <= text.length() ? (i + 2) : text.length()
            bytes[j] = 0xFF & Integer.valueOf(text.substring(i, end), 16)
            j++
        }

        return bytes
    }
}
