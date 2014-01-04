package zhmyh.yandex.api

import scala.util.{Try, Success, Failure}
import scalaj.http.{Http, HttpException}
import play.api.libs.json.{Json, JsValue}

/**
 * Makes generic Yandex API functionality available to specific API classes.
 *
 * @author zhmyh
*/
trait YandexAPI {

  /**
   * The API key and API version.
   */
  protected val key, apiVer: String

  /**
   * The API URI for specific Yandex service.
   */
  protected val apiURI: String

  /**
   * The local name of JSON interface.
   */
  protected val jsIfaceName: String

  /**
   * Returns URI for API JSON interface.
   */
  protected def jsonURI: String = apiURI + "/v" + apiVer + "/" + jsIfaceName

  /**
   * Sends HTTP request using either GET or POST method.
   * @param request Scalaj Http request.
   * @returns Response as instance of the Try[String].
   */
  private def sendRequest(request: Http.Request): Try[String] = {
    Try(request.asString) match {
      case Success(response) => Success(response)
      case Failure(HttpException(rcode, rmessage, body, cause)) => {
	val code = Try((Json.parse(body) \ "code").asOpt[Int] match {
	  case Some(x) => x
	  case None => Failure(HttpException(rcode, rmessage, body, cause))
	})
	val message = Try((Json.parse(body) \ "message").asOpt[String] match {
	  case Some(x) => x
	  case None => Failure(HttpException(rcode, rmessage, body, cause))
	})
	code flatMap (x => message.map(y => (x, y))) match {
	  case Success((x: Int, y: String)) => Failure(YandexAPIException(x, y))
	  case Failure(ex) => Failure(HttpException(rcode, rmessage, body, cause))
	  case Success(_) => Failure(HttpException(rcode, rmessage, body, cause))
	}
      }
      case Failure(e) => Failure(e)
    }
  }


  /**
   * Forms HTTP request, sends it using GET method and returns the result of the request as JsValue.
   * 
   * @param url The URL to query for a JsValue.
   * @param params Additional GET query parameters.
   * @return The instance of Try[JsValue]
   */
  protected def getJSON(url: String, params: List[(String, String)]): Try[JsValue] = {
    val res = sendRequest(Http(url).params(params))
    return res flatMap (r => Try(Json.parse(r)))
  }

  /**
   * Forms HTTP request, sends it using GET method and returns the result of the request as JsValue.
   * 
   * @param url The URL to query for a JsValue.
   * @return The instance of Try[JsValue]
   */
  protected def getJSON(url: String): Try[JsValue] = {
    return getJSON(url, List())
  }

  /**
   * Forms HTTP request, sends it using GET method and returns the result of the request as JsValue.
   * 
   * @param url The URL to query for a JsValue.
   * @param params Additional GET query parameters.
   * @return The instance of Try[JsValue]
   */
  protected def getJSON(url: String, params: (String, String)*): Try[JsValue] = {
    return getJSON(url, params.toList)
  }

  /**
   * Forms HTTP request, sends it using GET method and returns the result of the request as JsValue.
   * 
   * @param url The URL to query for a JsValue.
   * @param params Additional GET query parameters.
   * @return The instance of Try[JsValue]
   */
  protected def getJSON(url: String, params: Map[String, String]): Try[JsValue] = {
    return getJSON(url, params.toList)
  }

  /**
   * Forms HTTP request, sends it using POST method and returns the result of the request as JsValue.
   * 
   * @param url The URL to query for a JsValue.
   * @param params Additional POST parameters.
   * @return The instance of Try[JsValue]
   */
  protected def retrieveJSON(url: String, params: List[(String, String)]): Try[JsValue] = {
    val res = sendRequest(Http.post(url).params(params))
    return res flatMap (r => Try(Json.parse(r)))
  }

  /**
   * Forms HTTP request, sends it using POST method and returns the result of the request as JsValue.
   * 
   * @param url The URL to query for a JsValue.
   * @param params Additional POST parameters.
   * @return The instance of Try[JsValue]
   */
  protected def retrieveJSON(url: String, params: (String, String)*): Try[JsValue] = {
    return retrieveJSON(url, params.toList)
  }

  /**
   * Forms HTTP request, sends it using POST method and returns the result of the request as JsValue.
   * 
   * @param url The URL to query for a JsValue.
   * @param params Additional POST parameters.
   * @return The instance of Try[JsValue]
   */
  protected def retrieveJSON(url: String, params: Map[String, String]): Try[JsValue] = {
    return retrieveJSON(url, params.toList)
  }
}
