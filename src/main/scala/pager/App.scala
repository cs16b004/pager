
package pager

import com.typesafe.scalalogging._
import io.circe.{Json, ParsingFailure, yaml}
import laika.api._
import laika.format._
import laika.markdown.github.GitHubFlavor
import java.io.{InputStreamReader, Reader}
import scala.io.Source


object App {


  private val log = Logger(getClass.getName)
  def main(args: Array[String]): Unit = {
    log.info(s"Starting Pager: PWD ${os.pwd} ")

    val config = getClass.getClassLoader.getResource("config.yaml")
    log.info(s"""Config loaded from: ${config}""")
    val configStream = getClass.getClassLoader.getResourceAsStream("config.yaml")
    val configContent = scala.io.Source.fromInputStream(configStream).mkString

    //log.info(configContent)
    val jsonPro:Either[ParsingFailure,Json] = yaml.parser.parse(configContent)
    val emp:Json = null
    if(jsonPro.isRight){
      val conf:Json = jsonPro.swap.left.getOrElse(emp)

      Parser(conf).parse
      FileCopy.copyCss(conf)
      FileCopy.copyImages(conf)
      log.info("Config: "+conf.toString())

    }




  }

    
}
