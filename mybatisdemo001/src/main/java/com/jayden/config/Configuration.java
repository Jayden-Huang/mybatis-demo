package com.jayden.config;

import javax.sql.DataSource;

/**
 * datasourse 配置文件
 */

public class Configuration {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
