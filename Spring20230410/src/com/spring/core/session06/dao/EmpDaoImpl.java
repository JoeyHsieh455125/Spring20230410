 package com.spring.core.session06.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.core.session06.entity.Emp;

@Repository
public class EmpDaoImpl implements EmpDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 建立 CRUD - Create, Read, Update, Delete
	// 查詢多筆
	@Override
	public List<Map<String, Object>> query(){
		String sql = "select eid, ename, age, createtime from emp";
		List<Map<String, Object>> emps = jdbcTemplate.queryForList(sql);
		return emps;
	}

	@Override
	public List<Emp> queryEmps() {
		String sql = "select eid, ename, age, createtime from emp";
		// 利用 BeanPropertyRowMapper 將資料逐筆注入到指定物件中
		List<Emp> emps = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
		return emps;
	}

	@Override
	public Optional<Emp> getOne(Integer eid) {
		String sql = "select eid, ename, age, createtime from emp where eid=?";
		Object[] args = {eid};
		Emp emp = null;
		try {
			emp = jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<>(Emp.class));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Optional<Emp> optEmp = Optional.ofNullable(emp);
		return optEmp;
	}

	@Override
	public Integer count() {
		String sql = "select count(*) from emp"; // 一般都寫星號
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
	
	@Override
	public Double getAvgOfAge() {
		String sql = "select AVG(age) from emp";
		Double avg = jdbcTemplate.queryForObject(sql, Double.class);
		return avg;
	}

	@Override
	public int create(String ename, Integer age) {
		String sql = "Insert into emp(ename, age) values(?, ?)";
		int rowcount = jdbcTemplate.update(sql, ename, age);
		return rowcount;
	}

	@Override
	public int[] batchCreate(List<Object[]> list) {
		String sql = "Insert into emp(ename, age) values(?, ?)";
		int[] rowcounts = jdbcTemplate.batchUpdate(sql, list);
		return rowcounts;
	}

	@Override
	public int update(Integer eid, String ename, Integer age) {
		String sql = "Update emp set ename=?, age=? where eid=?";
		int rowcount = jdbcTemplate.update(sql, ename, age, eid);
		return rowcount;
	}

	@Override
	public int delete(Integer eid) {
		String sql = "Delete from emp where eid=?";
		int rowcount = jdbcTemplate.update(sql, eid);
		return rowcount;
	} 
}
