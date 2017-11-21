
import kotlinx.cinterop.*
import platform.posix.*

fun main(args:Array<String>) {
  scan@while(true) {
    val line = readLine()
    if ( line == null ) break@scan
    print(line)
  }

  val stacks = mutableListOf<String>()
  args.map { 
    println(it)
    val file = fopen(it, "r")
    val bufferLength = 64 * 1024
    memScoped {
      val buffer = allocArray<ByteVar>(bufferLength)
      read@while(true) {
        val nextLine = fgets(buffer, bufferLength, file)?.toKString()
        if (nextLine == null ) break@read
        stacks.add( nextLine!!)
      }
    }
  }
  stacks.map {
    print(it)
  }
}
