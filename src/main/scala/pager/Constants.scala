package pager
import scala.util.matching.Regex
object Constants{

  def matches(pat: Regex, text: String): Boolean = {
    text match {
      case pat(_*) => true
      case _ => false
    }

  }

    val homePageOpen:String = """
    <!DOCTYPE html>
        <html>
        <head>"""
     val homePageTitleOpen:String =  "<title>"
     val homePageTitleClose:String =  "</title>"
     val homePageBodyOpen:String = """<link rel = "stylesheet" href="css/home-page.css">
        </head>
        <body>"""

    val homePageHeader:String ="""
             <div class="home-header"></div>
            """
    val homePageHeadOpen:String = """<div class="home-title">"""

    val homePageHeadClose:String = "</div>"

    val homePageListOpen:String = """<div class="home-body">"""

    val homePageListClose:String = "</div>"
     
    val homePageBodyClose:String = """</body>
                                </html>""" 
    /* Blog Page Layout*/
    val blogPageOpenHead:String = """<!DOCTYPE html>
                                <html>
                                <head>"""

    val blogPageOpenTitle:String = """<title>"""
    val blogPageCloseTitle:String = """</title>"""
    val blogPageOpenBody:String  =  """<link rel = "stylesheet" href="css/blog-page.css">
                                </head>
                                <body>
                            """

     /**
      Make Header Using info from the config file
     */

      /**
      Make Body using Content from the home.md
      */

      /** Close the html and save*/
     

    /*
    *
    * CSS Files as constant Strings
    *
    * */

    val blogPageCss:String =
    """
      |
      |body{
      |  background-color: #eee;
      |  transition: all 0.5s ease-out;
      |  font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
      |}
      | .blog-header {
      |    justify-content: right;
      |    display: flex;
      |    flex-direction: row;
      |    font-size: 10px;
      |    color: #030303;
      |
      |    padding-top: 5%;
      |    padding-right: 1%;
      |    width: auto;
      |    overflow-x: hidden;
      |    /* border-color: blue;
      |  border-left: solid;
      |  border-top: solid;
      |  border-bottom: solid;
      |  border-right:solid ; */
      |
      |
      |}
      |
      |.blog-header a{
      |    font-size: 10px;
      |    font-weight: bold;
      |  text-align: inherit;
      |  padding: 14px 16px;
      |  text-decoration: line;
      |
      |}
      |.blog-header a:hover {
      |  color: black;
      |}
      |nav {
      |  display: flex;
      |  justify-content: space-evenly;
      |  flex-direction: column;
      |  flex-wrap: wrap;
      |  float: left;
      |  width: 20%;
      |  height: 200%; /* only for demonstration, should be removed */
      |  /* border-color: red;
      |  border-left: solid;
      |  border-top: solid;
      |  border-bottom: solid;
      |  border-right:solid ;
      |  background: #ccc;
      |  padding: 20px; */
      |}
      |.blog-index{
      | /* display: flex;
      |  justify-content: space-evenly;
      |    flex-direction: column;*/
      |  background-color: #edd;
      |  width: 20%;
      |
      |  margin-left: 0%;
      |
      |  color: #666;
      |  font-size: 15px;
      |  font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
      |
      |  /* border-color: red;
      |  border-left: solid;
      |  border-top: solid;
      |  border-bottom: solid;
      |  border-right:solid ;
      |   */
      |
      |
      |
      |}
      |nav li a{
      |  display: flex;
      |  color: #666;
      |  text-decoration: none;
      |}
      |.nav li a:hover{
      |  color: #04011e;
      |}
      |.blog-title{
      |  display: flex;
      |  flex-direction: row;
      |  justify-content: center;
      |  color: #04011e;
      |  font-weight: bold;
      |  text-align: center;
      |
      |  background-color: #eee;
      |  /* border-color: #030303;
      |  border-left: solid;
      |  border-top: solid;
      |  border-bottom: solid;
      |  border-right:solid ; */
      |}
      |.blog-title h1{
      |    font-size: 30px;
      |    color: #04011e;
      |    font-weight: bold;
      |    text-align: center;
      |    margin-left: 25%;
      |    margin-right: 25%;
      |
      |}
      |.blog-body{
      |  margin-left: 25%;
      |  margin-right: 25%;
      |  background-color: #eee;
      |  border-color: #030303;
      |  font-size: medium;
      |  color: #666;
      |  font-weight: 350;
      |  /* border-left: solid;
      |  border-top: solid;
      |  border-bottom: solid;
      |  border-right:solid ; */
      |}
      |
      |.blog-body img{
      |  margin-left: 5%;
      |  margin-right: 5%;
      |  background-color: #eee;
      |  border-color: #030303;
      |  text-rendering: optimizeLegibility;
      |  text-indent: 10em;
      |  /* border-left: solid;
      |  border-top: solid;
      |  border-bottom: solid;
      |  border-right:solid ; */
      |}
      |""".stripMargin

    val homePageCss =
      """
        |
        |body{
        |    background-color: #fff;
        |    transition: all 0.5s ease-out;
        |    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
        |  }
        |
        |  .center {
        |    display: block;
        |    margin-left: auto;
        |    margin-right: auto;
        |
        |  }
        |
        |  .home-header{
        |    margin-left: 25%;
        |    margin-right: 25%;
        |
        |    border-color: #030303;
        |    font-size: medium;
        |    color: #030303;
        |    font-weight: 250;
        |    border-left: solid;
        |    padding: 0%;
        |    border-right:solid ;
        |  }
        |
        |  .home-title{
        |    margin-left: 25%;
        |    margin-right: 25%;
        |    margin-top: 20%;
        |
        |    border-color: #633;
        |    font-size: medium;
        |    color: #030303;
        |  }
        |  .home-title h2{
        |    text-align: center;
        |  }
        |
        |  .home-body{
        |    margin-left: 30%;
        |    margin-right: 30%;
        |
        |    border-color: #633;
        |    font-size: medium;
        |    color: #030303;
        |    font-weight: 250;
        |
        |    border-left: solid #6333;
        |    padding: 0%;
        |    border-bottom: solid #6333;
        |    border-right:solid #6333 ;
        |  }
        |
        |  .home-body li a{
        |
        |    border-color: #030303;
        |    font-size: medium;
        |
        |    color: #677;
        |    font-weight: 400;
        |
        |
        |  }
        |  .home-body a:hover{
        |    margin-left: 2%;
        |    margin-right: 2%;
        |    color: #030303;
        |    font-size: medium;
        |    text-decoration: none
        |    font-weight: 400;
        |
        |
        |  }
        |
        |""".stripMargin
}

