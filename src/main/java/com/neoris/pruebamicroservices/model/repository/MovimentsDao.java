package com.neoris.pruebamicroservices.model.repository;

import com.neoris.pruebamicroservices.utils.Database;
import dto.MovimentBillingdto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.SQLData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class MovimentsDao {

    @Qualifier("JdbcMaster")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    Database database;


    public List<MovimentBillingdto> findAllMovimentsByNameAndDate(String name, String date) {
        List<MovimentBillingdto> resp = new ArrayList<>(0);
        try {
            resp = jdbcTemplate.query(database.getQueryMovimentsByDateAndName(),BeanPropertyRowMapper.newInstance(MovimentBillingdto.class),name , date);
            resp = resp.size() <= 0 ? null : resp;
        } catch (Exception e) {
            log.error(MovimentsDao.class.getName() + " - findAllMovimentsByNameAndDate" + e.getMessage());
        } finally {
            try {
                DataSourceUtils.getConnection(jdbcTemplate.getDataSource()).close();
            }catch (SQLException s){
               log.error(MovimentsDao.class.getName() + " - Error! close conection" + s.getMessage() + s.getLocalizedMessage());
            }

        }
     return resp;
    }

    public List<MovimentBillingdto> findAllMoviments(){
        List<MovimentBillingdto> result = new ArrayList<>(0);
        try {
          result = jdbcTemplate.query(database.getQueryAllMoviments(), BeanPropertyRowMapper.newInstance(MovimentBillingdto.class));
          if(result.isEmpty() || result == null){
              result = null;
              log.info("No found data!  --> getQueryAllMoviments()");
          }
        }catch (Exception e){
            log.error(MovimentsDao.class.getName() + " - findAllMoviments" + e.getMessage());
        }finally {
            try {
                DataSourceUtils.getConnection(jdbcTemplate.getDataSource()).close();
            }catch (SQLException s){
               log.error(MovimentsDao.class.getName() + " - Error! close conection" + s.getMessage() + s.getLocalizedMessage());
            }

        }
        return result;
    }

}
