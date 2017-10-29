# Demo_store
Starting the initial setup
●     В начале работы программа загружает существующий CSV файл для получения информации об ассортименте и текущем наличии товаров.

●     Каждый час в магазин приходит от 1 до 10 покупателей, которые приобретают от 0 до 10 единиц случайных товаров. Цена продажи товаров формируется в соответствии с описанными выше правилами наценки стоимости. Информация о каждой продаже выводится в консоль в процессе работы программы (что продано, цена продажи каждой единицы, какие правила наценки применены).

●     По окончании рабочего дня производится дозакупка недостающего товара.

●     После эмуляции 30 дней программа сохраняет в текстовый файл отчет, который содержит информацию:
○     Количество проданного товара для каждой позиции;
○     Количество дозакупленного товара для каждой позиции;
○     Прибыль магазина от продаж (сумма продаж минус себестоимость проданного товара);
○     Затраченные средства на дозакупку товара.

●     По окончании работы программы CSV файл перезаписывается (обновляется информация о наличии товара на конец работы программы).

Goods:
-Alcohol
-Alcohol_free

Alcohol:
-название - name
-закупочная цена - purchase price
-классификация  - classification (вино, крепкий алкоголь, пиво, ликеры)
-объем - volume
-крепкость напитка - beverage strength
-наличие шт. - stock

Alcohol_free:
-название - name
-закупочная цена - purchase price
-группа - group (минеральные воды, соки, прочие напитки)
-объем - volume
-состав - composition
-наличие шт. - stock

Available stock is in the CSV file (aka data base)

Rules of the trade:
- Стандартная наценка составляет 10% от закупочной цены - Standard extra charge is 10%
- В выходные дни наценка составляет 15% от закупочной цены - Weekend extra charge is 15%
- В период с 18:00 до 20:00 наценка составляет 8% от закупочной цены (выходные и будние дни) - From 18:00 to 20:00 extra charge is 8%
- При продаже от двух единиц товара за раз наценка на следующие единицы товара снижается до 7% от закупочной цены - After 2 consecutive acquisitions of the goods, extra charge for the next one's is 7%

Working hours 08:00 - 21:00. Weekly

В конце каждого дня производится дозакупка товара в размере 150 шт. для каждой позиции, наличие которой составляет меньше 10 шт.

Необходимо написать программу, которая будет эмулировать работу данного магазина и после эмуляции работы одного месяца (30 дней) подготовит отчет о прибыли:

"Вода минеральная Хорошо", 9.99, "минеральные воды", 0.3, "вода минеральная, лечебно-столовая", 570
"Вода минеральная Хорошо", 15.47, "минеральные воды", 1.5, "вода минеральная, лечебно-столовая", 412
"Пиво Одесское Новое", 13.25, "пиво", 0.5, 4.3%, 120
"Красная испанка", 80.00, "вино", 0.75, 14%, 92
"Сок Богач Грейпфрутовый", 22.00, "соки", 0.95, "вода, сок грейпфрутовый концентрированный, фруктоза, лимонная кислота", 156
"Енерджи бум Плюс", 24.15, "прочие напитки", 0.33, "вода, лимонная кислота, ароматизатор Яблоко, Е-345, Е-120, Е-630, Е-632, краситель Вишня", 78
"Мартини Биссе", "ликеры", 205.00, 1.0, 13%, 12
"Два моря", 195.00, "вино", 0.75, 12%, 0