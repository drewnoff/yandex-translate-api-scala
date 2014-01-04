yandex-translate-api-scala
==========================

Provides a simple, unofficial, Scala client API for using Yandex Translate.

Quickstart:
===========

```scala
scala> import zhmyh.yandex.api.translate.Translate

scala> import zhmyh.yandex.api.translate.Language

scala> val tr = new Translate("yandex-translate-api-key")

scala> tr.translate("Mais vale tarde do que nunca", Language.PORTUGUESE, Language.ENGLISH)
res0: scala.util.Try[String] = Success(Better late than never)

scala> tr.translate("Le meilleur est l'ennemi du bien", Language.ENGLISH)
res1: scala.util.Try[String] = Success(The best is the enemy of the good)

scala> tr.detect("Schijn bedriegt")
res2: scala.util.Try[zhmyh.yandex.api.translate.Language.Language] = Success(nl)

scala> tr.getLangs
res3: scala.util.Try[List[(zhmyh.yandex.api.translate.Language.Language, zhmyh.yandex.api.translate.Language.Language)]] = Success(List((az,ru), (be,bg), (be,cs), (be,de), (be,en), (be,es), (be,fr), (be,it), (be,pl), (be,ro), (be,ru), (be,sr), (be,tr), (bg,be), (bg,ru), (bg,uk), (ca,en), (ca,ru), (cs,be), (cs,en), (cs,ru), (cs,uk), (da,en), (da,ru), (de,be), (de,en), (de,es), (de,fr), (de,it), (de,ru), (de,tr), (de,uk), (el,en), (el,ru), (en,be), (en,ca), (en,cs), (en,da), (en,de), (en,el), (en,es), (en,et), (en,fi), (en,fr), (en,hu), (en,it), (en,lt), (en,lv), (en,mk), (en,nl), (en,no), (en,pt), (en,ru), (en,sk), (en,sl), (en,sq), (en,sv), (en,tr), (en,uk), (es,be), (es,de), (es,en), (es,ru), (es,uk), (et,en), (et,ru), (fi,en), (fi,ru), (fr,be), (fr,de), (fr,en), (fr,ru), (fr,uk), (hr,...

scala> val tr2  =  new Translate("wrong-translate-api-key")

scala> tr2.translate("Вывезет и авоська, да не знать куда", Language.ENGLISH)
res4: scala.util.Try[String] = Failure(zhmyh.yandex.api.YandexAPIException: 401: API key is invalid)
```