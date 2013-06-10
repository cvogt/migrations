package scala.slick.migrations

import scala.slick.driver._
import com.typesafe.config._
import scala.util.control.Exception._
import JdbcDriver._
import language.existentials

case class DB(driver: String, url: String, user: String, password: String) {
  def slickdriver: JdbcDriver = driver match {
    case "org.apache.derby.jdbc.EmbeddedDriver" => DerbyDriver
    case "org.h2.Driver" => H2Driver
    case "org.hsqldb.jdbcDriver" => HsqldbDriver
    case "com.mysql.jdbc.Driver" => MySQLDriver
    case "org.postgresql.Driver" => PostgresDriver
    case "org.sqlite.JDBC" => SQLiteDriver
    case "com.microsoft.sqlserver.jdbc.SQLServerDriver" => SQLServerDriver
    case _ => H2Driver
  }
  def slickdriverimport = slickdriver.getClass().getName() + ".simple._"
  def db = slickdriver.simple.Database.forURL(url, driver = driver, user = user, password = password)
  def session = slickdriver.simple.Database.threadLocalSession
}
object DB {
  val mydb = DB()
  val conf = ConfigFactory.load
  
  def confOpt(key: String) = catching(classOf[ConfigException]).opt(conf.getString(key))
  def dbConfig(item: String) = confOpt("datasource") match {
    case Some(ds) => s"db.$ds.$item"
    case _ => s"db.default.$item"
  }
  def dbOpt(item: String) = confOpt(dbConfig(item))

  def apply(): DB = {
    (dbOpt("driver"), dbOpt("url"), dbOpt("user"), dbOpt("password")) match {
      case (Some(driver), Some(url), Some(user), Some(password)) => DB(driver, url, user, password)
      case (Some(driver), Some(url), _, _) => DB(driver, url, "sa", "")
      case _ => DB("org.h2.Driver", "jdbc:h2:" + System.getProperty("user.dir") + "/test.tb", "sa", "")
    }
  }

  def database = mydb.db
  def importline = mydb.slickdriverimport
  val  driver = mydb.slickdriver
  implicit def session = mydb.session
  
}