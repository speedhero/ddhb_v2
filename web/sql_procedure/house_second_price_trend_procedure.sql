/*
 * 该存储过程是用来记录二手房当月价格历史记录的。它先会的house_rent_price_trend中查找
 * 每条租赁房源的当月价格是否已经有记录，如果有，则更新，如果没有，则插入一条新的记录
 */
DELIMITER //
CREATE PROCEDURE shp_trend()
BEGIN
  DECLARE house_id_temp VARCHAR(40) DEFAULT '';
  DECLARE price_temp FLOAT DEFAULT 0;
  DECLARE unit_price_temp FLOAT DEFAULT 0;
  DECLARE done BOOLEAN DEFAULT FALSE;
  DECLARE currentYearAndMonth DATE;
  DECLARE countInTrendTable INT DEFAULT 0;
  DECLARE cur1 CURSOR FOR SELECT shelving_id, price, unit_price FROM house_second_hand_house;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  SET currentYearAndMonth = CONCAT(YEAR(CURDATE()), '-', MONTH(CURDATE()), '-01 00:00:00');
  
  OPEN cur1;
      loop_lable:LOOP
	FETCH cur1 INTO house_id_temp, price_temp, unit_price_temp;
	SELECT COUNT(*) INTO countInTrendTable FROM house_second_price_trend WHERE house_id = house_id_temp AND dateandmonth = currentYearAndMonth;
	IF countInTrendTable = 1 THEN
	    UPDATE house_second_price_trend hpt SET hpt.price = price_temp, hpt.unit_price = unit_price_temp WHERE hpt.house_id = house_id_temp AND hpt.dateandmonth = currentYearAndMonth;
	ELSE 
	    INSERT INTO house_second_price_trend (house_id, dateandmonth, price, unit_price) VALUES (house_id_temp, currentYearAndMonth, price_temp, unit_price_temp);
	END IF;
	IF done THEN
	    LEAVE loop_lable;
	END IF;
      END LOOP;
  CLOSE cur1;
END;
//
delimiter ;
