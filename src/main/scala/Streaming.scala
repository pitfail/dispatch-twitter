package dispatch.twitter

import dispatch._
import dispatch.oauth._
import Request._
import OAuth._
import net.liftweb.json.JsonAST.JValue
import dispatch.liftjson.Js._

import scala.io.Source
import java.io.{InputStreamReader,BufferedReader}

sealed abstract class Follow {
  implicit def toMap : Map[String, String]
}
case object FollowAll    extends Follow {
  implicit def toMap = Map("follow" -> "always")
}
case object FollowMutual extends Follow {
  implicit def toMap = Map.empty
}

object UserStream {
  val host = :/("userstream.twitter.com").secure
  val svc = host / "2" / "user.json"
  def open(cons: Consumer, tok: Token, since_id: Option[String], follow: Follow )
          (listener: JValue => Unit) = {
    val req = svc <<? (Map.empty ++ since_id.map { id => "since_id" -> id } ++ follow.toMap)
    req <@ (cons, tok) ^# listener
  }
}
