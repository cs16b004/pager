package pager

import com.typesafe.scalalogging.Logger
import io.circe.Json
import laika.api._
import laika.format._
import laika.markdown.github.GitHubFlavor

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.collection.mutable
import scala.util.matching.Regex
import scala.collection.mutable._
import scala.io.Source
import scala.util.control.Breaks._

case class Page(name:String, title:String, mdFilePath:String){

    protected val transformer = Transformer
      .from(Markdown)
      .to(HTML)
      .using(GitHubFlavor)
      .build

    protected var parsedContent:String = ""
    private val log: Logger = Logger(getClass.getName)
    private val mdLinkPattern: Regex = ".*\\[(.*)\\]\\s*\\((.*\\.md)\\s*\\).*".r
    def parseTop(config:Json) :Unit = ???
    def parseContent: Unit = ???
    def htmlPath:String = {
        s"${mdFilePath.dropRight(2).mkString}html"

    }
    def saveHtml(config:Json):Unit = {



        val filePath:String = s"${htmlPath}"
                                    .replace(config.findAllByKey("blogDirectory").mkString.stripPrefix("\"").stripSuffix("\"")
                                                        ,config.findAllByKey("webDirectory").mkString.stripPrefix("\"").stripSuffix("\""))
        val file = new File(filePath)

        // Create the directory if it doesn't exist
        if (!file.getParentFile.exists())
            file.getParentFile.mkdirs()

        val writer = new PrintWriter(file)
        writer.write(parsedContent)
        writer.close()

        log.info(s"File saved at: $filePath")
    }


    def getPath: String = mdFilePath

    private def exists:Boolean = {
        Files.exists(Paths.get(mdFilePath))
    }

    def getLinkedPages: List[String] = {
        val pageListBuffer: mutable.ListBuffer[String] = new ListBuffer[String]
        if (!exists) {
            log.warn(s"File ${mdFilePath} not found")
            pageListBuffer.toList
        } else {
            for (matched_group <- mdLinkPattern.findAllMatchIn(Source.fromFile(mdFilePath).mkString)) {
                pageListBuffer += (matched_group.group(2))
            }
        }
        pageListBuffer.toList
    }


    override def toString: String = ???




}


class HomePage (name:String, title:String, mdFilePath:String) extends Page(name:String, title:String, mdFilePath:String)
{

    val log = Logger(getClass.getName)
    override def parseTop(config:Json):Unit = {

        val iconPath = config.findAllByKey("iconPath").mkString
        val name =  config.findAllByKey("name").mkString.stripPrefix("\"").stripSuffix("\"")
        val htmlContent:String =
            s"""
              |${Constants.homePageOpen}
              |${Constants.homePageTitleOpen}${title}
              |${Constants.homePageTitleClose}
              |${Constants.homePageBodyOpen}
              |${Constants.homePageHeader}
              |${Constants.homePageHeadOpen}
              |<img src=${iconPath} height="170" class="center">
              |<h2>${name}</h2>
              |${Constants.homePageHeadClose}
              |""".stripMargin

        parsedContent+=htmlContent
        log.info(s"Parsed the header content for ${toString}")
    }
    override def parseContent():Unit = {

        val pageContent: String =  Source.fromFile(mdFilePath).getLines.toArray.drop(1).mkString("\n")
        log.info(s"Page Content: ${pageContent}")
        val transformedContent =   transformer.transform(pageContent)
        transformedContent match{
            case Right(html) =>{
                var htmlContent: String =
                    s"""
                       |${Constants.homePageListOpen}
                       |${html.replaceAll("<p>","").replaceAll("</p>","")}
                       |${Constants.homePageListClose}
                       |${Constants.homePageBodyClose}
                       |""".stripMargin
                for (linkedPage <- getLinkedPages) {
                   htmlContent =  htmlContent.replace(linkedPage, s"${linkedPage.dropRight(2).mkString}html")
                }
                parsedContent+=htmlContent
                log.info(s"Parsed the body content for ${toString}")
            }
            case Left(err) => {
                log.error(s"Parsing Error in ${toString}")
                log.error(err.message)
                err.printStackTrace()
            }
        }


    }


    override def toString:String = {
        s"""
           |Name: ${name}
           |Title: ${title}
           |filePath: ${mdFilePath}
           |type: HomePage
           |""".stripMargin

    }
}

class BlogPage (name:String, title:String, mdFilePath:String) extends Page(name:String, title:String, mdFilePath:String) {

    val log = Logger(getClass.getName)
    override def parseTop(config:Json):Unit = {

                val git_username = config.findAllByKey("github").mkString.stripPrefix("\"").stripSuffix("\"")
                val linkedIn = config.findAllByKey("linkedIn").mkString.stripPrefix("\"").stripSuffix("\"")
                val e_mail = config.findAllByKey("mail").mkString.stripPrefix("\"").stripSuffix("\"")

                var htmlContent: String =
                    s"""
                       |${Constants.blogPageOpenHead}
                       |${Constants.blogPageOpenTitle}
                       |${title}
                       |${Constants.blogPageCloseTitle}
                       |${Constants.blogPageOpenBody}
                       |<header>
                       |    <div class = "blog-header">
                       |        <a href ="${linkedIn}"><img src = "icons/LI-In-Bug.png" width="30" height="30"></a>
                       |
                       |        <a href="https://www.github.com/${git_username}">
                       |            <img src = "icons/github-mark/github-mark.png" width="30" height="30">
                       |         </a>
                       |
                       |        <a href="mailto: ${e_mail}"><img src="icons/mailLogo.png" width="30" height="30"></a>
                       |</div>
                       |</header>
                       |""".stripMargin
                       parsedContent+=htmlContent
        log.info(s"Parsed the header content for ${toString}")
    }
    override def parseContent():Unit = {
        val pageContent: String = Source.fromFile(mdFilePath)
          .getLines.toArray.drop(1).mkString("\n")

        val transformedContent = transformer.transform(pageContent)
        transformedContent match {
            case Right(html) => {
                var htmlContent:String =
                    s"""
                      |<div class= "blog-title">
                      |<h1> ${title}</h1>
                      |</div>
                      |<div class= "blog-body">
                      |${html}
                      |</div>
                      |</body>
                      |</html>
                      |""".stripMargin
                for (linkedPage <- getLinkedPages) {
                    htmlContent = htmlContent.replace(linkedPage, s"${linkedPage.dropRight(2).mkString}html")
                }
                parsedContent+=htmlContent
                log.info(s"Parsed the body content for ${toString}")
            }
            case Left(err) =>{
                log.error(s"Parsing Error in ${toString}")
                log.error(err.message)
                err.printStackTrace()
            }
        }
    }


    override def toString: String = {
        s"""
           |Name: ${name}
           |Title: ${title}
           |filePath: ${mdFilePath}
           |type: BlogPage
           |""".stripMargin

    }
}
class ListPage (name:String, title:String, mdFilePath:String) extends Page(name:String, title:String, mdFilePath:String) {

    val log = Logger(getClass.getName)
    val leftSidePattern:Regex = "(\\s*)(#LEFT)(\\s*)".r
    val rightSidePattern:Regex = "(\\s*)(#RIGHT)(\\s*)".r
    override def parseTop(config:Json):Unit = {

        val git_username = config.findAllByKey("github").mkString.stripPrefix("\"").stripSuffix("\"")
        val linkedIn = config.findAllByKey("linkedIn").mkString.stripPrefix("\"").stripSuffix("\"")
        val e_mail = config.findAllByKey("mail").mkString.stripPrefix("\"").stripSuffix("\"")

        var htmlContent: String =
            s"""
               |${Constants.listPageOpen}
               |
               |${title}
               |${Constants.listPageHeadClose}
               |${Constants.listPageBodyOpen}
               |        <a href ="${linkedIn}"><img src = "icons/LI-In-Bug.png" width="30" height="30"></a>
               |
               |        <a href="https://www.github.com/${git_username}">
               |            <img src = "icons/github-mark/github-mark.png" width="30" height="30">
               |         </a>
               |
               |        <a href="mailto: ${e_mail}"><img src="icons/mailLogo.png" width="30" height="30"></a>
               |${Constants.listPageHeaderClose}
               |""".stripMargin
        parsedContent+=htmlContent
        log.info(s"Parsed the header content for ${toString}")
    }
    override def parseContent():Unit = {
        val pageContent: Array[String] = Source.fromFile(mdFilePath)
          .getLines.toArray.drop(1)
        var leftContent:String = ""
        var rightContent:String = ""
        var leftFound:Boolean = false
        var rightFound:Boolean = false
        for (line <- pageContent){
          breakable{
              if(Constants.matches(leftSidePattern,line)){
                leftFound = true
                  rightFound = false
                break
              }
              if(leftFound && !Constants.matches(rightSidePattern,line)){
                leftContent+=line
                  leftContent+="\n"
              }
              if(Constants.matches(rightSidePattern,line)){
                  rightFound  = true
                  leftFound=false
                  break
              }
              if(rightFound && !Constants.matches(leftSidePattern,line)){
                  rightContent+=line
                  rightContent+="\n"
              }
          }

        }
        var htmlContent:String =
            s"""
              |<div class="list-title">
              |    <h1>${title}</h1>
              |</div>
              |""".stripMargin
        val transformedLeftContent = transformer.transform(leftContent)
        transformedLeftContent match {
            case Right(html) => {
                htmlContent+=
                    s"""
                       |${Constants.listPageLeftBoxOpen}
                       |${html}
                       |${Constants.listPageLeftBoxClose}
                       |""".stripMargin

               // parsedContent+=htmlContent
                log.info(s"Parsed the left-body content for ${toString}")
            }
            case Left(err) =>{
                log.error(s"Parsing Error in ${toString}")
                log.error(err.message)
                err.printStackTrace()
            }
        }

        val transformedRightContent = transformer.transform(rightContent)
        transformedRightContent match {
            case Right(html) => {


                htmlContent +=
                  s"""
                     |${Constants.listPageRightBoxOpen}
                     |""".stripMargin
                var liCount = 0
                var i=0
                var j=0
                val htmlMut =  scala.collection.mutable.ArrayBuffer((html.toCharArray):_*)
                while(j+4<html.length){
                    val liCan = html.substring(i,i+4)

                    val c_liCan = html.substring(j,j+5)
                    if (liCan == "<li>"){


                        liCount+=1
                        if (liCount == 1){
                            htmlMut(i+1) = 'r'
                            htmlMut(i+2) = 'e'
                        }
                    }
                    if(c_liCan=="</li>"){

                        liCount-=1
                        if(liCount == 0){
                            htmlMut(j+2) = 'r'
                            htmlMut(j+3) = 'e'
                        }
                    }
                    i+=1
                    j+=1

                }

                var htmlMarked = htmlMut.mkString
                htmlMarked = htmlMarked.replace("<re>",s"${Constants.listPageRightListElemOpen}")
                  .replace("</re>",s"${Constants.listPageRightListElemClose}")
                htmlContent +=
                  s"""
                     |${htmlMarked}
                     |${Constants.listPageRightBoxClose}
                     |""".stripMargin



                log.info(s"Parsed the right-body content for ${toString}")
            }
            case Left(err) => {
                log.error(s"Parsing Error in ${toString}")
                log.error(err.message)
                err.printStackTrace()
            }
        }
        for (linkedPage <- getLinkedPages) {
            htmlContent = htmlContent.replace(linkedPage, s"${linkedPage.dropRight(2).mkString}html")
        }
        htmlContent+=
          s"""
            |${Constants.listPageBodyClose}
            |""".stripMargin
        parsedContent+=htmlContent
    }


    override def toString: String = {
        s"""
           |Name: ${name}
           |Title: ${title}
           |filePath: ${mdFilePath}
           |type: BlogPage
           |""".stripMargin

    }
}