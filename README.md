# Demo_store
Starting the initial setup

At the start app loads data from pre created CSV file. Data contains current stock of the drinks in the shop.

Rules of the attending the shop:
  * Every hour shop is visited by the customers in numbers from 1 to 10.
  * Every customer can choose from 0 to 10 drinks, which are currently in the stock.
 
Console output contains information about customer activities:
  * Time period of customers attendance.
  * Name of the sold drink.
  * Price of the sold drink.
  * Which rule of extra charging was applied.

By the end of the work, day store's stock in checked, in case if resupply is needed. Rules:
  * If overall amount of a checked drink in the stock is less then 10, then 150 unit of this drink is bought to refill the stock.

After the 30 days of activity emulation the app save a report to the text file, which contains: 
  * Amount of sold drinks (for each).
  * Amount of restocked drinks (for each).
  * Clean profit of the shop (sales summ minus default price of the drink (summ of extra charge, basically)).
  * Money spent on restocking.

By the end of app's lifecycle, CSV file (database) must be rewritten (refresh shop's stock).

Rules of the trade:
  * Default extra charge is 10%
  * Weekend extra charge is 15%
  * From 18:00 to 20:00 extra charge is 8%
  * After 2 consecutive acquisitions of the goods, extra charge for the next one's is 7%
-
Working hours 08:00 - 21:00. Weekly
-
Drinks:
  * Alcohol
  * Alcohol_free

Alcohol:
  * name
  * purchase price
  * classification (вино, крепкий алкоголь, пиво, ликеры)
  * volume
  * beverage strength
  * stock

Alcohol_free:
  * name
  * purchase price
  * group (минеральные воды, соки, прочие напитки)
  * volume
  * composition
  * stock

Available stock is in the CSV file (aka data base).

Info of CSV file:

"Вода минеральная Хорошо", 9.99, "минеральные воды", 0.3, "вода минеральная, лечебно-столовая", 570

"Вода минеральная Хорошо", 15.47, "минеральные воды", 1.5, "вода минеральная, лечебно-столовая", 412

"Пиво Одесское Новое", 13.25, "пиво", 0.5, 4.3%, 120

"Красная испанка", 80.00, "вино", 0.75, 14%, 92

"Сок Богач Грейпфрутовый", 22.00, "соки", 0.95, "вода, сок грейпфрутовый концентрированный, фруктоза, лимонная кислота", 156

"Енерджи бум Плюс", 24.15, "прочие напитки", 0.33, "вода, лимонная кислота, ароматизатор Яблоко, Е-345, Е-120, Е-630, Е-632, краситель Вишня", 78

"Мартини Биссе", "ликеры", 205.00, 1.0, 13%, 12

"Два моря", 195.00, "вино", 0.75, 12%, 0
