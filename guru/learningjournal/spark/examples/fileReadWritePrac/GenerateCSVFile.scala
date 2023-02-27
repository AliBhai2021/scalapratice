package guru.learningjournal.spark.examples.fileReadWritePrac

import java.io.{BufferedWriter, File, FileWriter}
import java.time.LocalTime

object GenerateCSVFile extends App{

    val file = new File("data/filename.csv")
    val bw = new BufferedWriter(new FileWriter(file))
    for (line <- 1 to 3) {
      var stmt = s"\n ${line},ABC,DEF,HIJ,${line*10},ZZZ,${LocalTime.now()} "
      bw.write(stmt)
    }
    bw.close()

  // consider char by char
  val readBuffer1 = scala.io.Source.fromFile("data/filename.csv")
  for(line <- readBuffer1)
    println(line)

  // consider one line at a time
    val readBuffer2 = scala.io.Source.fromFile("data/filename.csv")
    for(line <- readBuffer2.getLines())
      println(line)

    val readBuffer3 = scala.io.Source.fromFile("data/filename.csv")
    for(line <- readBuffer3.getLines.toList)
      println(line)

}
