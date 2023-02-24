package com.pts.Controller.site;

import com.pts.DAO.PaymentRepository;
import com.pts.DAO.TransactionRepository;
import com.pts.entity.Payment;
import com.pts.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/return")
public class IPNController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    @GetMapping
    public ResponseEntity<Transaction> showRespond(
            @RequestParam String vnp_Amount,
            @RequestParam String vnp_BankCode,
            @RequestParam String vnp_BankTranNo,
            @RequestParam String vnp_CardType,
            @RequestParam String vnp_OrderInfo,
            @RequestParam String vnp_PayDate,
            @RequestParam String vnp_ResponseCode,
            @RequestParam String vnp_TmnCode,
            @RequestParam String vnp_TransactionNo,
            @RequestParam String vnp_TransactionStatus,
            @RequestParam String vnp_TxnRef,
            @RequestParam String vnp_SecureHash){

        Transaction transactionEntity=new Transaction();
        transactionEntity.setTransactionNo(vnp_TransactionNo);
        transactionEntity.setTxnRef(vnp_TxnRef);
        transactionEntity.setAmount(vnp_Amount);
        transactionEntity.setTransactionStatus(vnp_TransactionStatus);
        transactionEntity.setBankTranNo(vnp_BankTranNo);
//        transactionRepository.save(transactionEntity);

        if(vnp_TransactionStatus.equals("00")){
            Payment paymentEntity =  paymentRepository.getByTxnref(vnp_TxnRef);
            paymentEntity.setStatus("YES");
            paymentRepository.save(paymentEntity);
        }

        System.out.println("THANH TOAN THANH CONG");

        return ResponseEntity.ok(transactionEntity);
    }
}
