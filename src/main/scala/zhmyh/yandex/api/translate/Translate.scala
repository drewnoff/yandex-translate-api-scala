package zhmyh.yandex.api.translate

import scala.util.{Try, Success, Failure}
import play.api.libs.json.Json

import zhmyh.yandex.api.YandexAPI
import zhmyh.yandex.api.translate.Language.Language

/**
 * Makes the Yandex Translate API available to Scala applications.
 * 
 * @author
 */
class Translate(protected val key: String) extends TranslateBase with YandexAPI {

  /**
   * {@inheritDoc}
   */
  protected val apiURI = "https://translate.yandex.net/api"

  /**
   * {@inheritDoc}
   */
  protected val apiVer = "1.5"

  /**
   * {@inheritDoc}
   */
  protected val jsIfaceName = "tr.json"

  /**
   * {@inheritDoc}
   */
  def getLangs: Try[List[(Language, Language)]] =  for {
    js <- getJSON(jsonURI + "/getLangs", ("key",  key))
    langs <- Try {
      (js \ "dirs").asOpt[List[String]] match {
	case Some(lst) => lst
	case None => List[String]()
      }
    }
  } yield langs map (x => x.split('-') match {
    case Array(l1, l2) => (l1, l2)
  }) withFilter (ll =>
    Language.langSet.contains(ll._1) & Language.langSet.contains(ll._2)
	       ) map (
    ll => (Language.withName(ll._1), Language.withName(ll._2)))

  /**
   * {@inheritDoc}
   */
  def translate(text: String, from: Language, to: Language) = for {
    js <- retrieveJSON(jsonURI + "/translate",
		       List(("key", key),
			    ("lang", from.toString + "-" + to.toString),
			    ("text", text)))
    text <- Try {
      (js \ "text").asOpt[List[String]] match {
	case Some(List(res)) => res
	case None => ""
      }
    }
  } yield text

  /**
   * {@inheritDoc}
   */
  def translate(text: String, to: Language) = for {
    js <- retrieveJSON(jsonURI + "/translate",
		       List(("key", key),
			    ("lang", to.toString),
			    ("text", text)))
    text <- Try {
      (js \ "text").asOpt[List[String]] match {
	case Some(List(res)) => res
	case None => ""
      }
    }
  } yield text

  /**
   * {@inheritDoc}
   */
  def detect(text: String): Try[Language] = for {
    js <- retrieveJSON(jsonURI + "/detect", ("key", key), ("text", text))
    lang <- Try {
      (js \ "lang").asOpt[String] match {
	case Some(l) => l
	case None => "_"
      }
    }
  } yield Language.withName(lang)
}
