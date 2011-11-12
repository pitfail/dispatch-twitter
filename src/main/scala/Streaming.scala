package dispatch.twitter

import dispatch._
import dispatch.oauth._
import Request._
import OAuth._
import net.liftweb.json.JsonAST.JValue
import dispatch.liftjson.Js._

import scala.io.Source
import java.io.{InputStreamReader,BufferedReader}

sealed abstract class Replies {
  implicit def toMap : Map[String, String]
}
case object RepliesAll    extends Replies {
  implicit def toMap = Map("replies" -> "always")
}
case object RepliesMutual extends Replies {
  implicit def toMap = Map.empty
}

object UserStream {
  val host = :/("userstream.twitter.com").secure
  val svc = host / "2" / "user.json"
  def open(cons: Consumer, tok: Token, since_id: Option[String], replies: Replies, track_items: Iterable[String])
          (listener: JValue => Unit) = {
          val req = svc <<? (Map.empty ++ since_id.map { id => "since_id" -> id } ++
                             replies.toMap ++
                             (if (track_items nonEmpty) { Map("track" -> track_items.mkString(",")) } else Map.empty ))
    req <@ (cons, tok) ^# listener
  }
}
