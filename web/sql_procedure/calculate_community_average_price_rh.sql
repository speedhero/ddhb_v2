/*
 * 计算小区二手房当月均价的存储过程，该存储过程先将二手房中所用到的小区的所有均价分别计算出来，
 * 然后在house_community_average_price表中查找每个小区当月是否已经有均价了，
 * 如果该小区已经有当月均价，那么更新当月均价为最新的值，如果没有，则插入一条新的记录。
 * 与此同时，还会往小区的二手房当月均价中更新一条记录，用来表示当月均价。
 * */
DELIMITER //
CREATE PROCEDURE calculate_community_rh()
BEGIN
    DECLARE community_id_temp VARCHAR(40) DEFAULT '';
    DECLARE avg_rent_price FLOAT DEFAULT 0;
    DECLARE current_month_row_count INT DEFAULT 0;
    DECLARE currentYearAndMonth DATE;
    DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE cur2 CURSOR FOR SELECT hrh.community_id, AVG(hrh.rent_price) FROM house_rent_house hrh WHERE hrh.deleteflag = 0 GROUP BY hrh.community_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    SET currentYearAndMonth = CONCAT(YEAR(CURDATE()), '-', MONTH(CURDATE()), '-01 00:00:00');
    
    OPEN cur2;
    
    loop_label:LOOP
        FETCH cur2 INTO community_id_temp, avg_rent_price;
        SELECT COUNT(*) INTO current_month_row_count FROM house_community_average_price WHERE community_id = community_id_temp AND calculateDate = currentYearAndMonth;
        IF current_month_row_count = 1 THEN
            UPDATE house_community_average_price SET rent_average_price = avg_rent_price WHERE community_id = community_id_temp AND calculateDate = currentYearAndMonth;
        ELSE
            INSERT INTO house_community_average_price (community_id, calculateDate, rent_average_price) VALUES (community_id_temp, currentYearAndMonth, avg_rent_price);
        END IF;
        
        UPDATE house_community SET current_month_rh_avg_price = avg_rent_price WHERE erp_id = community_id_temp;
        
        IF done THEN
            LEAVE loop_label;
        END IF;
    END LOOP;
    CLOSE cur2;
END;
//
delimiter ;