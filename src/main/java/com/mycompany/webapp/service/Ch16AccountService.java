package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.mycompany.webapp.dao.Ch16AccountDao;
import com.mycompany.webapp.dto.Ch16Account;
import com.mycompany.webapp.exception.Ch16NotEnoughBalanceException;
import com.mycompany.webapp.exception.Ch16NotFoundAccountException;

@Service
public class Ch16AccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch16AccountService.class);
	
	public enum TransferResult{
		SUCCESS,
		FAIL_NOT_FOUND_ACCOUNT,
		FAIL_NOT_ENOUGH_BALANCE,
	}
	
	
	@Resource
	private TransactionTemplate transactionTemplate;
	
	@Resource
	private Ch16AccountDao accountDao; 
	
	public List<Ch16Account> getAccount(){
		logger.info("실행");
		List<Ch16Account> accounts = accountDao.selectAll();
		return accounts;
	}
	
	// 프로그래밍 방식
	public TransferResult transfer1(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		String result = transactionTemplate.execute(new TransactionCallback<String>() {

			@Override
			public String doInTransaction(TransactionStatus status) {
				//출금하기
				try {
					Ch16Account fromAccount = accountDao.selectByAno(fromAno);
					
					if(fromAccount.getBalance()<amount) {
						 throw new Ch16NotFoundAccountException("notEnough");
					}
					
					fromAccount.setBalance(fromAccount.getBalance()-amount);
					accountDao.updateBalance(fromAccount);
					
	
					Ch16Account toAccount = accountDao.selectByAno(toAno);
					toAccount.setBalance(toAccount.getBalance()+amount);
					accountDao.updateBalance(toAccount);
					return "success";
				}catch (Exception e) {
					// 트랜잭션 작업을 모두 취소
					status.setRollbackOnly();
					//logger.info("예외 발생 ----------------");
					return e.getMessage();
				}
			}
		});
		
		if(result ==null) {
			return TransferResult.FAIL_NOT_FOUND_ACCOUNT;
		}else if(result=="successs"){
			return TransferResult.SUCCESS;
		}else {
			return TransferResult.FAIL_NOT_ENOUGH_BALANCE;
		}
		
	
	}
	
	
	
	// 선언적 방식
	@Transactional
	public void transfer2(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		try {
			Ch16Account fromAccount = accountDao.selectByAno(fromAno);
			fromAccount.setBalance(fromAccount.getBalance()-amount);
			accountDao.updateBalance(fromAccount);
			
			
			if(fromAccount.getBalance()<amount) {
				 throw new Ch16NotEnoughBalanceException("notEnough");
			}

			Ch16Account toAccount = accountDao.selectByAno(toAno);
			toAccount.setBalance(toAccount.getBalance()+amount);
			accountDao.updateBalance(toAccount);
			
		}catch (Exception e) {
			throw new Ch16NotFoundAccountException("계좌가 존재하지 않습니다.");
		}
	}
}
