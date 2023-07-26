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
        <head>
        <meta charset="UTF-8" />"""
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
                                <head>
                                <meta charset="UTF-8" />"""

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
     
    val listPageOpen:String =
      """
        |<!DOCTYPE html>
        |<html>
        |<head>
        |<meta charset="UTF-8" />
        |    <title>
        |""".stripMargin
   val listPageHeadClose:String =
     """
       |</title>
       |    <link rel = "stylesheet" href="css/list-page.css">
       |</head>
       |""".stripMargin
   val listPageBodyOpen:String =
     """
       |<body>
       |<header>
       |    <!--<header> -->
       |    <div class = "list-header">
       |""".stripMargin
  val listPageHeaderClose: String =
    """
      |</div>
      |    <!-- </header> -->
      |
      |</header>
      |""".stripMargin

     val listPageLeftBoxOpen:String =
       """
         |<div class="list-body">
         |    <div class="left-box">
         |""".stripMargin
     val listPageLeftBoxClose:String =
       """
         |</div>
         |""".stripMargin
     val listPageRightBoxOpen:String =
       """
         | <div class = "right-box">
         |    <div class="timeline">
         |""".stripMargin
      val listPageRightListElemOpen:String =
        """
          |<div class="container right">
          |          <div class="list content">
          |""".stripMargin
      val listPageRightListElemClose:String =
        """
          |   </div>
          |
          | </div>
          |""".stripMargin
      val listPageRightBoxClose:String =
        """
          |</div>
          |</div>
          |""".stripMargin
      val listPageBodyClose:String  =
        """
          |</div>
          |</body>
          |</html>
          |""".stripMargin

    /*
    *
    * CSS Files as constant Strings
    *
    * */
    val listPageCss:String =
      """
        |
        |
        |body{
        |  background-color: #eee;
        |  transition: all 0.5s ease-out;
        |  font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
        |}
        | .list-header {
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
        |.list-header a{
        |    font-size: 10px;
        |    font-weight: bold;
        |  text-align: inherit;
        |  padding: 14px 16px;
        |  text-decoration: line;
        |
        |}
        |.list-header a:hover {
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
        |.list-index{
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
        |
        |.list-title{
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
        |
        |
        |.list-title h1{
        |    font-size: 30px;
        |    color: #04011e;
        |    font-weight: bold;
        |    text-align: center;
        |    margin-left: 25%;
        |    margin-right: 25%;
        |
        |}
        |.list-body{
        |
        |   display: flex;
        |
        |
        |
        |  margin-left: 10%;
        |  margin-right: 10%;
        |  background-color: #eee;
        |  border-color: #030303;
        |  font-size: 10pt;
        |  line-height: 1.5;
        |  color: #666;
        |  font-weight: 350;
        |  /* border-left: solid;
        |  border-top: solid;
        |  border-bottom: solid;
        |  border-right:solid ; */
        |}
        |
        |
        |
        |* {
        |  box-sizing: border-box;
        |}
        |
        |
        |.left-box{
        |flex: 55%;
        |padding: 30px;
        |
        |}
        |.right-box{
        |   flex: 45%;
        |
        |}
        |
        |/* The actual timeline (the vertical ruler) */
        |.timeline {
        |  position: relative;
        |  max-width: 1200px;
        |
        |}
        |
        |/* The actual timeline (the vertical ruler) */
        |.timeline::after {
        |  content: '';
        |  position: absolute;
        |  width: 3px;
        |  background-color: #000e;
        |  top: 0;
        |  bottom: 0;
        |  left: 0%;
        |  margin-left: -9px;
        |}
        |
        |/* Container around content */
        |.container {
        | /* padding: 5px 40px;*/
        |  position: relative;
        |  background-color: #eee;
        |
        |  /*width: 90%;*/
        |}
        |
        |/* The circles on the timeline */
        |.container::after {
        |  content: '';
        |  position: absolute;
        |  width: 10px;
        |  height: 10px;
        |  right: -17px;
        |  background-color: #eee;
        |  border: 2px solid #666;
        |  top: 15px;
        |  border-radius: 50%;
        |  z-index: 1;
        |}
        |
        |/* Place the container to the left */
        |
        |
        |/* Place the container to the right */
        |.right {
        |  left: 0%;
        |}
        |
        |/* Add arrows to the left container (pointing right) */
        |/* .left::before {
        |  content: " ";
        |  height: 0;
        |  position: absolute;
        |  top: 22px;
        |  width: 0;
        |  z-index: 1;
        |  right: 30px;
        |  border: medium solid white;
        |  border-width: 10px 0 10px 10px;
        |  border-color: transparent transparent transparent white;
        |}*/
        |
        |/* Add arrows to the right container (pointing left) */
        |/*.right::before {
        |  content: " ";
        |  height: 0;
        |  position: absolute;
        |  top: 22px;
        |  width: 0;
        |  z-index: 1;
        |  left: 30px;
        |  border: #eee;
        |  border-width: 10px 10px 10px 0;
        |
        |}*/
        |
        |/* Fix the circle for containers on the right side */
        |.right::after {
        |  left: -55px;
        |  color: #0ff
        |}
        |
        |/* The actual content */
        |.content {
        |  padding: 3px;
        |
        |  border-radius: 4px;
        |}
        |
        |/* Media queries - Responsive timeline on screens less than 600px wide */
        |@media screen and (max-width: 600px) {
        |/* Place the timelime to the left */
        |  .timeline::after {
        |    left: 31px;
        |  }
        |
        |/* Full-width containers */
        |  .container {
        |    width: 100%;
        |    padding-left: 10px;
        |    padding-right: 25px;
        |  }
        |
        |/* Make sure that all arrows are pointing leftwards */
        |  .container::before {
        |    left: 20px;
        |    border: medium solid white;
        |    border-width: 1px 1px 1px 0;
        |    border-color: transparent white transparent transparent;
        |  }
        |
        |/* Make sure all circles are at the same spot */
        |  /*.right::after {
        |    left: 0px;
        |    background-color: #00e
        |  }*/
        |
        |/* Make all right containers behave like the left ones */
        |  .right {
        |    left: 0%;
        |  }
        |}
        |
        |
        |""".stripMargin
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
      |    //margin-left: 4%;
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

