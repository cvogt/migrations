import scala.slick.migrations._
object App{
  def run(mm:MyMigrationManager){
/*
    import datamodel.latest.schema.tables._
    import datamodel.latest.schema.version.Version._
    if( version != mm.latest ){
      println("!!Generated code is outdated, please run code generator") // or you could also do it automatically here
      return
    }
    println("Users in the database:")
    println(
      mm.db.withSession{
        Users.map(u=>u).list
      }
    )
    return
*/
    println("The body of App.scala is currently commented out (activate after at least version 1)")
  }
}
