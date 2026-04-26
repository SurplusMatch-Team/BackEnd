package tr.edu.agu.cs.surplus_match.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.agu.cs.surplus_match.model.*;
import tr.edu.agu.cs.surplus_match.repository.*;

import java.time.LocalDateTime;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ClaimService(ClaimRepository claimRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.claimRepository = claimRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional // Hem claim eklenmeli hem ürün güncellenmeli (ya ikisi ya hiçbiri!)
    public Claim createClaim(Long claimantId, Long productId) {
        // 1. Ürünü ve Kullanıcıyı (NGO) bulalım
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));
        
        User claimant = userRepository.findById(claimantId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        // 2. Caner'in istediği düzeltme: Ürün artık AVAILABLE olmamalı
        product.setStatus(ProductStatus.PENDING); 
        productRepository.save(product);

        // 3. Yeni Claim (Talep) nesnesini oluşturalım
        Claim claim = new Claim();
        claim.setProduct(product);
        claim.setClaimant(claimant);
        claim.setClaimDate(LocalDateTime.now());
        claim.setStatus(ClaimStatus.PENDING); // Talebin kendi durumu

        return claimRepository.save(claim);
    }
}