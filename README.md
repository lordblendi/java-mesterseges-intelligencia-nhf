# Mesterséges intelligencia nagyházi

Ezt a nagyházit a Mesterséges intelligencia tantárgyhoz készítettük [Nyíri Andrással](https://github.com/EndruBoy) a BME-s tanulmányaink során a 2013/2014/1 félévben.

## Feladat

A feladatunk egy tartalom-alapú tantárgyajánló-rendszer létrehozása. A tartalom-alapú ajánlórendszerek az egyes termékek tulajdonságai alapján egy profilt állítanak fel a termékekről, majd ezen tulajdonságok alapján meghatározzák a termékek egymáshoz viszonyított hasonlóságát. Az ajánlás folyamata során a felhasználó kiválaszt néhány terméket, illetve kiválasztja, hogy az adott tulajdonságok milyen súllyal szerepeljenek az ajánlás során, majd a rendszer a kiválasztott termékekhez hasonló tulajdonságútermékeket javasol. A programunkhoz az adatbázist a www.vik.bme.hu/kepzes/targyakhonlapról nyertük.

A mai internetes marketing nagyban épít az ajánló rendszerekre, mivel a felhasználókat úgy lehet hatékonyan egy adott holnapon huzamosabb ideig tartani, hogy ha folyamatosan találnak számukra releváns tartalmat. Ez a feladatuk ajánlórendszereknek. A felhasználóelőzetes tevékenységeit, szokásait figyelembe véve próbálnak releváns tartalmat szolgáltatni. Így aztán ez egy olyan fontos, központi funkció amely nagyban befolyásolhatja az adott honlap látogatottságát vagy egy szolgáltatás népszerűségét.

A legnagyobb és legnépszerűbb szolgáltatások, ahol ilyen ajánló rendszerek működnek, pl. a YouTube, Amazon, Facebook. Az alábbi képen pl. látható, hogy az Amazon megjegyezte, hogy megtekintettem az Effective Java c. könyvet és tőle jobbra a “Similar items avaliable...” címszó alatt ajánlott ehhez hasonló könyveket.

## Fordítás és futtatás

``` bash
cd src && javac -cp jsoup-1.7.2.jar *.java && java -cp jsoup-1.7.2.jar:. MIHFProgram
```
