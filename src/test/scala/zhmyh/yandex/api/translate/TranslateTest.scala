package zhmyh.yandex.api.translate

import org.scalatest.{FunSuite, TryValues}

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import zhmyh.yandex.api.translate.{Language => Lg}

/*
 * @author zhmyh
 */
@RunWith(classOf[JUnitRunner])
class TranslateTest extends FunSuite {
  val filepath = sys.props("user.home") + "/.yandex-api-keys/translate-api.key"
  private val apiKey = io.Source.fromFile(filepath).mkString.trim

  new TryValues {
    test("invalid api key") {
      val tr = new Translate("tr.invalidKey")
      val try0 = tr.translate("Hello, world", Lg.ENGLISH, Lg.RUSSIAN)
      assert(try0.failure.exception.getMessage == "401: API key is invalid")
    }
  }

  new TryValues {
    test("translate 'Hello, world'") {
      val tr = new Translate(apiKey)
      assert(tr.translate("Привет, мир",
			  Lg.RUSSIAN,
			  Lg.ENGLISH).success.value == "Hello, world", "ru-en")
      assert(tr.translate("مرحبا العالم",
			  Lg.ARABIC,
			  Lg.ENGLISH).success.value == "Hello world", "ar-en")
      assert(tr.translate("Bonjour tout le monde",
			  Lg.FRENCH,
			  Lg.ENGLISH).success.value == "Hello everyone", "fr-en")
      assert(tr.translate("Hello, world",
			  Lg.ENGLISH,
			  Lg.FRENCH).success.value == "Bonjour tout le monde", "en-ft")
      assert(tr.translate("Olá, mundo",
			  Lg.PORTUGUESE,
			  Lg.ENGLISH).success.value == "Hello, world", "pt-en")

      assert(tr.translate("Bonjour tout le monde",
			  Lg.ENGLISH).success.value == "Hello everyone", "auto fr-en")
    }
  }

  new TryValues {
    test("Detect language") {
      val tr = new Translate(apiKey)
      assert(tr.detect("Язик до Києва доведе").success.value == Lg.UKRANIAN, "uk")
      assert(tr.detect("le mieux est l'ennemi du bien").success.value == Lg.FRENCH, "fr")
    }
  }

}
