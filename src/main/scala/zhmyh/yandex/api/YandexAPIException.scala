/**
 *
 */
package zhmyh.yandex.api

/**
 * @author zhmyh
 */
case class YandexAPIException(code: Int, message: String) extends Exception(code + ": " + message)
