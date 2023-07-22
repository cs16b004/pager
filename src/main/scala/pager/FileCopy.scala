package pager

import com.typesafe.scalalogging.Logger
import io.circe.Json
import os.FilePath

object FileCopy {
  val log = Logger(getClass.getName)
  def copyCss(config:Json) = {
    val webDir  = os.pwd/config.findAllByKey("webDirectory").mkString.stripPrefix("\"").stripSuffix("\"")

    if(!os.exists(webDir/"css")){
      os.makeDir (webDir/"css")
    }
    val cssDir = webDir/"css"

    os.write.over(cssDir/"home-page.css",Constants.homePageCss)
    os.write.over(cssDir/"blog-page.css",Constants.blogPageCss)
    log.info(s"Copied css files to : ${cssDir.toString()}")

  }
  def copyImages(config:Json): Unit = {
    val webDir = os.pwd / config.findAllByKey("webDirectory").mkString.stripPrefix("\"").stripSuffix("\"")

    if (!os.exists(webDir / "images")) {
      os.makeDir(webDir / "images")
    }

    val fromImageDir = os.pwd / config.findAllByKey("blogDirectory").mkString.stripPrefix("\"").stripSuffix("\"") / "images"
    val toImageDir = webDir / "images"
    val toIconDir  = webDir / "icons"
    os.copy.over(fromImageDir,toImageDir,true)
    log.info(s"Copied images from ${fromImageDir.toString} to ${toImageDir}")
    if (!os.exists(toIconDir)) {
      os.makeDir(toIconDir)
    }

    val iconPath = os.pwd / "icons"/ "images"

    os.copy.over(iconPath, toIconDir, true)
    log.info(s"Copied images from ${iconPath.toString} to ${toImageDir}")
  }
}
