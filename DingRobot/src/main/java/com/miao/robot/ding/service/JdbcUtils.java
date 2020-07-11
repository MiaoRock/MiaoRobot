package com.miao.robot.ding.service;

import com.miao.robot.ding.entity.MiaoParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JdbcUtils {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, String> queryParamsMap(String sql) {
        List<MiaoParams> miaoParamsList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(MiaoParams.class));
        Map<String, String> queryRsString = new HashMap<>();
        for (MiaoParams miaoParams : miaoParamsList) {
            queryRsString.put(miaoParams.getParamCode(), miaoParams.getParamValue());
        }
        return queryRsString;
    }

    public Map<String, Object> queryForMap(String sql) {
        return jdbcTemplate.queryForMap(sql);
    }

    public List<Map<String, Object>> queryForList(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    public SqlRowSet queryForRowSet(String sql) {
        return jdbcTemplate.queryForRowSet(sql);
    }

}
