package zhmyh.yandex.api.translate

import scala.util.Try
import zhmyh.yandex.api.translate.Language.Language

/**
 * @author zhmyh
 */
abstract class TranslateBase {

  /**
   * Translates text from a given Language to another given Language using Yandex Translate.
   * @param text the String to translate.
   * @param from The language code to translate from.
   * @param to The language code to translate to.
   * @returns The translated String.
   * @returns The instance of Try[The translated String] on success.
   * @returns The instance of Failure[YandexAPIException] on service specific error.
   * @returns The instance of Failure[Exception] on error.
   */
  def translate(text: String, from: Language, to: Language): Try[String]

  /**
   * Recognizes source text language and translates to given Language using Yandex Translate.
   * @param text the String to translate.
   * @param to The language code to translate to.
   * @returns The instance of Try[The translated String] on success.
   * @returns The instance of Failure[YandexAPIException] on service specific error.
   * @returns The instance of Failure[Exception] on error.
   */
  def translate(text: String, to: Language): Try[String]

  /**
   * Gets a list of available translation directions using Yandex Translate.
   * @returns The instance of Success[list of translation directions] on success.
   * @returns The instance of Failure[YandexAPIException] on service specific error.
   * @returns The instance of Failure[Exception] on error.
   */
  def getLangs: Try[List[(Language, Language)]]

  /**
   * Detects The language from given text.
   * @returns The instance of Success[detected Language] on success.
   * @returns The instance of Failure[YandexAPIException] on service specific error.
   * @returns The instance of Failure[Exception] on error.
   */
  def detect(text: String): Try[Language]
}
