package pager

import com.typesafe.scalalogging.Logger
import io.circe.Json
import os.Path
import pager.Constants.matches

import java.util
import scala.collection.mutable
import scala.util.matching.Regex
import scala.collection.mutable._
import scala.io.Source



case class Parser(config:Json){

  private val childMap: mutable.HashMap[String,mutable.ListBuffer[String]] = new mutable.HashMap[String,mutable.ListBuffer[String]]()
  private val log = Logger(getClass.getName)
  private val homeTopLinePattern:Regex = "(\\s*\\{\\s*\\{\\s*PageType\\s*:\\s*HomePage\\s*\\}\\s*\\}\\s*)(.*)".r
  private val blogPagePattern:Regex = "(\\s*\\{\\s*\\{\\s*PageType\\s*:\\s*BlogPage\\s*\\}\\s*\\}\\s*)(.*)".r
  private val listPagePattern:Regex = "(\\s*\\{\\s*\\{\\s*PageType\\s*:\\s*ListPage\\s*\\}\\s*\\}\\s*)(.*)".r
  private val pageQueue: mutable.Queue[Page] = new mutable.Queue[Page]()


  private val pageList: mutable.ListBuffer[Page] = new mutable.ListBuffer[Page]

  private def makePage(pagePath:String):Either[String, Page] = {

    val pageContent: Array[String] =  Source.fromFile(s"${os.pwd.toString}/"+pagePath).getLines.toArray
    //Verify homePage Type
    if (pageContent.length == 0) {
      Left (s"Page: ${pagePath} is empty exiting")
    }else{
        if (matches (homeTopLinePattern, pageContent (0) ) ) {
          val name = homeTopLinePattern.findFirstMatchIn (pageContent (0) ).get.group (2)
          Right(new HomePage (name, s"""${config.findAllByKey ("name").mkString}'s Blog Page""", s"${os.pwd.toString}/"+pagePath))
        }else if(matches(blogPagePattern,pageContent(0))){
          val name = blogPagePattern.findFirstMatchIn (pageContent (0) ).get.group (2)
          Right(new BlogPage(name, name, s"${os.pwd.toString}/"+pagePath))
        }else if(matches (listPagePattern,pageContent(0))){
          val name = listPagePattern.findFirstMatchIn(pageContent(0)).get.group(2)
          Right(new ListPage(name, name, s"${os.pwd.toString}/" + pagePath))
        }
        else{
          Left(s"No Match found for the page: ${pagePath}")
        }
    }
  }
  def parse:Int = {

    val homePagePath = config.findAllByKey("homePagePath").mkString.stripPrefix("\"").stripSuffix("\"")
    val homeDir =config.findAllByKey("blogDirectory").mkString.stripPrefix("\"").stripSuffix("\"")


    val homePageContent:Array[String] = Source.fromFile(s"${os.pwd.toString}/"+homePagePath).getLines.toArray//os.read(Path(os.pwd/homePagePath)).split("\n")
    //Verify homePage Type
    log.info(homePageContent.mkString("\n"))
    if(homePageContent.length == 0) {
        log.error("Home Page is empty exiting")
        return -1
    }

    if(matches(homeTopLinePattern,homePageContent(0))){
          val name = homeTopLinePattern.findFirstMatchIn(homePageContent(0)).get.group(2)
          val homePage = new HomePage(name,
                                        s"""${config.findAllByKey("name").mkString}'s Blog Page""",
                                        os.pwd.toString() + "/" + homePagePath)
          pageQueue.enqueue(homePage)
          pageList+=(homePage)
          while(!pageQueue.isEmpty) {

            val tempPage = pageQueue.dequeue

            if(!childMap.contains(tempPage.getPath)) {
              //Create List for this element so its children could be added
              childMap(tempPage.getPath) = new ListBuffer[String]
              for (pagePath <- tempPage.getLinkedPages) {
                //add the child (linked ) Page
                childMap(tempPage.getPath)+=pagePath
                val newPage = makePage(s"$homeDir/$pagePath") match {
                  case Left(s) => {
                    log.error(s)
                    return -1
                  }
                  case Right(p) => p
                }
                pageQueue.enqueue(newPage)
                pageList += newPage
              }
            }
          }
          println(childMap)
          pageList.toList
          pageList.map(_.parseTop(config))
          pageList.map(_.parseContent)
          pageList.map(_.saveHtml(config))
      0

      // We have the childMap, list of Pages now we call the parse on each page.

    }else{
      log.error("PageType not defined for home page")
      -1
    }

  }
}