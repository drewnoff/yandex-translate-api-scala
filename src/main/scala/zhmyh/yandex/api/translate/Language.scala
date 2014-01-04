package zhmyh.yandex.api.translate

/**
 * Defines language information for the Yandex Translate API.
 * 
 * @author zhmyh
 */
object Language extends Enumeration {

  /**
   * Type alias.
   */
  type Language = Value

  val ALBANIAN = Value("sq")
  val ARABIC = Value("ar")
  val ARMENIAN = Value("hy")
  val AZERBAIJANI = Value("az")
  val BELARUSIAN = Value("be")
  val BULGARIAN = Value("bg")
  val CATALAN = Value("ca")
  val CROATIAN = Value("hr")
  val CZECH = Value("cs")
  val DANISH = Value("da")
  val DUTCH = Value("nl")
  val ENGLISH = Value("en")
  val ESTONIAN = Value("et")
  val FINNISH = Value("fi")
  val FRENCH = Value("fr")
  val GEORGIAN = Value("ka")
  val GERMAN = Value("de")
  val GREEK = Value("el")
  val HEBREW = Value("he")
  val HUNGARIAN = Value("hu")
  val ITALIAN = Value("it")
  val LATVIAN = Value("lv")
  val LITHUANIAN = Value("lt")
  val MACEDONIAN = Value("mk")
  val NORWEGIAN = Value("no")
  val POLISH = Value("pl")
  val PORTUGUESE = Value("pt")
  val ROMANIAN = Value("ro")
  val RUSSIAN = Value("ru")
  val SERBIAN = Value("sr")
  val SLOVAK = Value("sk")
  val SLOVENIAN = Value("sl")
  val SPANISH = Value("es")
  val SWEDISH = Value("sv")
  val TURKISH = Value("tr")
  val UKRANIAN = Value("uk")

  val langSet = this.values map (_.toString) toSet
}
