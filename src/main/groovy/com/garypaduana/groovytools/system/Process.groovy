package com.garypaduana.groovytools.system

class Process {

    /**
     * Convenience method wrapper for execute(String command, boolean print)
     */
    static def execute(String command) {
        return execute(command, true)
    }

    /**
     * Convenience method wrapper for execute(List<String> command, boolean print)
     */
    static def execute(List<String> command) {
        return execute(command, true)
    }

    /**
     * A way to make system calls while capturing the error stream.
     * @param command - the command to execute
     * @param print - should the output be printed to console line by line?
     * @return String - the complete output
     */
    static def execute(String command, boolean print) {
        return execute(new ArrayList<String>(Arrays.asList(command.split(" "))), print)
    }

    /**
     * A way to make system calls while capturing the error stream with an optional customer Writer for output.
     * @param command - the command to execute
     * @param print - should the output be printed line by line?
     * @param writer
     * @return String - the complete output
     */
    static def execute(String command, boolean print, Writer writer) {
        return execute(new ArrayList<String>(Arrays.asList(command.split(" "))), print, writer)
    }

    /**
     * A way to make system calls while capturing the error stream and optionally printing to System.out.
     *
     * @param command - the command to execute
     * @param print - should the output be printed to console line by line?
     * @return String - the complete output
     */
    static def execute(List<String> command, boolean print) {
        return execute(command, print, new PrintWriter(System.out))
    }

    /**
     * A way to make system calls while capturing the error stream with an optional customer Writer for output.
     * @param command
     * @param print
     * @param writer
     * @return
     */
    static def execute(List<String> command, boolean print, Writer writer) {
        StringBuilder sb = new StringBuilder()
        if (print) {
            writer.println("Executing: ${command.join(' ')}")
        }
        def process = new ProcessBuilder(command).redirectErrorStream(true).start()
        process.inputStream.eachLine {
            if (print) {
                writer.println(it)
                writer.flush()
            }
            sb.append(it).append("\n")
        }
        process.waitFor()
        writer.close()
        return [exitValue: process.exitValue(), body: sb.toString()]
    }
}