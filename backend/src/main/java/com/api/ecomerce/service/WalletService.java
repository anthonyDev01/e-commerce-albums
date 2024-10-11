package com.api.ecomerce.service;

import com.api.ecomerce.dto.request.WalletAddBalanceRequestDto;
import com.api.ecomerce.infra.exception.WalletNotFoundException;
import com.api.ecomerce.model.Album;
import com.api.ecomerce.model.Transaction;
import com.api.ecomerce.model.User;
import com.api.ecomerce.model.Wallet;
import com.api.ecomerce.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final AuthService authService;

    public Wallet createWallet(User user){
        log.info("Attempting to create a wallet");
        Wallet newWallet = Wallet.builder()
                .balance(new BigDecimal(0))
                .points(0)
                .lastUpdate(LocalDateTime.now())
                .user(user)
                .build();
        log.info("Wallet created successfully");
        return this.walletRepository.save(newWallet);
    }

    public Wallet findWalletById(UUID id) throws WalletNotFoundException {
        log.info("Fetching wallet with id: " + id);
        return this.walletRepository.findById(id).orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
    }



    public Wallet addBalance (WalletAddBalanceRequestDto request){
        User user = authService.getAuthenticatedUser();

        Wallet wallet = user.getWallet();
        log.info("Attempting to add credit to the wallet with id: " + wallet.getId());

        BigDecimal newBalance = wallet.getBalance().add(request.getBalance());

        wallet.setBalance(newBalance);
        wallet.setLastUpdate(LocalDateTime.now());
        log.info("Attempting to add credit to the wallet with id: " + wallet.getId());
        return walletRepository.save(wallet);
    }

    public Wallet deductCreditThePurchase(User user, BigDecimal value) throws WalletNotFoundException {
        Wallet wallet = this.findWalletById(user.getWallet().getId());

        BigDecimal newBalance = user.getWallet().getBalance().subtract(value);

        wallet.setBalance(newBalance);
        wallet.setLastUpdate(LocalDateTime.now());

        return walletRepository.save(wallet);
    }

    public Wallet updatePoints(Transaction transaction) throws WalletNotFoundException {
        Wallet wallet = findWalletById(transaction.getUser().getWallet().getId());
        Integer updatedPoints = wallet.getPoints() + transaction.getPointsEarned();
        wallet.setPoints(updatedPoints);
        return walletRepository.save(wallet);
    }

}
