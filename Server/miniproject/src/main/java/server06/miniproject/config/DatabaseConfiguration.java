package server06.miniproject.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Spring Framework에 Bean 내용들을 토대로 초기값을 설정
 * 
 * @Bean 들은 싱글턴 패턴으로 spring framework에 등록된다.
 * 
 *       Hikari CP를 Sql세션에 등록하기 위한 설정 내용
 * 
 *
 */

@Configuration
public class DatabaseConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * hikari cp config 명시 conf prop -> application.yml
	 */
	@Bean
	@ConfigurationProperties("spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	   /**
     * mybatis에 hikari cp 등록
     */
    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new HikariDataSource( hikariConfig() );
        return dataSource;
    }
    
    /**
     * @param dataSource
     * @return
     * @throws Exception
     * SqlFactoryBean에 dataSource와 mapper 경로 등록
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
        
        sqlFactoryBean.setDataSource(dataSource);
        sqlFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/**.xml"));
        return sqlFactoryBean.getObject();
    }
    
    /**
     * @param sqlSessionFactory
     * @return
     * SqlSessionFactory를 SqlSessionTemplate에 등록
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

    
   
