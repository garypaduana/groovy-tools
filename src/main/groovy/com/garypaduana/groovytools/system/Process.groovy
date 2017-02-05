package com.garypaduana.groovytools.system

class Process{

    /**
     * Convenience method wrapper for execute(String command, boolean print)
     */
    static def execute(String command){
        return execute(command, true)
    }

    /**
     * Convenience method wrapper for execute(List<String> command, boolean print)
     */
    static def execute(List<String> command){
        return execute(command, true)
    }

    /**
     * A way to make system calls while capturing the error stream.
     * @param command - the command to execute
     * @param print - should the output be printed to console line by line?
     * @return String - the complete output
     */
    static def execute(String command, boolean print){
        return execute(new ArrayList<String>(Arrays.asList(command.split(" "))), print)
    }

    /**
     * A way to make system calls while capturing the error stream.
     * @param command - the command to execute
     * @param print - should the output be printed to console line by line?
     * @return String - the complete output
     */
    static def execute(List<String> command, boolean print){
        StringBuilder sb = new StringBuilder()
        println "Executing: ${command.join(' ')}"
        def process = new ProcessBuilder(command).redirectErrorStream(true).start()
        process.inputStream.eachLine{
            if(print){
                println it
            }
            sb.append(it).append("\n")
        }
        process.waitFor()
        return [exitValue: process.exitValue(), body: sb.toString()]
    }
}