import scala.io.BufferedSource
import scala.io.Source.fromResource

object Main extends App{

    //Implementing the loan pattern:
    type Resource = { def close(): Unit}

    def using[A <: Resource, B] (resource: A) (consumer: A => B ): B = {
        try {
            consumer(resource)
        } finally {
            resource.close()
        }
    }


    def showSentences(source: BufferedSource): Unit =
        for (line <- source.getLines) {
            val sentences = line.split('.')
            sentences.foreach(println)
        }

    def getSentences(source: BufferedSource):List[String] = source.getLines.toList

    def getString(source: BufferedSource):String = source.getLines.mkString

    val res = "funes.txt"

    using(fromResource(res)){getSentences(_).foreach(println)}

}
